/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taba.taskmanager.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.json.bind.annotation.JsonbTransient;
import taba.taskmanager.models.Task;
import taba.taskmanager.services.TaskHandler;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;

/**
 *
 * @author domas
 */
@XmlRootElement
@XmlType(propOrder = {"id", "name", "description", "tasks"})
@JsonbPropertyOrder({"id", "name", "description", "tasks"})
public class Project
{
    private String id, name, description;
    private TaskHandler tHandler;
    
    public Project()
    {}
     
    public Project(String id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        tHandler = new TaskHandler(id);
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void resetTaskHandler()
    {
        tHandler = new TaskHandler(id);
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    public List<Task> getTasks()
    {
        return tHandler.getAllTasks();
    }

    @JsonbTransient
    public TaskHandler getHandler()
    {
        return tHandler;
    }
    
    
}
