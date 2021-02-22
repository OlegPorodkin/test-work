package ru.porodkin.usecase.port;

import ru.porodkin.Entry;

import java.util.Collection;

public interface EntryPresenter {
    void present(Collection<Entry> entries);
}
