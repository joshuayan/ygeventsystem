/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author joshua
 */
public class SyncEventTaskExecutor implements EventTaskExecutor
{
    final private static Logger logger = LoggerFactory.getLogger(SyncEventTaskExecutor.class);

    @Override
    public void execute(EventTask task) throws Exception
    {
        logger.info("executing sync task");
        task.run();
        logger.info("execut sync task OK");
    }

    @Override
    public void destroy()
    {
    }
}
