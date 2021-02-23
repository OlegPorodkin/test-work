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
import java.util.stream.IntStream;

public class MainApplication {

    public static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {

        //region Инициализация конфигурации приложения
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
        //endregion


        //region Подготовка данных для теситования
        List<Entry> entries = new ArrayList<>();

        int count = 1_000_000;

        if (args.length != 0 && args[0] != null) {
            count = Integer.parseInt(args[0]);
        } else {
            LOG.info("uses the default value of the quantity entry to write to the database");
        }

        IntStream
                .rangeClosed(1, count)
                .forEachOrdered(i -> {
                    Entry e = new Entry();
                    e.setField(i);
                    entries.add(e);
                });
        //endregion


        //region Основная бизнес логика
        entryCreator.createEntries(entries);
        LOG.info("Saving entries to DB complete.");
        if (entryMarshalling.convertToXml()) {
            LOG.info("Convert entry from DB to xml complete.");

            if (entryConversion.convert()) {
                LOG.info("Convert by xslt complete.");

                Long sum = entrySummator.getAllEntryValueSum();
                LOG.info("Sum of all entry = {}.", sum);
            } else {
                LOG.warn("Converting by xslt failed");
            }
        } else {
            LOG.warn("Convert entry from DB to xml failed");
        }
        //endregion

        LOG.info("Application stop.");

    }
}
