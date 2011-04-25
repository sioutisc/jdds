package rtjdds.rtps.types;

/**
 * Struct definition: Locator_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class Locator_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member kind
     */
    public int kind;

    /**
     * Struct member port
     */
    public int port;

    /**
     * Struct member address
     */
    public byte[] address;

    /**
     * Default constructor
     */
    public Locator_t()
    { }

    /**
     * Constructor with fields initialization
     * @param kind kind struct member
     * @param port port struct member
     * @param address address struct member
     */
    public Locator_t(int kind, int port, byte[] address)
    {
        this.kind = kind;
        this.port = port;
        this.address = address;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        Locator_t dst = (Locator_t) o;
        dst.kind = kind;
        dst.port = port;
        System.arraycopy(address,0,dst.address,0,address.length);
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        Locator_t src = (Locator_t) o;
        kind = src.kind;
        port = src.port;
        System.arraycopy(src.address,0,address,0,address.length);
    }

}
