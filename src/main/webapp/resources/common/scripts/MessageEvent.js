
/** JavaScript message event resolver. This provides a protocol to communicate 
 * between documents.
 * @author Ryan Johns
 * @version 0.1.0 (Jun 19, 2015)
 * @since OMIS 3.0 */
function validateMessage(event) {
	
	var origin = window.location.origin;
	
	/* IE 11 Fix */
	if (typeof origin == 'undefined') {
		window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
		origin = window.location.origin;
	}
	if (event.origin !== origin) {
		alert('Message Invalid Origin: '+event.origin);
	} else {
		return (typeof event.data.domain !== 'undefined' && event.data.functionName !== 'undefined');
	}
}