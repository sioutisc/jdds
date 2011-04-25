package rtjdds.rtps.types;

/**
 * Struct definition: ProtocolVersion_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class ProtocolVersion_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member major
     */
    public byte major;

    /**
     * Struct member minor
     */
    public byte minor;

    /**
     * Default constructor
     */
    public ProtocolVersion_t()
    { }

    /**
     * Constructor with fields initialization
     * @param major major struct member
     * @param minor minor struct member
     */
    public ProtocolVersion_t(byte major, byte minor)
    {
        this.major = major;
        this.minor = minor;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        ProtocolVersion_t dst = (ProtocolVersion_t) o;
        dst.major = major;
        dst.minor = minor;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        ProtocolVersion_t src = (ProtocolVersion_t) o;
        major = src.major;
        minor = src.minor;
    }

}
