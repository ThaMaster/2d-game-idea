package se.gmail.game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadImage(String path) {

        try {
            InputStream inputStream = ImageLoader.class.getResourceAsStream(path);
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                System.err.println("Image not found: " + path);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
