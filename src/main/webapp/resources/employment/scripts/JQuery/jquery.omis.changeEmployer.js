function employerOnClick() {
	$("#newEmployerYes").click(function() {
		$("#changeEmployerContainor").removeClass("hidden");
		$("#changeEmployerAddressFieldsRadioButtonsContainor").removeClass("hidden");
		$("#changeEmployerAddressFieldsContainer").addClass("hidden");
		$("#newEmployerInput").removeAttr('disabled');
		$("#employerInput").attr('disabled', 'disabled');
		$("#newAddressNo").prop('checked', 'checked');
	});
	$("#newEmployerNo").click(function() {
		$("#changeEmployerContainor").addClass("hidden");
		$("#changeEmployerAddressFieldsRadioButtonsContainor").addClass("hidden");
		$("#changeEmployerAddressFieldsContainer").addClass("hidden");
		$("#newEmployerInput").attr('disabled', 'disabled');
		$("#employerInput").removeAttr('disabled');
	})
};

function addressOnClick() {
	$("#newAddressYes").click(function() {
		$("#existingChangeEmployorAddress").attr('disabled', 'disabled');
		$("#changeEmployerAddressFieldsContainer").removeClass("hidden");
	});
	$("#newAddressNo").click(function() {
		$("#existingChangeEmployorAddress").removeAttr('disabled');
		$("#changeEmployerAddressFieldsContainer").addClass("hidden");
	})
};
	
	
	
	
	
	
	
	
