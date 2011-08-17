/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author joshua
 */
public class EventTask implements Runnable
{
    final private static Logger logger = LoggerFactory.getLogger(EventTask.class);
    private Object instance;
    private Method handlerMethod;
    private Object event;

    public EventTask(Object instance, Method handlerMethod, Object event)
    {
        this.instance = instance;
        this.handlerMethod = handlerMethod;
        this.event = event;
    }

    public Object getEvent()
    {
        return event;
    }

    public Method getHandlerMethod()
    {
        return handlerMethod;
    }

    public Object getInstance()
    {
        return instance;
    }

    @Override
    public void run()
    {
        try
        {
            logger.info("executing event task");
            this.handlerMethod.invoke(this.instance, this.event);
            logger.info("execut event task OK");
        } catch (IllegalAccessException ex)
        {
            logger.warn(ex.getMessage());
        } catch (IllegalArgumentException ex)
        {
            logger.warn(ex.getMessage());
        } catch (InvocationTargetException ex)
        {
            logger.warn(ex.getMessage());
        }
    }
}
