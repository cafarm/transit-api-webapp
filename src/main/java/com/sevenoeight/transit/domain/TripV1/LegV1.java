package com.sevenoeight.transit.domain.TripV1;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
public class LegV1 {

    private Date startTime;

    private Date endTime;

    private int distance;

    public enum Mode {
        WALK("walk"),
        BUS("bus"),
        RAIL("rail"),
        FERRY("ferry");

        private Mode(final String text) {
            this.text = text;
        }

        private final String text;

        @Override
        public String toString() {
            return text;
        }

    }

    private Mode mode;

    @XmlElement
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @XmlElement
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @XmlElement
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @XmlElement
    public String getMode() {
        return mode != null ? mode.toString() : null;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
