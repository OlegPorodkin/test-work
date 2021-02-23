package ru.porodkin.xmlpresenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.porodkin.domain.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

public class XmlWriter {

    private static final Logger LOG = LoggerFactory.getLogger(XmlWriter.class);

    public boolean create(Collection<Entry> entries, String toXmlFile){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element entriesElement = doc.createElement("entries");
            doc.appendChild(entriesElement);

            for (Entry e: entries){
                Element entry = doc.createElement("entry");
                entriesElement.appendChild(entry);
                Element field = doc.createElement("field");
                field.setTextContent(String.valueOf(e.getField()));
                entry.appendChild(field);
            }

            Transformer trf = TransformerFactory.newInstance().newTransformer();
            DOMSource src = new DOMSource(doc);
            trf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trf.setOutputProperty(OutputKeys.INDENT, "yes");

            FileOutputStream fos = new FileOutputStream(toXmlFile);

            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
            if (LOG.isDebugEnabled()) LOG.debug("xml created...");
            return true;
        } catch (ParserConfigurationException e) {
            LOG.error("Something happened during the parser configuration", e);
            return true;
        } catch (TransformerConfigurationException e) {
            LOG.error("Something happened during the transformation configuration", e);
            return true;
        } catch (TransformerException e) {
            LOG.error("Something happened during the transformation", e);
            return true;
        } catch (FileNotFoundException e) {
            LOG.error("{} file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason", toXmlFile, e);
            return true;
        }
    }
}
