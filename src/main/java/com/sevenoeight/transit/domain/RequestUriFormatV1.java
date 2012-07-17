package com.sevenoeight.transit.domain;

import javax.xml.bind.annotation.XmlElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class RequestUriFormatV1 {

    private URI uriBase;

    private HashMap<String, String> parameters;

    private HashMap<String, String> valueDateFormats;

    private HashMap<String, HashMap<String, String>> valueOptions;

    public RequestUriFormatV1() throws URISyntaxException {
    }

    public RequestUriFormatV1(URI uriBase,
                              HashMap<String, String> parameters,
                              HashMap<String, String> valueDateFormats,
                              HashMap<String, HashMap<String, String>> valueOptions) {

        this.uriBase = uriBase;
        this.parameters = parameters;
        this.valueDateFormats = valueDateFormats;
        this.valueOptions = valueOptions;
    }

    @XmlElement
    public URI getUriBase() {
        return uriBase;
    }

    @XmlElement
    public HashMap<String, String> getParameters() {
        return parameters;
    }

    @XmlElement
    public HashMap<String, String> getValueDateFormats() {
        return valueDateFormats;
    }

    @XmlElement
    public HashMap<String, HashMap<String, String>> getValueOptions() {
        return valueOptions;
    }
}
