package rtjdds.rtps.types;

/**
 * Struct definition: ContentFilterInfo_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class ContentFilterInfo_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member filterResult
     */
    public int[] filterResult;

    /**
     * Struct member filterSignatures
     */
    public int[][] filterSignatures;

    /**
     * Default constructor
     */
    public ContentFilterInfo_t()
    { }

    /**
     * Constructor with fields initialization
     * @param filterResult filterResult struct member
     * @param filterSignatures filterSignatures struct member
     */
    public ContentFilterInfo_t(int[] filterResult, int[][] filterSignatures)
    {
        this.filterResult = filterResult;
        this.filterSignatures = filterSignatures;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        ContentFilterInfo_t dst = (ContentFilterInfo_t) o;
        dst.filterResult = filterResult;
        for ( int i0=0; i0 < filterSignatures.length; i0++ )
        {
        dst.filterSignatures[i0] = filterSignatures[i0];

        }
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        ContentFilterInfo_t src = (ContentFilterInfo_t) o;
        filterResult = src.filterResult;
        for ( int i0=0; i0 < filterSignatures.length; i0++ )
        {
        filterSignatures[i0] = src.filterSignatures[i0];

        }
    }

}
