package rtjdds.rtps.types;

/**
 * Struct definition: Count_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class Count_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member value
     */
    public int value;

    /**
     * Default constructor
     */
    public Count_t()
    { }

    /**
     * Constructor with fields initialization
     * @param value value struct member
     */
    public Count_t(int value)
    {
        this.value = value;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        Count_t dst = (Count_t) o;
        dst.value = value;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        Count_t src = (Count_t) o;
        value = src.value;
    }

}
