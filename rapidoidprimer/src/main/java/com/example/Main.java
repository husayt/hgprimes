package com.example;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.rapidoid.annotation.GET;
import org.rapidoid.annotation.POST;
import org.rapidoid.annotation.Page;
import org.rapidoid.annotation.Param;
import org.rapidoid.gui.Btn;
import org.rapidoid.gui.GUI;
import org.rapidoid.http.Req;
import org.rapidoid.http.Resp;
import org.rapidoid.http.fast.On;
import ox.calc.PrimesService;

import java.util.Set;


public class Main {

    public static void main(String[] args) {
        On.get("/").json((Req req) -> req.params());
        //On.req(req -> req.uri());
        On.page("/hi").gui("Hello <b>world</b>!");

        //  On.error((req, resp, error) -> "Error: " + error.getMessage());
        On.req(new Object() {

            @GET
            public boolean isprime(@Param("method") String method, @Param("number") Long number) {
                if (number == null) {
                    throw new RuntimeException("missing parameter!");
                }

                final boolean prime = PrimesService.getGenerator(method).isPrime(number);
                return prime;
            }

            @Page(uri = "/pq", title = "Check if number is prime")
            public Object pq(PrimeQ pq) {

                if (pq == null) {
                    pq = new PrimeQ();

                }
                if (Strings.isNullOrEmpty(pq.method)) {
                    pq.method = "dict";
                } else if (pq.number != null && pq.number > 0) {
                    final boolean prime = PrimesService.getGenerator(pq.getMethod()).isPrime(pq.getNumber());

                    return GUI.h4("This number is " + (prime ? "" : "not ") + "a prime");
                }


                Btn check = GUI.btn("Check").primary().linkTo("/pq?method=" + pq.getMethod() + "&number=" + pq.getNumber());
                return GUI.edit(pq).buttons(check);
            }

            //@GET
            @Page(uri = "/primes", title = "Movie details")
            public Object primes(@Param("method") String method, @Param("count") long count) {
                PrimeReqResult res;
                try {

                    final Set<Long> firstNPrimes = PrimesService.getFirstNPrimes(method, count);
                    res = new PrimeReqResult(firstNPrimes);
                } catch (Exception x) {
                    res = new PrimeReqResult(x.getMessage());
                }

                //return res;
                return GUI.show(res).buttons(GUI.btn("OK"));
            }

            @POST
            public String lower(Req req, Resp resp, @Param("x") String s) {
                return s.toLowerCase();
            }

        });


    }

    public static class PrimeQ {
        @Getter
        @Setter
        private String method;
        @Getter
        @Setter
        private Long number;
    }

    public static class PrimesQ {
        @Getter
        @Setter
        private String method;
        @Getter
        @Setter
        private long count;
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
            count = numbers.size();
        }
    }
}