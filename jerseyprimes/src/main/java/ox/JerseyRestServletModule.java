/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox;


import com.google.common.collect.Maps;
import com.google.inject.Singleton;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import ox.servlet.GuestbookServlet;
import ox.servlet.SignGuestbookServlet;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class JerseyRestServletModule extends JerseyServletModule {


    void registerResources() {
        this.bind(WebResource.class);
    }

    public static final String mainFilterPath = "/((?!(_ah|old1|WEB-INF))|_ah/queue).*";


    public void bindServlets() {
        this.bind(SignGuestbookServlet.class).in(Singleton.class);
    }

    @Override
    protected void configureServlets() {
        log.info("Registering Jersey");

        this.bindServlets();
        this.registerResources();
        super.configureServlets();
        this.registerFilters();


        this.filterRegex(mainFilterPath).through(GuiceContainer.class, getJerseyParams());
        log.info("Guice Configuration Finished");
    }

    private void registerFilters() {


        this.serve("/old1/*").with(GuestbookServlet.class);
        this.serve("/old2/*").with(SignGuestbookServlet.class);
        //serve("/appstats/*").with(AppstatsServlet.class);
    }

    private static Map<String, String> getJerseyParams() {
        Map<String, String> params = Maps.newHashMap();

        params.put(ServletContainer.JSP_TEMPLATES_BASE_PATH, "/WEB-INF/jsp");

        params.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "/WEB-INF/.*");
        params.put(ResourceConfig.FEATURE_IMPLICIT_VIEWABLES, "true");

        // This allows us to use the Java security @RolesAllowed Annotations to secure resources and methods
        params.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory");

        params.put(PackagesResourceConfig.FEATURE_DISABLE_WADL, String.valueOf(true));
        return params;
    }
}
