package rtjdds.rtps.types;

/**
 * Struct definition: VendorId_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class VendorId_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member vendorId
     */
    public byte[] vendorId;

    /**
     * Default constructor
     */
    public VendorId_t()
    { }

    /**
     * Constructor with fields initialization
     * @param vendorId vendorId struct member
     */
    public VendorId_t(byte[] vendorId)
    {
        this.vendorId = vendorId;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        VendorId_t dst = (VendorId_t) o;
        System.arraycopy(vendorId,0,dst.vendorId,0,vendorId.length);
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        VendorId_t src = (VendorId_t) o;
        System.arraycopy(src.vendorId,0,vendorId,0,vendorId.length);
    }

}
