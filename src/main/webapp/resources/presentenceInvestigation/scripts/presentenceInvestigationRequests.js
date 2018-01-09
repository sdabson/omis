/*
 * Presentence investigation requests java script.
 * 
 * Author: Joel Norris
 * Author: Annie Jacques
 * Version: 0.1.1 (July 5, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
		
	}
}