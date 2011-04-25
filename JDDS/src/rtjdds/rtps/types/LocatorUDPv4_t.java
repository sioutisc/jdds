package rtjdds.rtps.types;

/**
 * Struct definition: LocatorUDPv4_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class LocatorUDPv4_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member address
     */
    public int address;

    /**
     * Struct member port
     */
    public int port;

    /**
     * Default constructor
     */
    public LocatorUDPv4_t()
    { }

    /**
     * Constructor with fields initialization
     * @param address address struct member
     * @param port port struct member
     */
    public LocatorUDPv4_t(int address, int port)
    {
        this.address = address;
        this.port = port;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        LocatorUDPv4_t dst = (LocatorUDPv4_t) o;
        dst.address = address;
        dst.port = port;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        LocatorUDPv4_t src = (LocatorUDPv4_t) o;
        address = src.address;
        port = src.port;
    }

}
