package com.sevenoeight.transit.domain.TripV1;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class ItineraryV1 {

    private List<LegV1> legs;

    @XmlElement
    public List<LegV1> getLegs() {
        return legs;
    }

    public void setLegs(List<LegV1> legs) {
        this.legs = legs;
    }
}
