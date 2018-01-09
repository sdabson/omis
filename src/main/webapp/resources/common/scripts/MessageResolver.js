/**
 * Resolves messages.
 * 
 * <p>Dependencies
 * <ul>
 *   <li>ServerConfig.js
 * </ul>
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 13, 2013)
 * @since OMIS 3.0
 */
var common = new Object();

/**
 * Instantiates a message resolver.
 * 
 * @param baseName bundle base name
 */
common.MessageResolver = function(baseName) {

	// Handle results
	function readyStateChange(request) {
		if (request.readyState == 4) {
			if (request.status == 200) {
				common.MessageResolver.prototype.messages = eval("(" + request.responseText + ")");
			} else {
				alert("Error - status: " + request.status + "; text: " + request.statusText);
			}
		}
	}
	
	var url = config.ServerConfig.getContextPath() + "/resources/common/scripts/resolveBundle.json";
	var request = new XMLHttpRequest();
	request.open("GET", url + "?baseName=" + baseName, false);
	request.onreadystatechange = function() {
		readyStateChange(request);
	};
	request.send(null);
};

/**
 * Returns a message according to the specified key.
 * 
 * @param key key
 * @param params optional parameters
 * @return message
 */
common.MessageResolver.prototype.getMessage = function(key, params) {
	var message = null;
	for (var m in this.messages) {
		if (m == key) {
			message = this.messages[m];
			break;
		}
	}
	if (message != null) {
		var count = 0;
		for (var param in params) {
			message = message.replace("{" + count + "}", params[param]);
			count++;
		}
	}
	return message;
};