package uk.mafu.loon.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import org.apache.log4j.Logger;

import uk.mafu.loon.common.ContentType;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {
    public static class ImageStatistics implements Serializable {
        private static final long serialVersionUID = 1L;
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    /**
     * Logger for this class
     */
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ImageUtil.class);

    public static int[] calculateResize(float[] hw, int maxh) {
        if (hw[0] <= maxh) {
            return new int[]{(int) hw[0], (int) hw[1]};
        }
        float hdiff = hw[0] - 100;
        float ratio = (hw[0] - hdiff) / hw[0];
        int h = (int) (hw[0] * ratio);
        int w = (int) (hw[1] * ratio);
        return new int[]{h, w};
    }

    /**
     * @param original
     * @param scaledWidth
     * @param scaledHeight
     * @return
     */
    public static BufferedImage createResizedCopy(BufferedImage original,
                                                  int scaledWidth, int scaledHeight) {
        ResampleOp resampleOp = new ResampleOp(scaledWidth, scaledHeight);
        resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
        resampleOp.setFilter(ResampleFilters.getBiCubicHighFreqResponse());
        BufferedImage rescaled = resampleOp.filter(original, null);
        return rescaled;
    }

    /*
      * public static BufferedImage createResizedCopy(BufferedImage
      * originalImage, int scaledWidth, int scaledHeight) { StopWatch s = new
      * StopWatch(); s.start(); if (logger.isDebugEnabled()) {
      * logger.debug("About to scale the image." + new Date(s.getStartTime())); }
      * BufferedImage bi = new BufferedImage(scaledWidth, scaledHeight,
      * BufferedImage.TYPE_INT_RGB); Graphics2D g = bi.createGraphics();
      * g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
      * Integer height = new Double(g.getFont().getMaxCharBounds(
      * g.getFontRenderContext()).getBounds().getHeight()).intValue(); Integer
      * width = new Double(g.getFont().getMaxCharBounds(
      * g.getFontRenderContext()).getBounds().getWidth()).intValue(); if
      * (logger.isDebugEnabled()) { logger.debug("height=" + height);
      * logger.debug("width=" + width); } g.setColor(Color.RED); // Perhaps this
      * is naughty, I can't decide, when logging is set to debug // it embeds the
      * id into the image, no big deal and helpfull for // debugging.
      *
      * g.dispose(); s.stop(); if (logger.isDebugEnabled()) {
      * logger.debug("Finished scaling the image." + new Date(s.getTime()) +
      * " took:" + s.getTime()); } return bi; }
      */

    public static ImageStatistics getStatistics(byte[] data, String mimetype) {
        ContentType type = ContentType.getInstanceByContentType(mimetype);
        switch (type) {
            case JPEG:
                return compressTheImage(data);
            case GIF:
                return compressTheImage(data);
            case PNG:
                return compressTheImage(data);
            default:
                throw new UnsupportedOperationException(
                        "Don't know how to handle format" + type);
        }
    }

    private static ImageStatistics compressTheImage(byte[] data) {
        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
            ImageStatistics imageStatistics = new ImageUtil.ImageStatistics();
            imageStatistics.setHeight(bi.getHeight());
            imageStatistics.setWidth(bi.getWidth());
            return imageStatistics;
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     *
     * @param data
     * @param width
     * @param height
     * @return
     */
    public static byte[] resizeImageToJpeg(byte[] data, int width, int height) {
        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(data));
            int [] resize =
                    calculateResize(new float[]{width,height},height);
            bi = ImageUtil.createResizedCopy(bi, resize[0], resize[1]);
            byte[] output = generateBufferedOutputData(bi);
            return output;
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static byte[] generateBufferedOutputData(BufferedImage bi) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        //Then, choose the first image writer available (unless you want to choose a specific writer)
        // and create an ImageWriter instance:
        ImageWriter writer = (ImageWriter) iter.next();
        // instantiate an ImageWriteParam object with default compression options
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        // 1 specifies minimum compression and maximum quality
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        //Now, we can set the compression quality:
        iwp.setCompressionType("JPEG");
        iwp.setCompressionQuality(1);   // an integer between 0 and 1

        ImageOutputStream ios = new MemoryCacheImageOutputStream(output);
        
        writer.setOutput(ios);
        IIOImage image = new IIOImage(bi, null, null);
        try {
            writer.write(null, image, iwp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.dispose();
        byte[] bytes = output.toByteArray();
        return bytes;
    }
}