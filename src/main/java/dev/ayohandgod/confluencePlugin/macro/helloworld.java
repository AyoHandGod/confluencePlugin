package dev.ayohandgod.confluencePlugin.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

// imports required for spring scanner and PageBuilderService to use CSS
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.webresource.api.assembler.PageBuilderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Scanned
public class helloworld implements Macro {

    private PageBuilderService pageBuilderService;

    @Autowired
    public helloworld(@ComponentImport PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }

    @Override
    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        pageBuilderService.assembler().resources()
                .requireWebResource("dev.ayohandgod.confluencePlugin:confluencePlugin-resources");
        String output = "<div class =\"helloworld\">";
        output = output + "<div class = \"" + map.get("Color") + "\">";
        if (map.get("Name") != null) {
            output = output + ("<h1>Hello " + map.get("Name") + "!</h1>");
        } else {
            output = output + "<h1>Hello World!</h1>";
        }
        output = output + "</div>" + "</div>";
        return output;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.NONE;
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}
