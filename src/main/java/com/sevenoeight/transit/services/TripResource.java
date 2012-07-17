
package com.sevenoeight.transit.services;

import com.sevenoeight.transit.domain.TripV1.ItineraryV1;
import com.sevenoeight.transit.domain.TripV1.LegV1;
import com.sevenoeight.transit.domain.TripV1.TransitLegV1;
import com.sevenoeight.transit.domain.TripV1.TripV1;

import javax.ws.rs.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
@Path("/trip")
public class TripResource {

    @GET
    @Produces("application/json")
    public TripV1 getTrip(@QueryParam("orig") String origin,
                          @QueryParam("dest") String destination,
                          @QueryParam("date") Date travelDate,
                          @DefaultValue("false") @QueryParam("arriveBy") Boolean shouldArriveBy,
                          @DefaultValue("best") @QueryParam("routing") String routingPreference,
                          @DefaultValue("false") @QueryParam("access") Boolean isAccessibleTrip,
                          @DefaultValue("805") @QueryParam("maxWalk") int maxWalkingDistance,
                          @QueryParam("version") Integer version) {

        if (version == null) {
            version = ApiVersionResource.CURRENT_API_VERSION;
        }

        if (travelDate == null) {
            travelDate = new Date();
        }

        switch (version) {
            case 1:

                break;
            default:

                break;
        }

//        return "origin: " + origin + "\n" +
//                "destination: " + destination + "\n" +
//                "travelDate: " + travelDate + "\n" +
//                "routingPreference: " + routingPreference + "\n" +
//                "shouldArriveBy: " + shouldArriveBy + "\n" +
//                "isAccessibleTrip: " + isAccessibleTrip + "\n" +
//                "maxWalkingDistance: " + maxWalkingDistance + "\n" +
//                "version: " + version + "\n";

        TransitLegV1 leg = new TransitLegV1();
        leg.setStartTime(new Date());

        List<LegV1> legs = new ArrayList<LegV1>();
        legs.add(leg);

        ItineraryV1 itinerary = new ItineraryV1();
        itinerary.setLegs(legs);

        List<ItineraryV1> itineraries = new ArrayList<ItineraryV1>();
        itineraries.add(itinerary);

        TripV1 trip = new TripV1();
        trip.setItineraries(itineraries);

        return trip;
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String createTrip(InputStream inputStream,
                             @FormParam("version") Integer version) {

        if (version == null) {
            version = ApiVersionResource.CURRENT_API_VERSION;
        }

        return "version: " + version;
    }
}
