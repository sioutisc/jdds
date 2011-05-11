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

import jtools.util.*;

/**
 * This class represent the specification associated with a single
 * argument of a command line. For example if you consider javac, one
 * <code>ArgSpec</code> would be the tuple (cp, path). 
 *
 * @author <a href="mailto:corsaro@doc.cs.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class ArgSpec {

    protected String argName;
    protected String[] argValidators;
    protected int argNum = 0;
    protected static final String [] nullValidator = new String[0];

    ///////////////////////////////////////////////////////////////////////////
    //               Inner Class
    class ValidatorsIterator {

        protected int position;

        
        public boolean hasNext() {
            return this.position < ArgSpec.this.argValidators.length;
        }
        
        public Validator next() throws Exception {
            if (this.position == ArgSpec.this.argValidators.length)
                return null;
            Class clazz = Class.forName(ArgSpec.this.argValidators[this.position++]);
            return (Validator)clazz.newInstance(); 
        }

        public void reset() {
            this.position = 0;
        }
    }
    //
    ///////////////////////////////////////////////////////////////////////////

    public ArgSpec(String argName) {
        Assert.preCondition(argName != null);
        this.argName = argName;
        this.argValidators = this.nullValidator;
    }

    public ArgSpec(String argName, String[] argValidators) {
        Assert.preCondition(argName != null && argValidators != null);
        this.argName = argName;
        this.argValidators = argValidators;
        this.argNum = argValidators.length;
    }

    public final String getName() {
        return this.argName;
    }

    final ValidatorsIterator validatorIterator() {
        return new ValidatorsIterator();
    }
    
    public int argNum() {
        return this.argValidators.length; 
    }
    
    public String toString() {
        return this.argName;
    }
}
