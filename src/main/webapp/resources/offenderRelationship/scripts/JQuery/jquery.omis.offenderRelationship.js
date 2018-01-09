/**
 * JQuery implementation for create relationships functionality.
 * 
 * Author: Joel Norris
 * Author: Sheronda Vaughn
 * Version: 0.1.0 (June 17, 2015)
 * Since: OMIS 3.0
 */
function applyOffenderRelationshipOnClick() {
	var newPerson = $("input[name='newPerson']");
	newPerson.change(function() {
		if($(this).val() == 'true') {
			$("#existingPersonFields").addClass("hidden");
			$("#createPersonFields").removeClass("hidden");
		} else {
			$("#existingPersonFields").removeClass("hidden");
			$("#createPersonFields").addClass("hidden");
		}
	});
	var newAddress = $("#newAddress");	
	var addressQuery = $("#addressQuery");
	newAddress.click(function() {
		if (newAddress.is(':checked')) {
			$("#existingAddressFields").addClass("hidden");
			$("#createAddressFields").removeClass("hidden");
			addressQuery.disabled = true;
		}		
	});
	var existingAddress = $("#existingAddress");
	existingAddress.click(function(){
		if (existingAddress.is(':checked')) {
			$("#existingAddressFields").removeClass("hidden");
			$("#createAddressFields").addClass("hidden");
			addressQuery.disabled = false;
		}
	});
	
	enterVictimDetailsChecked();
	enterFamilyMemberChecked();
	enterVisitorDetailsChecked();
	assignOnClick();
}

function createTelephoneNumbersActionMenuOnClick() {
	$("#createTelephoneNumberLink").click(function() {	
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/showTelephoneNumber.html",
		{async: false, type: "GET",
				data: { telephoneNumberIndex: currentTelephoneNumberIndex }, 
				success: function(data) {
					$("#telephoneNumbers").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#telephoneNumbers").html(jqXHR.responseText );
				}
		});
		assignRemoveOperationValue(currentTelephoneNumberIndex);
		assignCreateOperationValue(currentTelephoneNumberIndex);
		currentTelephoneNumberIndex++;
		return false;
	});
};

function createOnlineAccountsActionMenuOnClick() {
	$("#createOnlineAccountLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/offenderRelationship/showOnlineAccount.html",
		{async: false, type: "GET",
				data: { onlineAccountIndex: currentOnlineAccountIndex }, 
				success: function(data) {
					$("#onlineAccounts").append(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#onlineAccounts").html(jqXHR.responseText );
				}
		});
		assignOnlineAccountRemoveOperationValue(currentOnlineAccountIndex);
		assignOnlineAccountCreateOperationValue(currentOnlineAccountIndex);
		currentOnlineAccountIndex++;
		return false;
	});
};

function assignRemoveOperationValue(telephoneNumberIndex) {	
	$("#removeTelephoneNumber" + telephoneNumberIndex).click(function() {
		var numberRow = $("#telephoneNumberItems" + telephoneNumberIndex);		
		var operation = $("#telephoneNumberItemsOperation" + telephoneNumberIndex);			
		if(operation.val() == "UPDATE") {
			operation.val("REMOVE");
			numberRow.addClass("remove");
		} else if(operation.val() == "REMOVE") {
			operation.val("UPDATE");
			numberRow.removeClass("remove");
		} else if(operation.val() == "CREATE") {
			numberRow.remove();
		} else {
			console.log("Unknown operation: " + operation.val());
		}
		return false;
	});
};

function assignOnlineAccountRemoveOperationValue(onlineAccountIndex) {	
	$("#removeOnlineAccount" + onlineAccountIndex).click(function() {
		var numberRow = $("#onlineAccountItems" + onlineAccountIndex);		
		var operation = $("#onlineAccountItemsOperation" + onlineAccountIndex);			
		if(operation.val() == "UPDATE") {
			operation.val("REMOVE");
			numberRow.addClass("remove");
		} else if(operation.val() == "REMOVE") {
			operation.val("UPDATE");
			numberRow.removeClass("remove");
		} else if(operation.val() == "CREATE") {
			numberRow.remove();
		} else {
			console.log("Unknown operation: " + operation.val());
		}
		return false;
	});
};

function assignCreateOperationValue(telephoneNumberIndex) {
	var operation = $("#telephoneNumberItems" + telephoneNumberIndex);
	operation.val("CREATE");
};

function assignOnlineAccountCreateOperationValue(onlineAccountIndex) {
	var operation = $("#onlineAccountItems" + onlineAccountIndex);
	operation.val("CREATE");
};

function assignTelephoneNumberOnClick() {
	for (var telephoneNumberIndex = 0; telephoneNumberIndex < currentTelephoneNumberIndex; telephoneNumberIndex++) {
		var operation = $("#telephoneNumberItemsOperation" + telephoneNumberIndex);		
		var numberRow = $("#telephoneNumberItems" + telephoneNumberIndex);
		if(operation.val() == "REMOVE") {
			numberRow.addClass("remove");
		}		
		assignRemoveOperationValue(telephoneNumberIndex);	
	}	
};

function assignOnlineAccountOnClick() {
	for (var onlineAccountIndex = 0; onlineAccountIndex < currentOnlineAccountIndex; onlineAccountIndex++) {
		var operation = $("#onlineAccountItemsOperation" + onlineAccountIndex);		
		var numberRow = $("#onlineAccountItems" + onlineAccountIndex);
		if(operation.val() == "REMOVE") {
			numberRow.addClass("remove");
		}		
		assignOnlineAccountRemoveOperationValue(onlineAccountIndex);	
	}	
};

function assignOnClick(telephoneNumberIndex, onlineAccountIndex) {
};

function enterVictimDetailsChecked() {
	$("#victimFieldsRegisterDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#victimFieldsPacketSendDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	var enterVictimDetails = $("#enterVictimDetails");
	enterVictimDetails.click(function() {
		if (enterVictimDetails.is(':checked')) {
			$("#createVictimAssociation").removeClass("hidden");
		} else {
			$("#createVictimAssociation").addClass("hidden");
		}
})};

function enterVisitorDetailsChecked() {
	$("#visitationAssociationFieldsStartDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#visitationAssociationFieldsEndDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#visitationAssociationFieldsDecisionDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	var enterVictimDetails = $("#enterVisitorDetails");
	enterVictimDetails.click(function() {
		if (enterVictimDetails.is(':checked')) {
			$("#createVisitorAssociation").removeClass("hidden");
		} else {
			$("#createVisitorAssociation").addClass("hidden");
		}
})};

function enterFamilyMemberChecked() {
	$("#familyAssociationFieldsStartDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#familyAssociationFieldsEndDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#familyAssociationFieldsMarriageDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#familyAssociationFieldsDivorceDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	var enterVictimDetails = $("#enterFamilyMember");
	enterVictimDetails.click(function() {
		if (enterVictimDetails.is(':checked')) {
			$("#createFamilyAssociation").removeClass("hidden");
		} else {
			$("#createFamilyAssociation").addClass("hidden");
		}
})};