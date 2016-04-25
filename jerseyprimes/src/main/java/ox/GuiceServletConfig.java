/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceServletConfig extends GuiceServletContextListener {


    @Override
    protected Injector getInjector() {

        Injector injector = Guice.createInjector(
                new GuicePrimeModule(),
                new JerseyRestServletModule()
        );
        return injector;
    }
}