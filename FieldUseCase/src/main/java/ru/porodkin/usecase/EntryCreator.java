package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.Entry;
import ru.porodkin.usecase.port.EntryDao;

import java.util.Collection;

public class EntryCreator {

    public static final Logger LOG = LoggerFactory.getLogger(EntryCreator.class);

    private final EntryDao entryRepo;

    public EntryCreator(EntryDao entryRepo) {
        this.entryRepo = entryRepo;
    }

    public void createEntries(Collection<Entry> entries){
        LOG.info("Saving {} entities...", entries.size());
        entryRepo.saveEntries(entries);
        LOG.info("Saving complete.");
    }
}
