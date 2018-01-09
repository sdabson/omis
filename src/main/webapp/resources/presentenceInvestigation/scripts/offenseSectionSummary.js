window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("date"));
	applyAddTagLink();
	applyRemoveTagLinks();	
	applyTextCounter(document.getElementById("textSummary"), document.getElementById("textCharacterCounter"));	
	applyTextCounter(document.getElementById("chargeReasonSummary"), document.getElementById("chargeReasonCharacterCounter"));
	applyTextCounter(document.getElementById("involvementReasonSummary"), document.getElementById("involvementReasonCharacterCounter"));
	applyTextCounter(document.getElementById("courtRecommendationSummary"), document.getElementById("courtRecommendationCharacterCounter"));
	if (document.getElementById("documentData") != null) {
		applyFileExtensionNamer();
	}
	offenseSectionSummaryAssociableDocumentItemsCreateOnClick();
	for(var i = 0; i < currentOffenseSectionSummaryAssociableDocumentItemIndex; i++){
		offenseSectionSummaryAssociableDocumentItemRowOnClick(i);
		for(var j = 0; j < currentDocumentTagItemIndexes[i]; j++){
			documentTagItemRowOnClick(i,j);
		}
	}
	
	var textSummary = document.getElementById("textSummary");
	textSummary.onfocus = function() {
		applySessionExtender(textSummary, 
				config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/extendSession.html", 
				10000);		
	}		
	var chargeReasonSummary = document.getElementById("chargeReasonSummary");
	chargeReasonSummary.onfocus = function() {
			applySessionExtender(chargeReasonSummary, 
					config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/extendSession.html", 
					300000);
	}
	var involvementReasonSummary = document.getElementById("involvementReasonSummary");
	involvementReasonSummary.onfocus = function() {
			applySessionExtender(involvementReasonSummary, 
					config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/extendSession.html", 
					300000);
	} 
	var courtRecommendationSummary = document.getElementById("courtRecommendationSummary");
	courtRecommendationSummary.onfocus = function() {
			applySessionExtender(courtRecommendationSummary, 
					config.ServerConfig.getContextPath() + "/presentenceInvestigation/offenseSummary/extendSession.html", 
					300000);			
	}
}