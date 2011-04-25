package rtjdds.rtps.types;

/** 
 * Helper class for : EntityId_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class EntityId_tHelper
{
    /**
     * Read EntityId_t from a marshalled stream
     * @param istream the input stream
     * @return the readed EntityId_t value
     */
    public static rtjdds.rtps.types.EntityId_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.EntityId_t new_one = new rtjdds.rtps.types.EntityId_t();

        {
        int size7 = 3;
        new_one.entityKey = new byte[size7];
        istream.read_octet_array(new_one.entityKey, 0, new_one.entityKey.length);
        }
        new_one.entityKind = istream.read_octet();

        return new_one;
    }

    /**
     * Write EntityId_t into a marshalled stream
     * @param ostream the output stream
     * @param value EntityId_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.EntityId_t value)
    {
        ostream.write_octet_array( value.entityKey, 0, value.entityKey.length );
        ostream.write_octet( value.entityKind );
    }

}
