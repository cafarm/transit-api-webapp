
package com.sevenoeight.transit.services;

import com.sevenoeight.transit.domain.RequestUriFormatV1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
@Path("/request-uri-format")
public class RequestUriFormatResource {

    // *** DO NOT MODIFY THESE CONSTANTS ***
    // downstream services rely on these values
    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    public static final String ROUTING_PREFERENCE = "routingPreference";
    public static final String TRAVEL_DATE = "travelDate";
    public static final String DEPART_AT_OR_ARRIVE_BY = "departAtOrArriveBy";
    public static final String REQUIRES_ACCESSIBLE_TRIP = "requiresAccessibleTrip";
    public static final String MAX_WALK_DISTANCE = "maxWalkDistance";

    public static final String BEST_ROUTE = "bestRoute";
    public static final String FEWER_TRANSFERS = "fewerTransfers";
    public static final String LESS_WALKING = "lessWalking";

    public static final String DEPART_AT = "departAt";
    public static final String ARRIVE_BY = "arriveBy";

    public static final String YES = "yes";
    public static final String NO = "no";

    public static final String QUARTER_MILE = "quarterMile";
    public static final String HALF_MILE = "halfMile";
    public static final String THREE_QUARTER_MILE = "threeQuarterMile";
    public static final String ONE_MILE = "oneMile";

    @GET
    @Produces("application/json")
    public RequestUriFormatV1 getRequestURIFormat(@QueryParam("version") Integer version)
            throws URISyntaxException {

        if (version == null) {
            version = ApiVersionResource.CURRENT_API_VERSION;
        }

        URI uriBase = new URI("http://tripplanner.kingcounty.gov/cgi-bin/itin.pl");

        // The @ symbol signifies to a downstream service that it should replace
        // the parameter value with its own variable. The identifier after the
        // @ symbol tells the downstream service what variable to use.
        //
        // Not placing an @ symbol before the value signifies that the
        // downstream service should use the value as is.
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("Orig", "@" + ORIGIN);
        parameters.put("Dest", "@" + DESTINATION);
        parameters.put("Min", "@" + ROUTING_PREFERENCE);
        parameters.put("Date", "@" + TRAVEL_DATE);
        parameters.put("hour_time", "@" + TRAVEL_DATE);
        parameters.put("minute_time", "@" + TRAVEL_DATE);
        parameters.put("ampm_time", "@" + TRAVEL_DATE);
        parameters.put("Arr", "@" + DEPART_AT_OR_ARRIVE_BY);
        parameters.put("Atr", "@" + REQUIRES_ACCESSIBLE_TRIP);
        parameters.put("Walk", "@" + MAX_WALK_DISTANCE);

        HashMap<String, String> valueDateFormats = new HashMap<String, String>();
        valueDateFormats.put("Date", "MM'%2F'dd'%2F'yy");
        valueDateFormats.put("hour_time", "HH");
        valueDateFormats.put("minute_time", "mm");
        valueDateFormats.put("ampm_time", "a");

        HashMap<String, String> routingPreferenceOptions = new HashMap<String, String>();
        routingPreferenceOptions.put(BEST_ROUTE, "T");
        routingPreferenceOptions.put(FEWER_TRANSFERS, "X");
        routingPreferenceOptions.put(LESS_WALKING, "W");

        HashMap<String, String> departAtOrArriveByOptions = new HashMap<String, String>();
        departAtOrArriveByOptions.put(DEPART_AT, "D");
        departAtOrArriveByOptions.put(ARRIVE_BY, "A");

        HashMap<String, String> requiresAccessibleTripOptions = new HashMap<String, String>();
        requiresAccessibleTripOptions.put(YES, "Y");
        requiresAccessibleTripOptions.put(NO, "N");

        HashMap<String, String> maxWalkDistanceOptions = new HashMap<String, String>();
        maxWalkDistanceOptions.put(QUARTER_MILE, ".25");
        maxWalkDistanceOptions.put(HALF_MILE, ".50");
        maxWalkDistanceOptions.put(THREE_QUARTER_MILE, ".75");
        maxWalkDistanceOptions.put(ONE_MILE, "1.0");

        HashMap<String, HashMap<String, String>> valueOptions = new HashMap<String, HashMap<String, String>>();
        valueOptions.put(ROUTING_PREFERENCE, routingPreferenceOptions);
        valueOptions.put(DEPART_AT_OR_ARRIVE_BY, departAtOrArriveByOptions);
        valueOptions.put(REQUIRES_ACCESSIBLE_TRIP, requiresAccessibleTripOptions);
        valueOptions.put(MAX_WALK_DISTANCE, maxWalkDistanceOptions);

        return new RequestUriFormatV1(uriBase, parameters, valueDateFormats, valueOptions);
    }
}
