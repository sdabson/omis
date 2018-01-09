/** Sets up employment edit form.
 * author Ryan Johns (Sep 21, 2012)
 *
 * Dependencies -
 *  resources/common/scripts/EventRunner.js
 *  resources/common/scripts/Edit.js
 *  resources/3rdparty/JQuery/jquery.min.js
 *  resources/common/scripts/ServerConfig.js */

eventRunner.addEvent(fieldLogic);
eventRunner.addEvent(employerSetup);

/* Sets up conditional fields */
function fieldLogic() {
	if ($("#verification\\.result1:checked").val()) {
				$("#verification\\.method").show();
				$("#verify").show();
			} else {
				$("#verify").hide();
				$("#verification\\.method").hide();
			}
				
			$("#verification\\.result1").change( function() {
				if ($("#verification\\.result1:checked").val()) {
					$("#verification\\.method").show();
					$("#verify").show();
				} else {
					$("#verify").hide();
					$("#verification\\.method").hide(); 
				}
			});
		
			if ($("#employmentDates\\.endDate").val().length < 1) {
					$("#end").hide();
					$("#LeaveReason").hide();
				} else {
					$("#LeaveReason").show();
					$("#end").show();
				}
			$("#employmentDates\\.endDate").change( function() {
				if ($(this).val().length < 1) {
						$("#end").hide();
						$("#LeaveReason").hide();
				} else {
					$("#LeaveReason").show();
					$("#end").show();
				}
			});		
}//fieldLogic	
			
/* Binds Employer form submission to save button */
function bindSubmit() {
	$.ajax({
		type:"POST",
		url:config.ServerConfig.getContextPath()+"/employer/create.html",
		data: $("#employerForm").serialize()
	}).done(function(msg){
		$("#employerList").html(msg);

				
		$("#employerSubmit").click(bindSubmit);
				
		$("#employerList table tr ").each(function() {
			idtd = $(this).find("td:first");
			id = idtd.html();
			button = $("<button type=\"button\">Select</button>").click(function() {
				nametd = $(this).parent().parent().find("td:nth-child(2)").html();
				telephoned = $(this).parent().parent().find("td:nth-child(3)").html();
				addressd = $(this).parent().parent().find("td:nth-child(4)").html();
				$("#employerNameSpan").html(nametd);
				$("#employerNumber").html(telephoned);
				$("#employerAddress").html(addressd);
				$("#employer").val(id); 
				$("#employer-dialog").dialog("close");			
			});
		
			idtd.html(button);
		});
		position();
	});
}//bindSubmit

/* Sets up employer interaction/selection. */
function employerSetup() {
	$("#employer-dialog").dialog({ 
		buttons: { "Close":function() { $(this).dialog("close"); }},
		width: 600,
		height: 400,
		autoOpen: false
	});
			
	$("#employer-dialog").bind("dialogresizestop", position);
			
			
	$("#newEmployer").click(function() {
		$.ajax({
			url:config.ServerConfig.getContextPath()+"/employer/minimalCreate.html"
		}).done(function(msg) {
			$("#employerList").html(msg);
			
			$("#employerSubmit").click(bindSubmit);
		}).error(function(event, request, settings){
			  $(this).append("<li>Error requesting page " + settings.url + "</li>");
		});
	});	
				
	$("#employerName").keyup( function(event) {
		if ($("#employerName").val().length >= 4) 	
			$.ajax({
				url:config.ServerConfig.getContextPath()+"/employer/searchList.html?employer="+$('#employerName').val() 
			}).done(function(msg) { 
				$("#employerList").html(msg);
				$("#employerList table tr ").each(function() {
					idtd = $(this).find("td:first");
					id = idtd.html();
					button = $("<button type=\"button\">Select</button>").click(function() {
					nametd = $(this).parent().parent().find("td:nth-child(2)").html();
					telephoned = $(this).parent().parent().find("td:nth-child(3)").html();
					addressd = $(this).parent().parent().find("td:nth-child(4)").html();
					$("#employerNameSpan").html(nametd);
					$("#employerNumber").html(telephoned);
					$("#employerAddress").html(addressd);
					$("#employer").val(id); 
					$("#employer-dialog").dialog("close");				
				}).error(function(event, request, settings){
					  $(this).append("<li>Error requesting page " + settings.url + "</li>");
				});
								
				idtd.html(button);
			});
							
				position();
			}).fail(function() { alert("error"); });
		else {
			$("#employerList").html('');
		}	
	});
			
	$("#employerLookup").click(function() { $("#employer-dialog").dialog("open"); });
}//employerSetup		