/**
 * 
 * @author Yidong Li
 * Date: June 4, 2015
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	var employmentRows = document.getElementsByClassName("rowActionMenuItem");
	for(var x =0; x < employmentRows.length; x++) {
		applyActionMenu(employmentRows[x], function() {applyRemoveLinkConfirmation();});
	}
};