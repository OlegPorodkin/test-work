package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.gateway.ManualConfig;
import ru.porodkin.usecase.EntryConversion;
import ru.porodkin.usecase.EntryCreator;
import ru.porodkin.usecase.EntryMarshalling;
import ru.porodkin.usecase.EntrySummator;

import java.util.ArrayList;
import java.util.List;

public class MainApplication {

    public static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        LOG.info("Application staring...");
        ManualConfig config = new ManualConfig();
        LOG.info("config init...");
        EntryCreator entryCreator = config.entryCreator();
        LOG.info("entry creator init...");
        EntryConversion entryConversion = config.entryConversion();
        LOG.info("entry converter init...");
        EntryMarshalling entryMarshalling = config.entryMarshalling();
        LOG.info("entry marshalling init...");
        EntrySummator entrySummator = config.entryInput();
        LOG.info("entry summator init...");
        LOG.info("Application start.");

        List<Entry> entries = new ArrayList<>();

        int count = 1_000_000;

        if (args[0] != null) {
            LOG.debug("uses the default value of the quantity entry to write to the database");
            count = Integer.parseInt(args[0]);
        }

        for (int i = 1; i <= count; i++) {
            Entry e = new Entry();
            e.setField(i);
            entries.add(e);
        }

        entryCreator.createEntries(entries);
        LOG.info("Saving entries to DB complete.");
        if(entryMarshalling.convertToXml()){
            LOG.info("Convert entry from DB to xml complete.");
        }else {
            LOG.info("Convert entry from DB to xml failed");
        }
        entryConversion.convert();
        LOG.info("Convert by xslt complete.");
        Long sum = entrySummator.getAllEntryValueSum();
        LOG.info("Sum of all entry = {}.", sum);

        LOG.info("Application stop.");
    }
}
