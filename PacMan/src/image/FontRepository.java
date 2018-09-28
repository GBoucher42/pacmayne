package image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import utils.FileFinder;

public class FontRepository {

	private static String[] fontSprites = new String[26];
	private static String[] numberSprites = new String[10];
	private static FileFinder finder = new FileFinder("*.png");
	private static String resourceDirectoryPath = "ressource/sprites/";
	private char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'z'};
	private Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	private Map<Integer, String> map = new HashMap<>();
	private Map<Integer, String> map2 = new HashMap<>();
	
	public FontRepository() {
		letterToSprite();
		numberToSprite();
	}
	
	public String getFont(char letter) throws Exception {
		int index = (int) letter;
		if(!map.containsKey(index)) {
			throw new Exception("Try to access unknown letter " + letter);
		}
		return map.get(index);
	}
	
	public String getFont(int number) throws Exception {
		int index = number;
		if(!map2.containsKey(index)) {
			throw new Exception("Try to access unknown number " + number);
		}
		return map2.get(index);
	}
	
	private void letterToSprite() {
		for(int i=0; i<letters.length; ++i) {
			StringBuilder nameFile = new StringBuilder();
			nameFile.append("letter-");
			nameFile.append(letters[i]);
			nameFile.append(".png");
			finder.setPattern(nameFile.toString());
			try {
				Files.walkFileTree(Paths.get(resourceDirectoryPath), finder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// get the matched paths
	        Collection<Path> matchedFiles = finder.getMatchedPaths();
			
	        // print the matched paths
	        for (Path path : matchedFiles) {
	        	if(path != null) {
	        		fontSprites[i] = resourceDirectoryPath + path.getFileName().toString();
		        	map.put((int) letters[i], fontSprites[i]);
	        	}
	        }
	        finder.clearMatches();
		}
	}
	
	private void numberToSprite() {
		for(int i=0; i<numbers.length; ++i) {
			StringBuilder nameFile = new StringBuilder();
			nameFile.append("number-");
			nameFile.append(numbers[i].toString());
			nameFile.append("*.png");
			finder.setPattern(nameFile.toString());
			try {
				Files.walkFileTree(Paths.get(resourceDirectoryPath), finder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// get the matched paths
	        Collection<Path> matchedFiles = finder.getMatchedPaths();
			
	        // print the matched paths
	        for (Path path : matchedFiles) {
	        	numberSprites[i] = resourceDirectoryPath + path.getFileName().toString();
	        	map2.put(numbers[i], numberSprites[i]);
	        }
	        finder.clearMatches();
		}
	}

}
