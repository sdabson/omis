/**
 * Placement home screen behavior.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 */

/**
 * Lists location terms for element.
 * 
 * @param elt element
 */
function listLocationTerms(elt) {
	var url = elt.getAttribute("href");
	var request = new XMLHttpRequest();
	var supvOrgTermRow = $(elt).closest("tr.supervisoryOrganizationTerm");
	request.open("get", url, false);
	request.send(null);
	if (request.status == 200) {
		var associatedLocTermRows = document.getElementsByClassName("associatedLocationTermRow");
		for (count = 0; count < associatedLocTermRows.length; count++) {
			$(associatedLocTermRows[count]).remove();	
		}		
		$(request.responseText).insertAfter(supvOrgTermRow);
	} else {
		alert("Error - " + request.status);
	}
}

/**
 * Applies behavior to show locations terms for all <a/> elements with
 * class name.
 * 
 * @param className class name
 */
function applyLocationTermList(className) {
	var anchors = document.getElementById("supervisoryOrganizationTerms").getElementsByTagName("a");
	for (var index = 0; index < anchors.length; index++) {
		var anchor = anchors[index];
		if (anchor.getAttribute("class").indexOf(className) != -1) {
			anchor.onclick = function() {
				listLocationTerms(this);
				return false;
			};
		}
	}
	return false;
}

window.onload = function() {
	applyActionMenu(document.getElementById("placementActionMenu"));
	applyLocationTermList("locationTermsLink");
}