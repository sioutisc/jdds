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

import javax.realtime.Schedulable;

/**
 * This abstract class defines the basic protocol supported by executors.
 *
 * @author <a href="mailto:corsaro@cse.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public interface Executor /*extends Schedulable*/ {

    /**
     * Executes the given logic. If the logic is executed on a newly
     * created thread, or of a thread is borrowed from a pool is
     * implementation dependent.
     *
     * @param logic a <code>Runnable</code> value
     * @exception ShutdownExecutorException if the
     * <code>Executor</code> has already been shut down.
     */
    void execute(Runnable logic) throws ShutdownExecutorException;

    /**
     * Releases all the resources assoiated with the executor. No
     * subsequent invocation of the <code>execute()</code> method
     * should be performed after the executor has been shutdown.
     *
     */
    void shutdown();

}
