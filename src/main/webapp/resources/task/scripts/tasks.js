/**
 * Behavior of task listing screen.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	var tasksTableBody = document.getElementById("tasks");
	var rowLinks = tasksTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink);
		}
	}	
};