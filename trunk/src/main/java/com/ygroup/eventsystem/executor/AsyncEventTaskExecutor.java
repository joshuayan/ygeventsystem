/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygroup.eventsystem.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author joshua
 */
public class AsyncEventTaskExecutor implements EventTaskExecutor
{
    final private static Logger logger = LoggerFactory.getLogger(AsyncEventTaskExecutor.class);
    private Executor executor;

    public void setExecutor(Executor executor)
    {
        this.executor = executor;
    }

    public AsyncEventTaskExecutor()
    {
        this.executor = Executors.newFixedThreadPool(10);
    }

    @Override
    public void execute(EventTask task)
    {
        logger.info("committing async task");
        this.executor.execute(task);
        logger.info("commit async task OK");
    }

    @Override
    public void destroy()
    {        
    }
}
