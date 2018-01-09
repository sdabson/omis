/**
 * Message behavior.
 * 
 * @author Stephen Abson
 */

/**
 * Closes modal message dialog when close link is clicked.
 * 
 * @param modalAreaElt modal area element
 * @param closeLinkElt close link element
 */
function applyModalMessageClose(modalAreaElt, closeLinkElt) {
	closeLinkElt.onclick = function() {
		modalAreaElt.parentNode.removeChild(modalAreaElt);
		return false;
	};
}