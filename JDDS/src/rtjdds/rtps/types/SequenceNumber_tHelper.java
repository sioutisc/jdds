package rtjdds.rtps.types;

/** 
 * Helper class for : SequenceNumber_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class SequenceNumber_tHelper
{
    /**
     * Read SequenceNumber_t from a marshalled stream
     * @param istream the input stream
     * @return the readed SequenceNumber_t value
     */
    public static rtjdds.rtps.types.SequenceNumber_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.SequenceNumber_t new_one = new rtjdds.rtps.types.SequenceNumber_t();

        new_one.value = istream.read_longlong();

        return new_one;
    }

    /**
     * Write SequenceNumber_t into a marshalled stream
     * @param ostream the output stream
     * @param value SequenceNumber_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.SequenceNumber_t value)
    {
        ostream.write_longlong( value.value );
    }

}
