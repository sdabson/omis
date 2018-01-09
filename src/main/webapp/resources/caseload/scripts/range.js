$(document).ready(function() {
	$("#dateRangeStart.date, #dateRangeEnd.date").datepicker();
	
	/*$("#dateRangeEndContainer").hide();
	$("#isRanged").change(function() {
		$("#dateRangeEndContainer").toggle();
		$("#dateRangeEnd").val("");
	});*/
	$("#dateRangeRefresh").click(function(personID) {
		var url = config.ServerConfig.getContextPath()+"/caseload/refreshList.html?searchCriteria=" + personId;
		
		if ($(dateRangeStart).val()) { 			
			url +='&dateRangeStart='+$(dateRangeStart).val();
			

			if ($(dateRangeEnd).val()) {
				url += '&dateRangeEnd='+$(dateRangeEnd).val(); 
			}
		}
		$.ajax({			
			type:'GET',
			dataType:"html",			
			url: url,
			success: function(offenderSummary) {
				$("#caseloadListTableBody>tbody:last").html(offenderSummary);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}});
	});
});