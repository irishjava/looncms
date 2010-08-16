package uk.mafu.loon;

import org.junit.Before;
import org.junit.Test;
import uk.mafu.loon.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;


public class ThumbnailViewControllerTest {
    @Before
    public void init(){
        System.out.println("init");
    }

    @Test
    public void blah() throws IOException {
        System.out.println("blah");

        InputStream is =  this.getClass().getClassLoader().getResourceAsStream("sample.jpg");
        
        BufferedImage bi = ImageIO.read(is);
		bi = ImageUtil.createResizedCopy(bi, 80, 60);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

        File f = File.createTempFile("sample-openjdk",".jpeg");

        System.out.println(f.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(f);

        ImageIO.write(bi,"jpeg",fos);
        
        fos.close();

        Iterator<ImageWriter> formats = ImageIO.getImageWritersByFormatName("jpeg");
        while(formats.hasNext()){
            ImageWriter  iw =  formats.next();
            System.out.println(iw.getClass());

        }



//ImageOutputStream ios = ImageIO.createImageOutputStream(output);
//Iterator<ImageWriter> formats = ImageIO.getImageWritersByFormatName("jpeg");
//ImageWriter imageWriter = formats.next();
//imageWriter.
//imageWriter.setOutput(ios);
//imageWriter.write(bi);


    }
}