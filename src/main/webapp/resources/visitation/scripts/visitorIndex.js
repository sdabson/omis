/**
 * visitorIndex.js
 * 
 * author: Joel Norris
 * version: 0.1.0 (Oct 24, 2013)
 * since: OMIS 3.0
 */

/*Assign on click functionality*/
function assignVisitorIndexOnClick() {
	var facilityListLink = $("#facilityListLink");
	var facilityLogLink = $("#facilityLogLink");
	var facilitySelectDropDown = $("#facilitySelect");
	disableLink(facilityListLink);
	disableLink(facilityLogLink);
	facilitySelectDropDown.click(function(){
		var facilitySelect = $("#facilitySelect").val();
		if (facilitySelect != "") {
			facilityListLink.attr('href', config.ServerConfig.getContextPath()
					+ "/visitation/list.html?facility=" + facilitySelect);
			facilityLogLink.attr('href', config.ServerConfig.getContextPath()
					+ "/visitation/visitorLog.html?facility=" + facilitySelect);
		} else {
			disableLink(facilityListLink);
			disableLink(facilityLogLink);
		}
	});
}

/*Disable the specified link by removing the "href" attribute*/
function disableLink(link) {
	link.removeAttr("href");
}

/*Function to run when the screen first loads.*/
$(document).ready(function() {
	assignVisitorIndexOnClick();
});