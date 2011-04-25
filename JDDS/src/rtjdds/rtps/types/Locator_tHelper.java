package rtjdds.rtps.types;

/** 
 * Helper class for : Locator_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class Locator_tHelper
{
    /**
     * Read Locator_t from a marshalled stream
     * @param istream the input stream
     * @return the readed Locator_t value
     */
    public static rtjdds.rtps.types.Locator_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.Locator_t new_one = new rtjdds.rtps.types.Locator_t();

        new_one.kind = rtjdds.rtps.types.LocatorKindHelper.read(istream);
        new_one.port = istream.read_ulong();
        {
        int size7 = 16;
        new_one.address = new byte[size7];
        istream.read_octet_array(new_one.address, 0, new_one.address.length);
        }

        return new_one;
    }

    /**
     * Write Locator_t into a marshalled stream
     * @param ostream the output stream
     * @param value Locator_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.Locator_t value)
    {
        rtjdds.rtps.types.LocatorKindHelper.write( ostream, value.kind );
        ostream.write_ulong(value.port);
        ostream.write_octet_array( value.address, 0, value.address.length );
    }

}
