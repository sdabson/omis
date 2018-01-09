/**
 * Add a new telephone number.
 */

function addOffenderRelationshipCreateTelephoneNumberItem(){
	$("#addCreateOffenderRelationTelephoneNumberItemLink").click(function(){
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
	});
	
	/*for (var x = 0; x <offenderRelationshipTelephoneNumberIndex; x++) {
		document.getElementById("telephoneNumberItems[" + x + "].telephoneNumberFields.primary").onclick = function() {
			$(this).checked = true;
			for(var y = 0; y < offenderRelationshipTelephoneNumberIndex; y++) {
				if(this!=document.getElementById("telephoneNumberItems[" + y + "].telephoneNumberFields.primary")){
					document.getElementById("telephoneNumberItems[" + y + "].telephoneNumberFields.primary").checked = false;
				}
			}
		};
	}*/
}