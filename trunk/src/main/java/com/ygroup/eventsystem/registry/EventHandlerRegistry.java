/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.registry;

import com.ygroup.eventsystem.annotation.processor.EventHandlerInfo;
import java.util.List;

/**
 *
 * @author joshua
 */
public interface EventHandlerRegistry
{

    List<EventHandlerInfo> findEventHandlers(Class eventType);

    void registerEventHandler(Object handlerObject);
    
    void destroy();
}
