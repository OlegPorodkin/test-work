package ru.porodkin.service;

import org.junit.jupiter.api.Test;

class XmlReaderTest {

    XmlReader reader = new XmlReader();

    @Test
    public void test(){
        reader.readAllFrom("2.xml");
    }

}