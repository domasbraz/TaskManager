/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taba.taskmanager.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author domas
 */

@XmlRootElement
@XmlType(propOrder = {"id", "name", "description", "status", "dateStart", "dateFinished", "projectId"})
@JsonbPropertyOrder({"id", "name", "description", "status", "dateStart", "dateFinished", "projectId"})
public class Task
{
    private String id, name, description, status, dateStart, dateFinished, projectId;
    
    public Task()
    {}
    
    public Task(String id, String name, String description, String projectId)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        status = "pending";
        dateStart = LocalDate.now().toString();
        dateFinished = "n/a";
        this.projectId = projectId;
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setDateStart(String dateStart)
    {
        this.dateStart = dateStart;
    }

    public void setDateFinished(String dateFinished)
    {
        this.dateFinished = dateFinished;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }
    
    
    
    public void markAsDone()
    {
        status = "completed";
        dateFinished = LocalDate.now().toString();
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

    public String getStatus()
    {
        return status;
    }

    public String getDateStart()
    {
        return dateStart;
    }

    public String getDateFinished()
    {
        return dateFinished;
    }

    public String getProjectId()
    {
        return projectId;
    }
    
    
}
