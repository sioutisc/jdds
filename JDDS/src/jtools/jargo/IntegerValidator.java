// ************************************************************************
//    $Id$
// ************************************************************************
//
//                               jTools
//
//               Copyright (C) 2001-2003 by Angelo Corsaro.
//                         <corsaro@cs.wustl.edu>
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
package jtools.jargo;

/**
 * The class <code>IntegerValidator</code> can be used to validate
 * command line argument values which are expected to be Integer.
 *
 * @author <a href="mailto:corsaro@doc.cs.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class IntegerValidator implements Validator {

    public Object validate(String argValue) throws InvalidArgumentValueException {
        try {
            return new Integer(Integer.parseInt(argValue));
        }
        catch (Exception e) {
            throw new InvalidArgumentValueException(argValue + " is not an Integer!");
        }
    }
}
