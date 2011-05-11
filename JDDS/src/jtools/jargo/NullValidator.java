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
 * This class represents a null validator that is usually associated
 * with command line arguments that accepts or ignore their arguments.
 *
 * @author <a href="mailto:corsaro@doc.cs.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class NullValidator implements Validator {

    /**
     * Validates a string and returns an instance of a type associated
     * with the value.
     *
     * @param value a <code>String</code> representing the value to be
     * validated
     * @return an <code>Object</code> representing the value as a Java
     * Object i.e. if the value field was an integer than the return
     * value would be and <code>Integer</code> instance
     */
    public Object validate(String value) {
        return value;
    }
}
