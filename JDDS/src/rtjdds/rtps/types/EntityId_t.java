package rtjdds.rtps.types;

/**
 * Struct definition: EntityId_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class EntityId_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member entityKey
     */
    public byte[] entityKey;

    /**
     * Struct member entityKind
     */
    public byte entityKind;

    /**
     * Default constructor
     */
    public EntityId_t()
    { }

    /**
     * Constructor with fields initialization
     * @param entityKey entityKey struct member
     * @param entityKind entityKind struct member
     */
    public EntityId_t(byte[] entityKey, byte entityKind)
    {
        this.entityKey = entityKey;
        this.entityKind = entityKind;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        EntityId_t dst = (EntityId_t) o;
        System.arraycopy(entityKey,0,dst.entityKey,0,entityKey.length);
        dst.entityKind = entityKind;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        EntityId_t src = (EntityId_t) o;
        System.arraycopy(src.entityKey,0,entityKey,0,entityKey.length);
        entityKind = src.entityKind;
    }

}
