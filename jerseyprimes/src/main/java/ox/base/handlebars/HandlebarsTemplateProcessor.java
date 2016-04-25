/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox.base.handlebars;

import com.github.jknack.handlebars.Template;
import com.google.common.base.Strings;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.template.ViewProcessor;
import lombok.extern.slf4j.Slf4j;


import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Provider
//@Singleton
@Slf4j
public class HandlebarsTemplateProcessor implements ViewProcessor<Template> {
    //   public static final String HANDLEBARS_TEMPLATES_BASE_PATH = "ox.base.handlebars.templateBasePath";

//    private String basePath = "WEB-INF/hbs";


    //    @Inject
//    @Context ResourceConfig resourceConfig
    public HandlebarsTemplateProcessor() {

        //(String) resourceConfig.getProperties().get(HANDLEBARS_TEMPLATES_BASE_PATH);
//        String path = "";
//        if (!StringUtils.isBlank(path)) {
//            this.basePath = path;
//        }
//        if (this.basePath.charAt(0) != '/') {
//            this.basePath = "/" + basePath;
//        }

//        TemplateLoader loader = new FileTemplateLoader(basePath, "");
//        handlebars = new Handlebars(loader);
    }

    @Override
    public Template resolve(final String name) {
        if (!Strings.isNullOrEmpty(name) && name.endsWith(".hbs")) {
            String templateName = (name.charAt(0) == '/') ? name : ('/' + name);

            try {
                return HandlebarsUtils.getHandlebars().compile(templateName);
            } catch (IOException e) {
                log.warn("handlebars template compile problem", e);
            }
        }
        return null;
    }

    @Override
    public void writeTo(final Template t, final Viewable viewable, final OutputStream out) throws IOException {
        // Commit the status and headers to the HttpServletResponse
        out.flush();
        //final ToolContext context = velocityView.createContext();
//        VelocityContext context = new VelocityContext();
//        context.put("data", viewable.getModel());

        try (Writer oWriter = new OutputStreamWriter(out)) {
            t.apply(viewable.getModel(), oWriter);
        }
    }

}
