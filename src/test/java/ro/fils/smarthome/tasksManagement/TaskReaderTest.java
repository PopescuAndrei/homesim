/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.smarthome.tasksManagement;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ro.fils.smarthome.model.Need;

/**
 *
 * @author Silvia
 */
public class TaskReaderTest {
    
    TaskReader instance;
    
    public TaskReaderTest() {
        instance = new TaskReader("/activitiesSet2.json");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getTasks method, of class TaskReader.
     */
    @Test
    public void testGetTasks() {
        System.out.println("getTasks");
        Collection<ITask> result = instance.getTasks();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getAppliances method, of class TaskReader.
     */
    @Test
    public void testGetAppliances() {
        System.out.println("getAppliances");
        Set<String> result = instance.getAppliances();
        assertTrue(result.size() > 0);
    }
    
}
