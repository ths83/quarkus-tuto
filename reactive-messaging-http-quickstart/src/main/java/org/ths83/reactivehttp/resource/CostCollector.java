package org.ths83.reactivehttp.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/cost-collector")
@ApplicationScoped
public class CostCollector {

    private double sum = 0;

    @POST
    public void consumeCost(String valueAsString) {
        sum += Double.parseDouble(valueAsString);
    }

    @GET
    public double getSum() {
        return sum;
    }

}