/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package taba.taskmanager.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    //enter this value within a custom header called "key"
    private String apiKey = "14c2529eb4498c5d1ffd6915d05bf58a91bdda796af59f41d480d11c099d0479";
    //this variable needs to be static because each time a request is sent, a new instance of this class is being made
    //static ensures that this object is synchronised with all instances of this class (all instances use the same object)
    private static ProjectHandler pHandler = new ProjectHandler(true);
    
    //Response data type is used to ensure server responds according to different situations (404, 403, etc.)
    //Response build() seems to be unable to convert List types to xml by default hence all data is returned as JSON
    
    //gets all projects
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
    
    //gets all tasks
    @GET
    @Path("/json/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTasks(@HeaderParam("key") String key)
    {
        if (apiKey.equals(key))
        {
            return Response.ok(pHandler.getAllTasks()).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //gets all pending tasks
    @GET
    @Path("/json/tasks/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasksByPendingStatus(@HeaderParam("key") String key)
    {
        if (apiKey.equals(key))
        {
            return Response.ok(pHandler.getTaskByStatus("pending")).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //gets all completed tasks
    @GET
    @Path("/json/tasks/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasksByCompletedStatus(@HeaderParam("key") String key)
    {
        if (apiKey.equals(key))
        {
            return Response.ok(pHandler.getTaskByStatus("completed")).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //gets project by Id
    @GET
    @Path("/json/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject(@HeaderParam("key") String key, @PathParam("projectId") String id)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                return Response.ok(project).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //gets tasks by project id
    @GET
    @Path("/json/{projectId}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectTasks(@HeaderParam("key") String key, @PathParam("projectId") String id)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                return Response.ok(taskHandler.getAllTasks()).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //gets task by project id and task id
    @GET
    @Path("/json/{projectId}/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@HeaderParam("key") String key, @PathParam("projectId") String id, @PathParam("taskId") String taskId)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                Task task = project.getHandler().getTask(taskId);
                if (task != null)
                {
                    return Response.ok(task).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //creates new project
    @PUT
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putProject(@HeaderParam("key") String key, Project project)
    {
        if (apiKey.equals(key))
        {
            pHandler.newProject(project);
            return Response.ok(pHandler.getProject(project.getId())).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //updates project name
    @PUT
    @Path("/json/{projectId}/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectName(@HeaderParam("key") String key, @PathParam("projectId") String id, Project name)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                project.setName(name.getName());
                return Response.ok(project).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //updates project description
    @PUT
    @Path("/json/{projectId}/description")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectDesciption(@HeaderParam("key") String key, @PathParam("projectId") String id, Project description)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                project.setDescription(description.getDescription());
                return Response.ok(project).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //creates task
    @PUT
    @Path("/json/{projectId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTask(@HeaderParam("key") String key, @PathParam("projectId") String id, Task task)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                final String taskId = task.getId();
                final String name = task.getName();
                final String description = task.getDescription();
                
                taskHandler.addTask(taskId, name, description);
                
                return Response.ok(taskHandler.getTask(taskId)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    //updates task name
    @PUT
    @Path("/json/{projectId}/{taskId}/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTaskName(@HeaderParam("key") String key, @PathParam("projectId") String id, @PathParam("taskId") String taskId, Task task)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                if (taskHandler.updateName(taskId, task.getName()))
                {
                    return Response.ok(taskHandler.getTask(taskId)).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //updates task description
    @PUT
    @Path("/json/{projectId}/{taskId}/description")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTaskDescription(@HeaderParam("key") String key, @PathParam("projectId") String id, @PathParam("taskId") String taskId, Task task)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                if (taskHandler.updateDescription(taskId, task.getDescription()))
                {
                    return Response.ok(taskHandler.getTask(taskId)).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //sets task status to completed
    @PUT
    @Path("/json/{projectId}/{taskId}/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeTask(@HeaderParam("key") String key, @PathParam("projectId") String id, @PathParam("taskId") String taskId)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(id)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                Task task;
                if ((task = taskHandler.getTask(taskId)) != null)
                {
                    if(task.getStatus().equalsIgnoreCase("completed"))
                    {
                        return Response.ok("Task already set as complete", MediaType.TEXT_PLAIN).build();
                    }
                    task.markAsDone();
                    return Response.ok(task).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //deletes project
    @DELETE
    @Path("/json/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropProject(@HeaderParam("key") String key, @PathParam("projectId") String id)
    {
        if (apiKey.equals(key))
        {
            if (pHandler.removeProject(id))
            {
                return Response.ok(pHandler.getAllProjects()).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    //deletes task
    @DELETE
    @Path("/json/{projectId}/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropTask(@HeaderParam("key") String key, @PathParam("projectId") String projectId, @PathParam("taskId") String taskId)
    {
        if (apiKey.equals(key))
        {
            Project project;
            if ((project = pHandler.getProject(projectId)) != null)
            {
                TaskHandler taskHandler = project.getHandler();
                if (taskHandler.removeTask(taskId))
                {
                    return Response.ok(project).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
