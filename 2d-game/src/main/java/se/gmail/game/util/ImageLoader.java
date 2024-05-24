package se.gmail.game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {

    private static HashMap<String, BufferedImage> loadedImages = new HashMap<>();
    private static HashMap<String, String> loadedImagePaths = new HashMap<>();


    /**
     * Function that returns a loaded images path. 
     * @param imageName The name of the image.
     * @return The path to the specified image.
     */
    public static String getImagePath(String imageName) {
        if(!loadedImagePaths.containsKey(imageName)) {
            return "IMAGE_NOT_FOUND";
        }

        return loadedImagePaths.get(imageName);
    }

    public static BufferedImage loadImage(String path) {
        if (loadedImages.containsKey(path)) {
            return loadedImages.get(path);
        }
        try {
            InputStream inputStream = ImageLoader.class.getResourceAsStream(path);
            if (inputStream != null) {
                BufferedImage bi = ImageIO.read(inputStream);

                loadedImages.put(path, bi);
                int lastSeparatorIndex = path.lastIndexOf('/');
                loadedImagePaths.put(path.substring(lastSeparatorIndex + 1), path);
                return bi;
            } else {
                System.err.println("[ImageLoader] Error: Image not found: " + path);
                return null;
            }
        } catch (IOException e) {
            System.err.println("[ImageLoader] Error: Failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<BufferedImage> loadWholeDirectory(String dirPath) {
        try {
            ArrayList<BufferedImage> images = new ArrayList<>();
            ArrayList<String> imagePaths = new ArrayList<>();
            
            // Ensure the path starts with a slash to denote it's relative to the classpath root
            if (!dirPath.startsWith("/")) {
                dirPath = "/" + dirPath;
            }
            
            URL resourceUrl = ImageLoader.class.getResource(dirPath);
            if (resourceUrl == null) {
                System.err.println("[ImageLoader] Error: Resource not found with path: " + dirPath);
                return null;
            }        
            
            Path path;
            
            try {
                path = Paths.get(resourceUrl.toURI());
            } catch (FileSystemNotFoundException | IllegalArgumentException e) {
                // Handling resources inside a JAR
                FileSystem fileSystem = FileSystems.newFileSystem(resourceUrl.toURI(), Collections.emptyMap());
                path = fileSystem.getPath(dirPath);
            }

            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for (Path p : directoryStream) {
                if (Files.isRegularFile(p)) {
                    imagePaths.add(p.toString());
                }
            }

            directoryStream.close();

            Collections.sort(imagePaths, new SpriteComparator());

            InputStream inputStream;
            for(String p : imagePaths) {
                if(loadedImages.containsKey(p)) {
                    images.add(loadedImages.get(p));
                    int lastSeparatorIndex = p.lastIndexOf('/');
                    loadedImagePaths.put(p.substring(lastSeparatorIndex + 1), p);
                } else {
                    inputStream = ImageLoader.class.getResourceAsStream(p.toString());
                    BufferedImage bi = ImageIO.read(inputStream);
                    loadedImages.put(p, bi);
                    images.add(bi);
                }
            }

            return images;
        } catch (URISyntaxException e) {
            System.err.println("[ImageLoader] Error: URI Syntax Exception when trying to load whole directory of images with path: " + dirPath);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("[ImageLoader] Error: IO Exception when trying to load whole directory of images with path: " + dirPath);
            e.printStackTrace();
            return null;
        }
    }
}
