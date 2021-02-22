package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        EntryCreator entryCreator = config.entryCreator();
        EntryConversion entryConversion = config.entryConversion();
        EntryMarshalling entryMarshalling = config.entryMarshalling();
        EntrySummator entrySummator = config.entryInput();
        LOG.info("Application start.");

        List<Entry> entries = new ArrayList<>();

        for (int i = 1; i <= 1_000_000; i++) {
            Entry e = new Entry();
            e.setField(i);
            entries.add(e);
        }

        entryCreator.createEntries(entries);
        LOG.info("Saving entries to DB complete.");
        entryMarshalling.convertToXml();
        LOG.info("Convert entry from DB to xml complete.");
        entryConversion.convert();
        LOG.info("Convert by xslt complete.");
        Long sum = entrySummator.getAllEntryValueSum();
        LOG.info("Sum of all entry = {}.", sum);

        LOG.info("Application stop.");
    }
}
