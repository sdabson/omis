/**
 * Add a new email.
 */
function addOffenderRelationshipOnlineAccountItem(){
	$("#addOffenderRelationshipOnlineAccountItemLink").click(function(){
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/update/addEditOffenderRelationshipOnlineAccountItem.html",
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
		if(document.getElementById("onlineAccountContactItemsOperation" + offenderRelationshipOnlineAccountIndex).value == "REMOVE"){
			$("#onlineAccountItems" + offenderRelationshipOnlineAccountIndex).removeClass("removeRow");
			$("#onlineAccountContactItemsOperation"+offenderRelationshipOnlineAccountIndex).val("UPDATE");
			return false;
		}
		if(document.getElementById("onlineAccountContactItemsOperation" + offenderRelationshipOnlineAccountIndex).value == "UPDATE"){
			$("#onlineAccountItems" + offenderRelationshipOnlineAccountIndex).addClass("hidden");
			$("#onlineAccountContactItemsOperation" + offenderRelationshipOnlineAccountIndex).val("REMOVE");
			return false;
		}
	});
}