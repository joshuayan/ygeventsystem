package com.ygroup.eventsystem;

import com.ygroup.eventsystem.DefaultEventBus;
import com.ygroup.eventsystem.dispatcher.DefaultEventDispatcher;
import com.ygroup.eventsystem.executor.AsyncEventTaskExecutor;
import com.ygroup.eventsystem.executor.EventTaskExecutor;
import com.ygroup.eventsystem.executor.SyncEventTaskExecutor;
import com.ygroup.eventsystem.registry.DefaultEventHandlerRegistry;
import com.ygroup.eventsystem.registry.EventHandlerRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    public static void main(String[] a)
    {
        EventHandlerRegistry register = new DefaultEventHandlerRegistry();
        DefaultEventDispatcher dispatcher = new DefaultEventDispatcher();
        EventTaskExecutor syncExecutor = new SyncEventTaskExecutor();
        EventTaskExecutor asyncExecutor = new AsyncEventTaskExecutor();
        dispatcher.setAsyncEventTaskExecutor(asyncExecutor);
        dispatcher.setSyncEventTaskExecutor(syncExecutor);
        dispatcher.setEventHandlerRegistry(register);
        DefaultEventBus bus = new DefaultEventBus();
        bus.setEventDispatcher(dispatcher);
        TestHandler handler = new TestHandler();
        register.registerEventHandler(handler);
        TestEvent event = new TestEvent("this a test message");
        try
        {
            bus.publishEvent(event);
        } catch (Exception ex)
        {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
