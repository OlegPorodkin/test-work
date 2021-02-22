package ru.porodkin.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.porodkin.Entry;

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

    public void create(Collection<Entry> entries){
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

            FileOutputStream fos = new FileOutputStream("1.xml");

            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
