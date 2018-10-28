package configs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Score;
import javafx.collections.transformation.SortedList;

public class HighScoreReposity {
	private FileWriter highScoreWriter = null;
	private FileReader highScoreFile = null;
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	private boolean isHighScore;

	public List<Score> getHighScores(String path) {
		List<Score> scores = new ArrayList<>();
		try {
			highScoreFile = new FileReader(path);
			bufferReader = new BufferedReader(highScoreFile);
			while(true) {
				String line = bufferReader.readLine();
				if(line == null) {
					break;
				}
				scores.add(createLineScore(line));
			}
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier: " + e.toString());
		} finally {
			try {
				bufferReader.close();
				highScoreFile.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			System.out.println("Lecture du fichier terminée.");
			return scores;
		}
	}



	public Score createLineScore(String line) {
		String[] score = new String[2];
		score = line.split(" ");
		int myScore = Integer.valueOf(score[0]);
		String myName = score[1];
		return new Score(myScore, myName);
	}

	public void writeHighScore(String path, List<Score> scores) {
		try {
			highScoreWriter = new FileWriter(path);
			bufferWriter = new BufferedWriter(highScoreWriter);
			
			for(Score score: scores) {
				String valueWrite = score.getScore() + " " + score.getName() + "\n";
				bufferWriter.write(valueWrite);
				System.out.println("Ecriture de : " + score.getScore() + " " + score.getName());
			}
			
		} catch (IOException e) {
			System.err.println("Impossible d'écrire sur le fichier: " + e.toString());
		} finally {
			try {
				bufferWriter.flush();
				bufferWriter.close();
				highScoreWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			System.out.println("Ecriture du fichier terminée.");
		}
	}



	public void replaceHighScore(Score score) {
		String fileHighScore = "";
		List<Score> scores = new ArrayList<>();
		scores = getHighScores("ressource/text/highScoreRead.txt");
		for(Score existingScore: scores) {
			int index = scores.indexOf(existingScore);
			if(score.getScore() > existingScore.getScore()) {
				scores.set(index, score);
				score = existingScore;
			} else {
				scores.set(index, existingScore);
			}
		}
		writeHighScore("ressource/text/highScoreRead.txt", scores);
	}


}
