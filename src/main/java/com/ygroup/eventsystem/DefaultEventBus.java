/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem;

import com.ygroup.eventsystem.dispatcher.EventDispatcher;

/**
 *
 * @author joshua
 */
public class DefaultEventBus implements EventBus
{
    private EventDispatcher eventDispatcher;

    public void setEventDispatcher(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void publishEvent(Object event)  throws Exception
    {
        this.eventDispatcher.dispatchEvent(event);
    }

    @Override
    public void destroy()
    {
        this.eventDispatcher.destroy();
    }
}
