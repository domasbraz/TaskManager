/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package taba.taskmanager.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
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
    ProjectHandler pHandler = new ProjectHandler(true);
    
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Project> getXml()
    {
        return pHandler.getAllProjects();
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
