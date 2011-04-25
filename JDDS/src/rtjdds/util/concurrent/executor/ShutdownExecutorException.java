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

/**
 * This exception is thrown by an {@link Executor} when its
 * <code>execute()</code> method is invoked after it has been shut
 * down.
 *
 * @author <a href="mailto:corsaro@cse.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class ShutdownExecutorException extends Exception {

    public ShutdownExecutorException() {
        super();
    }

    public ShutdownExecutorException(String msg) {
        super(msg);
    }
}
