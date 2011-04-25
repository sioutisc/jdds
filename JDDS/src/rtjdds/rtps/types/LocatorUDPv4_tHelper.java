package rtjdds.rtps.types;

/** 
 * Helper class for : LocatorUDPv4_t
 *  
 * @author OpenORB Compiler - R T j D D S modified 
 */ 
public class LocatorUDPv4_tHelper
{
    /**
     * Read LocatorUDPv4_t from a marshalled stream
     * @param istream the input stream
     * @return the readed LocatorUDPv4_t value
     */
    public static rtjdds.rtps.types.LocatorUDPv4_t read(org.omg.CORBA.portable.InputStream istream)
    {
        rtjdds.rtps.types.LocatorUDPv4_t new_one = new rtjdds.rtps.types.LocatorUDPv4_t();

        new_one.address = istream.read_ulong();
        new_one.port = istream.read_ulong();

        return new_one;
    }

    /**
     * Write LocatorUDPv4_t into a marshalled stream
     * @param ostream the output stream
     * @param value LocatorUDPv4_t value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, rtjdds.rtps.types.LocatorUDPv4_t value)
    {
        ostream.write_ulong(value.address);
        ostream.write_ulong(value.port);
    }

}
