/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.annotation.processor;

import java.lang.reflect.Method;

/**
 *
 * @author joshua
 */
public class EventHandlerInfo
{
    private Object instance;
    private Method method;
    boolean async;
    private Class eventType;

    public EventHandlerInfo(Object instance, Method method, boolean async, Class eventType)
    {
        this.instance = instance;
        this.method = method;
        this.async = async;
        this.eventType = eventType;
    }

    public boolean isAsync()
    {
        return async;
    }

    public Class getEventType()
    {
        return eventType;
    }

    public Object getInstance()
    {
        return instance;
    }

    public Method getMethod()
    {
        return method;
    }

    public boolean canHandleEvent(Class eventType)
    {
        return this.eventType.isAssignableFrom(eventType);
    }

    public boolean isSameAs(EventHandlerInfo handlerInfo)
    {
        return this.eventType.getName().equals(handlerInfo.getEventType().getName())
                && this.method.getName().equals(handlerInfo.getMethod().getName())
                && this.getInstance().getClass().getName().equals(handlerInfo.getInstance().getClass().getName());
    }

    @Override
    public String toString()
    {
        return "EventHandlerInfo{" + "instance=" + instance.getClass().getName() + ", method=" + method + ", async=" + async + ", eventType=" + eventType + '}';
    }
}
