package com.minsk.pendulum.util;


import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ImageUtil {

    public static String modifyJsonWithImageKeys(String instructions, String keys, List<String> imgKeys) {
        return instructions;
    }

    public static String checkSum(byte[] file){
        return DigestUtils.md5DigestAsHex(file);
    }

    public static byte[] convertImgToByteArray(BufferedImage image) {
        try {
            return toByteArrayAutoClosable(image, "png");
        } catch (IOException e) {
            return null;
        }
    }


    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }
}