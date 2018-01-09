/**
 * Needs jquery implementation java script.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (July 14, 2015)
 * Since: OMIS 3.0
 */

function applyDomainButtonOnClick() {
	$(".domainButton").each(function ()  {
		$(this).click(function() {
			if ($(this).attr("id") == "allDomains") {
				$("#domain").val('');
			} else {
				var id = $(this).attr("id");
				var domainId = id.replace("needDomain", "");
				$("#domain").val(domainId);
			}
			document.getElementById("domainForm").submit();
		});
	}) 
}

function applyRowActionMenusOnClick() {
	$(".rowActionMenuLink").each(function () {
		applyActionMenu(document.getElementById($(this).attr("id")),
				applyRowActionMenuOnClick);
	});
}


function applyRowActionMenuOnClick() {
			applyRemoveLinkConfirmation();
}