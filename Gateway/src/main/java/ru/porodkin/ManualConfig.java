package ru.porodkin;

import ru.porodkin.postgresql.PostgreFieldDao;
import ru.porodkin.usecase.EntryConversion;
import ru.porodkin.usecase.EntryCreator;
import ru.porodkin.usecase.EntryMarshalling;
import ru.porodkin.usecase.EntrySummator;
import ru.porodkin.usecase.port.EntryConverter;
import ru.porodkin.usecase.port.EntryDao;
import ru.porodkin.usecase.port.EntryInput;
import ru.porodkin.usecase.port.EntryPresenter;
import ru.porodkin.utils.PropertiesReader;

public final class ManualConfig {

    private final String urlJDBC = PropertiesReader.loadProperties("urlJDBC");
    private final String loginJDBC = PropertiesReader.loadProperties("loginJDBC");
    private final String passJDBC = PropertiesReader.loadProperties("passJDBC");
    private final String xmlPathOne = PropertiesReader.loadProperties("xmlPathOne");
    private final String xmlPathTwo = PropertiesReader.loadProperties("xmlPathTwo");
    private final String xslSchema = PropertiesReader.loadProperties("xslSchema");

    private final EntryDao entryDao =
            new PostgreFieldDao(urlJDBC, loginJDBC, passJDBC);

    private final EntryConverter entryConverter =
            new EntryConverterXml(xmlPathOne, xmlPathTwo, xslSchema);

    private final EntryPresenter entryPresenter = new EntryPresenterToXml();

    private final EntryInput entryInput = new EntryInputFromXml(xmlPathTwo);

    public EntryConversion entryConversion(){
        return new EntryConversion(entryConverter);
    }

    public EntryCreator entryCreator(){
        return new EntryCreator(entryDao);
    }

    public EntryMarshalling entryMarshalling(){
        return new EntryMarshalling(entryPresenter, entryDao);
    }

    public EntrySummator entryInput(){
        return new EntrySummator(entryInput);
    }

}
