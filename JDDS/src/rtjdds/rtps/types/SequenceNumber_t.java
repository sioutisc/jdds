package rtjdds.rtps.types;

/**
 * Struct definition: SequenceNumber_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class SequenceNumber_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member value
     */
    public long value;

    /**
     * Default constructor
     */
    public SequenceNumber_t()
    { }

    /**
     * Constructor with fields initialization
     * @param value value struct member
     */
    public SequenceNumber_t(long value)
    {
        this.value = value;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        SequenceNumber_t dst = (SequenceNumber_t) o;
        dst.value = value;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        SequenceNumber_t src = (SequenceNumber_t) o;
        value = src.value;
    }

}
