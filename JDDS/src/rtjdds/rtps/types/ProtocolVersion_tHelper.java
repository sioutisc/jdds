package rtjdds.rtps.types;

/** 
 * Helper class for : ProtocolVersion_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class ProtocolVersion_tHelper
{
    /**
     * Read ProtocolVersion_t from a marshalled stream
     * @param istream the input stream
     * @return the readed ProtocolVersion_t value
     */
    public static rtjdds.rtps.types.ProtocolVersion_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.ProtocolVersion_t new_one = new rtjdds.rtps.types.ProtocolVersion_t();

        new_one.major = istream.read_octet();
        new_one.minor = istream.read_octet();

        return new_one;
    }

    /**
     * Write ProtocolVersion_t into a marshalled stream
     * @param ostream the output stream
     * @param value ProtocolVersion_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.ProtocolVersion_t value)
    {
        ostream.write_octet( value.major );
        ostream.write_octet( value.minor );
    }

}
