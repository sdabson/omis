/**
 * Single screen mode behavior.
 * 
 * @author: Stephen Abson
 * @version: 0.1.0 (Dec 19, 2013)
 * @since OMIS 3.0
 */
window.onload = function() {
	var contentFrame = document.getElementById("contentFrame");
	var as = document.getElementsByTagName("a");
	for (var i = 0; i < as.length; i++) {
		var elt = as[i];
		if (elt.getAttribute("class") != null
				&& elt.getAttribute("class").indexOf("iconLink") > -1) {
			elt.onclick = function(event) {
				loadContentFrame(contentFrame, event.target.getAttribute("href"));
				return false;
			};
		}
	}
	// User has developer role
	if (document.getElementById("developerTools")) {
		var refreshLink = document.getElementById("refreshLink");
		var urlInput = document.getElementById("urlInput");
		refreshLink.onclick = function() {
			contentFrame.contentWindow.location.reload();
			return false;
		};
		contentFrame.onload = function() {
			urlInput.setAttribute("value", contentFrame.contentWindow.location.href);
			updateUrlInputForContentFrame(urlInput, contentFrame);
		};
		updateUrlInputForContentFrame(urlInput, contentFrame);
	}
};

/**
 * Loads content into frame.
 * 
 * @param contentFrame content frame
 * @param src source page
 */
function loadContentFrame(contentFrame, src) {
	contentFrame.setAttribute("src", src);
}

/**
 * Updates the input with the URL of the content frame.
 * 
 * @param urlInput input in which to display URL
 * @param contentFrame content frame
 */
function updateUrlInputForContentFrame(urlInput, contentFrame) {
	urlInput.setAttribute("value", contentFrame.contentWindow.location.href);
}