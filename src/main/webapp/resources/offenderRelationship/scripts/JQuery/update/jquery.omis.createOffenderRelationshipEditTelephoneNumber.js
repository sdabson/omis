/**
 * Add a new telephone number.
 */

function addOffenderRelationshipEditTelephoneNumberItem(){
	$("#addOffenderRelationTelephoneNumberItemLink").click(function(){
		$.ajax(this.href,
		   {
				type: "GET",
				async: false,
				data:  {telephoneNumberItemIndex: offenderRelationshipTelephoneNumberIndex},
				success: function(data) 
				{
					$("#telephoneNumbersTable > tbody").append(data);
					applyTelephoneNumberItemBehavior(offenderRelationshipTelephoneNumberIndex);
					offenderRelationshipTelephoneNumberIndex++;
				},
				error: function(jqXHR, textStatus, errorThrown) 
				{
					alert("In error");
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#telephoneNumbersTable > tbody").html(jqXHR.responseText );
				}
			});
		return false;
	});
}

function applyTelephoneNumberItemBehavior(offenderRelationshipTelephoneNumberIndex) {
	$("#removeTelephoneNumber" + offenderRelationshipTelephoneNumberIndex).click(function() {
		if(document.getElementById("telephoneNumberItemsOperation" + offenderRelationshipTelephoneNumberIndex).value == "CREATE") {
			$("#telephoneNumberItems" + offenderRelationshipTelephoneNumberIndex).addClass("hidden");
			$("#telephoneNumberItemsOperation"+offenderRelationshipTelephoneNumberIndex).val("REMOVE");
			return false;
		}
		if(document.getElementById("telephoneNumberItemsOperation" + offenderRelationshipTelephoneNumberIndex).value=="REMOVE"){
			$("#telephoneNumberItems" + offenderRelationshipTelephoneNumberIndex).removeClass("removeRow");
			$("#telephoneNumberItemsOperation"+offenderRelationshipTelephoneNumberIndex).val("UPDATE");
			return false;
		}
		if(document.getElementById("telephoneNumberItemsOperation" + offenderRelationshipTelephoneNumberIndex).value=="UPDATE"){
			$("#telephoneNumberItems" + offenderRelationshipTelephoneNumberIndex).addClass("hidden");
			$("#telephoneNumberItemsOperation"+offenderRelationshipTelephoneNumberIndex).val("REMOVE");
			return false;
		}
	});
}