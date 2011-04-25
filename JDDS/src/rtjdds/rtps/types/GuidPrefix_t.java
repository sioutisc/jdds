package rtjdds.rtps.types;

/**
 * Struct definition: GuidPrefix_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class GuidPrefix_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member prefix
     */
    public byte[] prefix;

    /**
     * Default constructor
     */
    public GuidPrefix_t()
    { }

    /**
     * Constructor with fields initialization
     * @param prefix prefix struct member
     */
    public GuidPrefix_t(byte[] prefix)
    {
        this.prefix = prefix;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        GuidPrefix_t dst = (GuidPrefix_t) o;
        System.arraycopy(prefix,0,dst.prefix,0,prefix.length);
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        GuidPrefix_t src = (GuidPrefix_t) o;
        System.arraycopy(src.prefix,0,prefix,0,prefix.length);
    }

}
