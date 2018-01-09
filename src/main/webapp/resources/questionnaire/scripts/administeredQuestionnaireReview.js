window.onload = function(){
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	var btn = document.getElementById("saveAsFinal");
	var resolver = new common.MessageResolver("omis.questionnaire.msgs.questionnaire");
	var message = resolver.getMessage("confirmFinalizeQuestionnaire", null);
	
	btn.onclick = function() {
		if (confirm(message)) {
			return true;
		} else {
			return false;
		}
	};
}