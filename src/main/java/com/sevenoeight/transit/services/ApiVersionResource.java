package com.sevenoeight.transit.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author <a href="mailto:mark.cafaro@gmail.com">Mark Cafaro</a>
 */
@Path("/api-version")
public class ApiVersionResource {

    public static final int CURRENT_API_VERSION = 1;

    @GET
    @Produces("text/plain")
    public String getCurrentApiVersion() {
        return Integer.toString(CURRENT_API_VERSION);
    }
}
