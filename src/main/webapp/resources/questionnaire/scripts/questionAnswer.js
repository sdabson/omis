window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyCreateQuestionOnClick('last');
	var rows = document.getElementsByClassName('questionAnswerSet');
	for(var i = 0; i < rows.length; i++){
		applyQuestionSetFunctions(i);
	}
}

