/**
 * Behavior for the caseload listing screen.
 * 
 * Author: Sheronda Vaughn
 */
window.onload = function() {
	applyDatePicker(document.getElementById("contactDate"));
	applyDatePicker(document.getElementById("dateRangeStart"));
	applyDatePicker(document.getElementById("dateRangeEnd"));
	applyDatePicker(document.getElementById("endDate"));
	applyStaffSearch(document.getElementById("contactByName"),
			document.getElementById("contactBy"),
			document.getElementById("contactByCurrentLabel"),
			document.getElementById("contactByUser"),			
			document.getElementById("clearContactByUserLink"));
		
		applyDatePicker(document.getElementById("reassignDate"));
		applyCaseloadSearch(document.getElementById("caseloadName"),
				document.getElementById("caseload"),
				document.getElementById("clearCaseloadLink"));	
		
		applyPersonSearch(document.getElementById("staffMemberName"),
				document.getElementById("staffMember"),
				document.getElementById("staffMemberUser"),			
				document.getElementById("clearStaffMemberUserLink"));
		var rowActionMenus = document.getElementsByClassName("rowActionMenuLinks");
		for (var count = 0; count < rowActionMenus.length; count++) {			
			var rowActionMenu = rowActionMenus[count];
			if (rowActionMenu.getAttribute("class") != null && rowActionMenu.getAttribute("class").indexOf("actionMenuItem") > -1) {
				applyActionMenu(rowActionMenu);
			}		
		};
		
		$("#dateRangeRefresh").click(function() {			
			var url = config.ServerConfig.getContextPath()+"/caseload/refreshList.html?searchCriteria=" + workerAssignmentId;
			var dateRangeStartDate = $("#dateRangeStart");
			var dateRangeEndDate = $("#dateRangeEnd");
			if ($(dateRangeStart).val() || $(dateRangeEnd).val()) {
				$("#dateRangeRefreshGroup").removeClass("hidden");
			} else {
				alert("No date range specified");
			}
			if ($(dateRangeStart).val()) { 			
				url +='&dateRangeStart='+$(dateRangeStart).val();	
				dateRangeStartDate = $(dateRangeStart).val();					
				$("#dateRangeStart").append(dateRangeStartDate);
			}
			if ($(dateRangeEnd).val()) {
				url +='&dateRangeEnd='+$(dateRangeEnd).val();
				dateRangeEndDate = $(dateRangeEnd).val();
				$("#dateRangeEndDate").append(dateRangeEndDate);
			}	
			$.ajax({			
				type:'GET',
				dataType:"html",			
				url: url,
				success: function(transferItems) {
					$("#transferingOffenders").html(transferItems);
				},
				error: function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}});			
		});		
}