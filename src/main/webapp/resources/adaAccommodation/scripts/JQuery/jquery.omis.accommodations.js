/**
 * Author: Sheronda Vaughn
 * 
 * @param event event 
 * @param targetElement target element
 */
function applyAccommodationRowActionMenuOnClick(event, targetElement) {
	
	var element = event.target;
	var accRow = $(element).closest("tr.accommodationClass");
	var viewIssuances = $(targetElement).find(".viewIssuanceLink")[0];
	$(viewIssuances).click(function() {	
		$.ajax(viewIssuances.href,
				{async: false, type: "GET",
				cache: false,
				success: function(response) {
					var associatedIssuanceRows = document.getElementsByClassName("associatedIssuanceRow");
					for (count = 0; count < associatedIssuanceRows.length; count++) {
					 $(associatedIssuanceRows[count]).remove();
					}
					$(response).insertAfter(accRow);
					applyIssuanceRowActionMenu();
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$(accRow).html(jqXHR.responseText );
				}
			});
		return false;
	});

	applyRemoveLinkConfirmation();
};

function applyIssuanceRowActionMenu() {
	var issuanceRowActionMenus = document.getElementsByClassName("issuanceRowActionMenuLinks");
	for (var count = 0; count < issuanceRowActionMenus.length; count++) {	
		var issuanceRowActionMenu = issuanceRowActionMenus[count];
		if (issuanceRowActionMenu.getAttribute("class") != null && issuanceRowActionMenu.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(issuanceRowActionMenu, function() {
				applyRemoveLinkConfirmation();
			});
		}	
	}	
};