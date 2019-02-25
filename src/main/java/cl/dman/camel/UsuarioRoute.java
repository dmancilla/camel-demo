package cl.dman.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRoute extends RouteBuilder {

    private final String rutaInicio;

    public UsuarioRoute() {
        this.rutaInicio = "direct:usuarioPost";
    }

    public UsuarioRoute(String rutaInicio) {
        this.rutaInicio = rutaInicio;
    }

    @Override
    public void configure() {
        inicializarRest();

        //Ruta de entrada de usuario
        rest("/usuarios").description("Servicio REST de usuarios")
            .post("/").description("Crear un usuario").type(UsuarioRequest.class)
            .param().name("body").type(RestParamType.body).description("El usuario a crear").endParam()
            .responseMessage().code(HttpStatus.OK.value()).message("Usuario recibido").endResponseMessage()
            .to(rutaInicio);

        //Ruta de proceso de usuario
        from(rutaInicio).id("direct-usuarioPost")
            .log("body: ${body}")
            .convertBodyTo(UsuarioRequest.class)
            .log("body: ${body}")

            .process(exchange -> {
                UsuarioRequest body = exchange.getIn().getBody(UsuarioRequest.class);
                //TODO: Procesar el usuario
                UsuarioRespuesta respuesta = new UsuarioRespuesta("Usuario " + body.getEmail() + " agregado correctamente");
                exchange.getIn().setBody(respuesta);
            })
        ;
    }

    private void inicializarRest() {
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .contextPath("/api")
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "API de Usuario")
            .apiProperty("api.version", "1.0");
    }
}
