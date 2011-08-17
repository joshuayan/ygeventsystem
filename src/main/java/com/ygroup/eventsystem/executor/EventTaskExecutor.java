/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.executor;

/**
 *
 * @author joshua
 */
public interface EventTaskExecutor
{
    void execute(EventTask task);

    void destroy();
}
