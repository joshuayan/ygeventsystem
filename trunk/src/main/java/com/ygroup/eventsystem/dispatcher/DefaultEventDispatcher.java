/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.dispatcher;

import com.ygroup.eventsystem.annotation.processor.EventHandlerInfo;
import com.ygroup.eventsystem.executor.EventTask;
import com.ygroup.eventsystem.executor.EventTaskExecutor;
import com.ygroup.eventsystem.registry.EventHandlerRegistry;
import java.util.List;

/**
 *
 * @author joshua
 */
public class DefaultEventDispatcher implements EventDispatcher
{
    private EventHandlerRegistry eventHandlerRegistry;
    private EventTaskExecutor syncEventTaskExecutor;
    private EventTaskExecutor asyncEventTaskExecutor;

    public void setAsyncEventTaskExecutor(EventTaskExecutor asyncEventTaskExecutor)
    {
        this.asyncEventTaskExecutor = asyncEventTaskExecutor;
    }

    public void setEventHandlerRegistry(EventHandlerRegistry eventHandlerRegistry)
    {
        this.eventHandlerRegistry = eventHandlerRegistry;
    }

    public void setSyncEventTaskExecutor(EventTaskExecutor syncEventTaskExecutor)
    {
        this.syncEventTaskExecutor = syncEventTaskExecutor;
    }

    @Override
    public void dispatchEvent(Object event)
    {
        if (event != null)
        {
            List<EventHandlerInfo> handlerInfos = eventHandlerRegistry.findEventHandlers(event.getClass());
            for (EventHandlerInfo eventHandlerInfo : handlerInfos)
            {
                dispatchEvent(event, eventHandlerInfo);
            }
        }
    }

    private void dispatchEvent(Object event, EventHandlerInfo handlerInfo)
    {
        EventTask task = new EventTask(handlerInfo.getInstance(), handlerInfo.getMethod(), event);
        if (handlerInfo.isAsync())
        {
            asyncEventTaskExecutor.execute(task);
        } else
        {
            syncEventTaskExecutor.execute(task);
        }

    }

    @Override
    public void destroy()
    {
        this.eventHandlerRegistry.destroy();
        this.syncEventTaskExecutor.destroy();
        this.asyncEventTaskExecutor.destroy();
    }
}
