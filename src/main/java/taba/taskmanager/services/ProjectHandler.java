/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taba.taskmanager.services;

import jakarta.xml.bind.annotation.XmlRootElement;
import taba.taskmanager.models.Project;
import java.util.ArrayList;
import java.util.List;
import taba.taskmanager.models.Task;

/**
 *
 * @author domas
 */
@XmlRootElement
public class ProjectHandler
{
    private ArrayList<Project> projects = new ArrayList<Project>();
    
    public ProjectHandler()
    {}
    
    public ProjectHandler(boolean addTestValues)
    {
        if (addTestValues)
        {
            test();
        }
    }
    
    //adds some projects for testing so that server is not empty
    private void test()
    {
        addProject("p1", "project 1", "test");
        addProject("p2", "project 2", "test");
        
        Project p1 = getProject("p1");
        taba.taskmanager.services.TaskHandler th = p1.getHandler();
        
        th.addTask("t1", "task 1", "another test");
        th.addTask("t2", "task 2", "another test");
        th.addTask("t3", "task 3", "another test");
        
        System.out.println("being called");
    }
    
    private void addProject(String id, String name, String description)
    {
        Project newProject = new Project(id, name, description);
        
        if(!handleDuplicates(newProject))
        {
            projects.add(newProject);
        }
    }
    
    public void newProject(Project project)
    {
        //used constants to prevent data from being stored as a reference
        //this is mostly a failsafe measure
        final String id = project.getId();
        final String name = project.getName();
        final String description = project.getDescription();
        addProject(id, name, description);
    }
    
    //returns true if a duplicate is found and replaced, false if no duplicate is found
    private boolean handleDuplicates(Project newProject)
    {
        if (projects.size() < 1)
        {
            return false;
        }
        String newId = newProject.getId();
        
        for (int i = 0; i < projects.size(); i++)
        {
            Project project = projects.get(i);
            String id = project.getId();
            
            if (newId.equals(id))
            {
                projects.set(i, newProject);
                return true;
            }
        }
        
        return false;
    }
    
    //get project by id
    private Project search(String id)
    {
        for (Project project : projects)
        {
            if (project.getId().equals(id))
            {
                return project;
            }
        }
        
        return null;
    }
    
    public Project getProject(String id)
    {
        return search(id);
    }
    
    public boolean updateName(String id, String newName)
    {
        Project project;
        if ((project = search(id)) != null)
        {
            project.setName(newName);
            return true;
        }
        return false;
    }
    
    public boolean updateDescription(String id, String newDescription)
    {
        Project project;
        if ((project = search(id)) != null)
        {
            project.setDescription(newDescription);
            return true;
        }
        return false;
    }
    
    public boolean removeProject(String id)
    {
        Project project;
        if ((project = search(id)) != null)
        {
            projects.remove(project);
            return true;
        }
        return false;
    }
    
    public List<Project> getAllProjects()
    {
        return projects;
    }
    
    public List<Task> getAllTasks()
    {
        List<Task> allTasks = new ArrayList<>();
        for (Project project : projects)
        {
            TaskHandler th = project.getHandler();
            
            List<Task> tasks = th.getAllTasks();
            
            allTasks.addAll(tasks);
        }
        
        return allTasks;
    }
    
    public List<Task> getTaskByStatus(String status)
    {
        List<Task> allTasks = getAllTasks();
        
        List<Task> newTasks = new ArrayList<>();
        
        for (Task task : allTasks)
        {
            String taskStatus = task.getStatus();
            
            if (taskStatus.equalsIgnoreCase(status))
            {
                newTasks.add(task);
            }
        }
        
        return newTasks;
    }
}
