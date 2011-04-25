package rtjdds.rtps.types;

/** 
 * Helper class for : Time_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class Time_tHelper
{
    /**
     * Read Time_t from a marshalled stream
     * @param istream the input stream
     * @return the readed Time_t value
     */
    public static rtjdds.rtps.types.Time_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.Time_t new_one = new rtjdds.rtps.types.Time_t();

        new_one.seconds = istream.read_long();
        new_one.fraction = istream.read_ulong();

        return new_one;
    }

    /**
     * Write Time_t into a marshalled stream
     * @param ostream the output stream
     * @param value Time_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.Time_t value)
    {
        ostream.write_long( value.seconds );
        ostream.write_ulong(value.fraction);
    }

}
