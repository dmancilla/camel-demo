package cl.dman.camel;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioRouteTest extends CamelTestSupport {
    private final String ORIGEN_URI = "direct:in";

    @Produce(uri = ORIGEN_URI)
    private ProducerTemplate directIn;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new UsuarioRoute(ORIGEN_URI);
    }

    @Test
    public void simpleTestUsuario() {
        //Arrange
        UsuarioRequest u = new UsuarioRequest("david.mancilla@axity.com", "David Mancilla");

        //Act
        UsuarioRespuesta respuesta = directIn.requestBody(u, UsuarioRespuesta.class);

        //Assert
        Assert.assertEquals("Usuario david.mancilla@axity.com agregado correctamente", respuesta.getMensaje());
    }

}