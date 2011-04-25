package rtjdds.rtps.types;

/**
 * Struct definition: ContentFilterProperty_t.
 * 
 * @author the R T j D D S Compiler (based on OpenORB compiler) 
*/
import rtjdds.util.collections.Copyable;

public final class ContentFilterProperty_t implements org.omg.CORBA.portable.IDLEntity, Copyable  {
    /**
     * Struct member contentFilteredTopicName
     */
    public String contentFilteredTopicName;

    /**
     * Struct member relatedTopicName
     */
    public String relatedTopicName;

    /**
     * Struct member filterClassName
     */
    public String filterClassName;

    /**
     * Struct member filterExpression
     */
    public String filterExpression;

    /**
     * Struct member expressionParameters
     */
    public String[] expressionParameters;

    /**
     * Default constructor
     */
    public ContentFilterProperty_t()
    { }

    /**
     * Constructor with fields initialization
     * @param contentFilteredTopicName contentFilteredTopicName struct member
     * @param relatedTopicName relatedTopicName struct member
     * @param filterClassName filterClassName struct member
     * @param filterExpression filterExpression struct member
     * @param expressionParameters expressionParameters struct member
     */
    public ContentFilterProperty_t(String contentFilteredTopicName, String relatedTopicName, String filterClassName, String filterExpression, String[] expressionParameters)
    {
        this.contentFilteredTopicName = contentFilteredTopicName;
        this.relatedTopicName = relatedTopicName;
        this.filterClassName = filterClassName;
        this.filterExpression = filterExpression;
        this.expressionParameters = expressionParameters;
    }

    /**
     * Deep-Copies this instance to the passed instance following the Copyable.copyTo() semantics
     * @param o the destination object
     */
    public void copyTo (Copyable o) { 
        ContentFilterProperty_t dst = (ContentFilterProperty_t) o;
        System.arraycopy(contentFilteredTopicName,0,dst.contentFilteredTopicName,0,contentFilteredTopicName.length());
        System.arraycopy(relatedTopicName,0,dst.relatedTopicName,0,relatedTopicName.length());
        System.arraycopy(filterClassName,0,dst.filterClassName,0,filterClassName.length());
        System.arraycopy(filterExpression,0,dst.filterExpression,0,filterExpression.length());
        for ( int i0=0; i0 < expressionParameters.length; i0++ )
        {
        System.arraycopy(expressionParameters[i0],0,dst.expressionParameters[i0],0,expressionParameters[i0].length());

        }
    }

    /**
     * Deep-Copies this instance from the passed instance following the Copyable.copyFrom() semantics
     * @param o the source object
     */
    public void copyFrom (Copyable o) { 
        ContentFilterProperty_t src = (ContentFilterProperty_t) o;
        System.arraycopy(src.contentFilteredTopicName,0,contentFilteredTopicName,0,contentFilteredTopicName.length());
        System.arraycopy(src.relatedTopicName,0,relatedTopicName,0,relatedTopicName.length());
        System.arraycopy(src.filterClassName,0,filterClassName,0,filterClassName.length());
        System.arraycopy(src.filterExpression,0,filterExpression,0,filterExpression.length());
        for ( int i0=0; i0 < expressionParameters.length; i0++ )
        {
        System.arraycopy(src.expressionParameters[i0],0,expressionParameters[i0],0,expressionParameters[i0].length());

        }
    }

}
