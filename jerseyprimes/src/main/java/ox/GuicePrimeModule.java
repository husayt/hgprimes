/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox;

import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ox.base.JacksonConfigurator;
import ox.base.handlebars.HandlebarsTemplateProcessor;
import ox.base.jersey.JacksonMessageBodyProvider;
import lombok.extern.slf4j.Slf4j;
import ox.calc.IPrimeGen;
import ox.calc.PrimeGenDic;
import ox.calc.PrimeGenSet;


@Slf4j
public class GuicePrimeModule extends AbstractModule {
    /* (non-Javadoc)
    * @see com.google.inject.AbstractModule#configure()
    */
    @Override
    protected void configure() {

        log.info("Registering Guice");


        this.requestStaticInjection(BaseUtils.class);
        bind(IPrimeGen.class)
                .annotatedWith(Names.named("PrimeDictionary"))
                .to(PrimeGenDic.class);
        bind(IPrimeGen.class)
                .annotatedWith(Names.named("PrimeSet"))
                .to(PrimeGenSet.class);

        this.bind(JacksonConfigurator.class).in(Singleton.class);
        this.bind(JacksonMessageBodyProvider.class).in(Singleton.class);
        this.bind(JacksonJaxbXMLProvider.class).in(Singleton.class);
        this.bind(HandlebarsTemplateProcessor.class).in(Singleton.class);


    }


}
