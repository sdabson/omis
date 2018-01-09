/**
 * @author Stephen Abson
 * @version 0.1.0 (Jun 8, 2012)
 * @since OMIS 3.0
 */

/**
 * Returns an HTTP request object appropriate for the browser. Returns
 * <tt>null</tt> if no asynchronous request object is available.
 * @return HTTP request object
 */
function getRequestObject() {
	if (window.XMLHttpRequest) {
		return new window.XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new window.ActiveXObject("Microsoft.XMLHTTP");
	} else {
		return null;
	}
}