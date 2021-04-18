package org.ths83.kafka.streams.aggregator.rest;

import org.ths83.kafka.streams.aggregator.streams.GetWeatherStationDataResult;
import org.ths83.kafka.streams.aggregator.streams.InteractiveQueries;
import org.ths83.kafka.streams.aggregator.streams.PipelineMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ApplicationScoped
@Path("/weather-stations")
public class WeatherStationEndpoint {

    @Inject
    InteractiveQueries interactiveQueries;

    @GET
    @Path("/data/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherStationData(@PathParam("id") int id) {
        GetWeatherStationDataResult result = interactiveQueries.getWeatherStationData(id);

        if (result.getResult().isPresent()) {
            return Response.ok(result.getResult().get()).build();
        } else if (result.getHost().isPresent()) {
            URI otherUri = getOtherUri(result.getHost().get(), result.getPort().getAsInt(),
                    id);
            return Response.seeOther(otherUri).build();
        } else {
            return Response.status(Status.NOT_FOUND.getStatusCode(),
                    "No data found for weather station " + id).build();
        }
    }

    @GET
    @Path("/meta-data")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PipelineMetadata> getMetaData() {
        return interactiveQueries.getMetaData();
    }

    private URI getOtherUri(String host, int port, int id) {
        try {
            return new URI("http://" + host + ":" + port + "/weather-stations/data/" + id);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}