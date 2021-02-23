package ru.porodkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.xmlpresenter.XmlConverter;
import ru.porodkin.usecase.port.EntryConverter;

public class EntryConverterXml implements EntryConverter {
    public static final Logger LOG = LoggerFactory.getLogger(EntryConverterXml.class);
    private final XmlConverter converter = new XmlConverter();

    private final String sourceFrom;
    private final String sourceTo;
    private final String xslSchema;

    public EntryConverterXml(String sourceFrom, String sourceTo, String xslSchema) {
        this.sourceFrom = sourceFrom;
        this.sourceTo = sourceTo;
        this.xslSchema = xslSchema;
    }

    @Override
    public boolean convert() {
        LOG.debug("Prepare converting xml document...");
        boolean convert = converter.convert(sourceFrom, sourceTo, xslSchema);
        LOG.debug("Converting complete.");

        return convert;
    }
}
