/**
 * Victim documents java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Aug 31, 2017)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("victimDocumentsActionMenuLink"), applyVictimDocumentsActionMenuOnClick);
	for (i = 0; i < currentVictimDocumentItemIndex; i++) {
		victimDocumentItemContentOnClick(i);
		for(var t = 0; t < currentDocumentTagItemIndexes[i]; t++) {
			documentTagItemOnClick(i,t);
		}
	}
};