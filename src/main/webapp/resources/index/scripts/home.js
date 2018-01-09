/**
 * Home screen behavior.
 *
 * Author: Stephen Abson
 * Author: Ryan Johns
 *  
 * Dependencies -
 *	resources/common/scripts/EventRunner.js
 */
eventRunner.addEvent(setUpIndex);

/** Register index JS with event runner. */

function setUpIndex() {
	loadOffenderProfilePhotosOnDemand(document.getElementById("caseOffenderResults"));	
	
var contents = document.getElementsByClassName("content");
	
	/*for(var x=0; x < contents.length; x++) {
		$(contents[x]).scrollContainer({scrollX:true, 
			scrollY:false, 
			elementCss:{
				}});
	}*/
}

window.onload = function() {
	applyActionMenu(document.getElementById("searchActionMenu"));
	applyActionMenu(document.getElementById("caseLoadActionMenu"));
	applyActionMenu(document.getElementById("workCentersActionMenu"));
}