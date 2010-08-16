package uk.mafu.loon.mvc;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.services.DataService;
import uk.mafu.loon.util.ImageUtil;

import java.io.File;

import static javax.imageio.ImageIO.read;

public class ThumbnailViewController extends AbstractController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ThumbnailViewController.class);

    private DataService dataService;
    public static final int BUF_SIZE = 4096;

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

  @Override
        protected ModelAndView handleRequestInternal(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
                Integer id = extractId(request);
                LoonImage image = getDataService().getImageByImageLinkPk(id);
                ServletOutputStream out = response.getOutputStream();

                //Create a BufferedImage from the DB image data.
                BufferedImage bi = read(new ByteArrayInputStream(image
                                .getData()));
                //Calculate the optimum size to which we shall resize the image
                int[] nhw = ImageUtil.calculateResize(new float[] { bi.getHeight(),
                                bi.getWidth() }, 100);

                //Perform the image resize operation... more complex than you might imagine
                bi = ImageUtil.createResizedCopy(bi, nhw[1], nhw[0]);
                byte[] output = ImageUtil.generateBufferedOutputData(bi);

                //Configure response parameters.
                response.setBufferSize(BUF_SIZE);
                response.setContentLength(output.length);
                response.setContentType("image/jpeg");

                //send the image to the client,flush and close connection.
                out.write(output);
                out.flush();
                out.close();
                return null;
        }



    protected Integer extractId(HttpServletRequest request) {
        try {
            return new Integer(request.getParameter("pk"));
        } catch (NumberFormatException e) {
            throw new UnsupportedOperationException("missing pk parameter");
        }
    }
}