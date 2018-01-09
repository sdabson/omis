package omis.content;

import java.util.List;

/**
 * Scans an application for request content mappings returning a list of
 * request content details.
 * <p>
 * A can either be configured using XML (for instance) or can be indicated by
 * using the {@code ScreenMapping} annotation. The choice depends on
 * the implementation strategy.
 * @author Stephen Abson
 * @version 0.1.0 (June 15, 2012)
 * @since OMIS 3.0
 */
public interface RequestContentMappingScanner {
	
	/**
	 * Scans the application for screens and returns them.
	 * @return list of screen
	 */
	List<RequestContent> getRequestContents();
}