/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox.base;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;


@Provider
@Produces("application/json")
public class JacksonConfigurator implements com.google.inject.Provider<ObjectMapper>, ContextResolver<ObjectMapper> {


    private static final ObjectMapper objectMapper = createIinitialiseOM();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static ObjectMapper createIinitialiseOM() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new AfterburnerModule());
        om.registerModule(new JodaModule());
        om.registerModule(new GuavaModule());

        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

        return om;
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return getObjectMapper();
    }

    @Override
    public ObjectMapper get() {
        return getObjectMapper();
    }
}