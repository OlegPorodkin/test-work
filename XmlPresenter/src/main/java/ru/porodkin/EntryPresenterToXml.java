package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.xmlpresenter.XmlWriter;
import ru.porodkin.usecase.port.EntryPresenter;

import java.util.Collection;

public class EntryPresenterToXml implements EntryPresenter {
    public static final Logger LOG = LoggerFactory.getLogger(EntryPresenterToXml.class);

    private final XmlWriter writer = new XmlWriter();

    @Override
    public boolean present(Collection<Entry> entries, String to) {

        if (LOG.isDebugEnabled()) LOG.debug("Prepare to marshaling entries...");
        boolean result = writer.create(entries, to);
        if (LOG.isDebugEnabled() && result){
            LOG.debug("Marshaling complete.");
        } else {
            LOG.debug("Marshaling failed");
        }

        return result;
    }
}
