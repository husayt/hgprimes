/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox.base.handlebars;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.ValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;

@Slf4j
public final class HandlebarsUtils {

    private HandlebarsUtils() {
    }

    //    OxMapValueResolver.INSTANCE,
    private static final ValueResolver[] valueResolvers = { MapValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE,};

    private static final Supplier<Handlebars> hs = Suppliers.memoize(new Supplier<Handlebars>() {
        @Override
        public Handlebars get() {
            return buildHbs();
        }
    });


    public static Handlebars getHandlebars() {
        return hs.get();
    }


    public static Handlebars buildHbs() {
        FileTemplateLoader loader = new FileTemplateLoader("WEB-INF/hbs", "");

        return buildHbs(loader);
    }

    public static Handlebars buildHbs(TemplateLoader loader) {
        Handlebars hbs = new Handlebars(loader);
        hbs.handlebarsJsFile("handlebars.min.js");

        return hbs;
    }

    public static String runHandlebarsTemplateInline(String templateInlineSource, Object data) throws IOException {


        return getHandlebars().compileInline(templateInlineSource).apply(Context.newBuilder(data)
                .resolver(valueResolvers)
                .build());
    }

    public static String tryRunHandlebarsTemplateInline(String templateInlineSource, Object data) {
        String out;

        try {
            out = runHandlebarsTemplateInline(templateInlineSource, data);
        } catch (IOException e) {
            out = "...";
            log.error("Problem while running inline hbs template", e);
        }

        return out;
    }

    public static com.github.jknack.handlebars.Template compileTemplateInline(String templateInlineSource) throws IOException {
        return getHandlebars().compileInline(templateInlineSource);
    }

    public static String runCompiledTemplate(com.github.jknack.handlebars.Template template, Object data) throws IOException {
        return template.apply(Context.newBuilder(data)
                .resolver(valueResolvers)
                .build());
    }

    public static void runCompiledTemplate(com.github.jknack.handlebars.Template template, Object data, Writer writer) throws IOException {
        template.apply(Context.newBuilder(data)
                .resolver(valueResolvers)
                .build(), writer);
    }

    public static String runHandlebarsTemplate(String pathToTemplate, Object data) {
        //todo we can use handlebars maven plugin to precompile templates in Web-Inf
        try {
            return getHandlebars().compile(pathToTemplate).apply(
                    Context.newBuilder(data)
                            .resolver(valueResolvers)
                            .build()
            );
        } catch (IOException iex) {
            log.error("Error generating template: ", iex);
            return "";
        }
    }


}
