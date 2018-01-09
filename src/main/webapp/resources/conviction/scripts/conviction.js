/**
 * Conviction behavior.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (May 15, 2017)
 * @since OMIS 3.0
 */

window.onload = function() {
	applyActionMenu(document.getElementById("convictionsActionMenuLink"), convictionActionMenuOnClick);
	applyActionMenu(document.getElementById("actionMenuLink"));
	for (var index = 0; index < currentConvictionIndex; index++) {
		convictionRowOnClick(index);
	}
};
