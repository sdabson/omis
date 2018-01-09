/**
 * Add a new email.
 */
function addOffenderRelationshipOnlineAccountItem(){
	$("#addOffenderRelationshipOnlineAccountItemLink").click(function(){
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/addCreateOffenderRelationshipOnlineAccountItem.html",
		   {
				type: "GET",
				async: false,
				data:  {onlineAccountItemIndex: offenderRelationshipOnlineAccountIndex},
				success: function(data) 
				{
					$("#onlineAccountsTable > tbody").append(data);
					applyOnlineAccountItemBehavior(offenderRelationshipOnlineAccountIndex);
					offenderRelationshipOnlineAccountIndex++;
					
					
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#onlineAccountsTable > tbody").html(jqXHR.responseText );
				}
			});
		return false;
	});
}

function applyOnlineAccountItemBehavior(offenderRelationshipOnlineAccountIndex) {
	$("#removeOnlineAccount" + offenderRelationshipOnlineAccountIndex).click(function() {
		if(document.getElementById("onlineAccountContactItemsOperation" + offenderRelationshipOnlineAccountIndex).value == "CREATE"){
			$("#onlineAccountItems" + offenderRelationshipOnlineAccountIndex).addClass("hidden");
			$("#onlineAccountContactItemsOperation"+offenderRelationshipOnlineAccountIndex).val("REMOVE");
			return false;
		}
	});
	
	/*for (var x = 0; x <offenderRelationshipOnlineAccountIndex; x++) {
		document.getElementById("onlineAccountContactItems[" + x + "].onlineAccountFields.primary").onclick = function() {
			$(this).checked = true;
			for(var y = 0; y < offenderRelationshipOnlineAccountIndex; y++) {
				if(this!=document.getElementById("onlineAccountContactItems[" + y + "].onlineAccountFields.primary")){
					document.getElementById("onlineAccountContactItems[" + y + "].onlineAccountFields.primary").checked = false;
				}
			}
	};
	}*/
}