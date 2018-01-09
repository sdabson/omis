$(document).ready(function(feeRequirementIndex) {
	applyActionMenu(document.getElementById("actionMenuLink"));	
	$("#startDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#endDate").datepicker({
		changeMonth: true,
		changeYear: true
	});		
	applyFormUpdateChecker(document.getElementById("supervisionFeeForm"));
	$("#addCourtFeeRequirementLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/supervisionFee/addCourtFeeRequirement.html",
				{async: false, type: "GET",
				data: { feeRequirementIndex: currentFeeRequirementIndex },
				success: function(data) {
					$("#feeRequirements").append(data);
				}
			});
		applyFeeRequirementLinkBehavior(currentFeeRequirementIndex);
		assignRemoveOperationValue(currentFeeRequirementIndex);
		assignCreateOperationValue(currentFeeRequirementIndex);	
		currentFeeRequirementIndex++;
		return false;
	});
	$("#addOfficerFeeRequirementLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/supervisionFee/addOfficerFeeRequirement.html",
				{async: false, type: "GET",
				data: { feeRequirementIndex: currentFeeRequirementIndex },
				success: function(data) {
					$("#feeRequirements").append(data);
				}
			});
		applyFeeRequirementLinkBehavior(currentFeeRequirementIndex);
		assignRemoveOperationValue(currentFeeRequirementIndex);
		assignCreateOperationValue(currentFeeRequirementIndex);
		applyOfficerSearch(currentFeeRequirementIndex);
		currentFeeRequirementIndex++;	
		return false;
	});
	for (var feeRequirementIndex = 0; feeRequirementIndex < currentFeeRequirementIndex;  feeRequirementIndex++) {
		var operation = $("#feeRequirementOperation" + feeRequirementIndex);
		applyFeeRequirementLinkBehavior(feeRequirementIndex);
		if(operation.val()=="REMOVE") {
			var feeRequirementRow = $("#feeRequirementRow" + feeRequirementIndex);
			feeRequirementRow.addClass("remove");
		} 
		assignRemoveOperationValue(feeRequirementIndex);
		applyOfficerSearch(feeRequirementIndex);
	}
	assignFeeRequirementOnClick(feeRequirementIndex);
});
