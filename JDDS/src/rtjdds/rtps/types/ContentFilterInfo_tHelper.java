package rtjdds.rtps.types;

/** 
 * Helper class for : ContentFilterInfo_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class ContentFilterInfo_tHelper
{
    /**
     * Read ContentFilterInfo_t from a marshalled stream
     * @param istream the input stream
     * @return the readed ContentFilterInfo_t value
     */
    public static rtjdds.rtps.types.ContentFilterInfo_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.ContentFilterInfo_t new_one = new rtjdds.rtps.types.ContentFilterInfo_t();

        new_one.filterResult = rtjdds.rtps.types.FilterResult_tHelper.read(istream);
        {
        int size7 = istream.read_ulong();
        new_one.filterSignatures = new int[size7][];
        for (int i7=0; i7<new_one.filterSignatures.length; i7++)
         {
            new_one.filterSignatures[i7] = rtjdds.rtps.types.FilterSignature_tHelper.read(istream);

         }
        }

        return new_one;
    }

    /**
     * Write ContentFilterInfo_t into a marshalled stream
     * @param ostream the output stream
     * @param value ContentFilterInfo_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.ContentFilterInfo_t value)
    {
        rtjdds.rtps.types.FilterResult_tHelper.write( ostream, value.filterResult );
        ostream.write_ulong( value.filterSignatures.length );
        for ( int i7 = 0; i7 < value.filterSignatures.length; i7++ )
        {
            rtjdds.rtps.types.FilterSignature_tHelper.write( ostream, value.filterSignatures[ i7 ] );

        }
    }

}
