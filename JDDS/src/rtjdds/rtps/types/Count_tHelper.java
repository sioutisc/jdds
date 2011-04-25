package rtjdds.rtps.types;

/** 
 * Helper class for : Count_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class Count_tHelper
{
    /**
     * Read Count_t from a marshalled stream
     * @param istream the input stream
     * @return the readed Count_t value
     */
    public static rtjdds.rtps.types.Count_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.Count_t new_one = new rtjdds.rtps.types.Count_t();

        new_one.value = istream.read_long();

        return new_one;
    }

    /**
     * Write Count_t into a marshalled stream
     * @param ostream the output stream
     * @param value Count_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.Count_t value)
    {
        ostream.write_long( value.value );
    }

}
