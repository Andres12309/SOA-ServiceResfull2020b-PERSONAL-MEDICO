/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entidades.Medico;
import com.mycompany.session.MedicoFacade;
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
@Path("Medico")
public class MedicoRest {
    @EJB
    private MedicoFacade medicoFacade;
    
    
     //VAMOS A TRAER LOS DATOS DE LA TABLA DE LA BDD
    @GET
    //CON UN FORMATO JSON
    @Produces({MediaType.APPLICATION_JSON})
    public List<Medico> findAll() {
        return medicoFacade.findAll();
    }
    
    //TRAER UN ID ESPECIFICO DE LA BASE DE DATOS
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
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Medico medico) 
    {
        Medico found = null;
        for (int i = 0; i < findAll().size(); i++) {
            if (findAll().get(i).getId() == id) {
                found = medicoFacade.findAll().get(i);
            }
        }
 
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("Medico no encontrado").build();
        } else {
            found.setApellido(medico.getApellido());
            found.setDireccion(medico.getDireccion());
            found.setEdad(medico.getEdad());
            found.setEmail(medico.getEmail());
            found.setEspecialidad(medico.getEspecialidad());
            found.setFechaIngreso(medico.getFechaIngreso());
            found.setFoto(medico.getFoto());
            found.setNombre(medico.getNombre());
            found.setTelefono(medico.getTelefono());
            return Response.ok(found).build();
        }
    }
}
