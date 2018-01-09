$(document).ready(function() {
	applyRemoveLinkConfirmation("removeLink");
	
	$(".offenderCaseList").each(function() {
		var element = $(this);
		$.ajax({
			url:config.ServerConfig.getContextPath() + "/caseLoad/caseOffenderAssignment/updateSummaryList.html?caseLoad="+element.attr("id")
		}).success(function(html) {
			element.children("tbody").eq(0).html(html);
		});
		
		$(".workerCaseList").each(function() {
			var element = $(this);
			$.ajax({
				url:config.ServerConfig.getContextPath() + "/caseLoad/caseWorkerAssignment/updateSummaryList.html?caseLoad="+element.attr("id")
			}).success(function(html) {
				element.children("tbody").eq(0).html(html);
			});
		});
	});
});