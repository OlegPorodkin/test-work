package ru.porodkin.postgresql;

import org.junit.jupiter.api.Test;
import ru.porodkin.Entry;

import java.util.ArrayList;
import java.util.Collection;

class PostgreFieldDaoTest {

//    PostgreFieldDao testDao = new PostgreFieldDao();

    @Test
    public void firstTest(){
//        testDao.getAllEntries();
    }

    @Test
    public void secondTest(){
        Collection<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Entry entry = new Entry();
            entry.setField(i);
            entries.add(entry);
        }

//        testDao.saveEntries(entries);
    }

}