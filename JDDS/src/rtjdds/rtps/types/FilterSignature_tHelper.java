package rtjdds.rtps.types;

/** 
 * Helper class for : FilterSignature_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class FilterSignature_tHelper
{
    /**
     * Read FilterSignature_t from a marshalled stream
     * @param istream the input stream
     * @return the readed FilterSignature_t value
     */
    public static int[] read(org.omg.CORBA.portable.InputStream istream)
    {
        int[] new_one;
        {
        int size7 = 4;
        new_one = new int[size7];
        istream.read_long_array(new_one, 0, new_one.length);
        }

        return new_one;
    }

    /**
     * Write FilterSignature_t into a marshalled stream
     * @param ostream the output stream
     * @param value FilterSignature_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, int[] value)
    {
        ostream.write_long_array( value, 0, value.length );
    }

}
