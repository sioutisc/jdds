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

// -- jTools Import --
import jtools.util.Assert;

/**
 * The class <code>NaturalValidator</code> can be used to validate
 * command line argument values which are expected to be non negative
 * Integer i.e. a Natural number.
 *
 * @author <a href="mailto:corsaro@doc.cs.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class NaturalValidator implements Validator {

    public Object validate(String argValue) throws InvalidArgumentValueException {
        try {
            int val = Integer.parseInt(argValue);
            Assert.postCondition(val >= 0);
            return new Integer(val);
        }
        catch (Exception e) {
            throw new InvalidArgumentValueException(argValue + " is not an non-negative Integer!");
        }
    }
}
