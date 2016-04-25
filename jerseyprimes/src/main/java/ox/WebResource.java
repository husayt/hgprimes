/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox;

import com.sun.jersey.api.view.Viewable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ox.calc.PrimesService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/rst")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class WebResource {



    @GET
    @Path("/isprime")
    public String isPrime(@QueryParam("number") long number, @QueryParam("method") String methodname) {
        try {
            final Boolean prime = PrimesService.getGenerator(methodname).isPrime(number);
            return prime.toString();
        } catch (Exception x) {
            return "Error: "+x.getMessage();
        }
    }
        @GET
        @Path("/listprimes")
        public PrimeReqResult getPrimes(@QueryParam("count") long count, @QueryParam("method") String methodname) {
            PrimeReqResult res;
            try {

                final Set<Long> firstNPrimes = PrimesService.getFirstNPrimes(methodname, count);
                res = new PrimeReqResult(firstNPrimes);
            } catch (Exception x) {
                res = new PrimeReqResult(x.getMessage());
            }

            return res;
        }

    static class PrimeReqResult {
        @Getter
        private String error;
        @Getter
        private Set<Long> numbers;
        @Getter
        private long count;


        public PrimeReqResult(String error) {
            this.error = error;
        }

        public PrimeReqResult(Set<Long> numbers) {
            this.numbers = numbers;
            count=numbers.size();
        }
    }
}