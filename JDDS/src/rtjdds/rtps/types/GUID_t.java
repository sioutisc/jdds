package rtjdds.rtps.types;

/**
 * Struct definition: GUID_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class GUID_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member guidPrefix
     */
    public rtjdds.rtps.types.GuidPrefix_t guidPrefix;

    /**
     * Struct member entityId
     */
    public rtjdds.rtps.types.EntityId_t entityId;

    /**
     * Default constructor
     */
    public GUID_t()
    { }

    /**
     * Constructor with fields initialization
     * @param guidPrefix guidPrefix struct member
     * @param entityId entityId struct member
     */
    public GUID_t(rtjdds.rtps.types.GuidPrefix_t guidPrefix, rtjdds.rtps.types.EntityId_t entityId)
    {
        this.guidPrefix = guidPrefix;
        this.entityId = entityId;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        GUID_t dst = (GUID_t) o;
        guidPrefix.copyTo(dst.guidPrefix);
        entityId.copyTo(dst.entityId);
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        GUID_t src = (GUID_t) o;
        guidPrefix.copyFrom(src.guidPrefix);
        entityId.copyFrom(src.entityId);
    }

}
