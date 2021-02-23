package ru.porodkin.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.porodkin.usecase.port.EntryConverter;

public class EntryConversion {

    public static final Logger LOG = LoggerFactory.getLogger(EntryConversion.class);

    private final EntryConverter converter;

    public EntryConversion(EntryConverter converter) {
        this.converter = converter;
    }

    public boolean convert(){
        if (LOG.isDebugEnabled()) LOG.debug("Preparing for conversion ...");
        boolean convert = converter.convert();
        if (LOG.isDebugEnabled() && convert) LOG.debug("Conversion complete.");
        return convert;
    }
}
