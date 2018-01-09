/**
 * Link behavior.
 *
 * Author: Stephen Abson
 */

function applyRemoveLinkConfirmation(className) {
	if (typeof(className) == "undefined") {
		className = "removeLink";
	}
	addConfirmation(className, "removeConfirmMessage");
}

function applyCancelLinkConfirmation(className, param) {
	if (typeof(className) == "undefined") {
		className = "cancelLink";
	}
	addConfirmation(className, "cancelConfirmMessage", [param]);
}

function addConfirmation(className, messageKey, params) {
	var as = document.getElementsByTagName("a");
	var message = null;
	for (var i = 0; i < as.length; i++) {
		var a = as[i];
		var classAttr = a.getAttribute("class");
		if (classAttr != null) {
			if (classAttr.indexOf(className) != -1) {
				if (message == null) {
					var resolver = new common.MessageResolver("omis.msgs.common");
					message = resolver.getMessage(messageKey, params);
				}
				a.onclick = function() {
					if (confirm(message)) {
						return true;
					} else {
						return false;
					}
				};
			}
		}
	}
	
	
}