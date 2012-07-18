package com.sevenoeight.transit.domain;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class Trip {

    private List<Itinerary> itineraries;

    @XmlElement
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}
