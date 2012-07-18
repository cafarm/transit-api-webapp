package com.sevenoeight.transit.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
@Path("/request-uri")
public class RequestUri {

    // These are the values for "their" (King County Metros at the moment)
    // service and should be modified to keep up with any changes "they" make.
    private static final String THEIR_URI_BASE = "http://tripplanner.kingcounty.gov/cgi-bin/itin.pl?action=entry";

    private static final String THEIR_ORIGIN = "Orig";
    private static final String THEIR_DESTINATION = "Dest";

    private static final String THEIR_ROUTING_PREFERENCE = "Min";
    private static final String THEIR_BEST_ROUTE = "T";
    private static final String THEIR_FEWER_TRANSFERS = "X";
    private static final String THEIR_LESS_WALKING = "W";

    private static final String THEIR_TRAVEL_DATE = "Date";
    private static final String THEIR_TRAVEL_TIME_HOUR = "hour_time";
    private static final String THEIR_TRAVEL_TIME_MINUTE = "minute_time";
    private static final String THEIR_TRAVEL_TIME_AM_OR_PM = "ampm_time";

    private static final String THEIR_DEPART_AT_OR_ARRIVE_BY = "Arr";
    private static final String THEIR_DEPART_AT = "D";
    private static final String THEIR_ARRIVE_BY = "A";

    private static final String THEIR_REQUIRES_ACCESSIBLE_TRIP = "Atr";
    private static final String THEIR_REQUIRES_ACCESSIBLE_TRIP_YES = "Y";
    private static final String THEIR_REQUIRES_ACCESSIBLE_TRIP_NO = "N";

    private static final String THEIR_MAX_WALKING_DISTANCE = "Walk";
    private static final String THEIR_QUARTER_MILE = ".25";
    private static final String THEIR_HALF_MILE = ".50";
    private static final String THEIR_THREE_QUARTER_MILE = ".75";
    private static final String THEIR_ONE_MILE = "1.0";

    // These are our public facing parameters values, they should not be
    // modified.
    public static final String API_ORIGIN = "orig";
    public static final String API_DESTINATION = "dest";
    public static final String API_TRAVEL_DATE = "date";
    public static final String API_SHOULD_ARRIVE_BY = "arriveBy";

    public static final String API_ROUTING_PREFERENCE = "routing";
    public static final String API_BEST_ROUTE = "best";
    public static final String API_FEWER_TRANSFERS = "fewTransfer";
    public static final String API_LESS_WALKING = "lessWalk";

    public static final String API_REQUIRES_ACCESSIBLE_TRIP = "access";
    public static final String API_MAX_WALKING_DISTANCE = "maxWalk";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRequestUriResponse(@Encoded @QueryParam(API_ORIGIN) String origin,
                                          @Encoded @QueryParam(API_DESTINATION) String destination,
                                          @QueryParam(API_TRAVEL_DATE) Date travelDate,
                                          @DefaultValue("false") @QueryParam(API_SHOULD_ARRIVE_BY) Boolean shouldArriveBy,
                                          @DefaultValue(API_BEST_ROUTE) @QueryParam(API_ROUTING_PREFERENCE) String routingPreference,
                                          @DefaultValue("false") @QueryParam(API_REQUIRES_ACCESSIBLE_TRIP) Boolean requiresAccessibleTrip,
                                          @DefaultValue("805") @QueryParam(API_MAX_WALKING_DISTANCE) int maxWalkingDistance) {

        if (travelDate == null) {
            travelDate = new Date();
        }

        try {
            URI uri = getRequestUri(origin, destination, travelDate, shouldArriveBy, routingPreference, requiresAccessibleTrip, maxWalkingDistance);
            return Response.ok().entity(uri.toString()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
        } catch (URISyntaxException e) {
            return Response.serverError().type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
        }
    }

    public URI getRequestUri(String origin,
                             String destination,
                             Date travelDate,
                             Boolean shouldArriveBy,
                             String routingPreference,
                             Boolean requiresAccessibleTrip,
                             int maxWalkingDistance)
            throws IllegalArgumentException, URISyntaxException {


        // The origin and destination were left encoded

        SimpleDateFormat formatter = new SimpleDateFormat("MM'%2F'dd'%2F'yy");
        String encodedTravelDate = formatter.format(travelDate);

        formatter.applyPattern("HH");
        String encodedTravelTimeHour = formatter.format(travelDate);

        formatter.applyPattern("mm");
        String encodedTravelTimeMinute = formatter.format(travelDate);

        formatter.applyPattern("a");
        String encodedTravelTimeAmOrPm = formatter.format(travelDate);

        String encodedDepartAtOrArriveBy;
        if (shouldArriveBy) {
            encodedDepartAtOrArriveBy = THEIR_ARRIVE_BY;
        } else {
            encodedDepartAtOrArriveBy = THEIR_DEPART_AT;
        }

        String encodedRoutingPreference;
        if (routingPreference.equals(API_BEST_ROUTE)) {
            encodedRoutingPreference = THEIR_BEST_ROUTE;
        } else if (routingPreference.equals(API_FEWER_TRANSFERS)) {
            encodedRoutingPreference = THEIR_FEWER_TRANSFERS;
        } else if (routingPreference.equals(API_LESS_WALKING)) {
            encodedRoutingPreference = THEIR_LESS_WALKING;
        } else {
            throw new IllegalArgumentException();
        }

        String encodedRequiresAccessibleTrip;
        if (requiresAccessibleTrip) {
            encodedRequiresAccessibleTrip = THEIR_REQUIRES_ACCESSIBLE_TRIP_YES;
        } else {
            encodedRequiresAccessibleTrip = THEIR_REQUIRES_ACCESSIBLE_TRIP_NO;
        }

        String encodedMaxWalkingDistance;
        float maxWalkingDistanceInMiles = maxWalkingDistance * 0.00062f;
        if (maxWalkingDistanceInMiles < .25) {
            encodedMaxWalkingDistance = THEIR_QUARTER_MILE;
        } else if (maxWalkingDistanceInMiles < .5) {
            encodedMaxWalkingDistance = THEIR_HALF_MILE;
        } else if (maxWalkingDistanceInMiles < .75) {
            encodedMaxWalkingDistance = THEIR_THREE_QUARTER_MILE;
        } else {
            encodedMaxWalkingDistance = THEIR_ONE_MILE;
        }

        String uriString = THEIR_URI_BASE + "&" +
                THEIR_ORIGIN + "=" + origin + "&" +
                THEIR_DESTINATION + "=" + destination + "&" +
                THEIR_TRAVEL_DATE + "=" + encodedTravelDate + "&" +
                THEIR_TRAVEL_TIME_HOUR + "=" + encodedTravelTimeHour + "&" +
                THEIR_TRAVEL_TIME_MINUTE + "=" + encodedTravelTimeMinute + "&" +
                THEIR_TRAVEL_TIME_AM_OR_PM + "=" + encodedTravelTimeAmOrPm + "&" +
                THEIR_DEPART_AT_OR_ARRIVE_BY + "=" + encodedDepartAtOrArriveBy + "&" +
                THEIR_ROUTING_PREFERENCE + "=" + encodedRoutingPreference + "&" +
                THEIR_REQUIRES_ACCESSIBLE_TRIP + "=" + encodedRequiresAccessibleTrip + "&" +
                THEIR_MAX_WALKING_DISTANCE + "=" + encodedMaxWalkingDistance;

        return new URI(uriString);
    }
}
