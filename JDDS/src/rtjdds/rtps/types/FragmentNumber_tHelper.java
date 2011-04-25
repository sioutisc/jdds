package rtjdds.rtps.types;

/** 
 * Helper class for : FragmentNumber_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class FragmentNumber_tHelper
{
    /**
     * Read FragmentNumber_t from a marshalled stream
     * @param istream the input stream
     * @return the readed FragmentNumber_t value
     */
    public static rtjdds.rtps.types.FragmentNumber_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.FragmentNumber_t new_one = new rtjdds.rtps.types.FragmentNumber_t();

        new_one.value = istream.read_ulong();

        return new_one;
    }

    /**
     * Write FragmentNumber_t into a marshalled stream
     * @param ostream the output stream
     * @param value FragmentNumber_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.FragmentNumber_t value)
    {
        ostream.write_ulong(value.value);
    }

}
