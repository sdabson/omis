/**
 * 
 * @author Yidong Li
 * Date: Jan 13, 2015
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	var vehicleRows = document.getElementsByClassName("rowMenuItem");
	for(var x =0; x < vehicleRows.length; x++) {
		applyActionMenu(vehicleRows[x], function() {applyRemoveLinkConfirmation();});
	}
};