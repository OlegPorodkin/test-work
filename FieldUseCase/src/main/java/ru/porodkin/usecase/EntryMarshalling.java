package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.Entry;
import ru.porodkin.usecase.port.EntryDao;
import ru.porodkin.usecase.port.EntryPresenter;

import java.util.Collection;

public class EntryMarshalling {
    public static final Logger LOG = LoggerFactory.getLogger(EntryMarshalling.class);

    private final EntryPresenter presenter;
    private final EntryDao entryRepo;

    public EntryMarshalling(EntryPresenter presenter, EntryDao entryRepo) {
        this.presenter = presenter;
        this.entryRepo = entryRepo;
    }

    public void convertToXml(){
        Collection<Entry> allEntries = entryRepo.getAllEntries();
        if (allEntries.isEmpty()){
            LOG.info("Storage is empty. Nothing to prepare for marshalling.");
            return;
        }

        LOG.info("Prepare to marshalling {} entries", allEntries.size());
        presenter.present(allEntries);
        LOG.info("Marshaling complete.");
    }
}
