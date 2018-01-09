package omis.content;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate that a method is used to producer content in response
 * to a request invocation.
 * 
 * <p>A request content mapping has a name, description and screen type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (June 15, 2012)
 * @since OMIS 3.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestContentMapping {
	
	/** Request content mapping message key. */
	String nameKey();
	
	/** Request content mapping description message key. */
	String descriptionKey();
	
	/** Message bundle. */
	String messageBundle();
	
	/** Type of request content returned in the response. */
	RequestContentType screenType();
}