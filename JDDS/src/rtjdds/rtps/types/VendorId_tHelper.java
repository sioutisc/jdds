package rtjdds.rtps.types;

/** 
 * Helper class for : VendorId_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class VendorId_tHelper
{
    /**
     * Read VendorId_t from a marshalled stream
     * @param istream the input stream
     * @return the readed VendorId_t value
     */
    public static rtjdds.rtps.types.VendorId_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.VendorId_t new_one = new rtjdds.rtps.types.VendorId_t();

        {
        int size7 = 2;
        new_one.vendorId = new byte[size7];
        istream.read_octet_array(new_one.vendorId, 0, new_one.vendorId.length);
        }

        return new_one;
    }

    /**
     * Write VendorId_t into a marshalled stream
     * @param ostream the output stream
     * @param value VendorId_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.VendorId_t value)
    {
        ostream.write_octet_array( value.vendorId, 0, value.vendorId.length );
    }

}
