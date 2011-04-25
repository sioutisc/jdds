// ************************************************************************
//    $Id$
// ************************************************************************
//
//                                jRate
//
//               Copyright (C) 2001-2004 by Angelo Corsaro.
//                         <corsaro@cse.wustl.edu>
//                          All Rights Reserved.
//
//   Permission to use, copy, modify, and distribute this software and
//   its  documentation for any purpose is hereby  granted without fee,
//   provided that the above copyright notice appear in all copies and
//   that both that copyright notice and this permission notice appear
//   in  supporting  documentation. I don't make  any  representations
//   about the  suitability  of this  software for any  purpose. It is
//   provided "as is" without express or implied warranty.
//
//
// *************************************************************************
//  
// *************************************************************************
package rtjdds.util.concurrent.executor;

import javax.realtime.MemoryParameters;
import javax.realtime.ReleaseParameters;
import javax.realtime.SchedulingParameters;
import javax.realtime.MemoryArea;
import javax.realtime.ProcessingGroupParameters;
import javax.realtime.Scheduler;
import javax.realtime.RealtimeThread;
import javax.realtime.NoHeapRealtimeThread;

import javax.realtime.Schedulable;
import javax.realtime.ScopedMemory;
import javax.realtime.ImmortalMemory;

/**
 * This class implements an {@link Executor} that has permanently a
 * thread bound.
 *
 * @author <a href="mailto:corsaro@cse.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class ThreadBoundExecutor implements Executor {

    protected RealtimeThread thread;
    protected boolean active = false;
    protected ExecutorLogic executorLogic = new ExecutorLogic();
    protected int executionEligibility = -1;
    
    ///////////////////////////////////////////////////////////////////////////
    //                         Inner Classes
    //
    static private class ExecutorLogic implements Runnable {

        private Runnable task;
        private boolean active;
        protected EventVariable taskAvailableEvent;
        protected EventVariable executorIdleEvent;
        protected boolean isIdle;
        
        ExecutorLogic() {
            active = true;
            this.taskAvailableEvent = new EventVariable();
            this. executorIdleEvent =
                new EventVariable(true); // Create a "signaled" event
            this.isIdle = true;
        }
        
        void shutdown() {
            if (this.active()) {
                final ExecutorLogic logic = this;
                Runnable shutDownLogic = new Runnable() {
                        public void run() {
                            logic.active(false);
                        }
                    };
                try {
                    this.execute(shutDownLogic);
                } catch (ShutdownExecutorException e) {
                    e.printStackTrace();
                }
                shutDownLogic = null;
            }
        }
        
        void  execute(Runnable task) throws ShutdownExecutorException {
            //            System.out.println(">> ExecutorLogic.execute()");
            if (!this.active())
                throw new ShutdownExecutorException(">> Unable to execute logic, ThreadBoundExecutor" +
                                                    "has been already shut down");
            try {
                //                System.out.println(">> ExecutorLogic: Waiting for Idle Event");
                executorIdleEvent.await();
                this.isIdle(false);
                if (!this.active())
                    throw new ShutdownExecutorException(">> Unable to execute logic, ThreadBoundExecutor" +
                                                        "has been already shut down");
                this.task = task;
                //                System.out.println(">> ExecutorLogic: Signaling Available Task Event");
                taskAvailableEvent.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (this.active) {
                    //                    System.out.println("#>> ExecutorLogic: Waiting for some Task");
                    taskAvailableEvent.await();
                    //                    System.out.println("#>> ExecutorLogic: Running Task " + this.task);                    
                    this.task.run();
                    //                    System.out.println("#>> ExecutorLogic: Signaling Idle Event");
                    executorIdleEvent.signal();
                    this.isIdle(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized boolean active() {
            return this.active;
        }

        synchronized void active(boolean bool) {
            this.active = bool;
        }

        synchronized boolean isIdle() {
            return this.isIdle;
        }

        private synchronized void isIdle(boolean bool) {
            this.isIdle = bool;
        }
        

    }
    //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * Creates a new <code>ThreadBoundExecutor</code> instance with the
     * specified parameters.
     *
     * @param schedulingParam a <code>SchedulingParameters</code> value
     * which will be associated with the constructed instance of this. 
     * If <code>null</code> this will be assigned the reference to the
     * {@link SchedulingParameters} of the current thread.
     * @param releaseParam a <code>ReleaseParameters</code> value
     * which will be associated with the constructed instance of this. 
     * If null this will have no {@link ReleaseParameters}.
     * @param memoryParam a <code>MemoryParameters</code> value which
     * will be associated with the constructed instance of this. If
     * null this will have no {@link MemoryParameters}.
     * @param memoryArea The {@link MemoryArea} for this
     * <code>ThreadBoundExecutor</code>. If null, inherit the current
     * memory area at the time of construction. The initial memory
     * area must be a reference to a {@link ScopedMemory} or {@link
     * ImmortalMemory} object if <code>noheap</code> is
     * <code>true</code>.
     * @param groupParam A {@link ProcessingGroupParameters} object
     * to which this will be associated. If null this will not be
     * associated with any processing group.
     * @param noHeap A flag meaning, when <code>true</code>, that this
     * will have characteristics identical to a {@link
     * NoHeapRealtimeThread}. A false value means this will have
     * characteristics identical to a {@link RealtimeThread}. If
     * <code>true</code> and the current thread is not a {@link
     * NoHeapRealtimeThread} or a {@link RealtimeThread} executing
     * within a {@link ScopedMemory} or {@link ImmortalMemory} scope
     * then an {@link IllegalArgumentException} is thrown.
     */
    public ThreadBoundExecutor(SchedulingParameters schedulingParam,
                               ReleaseParameters releaseParam,
                               MemoryParameters memoryParam,
                               MemoryArea memoryArea,
                               ProcessingGroupParameters groupParam,
                               boolean noHeap) throws IllegalArgumentException {
        if (noHeap)
            this.thread = new NoHeapRealtimeThread(schedulingParam,
                                                   releaseParam,
                                                   memoryParam,
                                                   memoryArea,
                                                   groupParam,
                                                   this.executorLogic);
        else
            this.thread = new RealtimeThread(schedulingParam,
                                             releaseParam,
                                             memoryParam,
                                             memoryArea,
                                             groupParam,
                                             this.executorLogic);
        this.thread.setDaemon(true);
        this.thread.start();
    }

    /**
     * Creates a new <code>ThreadBoundExecutor</code> instance with the
     * specified parameters.
     *
     * @param schedulingParam a <code>SchedulingParameters</code> value
     * which will be associated with the constructed instance of this. 
     * If <code>null</code> this will be assigned the reference to the
     * {@link SchedulingParameters} of the current thread.
     */
    public ThreadBoundExecutor(SchedulingParameters schedulingParam)
        throws IllegalArgumentException
    {
        this(schedulingParam, null, null, null, null, false);
    }

    //
    //   -- javax.realtime.Executor Interface Methods Implementation --
    //
    
    /**
     * Executes the given logic. If the logic is executed on a newly
     * created thread, or of a thread is borrowed from a pool is
     * implementation dependent.
     *
     * @param logic a <code>Runnable</code> value
     * @exception ShutdownExecutorException if the
     * <code>Executor</code> has already been shut down.
     */
    public void execute(Runnable logic) throws ShutdownExecutorException {
        //        System.out.println(">> ThreadBoundExecutor.execute()");
        this.executorLogic.execute(logic);
    }
    
    /**
     * Releases all the resources assoiated with the executor. No
     * subsequent invocation of the <code>execute()</code> method
     * should be performed after the executor has been shutdown.
     *
     */
    public void shutdown() {
        this.executorLogic.shutdown();
    }
    
}
