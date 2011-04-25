package rtjdds.rtps.types;

/** 
 * Helper class for : SubmessageKind
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class SubmessageKindHelper
{
    /**
     * Read SubmessageKind from a marshalled stream
     * @param istream the input stream
     * @return the readed SubmessageKind value
     */
    public static byte read(org.omg.CORBA.portable.InputStream istream)
    {
        byte new_one;
        new_one = istream.read_octet();

        return new_one;
    }

    /**
     * Write SubmessageKind into a marshalled stream
     * @param ostream the output stream
     * @param value SubmessageKind value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, byte value)
    {
        ostream.write_octet( value );
    }

}
