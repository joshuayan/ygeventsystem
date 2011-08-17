/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem;

import com.ygroup.eventsystem.annotation.EventHandle;

/**
 *
 * @author joshua
 */
public class TestHandler
{
    @EventHandle
    public void testEvent(TestEvent event)
    {
        System.out.println("Testhandler," + event.getMessage());
    }
}
