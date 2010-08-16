package uk.mafu.loon.util;

import static org.junit.Assert.assertTrue;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

public class ImageUtilTest {

	@Test
	public void testCalculateResize() {
		// h 129,w 102  -->  h 100,w 79
		int[] resize = ImageUtil.calculateResize(new float[] {129,102 }, 100);
		assertTrue("new ", resize[0] == 100);
		assertTrue("new ", resize[1] == 79);
	}




	@Test
	public void testCreateResizedCopy() {
        try {
            InputStream s  = this.getClass().getClassLoader().getResourceAsStream("sample.jpg"); 
            byte [] input  = IOUtils.toByteArray(s);
            byte [] output = ImageUtil.resizeImageToJpeg(input, 400, 400);
            File f = File.createTempFile("something",".jpg");
            FileOutputStream fos = new FileOutputStream(f);

            fos.write(output);
            fos.flush();
            fos.close();
//
//            ByteArrayOutputStream bao = new ByteArrayOutputStream();
//            bao.writeTo(fos);
//            bao.write(output,0,output.length);
//            bao.flush();
//            bao.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

	@Test
	public void testGetStatistics() {

	}
} 