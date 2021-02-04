/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.session.MedicoFacade;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Personal
 */
@Path("Medico")
public class MedicoRest {
    @EJB
    private MedicoFacade medicoFacade;
    
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public String findAll() {
        return "---------------------";
    }
    /*
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Medico findById(@PathParam("id") Integer id) {
        return medicoFacade.find(id);
    }

    //BORRAR UN ID ESPECIFICO DE LA BASE DE DATOS
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        medicoFacade.remove(medicoFacade.find(id));
        return Response.ok("DATO eliminado",MediaType.APPLICATION_JSON).build();
        
    }

    //INSERTAR UN ELEMENTO EN LA BASE DE DATOS
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Medico medico) {
        medicoFacade.create(medico);
 
        return Response.ok("DATO INGRESADO",MediaType.APPLICATION_JSON).build();
    }

    //ACTUALIZAR UN ID ESPECIFICO DE LA BASE DE DATOS
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Medico medico) 
    {
        List<Medico> found = this.findAll().stream().filter(x -> medico.getId() == x.getId()).collect(Collectors.toList());
        
        if(found.isEmpty()) 
                    return Response.status(Status.BAD_REQUEST).entity("Registro no encontrado").build();
        
        medicoFacade.edit(found.get(0));
        
        return Response.ok("DATO ACTUALIZADO",MediaType.APPLICATION_JSON).build();
    }
*/
}
