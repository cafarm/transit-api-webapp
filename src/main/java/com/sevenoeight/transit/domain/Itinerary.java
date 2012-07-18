package com.sevenoeight.transit.domain;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class Itinerary {

    private List<Leg> legs;

    @XmlElement
    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
