package lesson5.practice.userSaver;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class TemplateEngine {
    private final Configuration config;

    public TemplateEngine(String root_location) throws IOException {
        this.config = new Configuration(Configuration.VERSION_2_3_30){{
            setDirectoryForTemplateLoading(new File(root_location));
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public static TemplateEngine folder(String root_location) throws IOException {
        return new TemplateEngine(root_location);
    }

    public void render(String templateFile, HashMap<String, Object> data, HttpServletResponse resp){
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try (PrintWriter w = resp.getWriter()){
            config.getTemplate(templateFile).process(data,w);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("Freemarker error", e);
        }
    }
}