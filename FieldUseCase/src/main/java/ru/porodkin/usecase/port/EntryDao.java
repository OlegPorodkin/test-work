package ru.porodkin.usecase.port;

import ru.porodkin.domain.Entry;

import java.util.Collection;

/**
 * Interface for saving and retrieving data from storage
 */
public interface EntryDao {
    /**
     * Save all {@link Entry} in collection (see {@link Collection}) to storage
     *
     * @param entries collection of {@link Entry}
     * @return count of saves entries
     * @throws NullPointerException if the specified collection is null and this
     *          collection does not permit null elements
     */
    int saveEntries(Collection<Entry> entries);

    /**
     * Get all {@link Entry} from storage
     *
     * @return {@link Collection} of {@link Entry}
     */
    Collection<Entry> getAllEntries();
}
