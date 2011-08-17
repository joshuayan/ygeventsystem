/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.annotation.processor;

import com.ygroup.eventsystem.annotation.EventHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author joshua
 */
public class EventHandlerAnnotationProcessor
{
    final private static Logger logger = LoggerFactory.getLogger(EventHandlerAnnotationProcessor.class);

    public static EventHandlerInfo[] process(Object handlerObject)
    {
        EventHandlerInfo[] result = new EventHandlerInfo[0];
        if (handlerObject != null)
        {
            Class clazz = handlerObject.getClass();
            logger.info("process class {}", clazz.getName());
            Method[] methods = clazz.getDeclaredMethods();
            List<Method> candidateMethods = new ArrayList<Method>();
            for (Method method : methods)
            {
                if (method.isAnnotationPresent(EventHandler.class) && method.getParameterTypes().length == 1)
                {
                    logger.info("add candidate method {}", method.getName());
                    candidateMethods.add(method);
                }
            }

            List<EventHandlerInfo> handlerInfos = new ArrayList<EventHandlerInfo>();
            for (Method method : candidateMethods)
            {
                EventHandler handler = method.getAnnotation(EventHandler.class);
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1)
                {
                    logger.info("add handlerinfo for method {} with parameter type {}", method.getName(), parameterTypes[0].getName());
                    EventHandlerInfo handlerInfo = new EventHandlerInfo(handlerObject, method, handler.async(), parameterTypes[0]);
                    handlerInfos.add(handlerInfo);
                }
            }
            result = handlerInfos.toArray(result);
        }
        return result;
    }
}
