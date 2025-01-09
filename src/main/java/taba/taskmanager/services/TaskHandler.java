/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taba.taskmanager.services;

import taba.taskmanager.models.Task;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author domas
 */
public class TaskHandler
{
    private List<Task> tasks = new ArrayList<>();
    private String projectId;
    
    public TaskHandler(String projectId)
    {
        this.projectId = projectId;
    }
    
    public void addTask(String id, String name, String description)
    {
        Task newTask = new Task(id, name, description, projectId);
        
        if (!handleDuplicates(newTask))
        {
            tasks.add(newTask);
        }
    }
    
    //returns true if a duplicate is found and replaced, false if no duplicate is found
    private boolean handleDuplicates(Task newTask)
    {
        if (tasks.size() < 1)
        {
            return false;
        }
        String newId = newTask.getId();
        
        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);
            String id = task.getId();
            
            if (newId.equals(id))
            {
                tasks.set(i, newTask);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean removeTask(String id)
    {
        for (Task task : tasks)
        {
            if (task.getId().equals(id))
            {
                tasks.remove(task);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean updateName(String id, String newName)
    {
        for (Task task : tasks)
        {
            if (task.getId().equals(id))
            {
                task.setName(newName);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean updateDescription(String id, String newDescription)
    {
        for (Task task : tasks)
        {
            if (task.getId().equals(id))
            {
                task.setDescription(newDescription);
                return true;
            }
        }
        
        return false;
    }
    
    public Task getTask(String id)
    {
        for (Task task : tasks)
        {
            if (task.getId().equals(id))
            {
                return task;
            }
        }
        
        return null;
    }
    
    public List<Task> getAllTasks()
    {
        return tasks;
    }
}
