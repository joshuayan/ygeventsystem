/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.dispatcher;

/**
 *
 * @author joshua
 */
public interface EventDispatcher
{
    void dispatchEvent(Object event)  throws Exception;

    void destroy();
}
