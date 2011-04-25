package rtjdds.rtps.types;

/** 
 * Helper class for : ContentFilterProperty_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class ContentFilterProperty_tHelper
{
    /**
     * Read ContentFilterProperty_t from a marshalled stream
     * @param istream the input stream
     * @return the readed ContentFilterProperty_t value
     */
    public static rtjdds.rtps.types.ContentFilterProperty_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.ContentFilterProperty_t new_one = new rtjdds.rtps.types.ContentFilterProperty_t();

        new_one.contentFilteredTopicName = istream.read_string();
        new_one.relatedTopicName = istream.read_string();
        new_one.filterClassName = istream.read_string();
        new_one.filterExpression = istream.read_string();
        {
        int size7 = istream.read_ulong();
        new_one.expressionParameters = new String[size7];
        for (int i7=0; i7<new_one.expressionParameters.length; i7++)
         {
            new_one.expressionParameters[i7] = istream.read_string();

         }
        }

        return new_one;
    }

    /**
     * Write ContentFilterProperty_t into a marshalled stream
     * @param ostream the output stream
     * @param value ContentFilterProperty_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.ContentFilterProperty_t value)
    {
        ostream.write_string( value.contentFilteredTopicName );
        ostream.write_string( value.relatedTopicName );
        ostream.write_string( value.filterClassName );
        ostream.write_string( value.filterExpression );
        ostream.write_ulong( value.expressionParameters.length );
        for ( int i7 = 0; i7 < value.expressionParameters.length; i7++ )
        {
            ostream.write_string( value.expressionParameters[ i7 ] );

        }
    }

}
