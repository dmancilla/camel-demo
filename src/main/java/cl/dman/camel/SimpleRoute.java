package cl.dman.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("servlet://hello").id("hello-java")
            .log("name = ${in.header.name}")
            .setBody(simple("Hello, ${in.header.name} from Java at ${date:now:yyyy/MM/dd HH:mm:ss}! #test"))
            .to("stream://out")
            .setBody(constant("Success!"));

    }
}
