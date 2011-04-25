package rtjdds.rtps.types;

/** 
 * Helper class for : TopicKind
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class TopicKindHelper
{
    /**
     * Read TopicKind from a marshalled stream
     * @param istream the input stream
     * @return the readed TopicKind value
     */
    public static int read(org.omg.CORBA.portable.InputStream istream)
    {
        int new_one;
        new_one = istream.read_long();

        return new_one;
    }

    /**
     * Write TopicKind into a marshalled stream
     * @param ostream the output stream
     * @param value TopicKind value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, int value)
    {
        ostream.write_long( value );
    }

}
