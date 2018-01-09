/**
 * visitorList.js
 * 
 * author: Joel Norris
 * version: 0.1.0 (Aug. 16, 2013)
 * since: OMIS 3.0
 */

function assignVisitorListOnClick() {
	$(".visitorSummary").each(function() {
		var element = $(this);
		var id = element.attr("id");
		var visitorSummary = id.replace("visitorSummary", "");
		var dissociateLink = $("#" + id + "DissociateLink");
		var offender = $("#offender").val();
		var facility = $("#facility").val();
		dissociateLink.click(function(){
			dissociateVisitor(offender, facility, visitorSummary);
		});
	});
	
	$(".visitationAssociation").each(function() {
		var element = $(this);
		var id = element.attr("id");
		var visitationAssociation = id.replace("visitationAssociation", "");
		var startVisitLink = $("#" + id + "StartVisitLink");
		var offender = $("#offender").val();
		var facility = $("#facility").val();
		var dialog = $("#dialog" + visitationAssociation);
		startVisitLink.click(function(){
			showDialog(offender, facility, visitationAssociation, dialog);
		});
	
		$(dialog).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: [{
				text: "Save Badge Number", 
				click: function() {
					saveBadgeNumber(dialog, offender, facility, visitationAssociation);
				},
			}, {
				text: "Cancel",
				click: function() {
					$(this).dialog( "close" );
				}
			}]
		});
	});
	
	$(".currentVisit").each(function() {
		var element = $(this);
		var id = element.attr("id");
		var visit = id.replace("currentVisit", "");
		var endVisitLink = $("#" + id + "EndVisitLink");
		var offender = $("#offender").val();
		var facility = $("#facility").val();
		if (offender) {
			endVisitLink.click(function(){
				endVisitOffender(visit, offender);
			});
		}
		if (facility) {
			endVisitLink.click(function(){
				endVisitFacility(visit, facility);
			});
		}
	});
}

function dissociateVisitor(offender, facility, visitationAssociation) {
	$.ajax({
		type: "GET",
		async: true,
		url: config.ServerConfig.getContextPath() + "/visitation/dissociate.html",
		data: {visitationAssociation: visitationAssociation,
			offender: offender, facility: facility},
		beforeSend: function() {
			$("#visitationAssociations").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$("#visitationAssociations").html(data);
			assignVisitorListOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitationAssociations").html(jqXHR.responseText );
		}
	});
}

function saveBadgeNumber(dialog, offender, facility, visitationAssociation) {
	if ($("#badgeNumber").val().length > 0){
		$.ajax({
			type: "GET",
			async: true,
			url: config.ServerConfig.getContextPath() + "/visitation/startVisit.html",
			data: {visitationAssociation: visitationAssociation,
				badgeNumber: $("#badgeNumber").val(),
				offender: offender, facility: facility},
			beforeSend: function() {
				$("#visitationAssociations").html("<img src='"
					+ config.ServerConfig.getContextPath()
					+ "/resources/common/images/ajaxLoader.gif'/>");
			},
			success: function(data) {
				$("#visitationAssociations").html(data);
				assignVisitorListOnClick();
				$(dialog).html('');
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#visitationAssociations").html(jqXHR.responseText );
			}
		});
		$(dialog).dialog("close");
	} else {
		$(".error").html("Please enter a badge number.");
	}
}

function showDialog(offender, facility, visitationAssociation, dialog) {
	$.ajax({
		type: "GET",
		async: true,
		url: config.ServerConfig.getContextPath() + "/visitation/showBadgeNumberForm.html",
		data: {visitationAssociation: visitationAssociation},
		beforeSend: function() {
			$(dialog).html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$(dialog).html(data);
			$("#badgeNumberForm").submit(function(){
				saveBadgeNumber(dialog, offender, facility, visitationAssociation);
				return false;
			});
			$(dialog).dialog("open");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$(dialog).html(jqXHR.responseText );
		}
	});
}

function endVisitOffender(visit, offender){
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/visitation/endVisit.html",
		data: {visit: visit,
			offender: offender},
		beforeSend: function() {
			$("#visitationAssociations").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$("#visitationAssociations").html(data);
			assignVisitorListOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitationAssociations").html(jqXHR.responseText );
		}
	});
}

function endVisitFacility(visit, facility){
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/visitation/endVisit.html",
		data: {visit: visit,
			facility: facility},
		beforeSend: function() {
			$("#visitationAssociations").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$("#visitationAssociations").html(data);
			assignVisitorListOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitationAssociations").html(jqXHR.responseText );
		}
	});
}

//Function to run when the screen first loads.
$(document).ready(function() {
	assignVisitorListOnClick();
});