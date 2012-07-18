package com.sevenoeight.transit.services;

import com.sevenoeight.transit.domain.Trip;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Date;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
@Path("/trip")
public class TripResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTripResponse(@QueryParam(RequestUri.API_ORIGIN) String origin,
                                    @QueryParam(RequestUri.API_DESTINATION) String destination,
                                    @QueryParam(RequestUri.API_TRAVEL_DATE) Date travelDate,
                                    @DefaultValue("false") @QueryParam(RequestUri.API_SHOULD_ARRIVE_BY) Boolean shouldArriveBy,
                                    @DefaultValue(RequestUri.API_BEST_ROUTE) @QueryParam(RequestUri.API_ROUTING_PREFERENCE) String routingPreference,
                                    @DefaultValue("false") @QueryParam(RequestUri.API_REQUIRES_ACCESSIBLE_TRIP) Boolean isAccessibleTrip,
                                    @DefaultValue("805") @QueryParam(RequestUri.API_MAX_WALKING_DISTANCE) int maxWalkingDistance) {

        Trip trip = getTrip(origin, destination, travelDate, shouldArriveBy, routingPreference, isAccessibleTrip, maxWalkingDistance);
        return Response.ok().entity(trip).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTripResponse(InputStream inputStream) {

        return null;
    }

    public Trip getTrip(String origin,
                        String destination,
                        Date travelDate,
                        Boolean shouldArriveBy,
                        String routingPreference,
                        Boolean isAccessibleTrip,
                        int maxWalkingDistance) {

        return null;
    }

}
