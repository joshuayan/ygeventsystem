/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem;

/**
 *
 * @author joshua
 */
public interface EventBus
{
    void publishEvent(Object event);

    void destroy();
}
