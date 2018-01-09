/**
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * Date: May 4, 2015
 */
window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
	for (var count = 0; count < rowActionMenus.length; count++) {
		applyActionMenu(document.getElementById("summaryActionMenuLink" + count));
	}		
};