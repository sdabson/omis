/**
 * 
 * @author Yidong Li
 * Date: June 22, 2015
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("familyListActionMenuLink"));
	var familyRows = document.getElementsByClassName("rowActionMenuItem");
	for(var x =0; x < familyRows.length; x++) {
		applyActionMenu(familyRows[x], function() {applyRemoveLinkConfirmation();});
	}
};