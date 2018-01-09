// Define config namespace
if (typeof config === 'undefined') {
	var config = new Object();
}

/**
 * Stores server configuration properties.
 * @author Stephen Abson
 * @version 0.1.0 (April 3, 2012)
 * @since OMIS 3.0
 */
config.ServerConfig = new function() {

	/**
	 * Returns the server context path.
	 * @return server context path
	 */
	this.getContextPath = function() {
		return "${pageContext.request.contextPath}";
	};

};