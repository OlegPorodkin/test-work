package ru.porodkin.usecase.port;

/**
 * Interface for conversion {@link ru.porodkin.domain.Entry}
 */
public interface EntryConverter {
    /**
     * converting from one state to another
     *
     * @return true if the conversion was successful
     */
    boolean convert();
}
