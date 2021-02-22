package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.service.XmlReader;
import ru.porodkin.usecase.port.EntryInput;

import java.util.Collection;

public class EntryInputFromXml implements EntryInput {
    public static final Logger LOG = LoggerFactory.getLogger(EntryInputFromXml.class);
    private final XmlReader xmlReader = new XmlReader();

    private final String filePath;

    public EntryInputFromXml(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Collection<Entry> getAllEntries() {

        Collection<Entry> entries = xmlReader.readAllFrom(filePath);

        return entries;
    }
}
