/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.entidades.Personal;
import com.mycompany.session.PersonalFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Personal
 */
@Path("Personal")
public class PersonalRest {
    
    @EJB
    private PersonalFacade personalFacade;
    private List<Personal> personal = new ArrayList<>();
    
     //VAMOS A TRAER LOS DATOS DE LA TABLA DE LA BDD
    @GET
    //CON UN FORMATO JSON
    @Produces({MediaType.APPLICATION_JSON})
    public List<Personal> findAll() {
        return personalFacade.findAll();
    }
    
    //TRAER UN ID ESPECIFICO DE LA BASE DE DATOS
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Personal findById(@PathParam("id") Integer id) {
        return personalFacade.find(id);
    }

    //BORRAR UN ID ESPECIFICO DE LA BASE DE DATOS
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        personalFacade.remove(personalFacade.find(id));
        return Response.ok("DATO ELIMINADO",MediaType.APPLICATION_JSON).build();
    }

    //INSERTAR UN ELEMENTO EN LA BASE DE DATOS
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Personal personal) {
        personalFacade.create(personal);
        return Response.ok("Dato ingresado",MediaType.APPLICATION_JSON).build();
    }

    //ACTUALIZAR UN ID ESPECIFICO DE LA BASE DE DATOS
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Personal personal) {
        List<Personal> found = this.findAll().stream().filter(x -> personal.getId() == x.getId()).collect(Collectors.toList());
        
        if(found.isEmpty()) 
                    return Response.status(Status.BAD_REQUEST).entity("Registro no encontrado").build();
        
        personalFacade.edit(found.get(0));
        return Response.ok("DATO ACTUALIZADO",MediaType.APPLICATION_JSON).build();
    }
}
