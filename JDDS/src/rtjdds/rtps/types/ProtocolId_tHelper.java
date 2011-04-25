package rtjdds.rtps.types;

/** 
 * Helper class for : ProtocolId_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class ProtocolId_tHelper
{
    /**
     * Read ProtocolId_t from a marshalled stream
     * @param istream the input stream
     * @return the readed ProtocolId_t value
     */
    public static rtjdds.rtps.types.ProtocolId_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.ProtocolId_t new_one = new rtjdds.rtps.types.ProtocolId_t();

        {
        int size7 = 4;
        new_one.value = new byte[size7];
        istream.read_octet_array(new_one.value, 0, new_one.value.length);
        }

        return new_one;
    }

    /**
     * Write ProtocolId_t into a marshalled stream
     * @param ostream the output stream
     * @param value ProtocolId_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.ProtocolId_t value)
    {
        ostream.write_octet_array( value.value, 0, value.value.length );
    }

}
