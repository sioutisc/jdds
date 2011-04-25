package rtjdds.rtps.types;

/**
 * Struct definition: Time_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class Time_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member seconds
     */
    public int seconds;

    /**
     * Struct member fraction
     */
    public int fraction;

    /**
     * Default constructor
     */
    public Time_t()
    { }

    /**
     * Constructor with fields initialization
     * @param seconds seconds struct member
     * @param fraction fraction struct member
     */
    public Time_t(int seconds, int fraction)
    {
        this.seconds = seconds;
        this.fraction = fraction;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        Time_t dst = (Time_t) o;
        dst.seconds = seconds;
        dst.fraction = fraction;
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        Time_t src = (Time_t) o;
        seconds = src.seconds;
        fraction = src.fraction;
    }

}
