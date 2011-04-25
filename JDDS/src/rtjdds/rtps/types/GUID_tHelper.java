package rtjdds.rtps.types;

/** 
 * Helper class for : GUID_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class GUID_tHelper
{
    /**
     * Read GUID_t from a marshalled stream
     * @param istream the input stream
     * @return the readed GUID_t value
     */
    public static rtjdds.rtps.types.GUID_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.GUID_t new_one = new rtjdds.rtps.types.GUID_t();

        new_one.guidPrefix = rtjdds.rtps.types.GuidPrefix_tHelper.read(istream);
        new_one.entityId = rtjdds.rtps.types.EntityId_tHelper.read(istream);

        return new_one;
    }

    /**
     * Write GUID_t into a marshalled stream
     * @param ostream the output stream
     * @param value GUID_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.GUID_t value)
    {
        rtjdds.rtps.types.GuidPrefix_tHelper.write( ostream, value.guidPrefix );
        rtjdds.rtps.types.EntityId_tHelper.write( ostream, value.entityId );
    }

}
