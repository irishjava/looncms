package com.callaway.web;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/xml/*")
public class CatchAllController {

    /**
     * Process batch job <i>crossSellList</i>
     *
     * @param file
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @RequestMapping("/xml/service")
    public void xmlService(InputStream i, OutputStream o) throws IOException,
            SAXException, ParserConfigurationException {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document document = builder.parse(i);
        String rootTagName = document.getDocumentElement().getTagName();
        if ("AirShoppingRequest".equals(rootTagName)) {
            InputStream inputStream = new ClassPathResource(
                    "AirShoppingResponse.xml").getInputStream();
            IOUtils.copy(inputStream, o);
        } else if ("CarShoppingRequest".equals(rootTagName)) {
            InputStream inputStream = new ClassPathResource(
                    "CarShoppingResponse.xml").getInputStream();
            IOUtils.copy(inputStream, o);
        } else if ("HotelShoppingRequest".equals(rootTagName)) {
            InputStream inputStream = new ClassPathResource(
                    "HotelShoppingResponse.xml").getInputStream();
            IOUtils.copy(inputStream, o);
        } else {
            throw new UnsupportedOperationException();
        }
        o.flush();
		o.close();
	}
}