/**
 * Behavior to edit assets and liabilities.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	
	// Apply asset item row behavior
	function applyAssetRowBehavior(itemIndex) {
		
		// Links
		var assetRemoveLink = document.getElementById("financialAssetItems[" + itemIndex + "].removeLink");
		
		// Applies behavior for remove link
		function applyRemoveLinkClick() {
			var assetOperation = document.getElementById("financialAssetItems[" + itemIndex + "].operation");
			var assetRow = document.getElementById("financialAssetItems[" + itemIndex + "].row");
			if (assetOperation.value == "UPDATE") {
				assetRemoveLink.onclick = function() {
					assetOperation.value = "REMOVE";
					ui.addClass(assetRow, "removeRow");
					applyUnremoveLinkClick();
					return false;
				};
			} else if (assetOperation.value == "CREATE"){
				assetRemoveLink.onclick = function() {
					assetRow.parentElement.removeChild(assetRow);
					return false;
				};
			} else if (assetOperation.value == "REMOVE"){
				ui.addClass(reductionRow, "removeRow");
				applyUnremoveLinkClick();
				return false;
			} else {
				alert("Unsupported operation: " + assetOperation.value);
			}
		}
		
		// Applies behavior for unremove link
		function applyUnremoveLinkClick() {
			assetRemoveLink.onclick = function() {
				var assetOperation = document.getElementById("financialAssetItems[" + itemIndex + "].operation");
				assetOperation.value = "UPDATE";
				var assetRow = document.getElementById("financialAssetItems[" + itemIndex + "].row");
				ui.removeClass(assetRow, "removeRow");
				applyRemoveLinkClick();
				return false;
			};
		}
		applyDatePicker(document.getElementById("financialAssetItems[" + itemIndex + "].reportedDate"));
		if (assetRemoveLink != null) {
			applyRemoveLinkClick();	
		}
	}
	
	// Apply asset item row behavior
	function applyLiabilityRowBehavior(itemIndex) {
		
		// Links
		var liabilityRemoveLink = document.getElementById("financialLiabilityItems[" + itemIndex + "].removeLink");
	
		// Applies behavior for remove link
		function applyRemoveLinkClick() {
			var liabilityOperation = document.getElementById("financialLiabilityItems[" + itemIndex + "].operation");
			var liabilityRow = document.getElementById("financialLiabilityItems[" + itemIndex + "].row");
			if (liabilityOperation.value == "UPDATE") {
				liabilityRemoveLink.onclick = function() {
					liabilityOperation.value = "REMOVE";
					ui.addClass(liabilityRow, "removeRow");
					applyUnremoveLinkClick();
					return false;
				};
			} else if (liabilityOperation.value == "CREATE"){
				liabilityRemoveLink.onclick = function() {
					liabilityRow.parentElement.removeChild(liabilityRow);
					return false;
				};
			} else if (liabilityOperation.value == "REMOVE"){
				ui.addClass(reductionRow, "removeRow");
				applyUnremoveLinkClick();
				return false;
			} 
			else {
				alert("Unsupported operation: " + liabilityOperation.value);
			}
		}
		
		// Applies behavior for unremove link
		function applyUnremoveLinkClick() {
			liabilityRemoveLink.onclick = function() {
				var liabilityOperation = document.getElementById("financialLiabilityItems[" + itemIndex + "].operation");
				liabilityOperation.value = "UPDATE";
				var liabilityRow = document.getElementById("financialLiabilityItems[" + itemIndex + "].row");
				ui.removeClass(liabilityRow, "removeRow");
				applyRemoveLinkClick();
				return false;
			};
		}
		applyDatePicker(document.getElementById("financialLiabilityItems[" + itemIndex + "].reportedDate"));
		if (liabilityRemoveLink != null) {
			applyRemoveLinkClick();	
		}
		
	}
	
	// Apply recurring deduction row behavior
	function applyRecurringDeductionBehavior(itemIndex) {
		
		// Links
		var reductionRemoveLink = document.getElementById("recurringDeductionItems[" + itemIndex + "].removeLink");
		
		// Applies behavior for remove link
		function applyRemoveLinkClick() {
			var reductionOperation = document.getElementById("recurringDeductionItems[" + itemIndex + "].operation");
			var reductionRow = document.getElementById("recurringDeductionItems[" + itemIndex + "].row");
			if (reductionOperation.value == "UPDATE") {
				reductionRemoveLink.onclick = function() {
					reductionOperation.value = "REMOVE";
					ui.addClass(reductionRow, "removeRow");
					applyUnremoveLinkClick();
					return false;
				};
			} else if (reductionOperation.value == "CREATE"){
				reductionRemoveLink.onclick = function() {
					reductionRow.parentElement.removeChild(reductionRow);
					return false;
				};
			} else if (reductionOperation.value == "REMOVE"){
				ui.addClass(reductionRow, "removeRow");
				applyUnremoveLinkClick();
				return false;
			} 
			else {
				alert("Unsupported operation: " + reductionOperation.value);
			}
		}
		
		// Applies behavior for unremove link
		function applyUnremoveLinkClick() {
			reductionRemoveLink.onclick = function() {
				var reductionOperation = document.getElementById("recurringDeductionItems[" + itemIndex + "].operation");
				reductionOperation.value = "UPDATE";
				var reductionRow = document.getElementById("recurringDeductionItems[" + itemIndex + "].row");
				ui.removeClass(reductionRow, "removeRow");
				applyRemoveLinkClick();
				return false;
			};
		}
		applyDatePicker(document.getElementById("recurringDeductionItems[" + itemIndex + "].reportedDate"));
		if (reductionRemoveLink != null) {
			applyRemoveLinkClick();	
		}
	}
	
	// Apply asset behavior
	applyActionMenu(document.getElementById("assetsActionMenuLink"), function() {
		var createAssetLink = document.getElementById("createAssetLink");
		createAssetLink.onclick = function() {
			var url = createAssetLink.getAttribute("href") + "?itemIndex=" + financialAssetItemIndex;
			var request = new XMLHttpRequest();
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				ui.appendHtml(document.getElementById("assetsBody"), request.responseText);
				applyAssetRowBehavior(financialAssetItemIndex);
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
			}
			financialAssetItemIndex = financialAssetItemIndex + 1;
			return false;
		};
	});
	// Apply liability behavior
	applyActionMenu(document.getElementById("liabilitiesActionMenuLink"), function() {
		var createAssetLink = document.getElementById("createLiabilityLink");
		createAssetLink.onclick = function() {
			var url = createAssetLink.getAttribute("href") + "?itemIndex=" + financialLiabilityItemIndex;
			var request = new XMLHttpRequest();
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				ui.appendHtml(document.getElementById("liabilitiesBody"), request.responseText);
				applyLiabilityRowBehavior(financialLiabilityItemIndex);
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
			}
			financialLiabilityItemIndex = financialLiabilityItemIndex + 1;
			return false;
		};
	});
	// Apply recurring deduction behavior
	applyActionMenu(document.getElementById("recurringDeductionActionMenuLink"), function() {
		var createRecurringDeductionLink = document.getElementById("createRecurringDeductionLink");
		createRecurringDeductionLink.onclick = function() {
			var url = createRecurringDeductionLink.getAttribute("href") + "?itemIndex=" + recurringDeductionItemIndex;
			var request = new XMLHttpRequest();
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				ui.appendHtml(document.getElementById("recurringDeductionBody"), request.responseText);
				applyRecurringDeductionBehavior(recurringDeductionItemIndex);
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
			}
			recurringDeductionItemIndex = recurringDeductionItemIndex + 1;
			return false;
		};
	});
	
	for (var itemIndex = 0; itemIndex < financialAssetItemIndex; itemIndex++) {
		if (document.getElementById("financialAssetItems[" + itemIndex + "].row") != null) {
			applyAssetRowBehavior(itemIndex);
		}
	}
	for (var itemIndex = 0; itemIndex < financialLiabilityItemIndex; itemIndex++) {
		if (document.getElementById("financialLiabilityItems[" + itemIndex + "].row") != null) {
			applyLiabilityRowBehavior(itemIndex);
		}
	}
	for (var itemIndex = 0; itemIndex < recurringDeductionItemIndex; itemIndex++) {
		if (document.getElementById("recurringDeductionItems[" + itemIndex + "].row") != null) {
			applyRecurringDeductionBehavior(itemIndex);
		}
	}
	for(var i = 0; i < financialDocumentAssociationItemIndex; i++) {
		financialDocumentAssociationItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++) {
			documentTagItemRowOnClick(i,j);
		}
	}
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("financialProfileForm"));
	financialDocumentAssociationItemsCreateOnClick();
	
};
