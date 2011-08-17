/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.registry;

import com.ygroup.eventsystem.annotation.processor.EventHandlerAnnotationProcessor;
import com.ygroup.eventsystem.annotation.processor.EventHandlerInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author joshua
 */
public class DefaultEventHandlerRegistry implements EventHandlerRegistry
{
    final private static Logger logger = LoggerFactory.getLogger(DefaultEventHandlerRegistry.class);
    final private Map<Class, List<EventHandlerInfo>> eventHandlerCache = new ConcurrentHashMap<Class, List<EventHandlerInfo>>();
    final private Set<EventHandlerInfo> eventHandlerInfos = new HashSet<EventHandlerInfo>();

    @Override
    public void registerEventHandler(Object handlerObject)
    {
        EventHandlerInfo[] handlerInfos = EventHandlerAnnotationProcessor.process(handlerObject);
        if (handlerInfos != null)
        {
            eventHandlerCache.clear();
            for (EventHandlerInfo handlerInfo : handlerInfos)
            {
                registerEventHandlerInfo(handlerInfo);
            }
        }
    }

    private void registerEventHandlerInfo(EventHandlerInfo handlerInfo)
    {
        logger.info("registering event handler {}", handlerInfo);
        boolean registered = false;
        for (EventHandlerInfo eventHandlerInfo : eventHandlerInfos)
        {
            if (eventHandlerInfo.isSameAs(handlerInfo))
            {
                registered = true;
                logger.warn("hander {} was registered. ignored", handlerInfo);
                break;
            }
        }
        if (!registered)
        {
            eventHandlerInfos.add(handlerInfo);
            logger.info("hander {} is registered OK.", handlerInfo);
        }
    }

    @Override
    public List<EventHandlerInfo> findEventHandlers(Class eventType)
    {
        List<EventHandlerInfo> tmpResult = new ArrayList<EventHandlerInfo>();
        if (eventHandlerCache.containsKey(eventType))
        {
            logger.info("find eventhandler from cache for event {}", eventType);
            tmpResult = eventHandlerCache.get(eventType);
        } else
        {
            for (EventHandlerInfo handlerInfo : eventHandlerInfos)
            {
                if (handlerInfo.canHandleEvent(eventType))
                {
                    logger.info("find eventhandler {} for event {}", handlerInfo, eventType);
                    tmpResult.add(handlerInfo);
                }
            }
            logger.info("add event handler for event {} to cache.", eventType);
            eventHandlerCache.put(eventType, tmpResult);
        }


        return Collections.unmodifiableList(tmpResult);
    }

    @Override
    public void destroy()
    {
        this.eventHandlerCache.clear();
        this.eventHandlerInfos.clear();
    }
}
