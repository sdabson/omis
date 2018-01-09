/** Prepares index JavaScript
 *
 * author JJRKS (June 3, 2013)
 *  
 * Dependencies -
 *	resources/common/scripts/EventRunner.js*/ 
eventRunner.addEvent(function() {
	//Framebreaker
	if (top.location != location) {
	    top.location.href = document.location.href ;
	}
});
// Sets up menu
eventRunner.addEvent(function() {
 	//var desktopCount = 1; // Not used!!!???? Abson (Dec 17, 2013)
	var lookUpMessageResolver = new common.MessageResolver("omis.application.msgs.application");
	
	// displays launch menu
	$("#applicationLaunchIcon").click(function() {
		var launchMenu = $("#launchMenu");
		var opened = launchMenu.css("display") == "block";
		if (!opened) {
			launchMenu.css("display", "block");
			loadMenuItems();
		} else {
			launchMenu.css("display", "none");
		}
	});
	
	// displays navigation menu
	$("#navigationLaunchIcon").click(function() {
		var navigationMenu = $("#navigationMenu");
		var opened = navigationMenu.css("display") == "block";
		if (!opened) {
			navigationMenu.css("display", "block");
		} else {
			navigationMenu.css("display", "none");
		}
	});
	
	$("#launchMenu").mouseleave( function() {  $(this).css("display", "none");});
	
	cartographer = new $.fn.cartographer({
		contentElement: $("#content"),
		contentMapElement: $("#navigationMenuContentItems"),
		contentHistoryMapElement: $("#navigationMenuHistoricContentItems"),
		contentMiniMapElement: $("#navigationItems")
	});
	
	
	$("#searchCriteria").offenderSearch({resultArea:"#launchArea", callBackFunction : function(json, element) {
		
	
	/*	img = document.createElement('img');
		$(img).attr('src', config.ServerConfig.getContextPath()+"/offenderPhoto/display.html?offender="+json.personId+"&contentType=image/jpg");
		$(img).css('height','40px');
		$(img).css('width','64px');
		div = document.createElement('span');
		$(div).css('height', '42px');
		$(div).css('width', '66px');
		
		label = document.createElement('span');
		$(label).html(json.offenderNumber);
		$(label).css('position', 'absolute');
		$(div).append(label);
		$(div).append(img);*/
		cartographer.add(config.ServerConfig.getContextPath()+'/offender/modules.html?offender='+ json.personId);
		
	}});
	
	
	
	$("#searchCriteria").click(function() {
		if ($(this).val() == lookUpMessageResolver.getMessage("searchCriteriaDefault")) {
			$(this).val("");
			$(this).removeClass("defaultDisplayText");
		}
		});
	
	$("#searchCriteria").blur(function() {
		if ($(this).val() == "") {
			loadMenuItems();
		}
	});
	
	function loadMenuItems() {
		$("#searchCriteria").val(lookUpMessageResolver.getMessage("searchCriteriaDefault"));
		$(this).addClass("defaultDisplayText");
		$.ajax({
			type: "GET",
			async: false,
			url: config.ServerConfig.getContextPath() + "/application/launchMenuItems.html",
			beforeSend: function() {
				$("#launchArea").html("<img src='"
						+ config.ServerConfig.getContextPath()
						+ "/resources/common/images/ajaxLoader.gif'/>");
			},
			success: function(response) {
				$("#launchArea").html(response);
				$(".launchMenuItemLink").click(function() {
					$("#launchMenu").css("display", "none");
					loadContent($(this).attr("href"));
					return false;
				});
				$("#searchCriteria").select();
				$("#searchCriteria").focus();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#launchArea").html(jqXHR.responseText);
			}
		});
	}
	
	$(".leftPanelLink").click(function() {
		var leftPanel = document.getElementById("leftPanel");
		var leftPanelContent = document.getElementById("leftPanelContent");
		togglePanel(leftPanel, leftPanelContent, this);
		return false;
	});
	
	$(".rightPanelLink").click(function() {
		var rightPanel = document.getElementById("rightPanel");
		var rightPanelContent = document.getElementById("rightPanelContent");
		togglePanel(rightPanel, rightPanelContent, this);
		return false;
	});
	
	function togglePanel(panel, panelContent, link) {
		var opened = $(panelContent).is(":visible");
		if (opened) {
			hidePanel(panel, panelContent, link);
		} else {
			showPanel(panel, panelContent, link);
		}
	}
	
	function showPanel(panel, panelContent, link) {
		$(panelContent).show();
		$(panel).css("width", "193px");
	}
	
	function hidePanel(panel, panelContent, link) {
		$(panelContent).hide();
		$(panelContent).empty();
		$(panel).css("width", "33px");
	}
});

/**
 * Loads the content .
 * 
 * @param url URL from which to load content
 */
function loadContent(url) {
	alert("This will be loaded " + url);
}