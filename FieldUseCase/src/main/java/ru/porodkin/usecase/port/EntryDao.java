package ru.porodkin.usecase.port;

import ru.porodkin.Entry;

import java.util.Collection;

public interface EntryDao {
    int saveEntries(Collection<Entry> entries);
    Collection<Entry> getAllEntries();
}
