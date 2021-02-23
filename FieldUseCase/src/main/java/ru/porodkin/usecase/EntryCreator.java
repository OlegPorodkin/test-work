package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.usecase.port.EntryDao;

import java.util.Collection;

public class EntryCreator {

    public static final Logger LOG = LoggerFactory.getLogger(EntryCreator.class);

    private final EntryDao entryRepo;

    public EntryCreator(EntryDao entryRepo) {
        this.entryRepo = entryRepo;
    }

    public void createEntries(Collection<Entry> entries){

        if(entries == null){
            LOG.warn("cannot save null entries collection");
            return;
        }

        if (entries.isEmpty()){
            LOG.warn("Nothing to save. Entries collection is empty.");
            return;
        }

        if(LOG.isDebugEnabled()) LOG.debug("Saving {} entities...", entries.size());
        int count = 0;
        try {
            count = entryRepo.saveEntries(entries);
        } catch (NullPointerException e) {
            LOG.error("Collection of entry or collection contains one or more null elements");
            throw new NullPointerException(e.getMessage());
        }
        if(LOG.isDebugEnabled()) LOG.debug("Saving {} entries complete.", count);
    }
}
