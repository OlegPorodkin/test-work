package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.service.XmlWriter;
import ru.porodkin.usecase.port.EntryPresenter;

import java.util.Collection;

public class EntryPresenterToXml implements EntryPresenter {
    public static final Logger LOG = LoggerFactory.getLogger(EntryPresenterToXml.class);

    private final XmlWriter writer = new XmlWriter();

    @Override
    public void present(Collection<Entry> entries) {
        LOG.info("Prepare to marshaling entries...");
        writer.create(entries);
        LOG.info("Marshaling complete.");
    }
}
