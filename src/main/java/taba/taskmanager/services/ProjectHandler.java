/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taba.taskmanager.services;

import jakarta.xml.bind.annotation.XmlRootElement;
import taba.taskmanager.models.Project;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author domas
 */
@XmlRootElement
public class ProjectHandler
{
    private List<Project> projects = new ArrayList<Project>();
    
    public ProjectHandler()
    {}
    
    public ProjectHandler(boolean addTestValues)
    {
        if (addTestValues)
        {
            test();
        }
    }
    
    private void test()
    {
        addProject("p1", "project 1", "test");
        
        Project p1 = getProject("p1");
        taba.taskmanager.services.TaskHandler th = p1.getHandler();
        
        th.addTask("t1", "task 1", "another test");
        th.addTask("t2", "task 2", "another test");
        th.addTask("t3", "task 3", "another test");
    }
    public void addProject(String id, String name, String description)
    {
        Project newProject = new Project(id, name, description);
        
        if(!handleDuplicates(newProject))
        {
            projects.add(newProject);
        }
    }
    
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
}
