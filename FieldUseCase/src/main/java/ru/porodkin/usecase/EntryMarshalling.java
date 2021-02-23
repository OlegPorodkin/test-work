package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.usecase.port.EntryDao;
import ru.porodkin.usecase.port.EntryPresenter;

import java.util.Collection;

public class EntryMarshalling {
    public static final Logger LOG = LoggerFactory.getLogger(EntryMarshalling.class);

    private final EntryPresenter presenter;
    private final EntryDao entryRepo;
    private final String xml;

    public EntryMarshalling(EntryPresenter presenter, EntryDao entryRepo, String xml) {
        this.presenter = presenter;
        this.entryRepo = entryRepo;
        this.xml = xml;
    }

    public boolean convertToXml(){
        Collection<Entry> allEntries = entryRepo.getAllEntries();
        if (allEntries.isEmpty()){
            LOG.info("Storage is empty. Nothing to prepare for marshalling.");
            return false;
        }

        if (LOG.isDebugEnabled()) LOG.debug("Prepare to marshalling {} entries", allEntries.size());
        boolean present = presenter.present(allEntries, xml);
        if (LOG.isDebugEnabled() && present) LOG.debug("Marshaling complete to {}.", xml);
        return present;
    }
}
