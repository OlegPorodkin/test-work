package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.xmlpresenter.XmlReader;
import ru.porodkin.usecase.port.EntryInput;

import java.util.Collection;

public class EntryInputFromXml implements EntryInput {
    public static final Logger LOG = LoggerFactory.getLogger(EntryInputFromXml.class);
    private final XmlReader xmlReader = new XmlReader();

    @Override
    public Collection<Entry> getAllEntries(String from) {
        Collection<Entry> entries = xmlReader.readAllFrom(from);
        if (LOG.isDebugEnabled()) LOG.debug("Getting from {} {} entries", from, entries.size());
        return entries;
    }
}
