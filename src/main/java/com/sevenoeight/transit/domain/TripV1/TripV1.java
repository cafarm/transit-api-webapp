package com.sevenoeight.transit.domain.TripV1;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class TripV1 {

    private List<ItineraryV1> itineraries;

    @XmlElement
    public List<ItineraryV1> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<ItineraryV1> itineraries) {
        this.itineraries = itineraries;
    }
}
