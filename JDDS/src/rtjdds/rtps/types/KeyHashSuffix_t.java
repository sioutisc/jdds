package rtjdds.rtps.types;

/**
 * Struct definition: KeyHashSuffix_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class KeyHashSuffix_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member value
     */
    public byte[] value;

    /**
     * Default constructor
     */
    public KeyHashSuffix_t()
    { }

    /**
     * Constructor with fields initialization
     * @param value value struct member
     */
    public KeyHashSuffix_t(byte[] value)
    {
        this.value = value;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        KeyHashSuffix_t dst = (KeyHashSuffix_t) o;
        System.arraycopy(value,0,dst.value,0,value.length);
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        KeyHashSuffix_t src = (KeyHashSuffix_t) o;
        System.arraycopy(src.value,0,value,0,value.length);
    }

}
