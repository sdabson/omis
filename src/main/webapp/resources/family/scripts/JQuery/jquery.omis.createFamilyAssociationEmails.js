/**
 * Add a new family association note.
 */
function addFamilyAssociationEmailItem(){
	$("#addFamilyAssociationEmailItemLink").click(function(){
		$.ajax(this.href,
		   {
				type: "GET",
				async: false,
				data:  {emailItemIndex: familyAssociationOnlineAccountIndex},
				success: function(data) 
				{
					$("#onlineAccountTable > tbody").append(data);
					applyEmailItemBehavior(familyAssociationOnlineAccountIndex);
					familyAssociationOnlineAccountIndex++;
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#onlineAccountTable > tbody").html(jqXHR.responseText );
				}
			});
		return false;
	});
}

function applyEmailItemBehavior(familyAssociationOnlineAccountIndex) {
	$("#removeOnlineAccount" + familyAssociationOnlineAccountIndex).click(function() {
		if(document.getElementById("familyAssociationOnlineAccountItemsOperation" + familyAssociationOnlineAccountIndex).value == "CREATE"){
			$("#onlineAccountItems" + familyAssociationOnlineAccountIndex).addClass("hidden");
			$("#familyAssociationOnlineAccountItemsOperation"+familyAssociationOnlineAccountIndex).val("REMOVE");
			return false;
		}
		if(document.getElementById("familyAssociationOnlineAccountItemsOperation" + familyAssociationOnlineAccountIndex).value=="REMOVE"){
			$("#onlineAccountItems" + familyAssociationOnlineAccountIndex).removeClass("removeRow");
			$("#familyAssociationOnlineAccountItemsOperation"+familyAssociationOnlineAccountIndex).val("UPDATE");
			return false;
		}
		if(document.getElementById("familyAssociationOnlineAccountItemsOperation" + familyAssociationOnlineAccountIndex).value=="EDIT"){
			$("#onlineAccountItems" + familyAssociationOnlineAccountIndex).addClass("removeRow");
			$("#familyAssociationOnlineAccountItemsOperation"+familyAssociationOnlineAccountIndex).val("REMOVE");
			return false;
		}
	});
}