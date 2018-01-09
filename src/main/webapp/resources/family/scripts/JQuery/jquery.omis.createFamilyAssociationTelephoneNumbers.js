/**
 * Add a new family association note.
 */
function addFamilyAssociationTelephoneNumberItem(){
	$("#addFamilyAssociationTelephoneNumberItemLink").click(function(){
		$.ajax(this.href,
		   {
				type: "GET",
				async: false,
				data:  {telephoneNumberItemIndex: familyAssociationTelephoneNumberIndex},
				success: function(data) 
				{
					$("#telephoneNumberTable > tbody").append(data);
					applyTelephoneNumberItemBehavior(familyAssociationTelephoneNumberIndex);
					familyAssociationTelephoneNumberIndex++;
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#telephoneNumberTable > tbody").html(jqXHR.responseText );
				}
			});
		return false;
	});
}

function applyTelephoneNumberItemBehavior(familyAssociationTelephoneNumberIndex) {
	$("#removeTelephoneNumber" + familyAssociationTelephoneNumberIndex).click(function() {
		if(document.getElementById("telephoneNumberItemsOperation" + familyAssociationTelephoneNumberIndex).value == "CREATE") {
			$("#telephoneNumberItems" + familyAssociationTelephoneNumberIndex).addClass("hidden");
			$("#telephoneNumberItemsOperation"+familyAssociationTelephoneNumberIndex).val("REMOVE");
			return false;
		}
		if(document.getElementById("telephoneNumberItemsOperation" + familyAssociationTelephoneNumberIndex).value=="REMOVE"){
			$("#telephoneNumberItems" + familyAssociationTelephoneNumberIndex).removeClass("removeRow");
			$("#telephoneNumberItemsOperation"+familyAssociationTelephoneNumberIndex).val("UPDATE");
			return false;
		}
		if(document.getElementById("telephoneNumberItemsOperation" + familyAssociationTelephoneNumberIndex).value=="EDIT"){
			$("#telephoneNumberItems" + familyAssociationTelephoneNumberIndex).addClass("removeRow");
			$("#telephoneNumberItemsOperation"+familyAssociationTelephoneNumberIndex).val("REMOVE");
			return false;
		}
	});
}