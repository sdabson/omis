package omis.content;

/**
 * Request content.
 * @author Stephen Abson
 * @version 0.1.0 (June 15, 2012)
 * @since OMIS 3.0
 */
public interface RequestContent {

	/**
	 * Returns the URL of the request.
	 * @return screen URL
	 */
	String getUrl();
	
	/**
	 * Returns the request content name message key.
	 * @return request content message key
	 */
	String getNameKey();
	
	/**
	 * Returns the request content description message key.
	 * @return request content description message key
	 */
	String getDescriptionKey();
	
	/**
	 * Returns the message bundle.
	 * @return message bundle
	 */
	String getMessageBundle();
	
	/**
	 * Returns the type of request content.
	 * @return request content type
	 */
	RequestContentType getType();
	
	/**
	 * Returns the authorization required to access the request content.
	 * <p>
	 * The format of this string is security implementation dependent.
	 * <p>
	 * If no authorization information is available, the string returned is
	 * required to be empty ({@code ""}) and not {@code null}.
	 * @return authority required to access request content or an empty string
	 * if no authority information is required
	 */
	String getRequiredAuthorization();
	
	/**
	 * Returns the name of the class invoked by the URL.
	 * <p>
	 * In some implementations, such a class might not exist.
	 * @return class invoked by URL
	 */
	String getClassName();
	
	/**
	 * Returns the name of the method invoked by the URL.
	 * <p>
	 * In some implementations, such a method might not exist.
	 * @return method invoked by URL
	 */
	String getMethodName();
}