package ru.porodkin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XmlConverter {

    public static final Logger LOG = LoggerFactory.getLogger(XmlConverter.class);


    public void convert(String from, String to, String xsl) {
        try {

            TransformerFactory factory = TransformerFactory.newInstance();

            Source xml = new StreamSource(new File(from));
            Source xslt = new StreamSource(new File(xsl));

            Transformer trf = factory.newTransformer(xslt);
            trf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trf.setOutputProperty(OutputKeys.INDENT, "yes");

            trf.transform(xml, new StreamResult(new File(to)));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}