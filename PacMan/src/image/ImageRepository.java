package image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import entities.Direction;
import javafx.scene.image.Image;
import utils.FileFinder;


public class ImageRepository {	
	private static FileFinder finder = new FileFinder("*.png");
	private static String resourceDirectoryPath = "ressource/sprites/";
	
	public static ArrayList<String> getImages(String entityName, Direction direction) {
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder nameFile = new StringBuilder();
		
		nameFile.append(entityName);
		
		//Récupération de la deuxième partie du nom
		switch(direction)
		{
		case DOWN:
			nameFile.append("-d");
			break;
		case LEFT:
			nameFile.append("-l");
			break;
		case RIGHT:
			nameFile.append("-r");
			break;
		case UP:
			nameFile.append("-u");
			break;
		case NONE:
			//nameFile.append("-");
			break;
		default:
			break;
		}		
		
		nameFile.append("*.png");
		finder.setPattern(nameFile.toString());
		
		// pass the initial directory and the finder to the file tree walker
        try {
			Files.walkFileTree(Paths.get(resourceDirectoryPath), finder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // get the matched paths
        Collection<Path> matchedFiles = finder.getMatchedPaths();
		
        // print the matched paths
        for (Path path : matchedFiles) {
        	result.add(resourceDirectoryPath + path.getFileName().toString());
        }
       
        finder.clearMatches();
        
		return result;
	}
}


