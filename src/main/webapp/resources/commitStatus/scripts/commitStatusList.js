/**
 * 
 * @author Yidong Li
 * Date: June 19, 2015
 */
window.onload = function() {
	applyActionMenu(document.getElementById("commitStatusListScreenActionMenuLink"));
	applyRemoveLinkConfirmation();
	var termRows = document.getElementsByClassName("rowActionMenuItem");
	for(var x =0; x < termRows.length; x++) {
		applyActionMenu(termRows[x], function() {applyRemoveLinkConfirmation();});
	}
	
}