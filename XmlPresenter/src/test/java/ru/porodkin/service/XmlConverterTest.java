package ru.porodkin.service;

import org.junit.jupiter.api.Test;

class XmlConverterTest {

    XmlConverter xmlConverter = new XmlConverter();

    @Test
    public void test(){
        xmlConverter.convert("1.xml","2.xml","1to2.xsl");
    }

}