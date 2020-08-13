package khoaphd.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author KhoaPHD
 */
public class ScaledImage {
    
    public static Image getScaledImage(String filepath, int width, int height)
            throws IOException {
        BufferedImage bImg = ImageIO.read(new File(filepath));
        Image img = bImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return img;
    }
}
