package ucom.py.controllers;

import java.util.HashMap;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ucom.py.entities.HolaMundo;

@Path("/recursos-basicos")
public class RecursosBasicos {

    // Creo un método GET que retorna un mensaje de texto plano como prueba.
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String recursoBasicoTest() {
        return "Se crea un nuevo recurso en OpenApi";
    }

    // Creo un método GET que retorna la suma de 5 + 5 como texto plano.
    @GET
    @Path("suma")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer suma() {
        return 5 + 5;
    }

    // Creo un método GET que retorna la resta de 100 - 5 como texto plano.
    @GET
    @Path("resta")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer resta() {
        return 100 - 5;
    }

    // Creo un método POST que recibe un HashMap en formato JSON y lo retorna tal cual.
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Object> respuestaPost(HashMap<String, Object> param) {
        // Imprimo en consola el valor asociado a la clave "joni".
        System.out.println(param.get("joni"));
        return param;
    }

    // Creo un método POST que recibe un objeto HolaMundo en formato JSON y lo retorna.
    @POST
    @Path("hola")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HolaMundo holaMundo(HolaMundo param) {
        // Imprimo en consola el objeto HolaMundo recibido.
        System.out.println(param);
        return param;
    }

}
