function applyFeeRequirementLinkBehavior(feeRequirementIndex) {
	$("#feeRequirement" + feeRequirementIndex + "StartDate").attr("autocomplete", "off");
	$("#feeRequirement" + feeRequirementIndex + "StartDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#feeRequirement" + feeRequirementIndex + "EndDate").attr("autocomplete", "off");
	$("#feeRequirement" + feeRequirementIndex + "EndDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
};

function assignRemoveOperationValue(feeRequirementIndex) {
	$("#removeFeeRequirement" + feeRequirementIndex).click(function() {
		var feeRequirementRow = $("#feeRequirementRow" + feeRequirementIndex);
		var operation = $("#feeRequirementOperation" + feeRequirementIndex);
		var feeRequirementId = $("#feeRequirementId" + feeRequirementIndex);
		var fee = $("#feeRequirement" + feeRequirementIndex + "AdjustedFee");
		var startDate = $("#feeRequirement" + feeRequirementIndex + "StartDate");
		if(operation.val()=="UPDATE") {
			operation.val("REMOVE");
			feeRequirementRow.addClass("remove");
		} else if(operation.val()=="REMOVE") {
			operation.val("UPDATE");
			feeRequirementRow.removeClass("remove");			
		} else if(operation.val()=="CREATE") {
			feeRequirementRow.remove();
		}else {
			console.log("Unknown operation" + operation.val());
		}		
		return false;		
		});	
};

function applyOfficerSearch(feeRequirementIndex) {
	applyStaffSearch(document.getElementById("feeRequirement"+feeRequirementIndex+"Authority"),
			document.getElementById("feeRequirement"+feeRequirementIndex+"Officer"), 
			document.getElementById("feeRequirement"+feeRequirementIndex+"OfficerCurrentLabel"),
			null,
			document.getElementById("feeRequirement"+feeRequirementIndex+"OfficerClear"));
};

function assignCreateOperationValue(feeRequirementIndex) {
	var operation = $("#feeRequirementOperation" + feeRequirementIndex);
	operation.val("CREATE");
};

function assignFeeRequirementOnClick(feeRequirementIndex) {
};