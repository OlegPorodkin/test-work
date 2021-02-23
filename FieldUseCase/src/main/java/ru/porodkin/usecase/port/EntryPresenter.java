package ru.porodkin.usecase.port;

import ru.porodkin.domain.Entry;

import java.util.Collection;

/**
 * Interface for conversion
 */
public interface EntryPresenter {

    /**
     * Converts the collection to display in a different view
     *
     * @param entries {@link Collection} entries to converting
     * @param to what to convert to
     * @return true if converting successful
     */
    boolean present(Collection<Entry> entries, String to);
}
