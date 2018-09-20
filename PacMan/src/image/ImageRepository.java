package image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entities.Direction;
import javafx.scene.image.Image;
import utils.FileFinder;


public class ImageRepository {	
	private static FileFinder finder = new FileFinder("*.png");
	private static String resourceDirectoryPath = "ressource/sprites/";
	private static Map<String, ArrayList<String>> imagesMap = new HashMap<String, ArrayList<String>>();
	
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
			break;
		default:
			break;
		}	
		String key = nameFile.toString();
		
		if (imagesMap.containsKey(key)) {
			return imagesMap.get(key);
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
       
        imagesMap.put(key, result);
        finder.clearMatches();
        
		return result;
	}
}


