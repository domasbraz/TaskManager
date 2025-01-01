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
    
    public TaskHandler()
    {}
    
    public TaskHandler(boolean addTestValue)
    {
        if (addTestValue)
        {
            test();
        }
    }
    
    public void test()
    {
        addTask("1", "test", "test");
        addTask("2", "test", "test");
        addTask("3", "test", "test");
    }
    
    public void addTask(String id, String name, String description)
    {
        Task newTask = new Task(id, name, description);
        
        if (!handleDuplicates(newTask))
        {
            tasks.add(newTask);
        }
    }
    
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
    
    public List<Task> getAllTasks()
    {
        return tasks;
    }
}
