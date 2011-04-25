package rtjdds.rtps.types;

/** 
 * Helper class for : KeyHashSuffix_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class KeyHashSuffix_tHelper
{
    /**
     * Read KeyHashSuffix_t from a marshalled stream
     * @param istream the input stream
     * @return the readed KeyHashSuffix_t value
     */
    public static rtjdds.rtps.types.KeyHashSuffix_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.KeyHashSuffix_t new_one = new rtjdds.rtps.types.KeyHashSuffix_t();

        {
        int size7 = 4;
        new_one.value = new byte[size7];
        istream.read_octet_array(new_one.value, 0, new_one.value.length);
        }

        return new_one;
    }

    /**
     * Write KeyHashSuffix_t into a marshalled stream
     * @param ostream the output stream
     * @param value KeyHashSuffix_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.KeyHashSuffix_t value)
    {
        ostream.write_octet_array( value.value, 0, value.value.length );
    }

}
