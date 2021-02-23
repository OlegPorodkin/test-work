package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.domain.Entry;
import ru.porodkin.usecase.port.EntryInput;

import java.util.ArrayList;
import java.util.List;

public class EntrySummator {

    public static final Logger LOG = LoggerFactory.getLogger(EntrySummator.class);
    private final EntryInput input;
    private final String inputSource;

    public EntrySummator(EntryInput input, String inputSource) {
        this.input = input;
        this.inputSource = inputSource;
    }

    private long sum;

    public Long getAllEntryValueSum() {
        List<Entry> entries = new ArrayList<>(input.getAllEntries(inputSource));

        if (entries.isEmpty()) {
            LOG.debug("Entries is empty.");
            return 0L;
        }

        for (Entry e: entries) {
            int field = e.getField();
            sum += field;
        }
        return sum;
    }
}
