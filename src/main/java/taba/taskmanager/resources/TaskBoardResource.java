/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package taba.taskmanager.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import taba.taskmanager.services.ProjectHandler;
import taba.taskmanager.models.Project;
import taba.taskmanager.services.TaskHandler;
import taba.taskmanager.models.Task;
import java.util.List;

/**
 * REST Web Service
 *
 * @author domas
 */
@Path("taskBoard")
public class TaskBoardResource
{
    private String apiKey = "14c2529eb4498c5d1ffd6915d05bf58a91bdda796af59f41d480d11c099d0479";
    ProjectHandler pHandler = new ProjectHandler(true);
    
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@HeaderParam("key") String key)
    {
        if (apiKey.equals(key))
        {
            List<Project> projects = pHandler.getAllProjects();
            return Response.ok(projects).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    
//    @GET
//    @Path("/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Project> getJson()
//    {
//        return pHandler.getAllProjects();
//    }

    /**
     * PUT method for updating or creating an instance of TaskBoardResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content)
//    {
//    }
}
