/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ylhome.eventsystem;

import com.ygroup.eventsystem.annotation.EventHandler;

/**
 *
 * @author joshua
 */
public class TestHandler
{
    @EventHandler
    public void testEvent(TestEvent event)
    {
        System.out.println("Testhandler," + event.getMessage());
    }
}
