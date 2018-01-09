package omis.web.util;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * Retrieves URL redirects from session.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class RedirectUrlRetriever {
	
	private final HttpSession session;
	
	private static final String URL_ATTRIBUTE_NAME = "redirectUrl";

	// Hides constructor - use createInstance() instead
	private RedirectUrlRetriever(final HttpSession session) {
		this.session = session;
	}
	
	/**
	 * Creates and returns new instance of retriever for session.
	 * 
	 * @param session session
	 * @return created instance of retriever for session
	 */
	public static RedirectUrlRetriever createInstance(
			final HttpSession session) {
		return new RedirectUrlRetriever(session);
	}
	
	/**
	 * Returns whether redirect URL is currently set.
	 * 
	 * @return whether redirect URL is currently set
	 */
	public boolean isSet() {
		Enumeration<String> attributes = this.session.getAttributeNames();
		while (attributes.hasMoreElements()) {
			if (attributes.nextElement().equals(URL_ATTRIBUTE_NAME)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns redirect URL and removes it.
	 * 
	 * <p>Returns {@code null} if not set.
	 * 
	 * @return removed redirect URL; {@code null} if not set
	 */
	public String retrieveAndRemove() {
		String redirectUrl = (String) session.getAttribute(URL_ATTRIBUTE_NAME);
		this.session.removeAttribute(URL_ATTRIBUTE_NAME);
		return redirectUrl;
	}
	
	/**
	 * Sets redirect URL. Replaces existing redirect URL if set.
	 * 
	 * @param redirectUrl redirect URL
	 */
	public void setOrReplace(final String redirectUrl) {
		this.session.setAttribute(URL_ATTRIBUTE_NAME, redirectUrl);
	}
	
	/** Clears redirect URL. */
	public void clear() {
		this.session.removeAttribute(URL_ATTRIBUTE_NAME);
	}
}