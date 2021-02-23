package ru.porodkin.usecase.port;

import ru.porodkin.domain.Entry;

import java.util.Collection;

/**
 * Interface for outputting the {@link Entry} from the outside
 */
public interface EntryInput {

    /**
     * Getting all {@link Entry} from source
     *
     * @param from data source
     * @return {@link Collection} of {@link Entry}
     */
    Collection<Entry> getAllEntries(String from);
}
