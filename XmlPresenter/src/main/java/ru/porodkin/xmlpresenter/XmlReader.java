package ru.porodkin.xmlpresenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XmlReader {

    public static final Logger LOG = LoggerFactory.getLogger(XmlReader.class);
    List<Entry> entries = new ArrayList<>();

    public Collection<Entry> readAllFrom(String from){

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(from));

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("entry")) {
                        String field = startElement.getAttributeByName(new QName("field")).getValue();

                        Entry ent = new Entry();
                        ent.setField(Integer.parseInt(field));
                        entries.add(ent);
                        if (LOG.isTraceEnabled()) LOG.trace("Add {} to entries collection", ent);
                    }

                }
            }

            if (LOG.isDebugEnabled()) LOG.debug("Entry from {} count of fields = {}", from, entries.size());

        } catch (XMLStreamException e) {
            LOG.error("Something wrong when XMLEventReader get InputStream from {}", from);
        } catch (FileNotFoundException e) {
            LOG.error("Not found {}", from);
        }
        return entries;
    }
}
