package ch.zhaw.gratisbrockibackend.utils;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

@Component
public class ImageToByteArray {

    public byte[] convert (String filePath) throws Exception {
        BufferedImage bImage = ImageIO.read(new File(filePath));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        return bos.toByteArray();
    }
}
