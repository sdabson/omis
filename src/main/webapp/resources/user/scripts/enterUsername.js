/**
 * Convert the value of the username field to upper case when the element
 * is blurred.
 * @author Stephen Abson
 * @version 0.1.0 (Oct 22, 2012)
 * @since OMIS 3.0
 */
$(document).ready(
	function() {
		var username = $("#username");
		username.blur(
			function() {
				username.val(username.val().toUpperCase());
			});
		username.focus();
	}
);