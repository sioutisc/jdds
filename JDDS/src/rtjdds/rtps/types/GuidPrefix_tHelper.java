package rtjdds.rtps.types;

/** 
 * Helper class for : GuidPrefix_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class GuidPrefix_tHelper
{
    /**
     * Read GuidPrefix_t from a marshalled stream
     * @param istream the input stream
     * @return the readed GuidPrefix_t value
     */
    public static rtjdds.rtps.types.GuidPrefix_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.GuidPrefix_t new_one = new rtjdds.rtps.types.GuidPrefix_t();

        {
        int size7 = 12;
        new_one.prefix = new byte[size7];
        istream.read_octet_array(new_one.prefix, 0, new_one.prefix.length);
        }

        return new_one;
    }

    /**
     * Write GuidPrefix_t into a marshalled stream
     * @param ostream the output stream
     * @param value GuidPrefix_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.GuidPrefix_t value)
    {
        ostream.write_octet_array( value.prefix, 0, value.prefix.length );
    }

}
