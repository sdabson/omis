$(document).ready(function() {
	$("#startDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#endDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	applyFormUpdateChecker(document.getElementById("specialNeedForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyTextCounter(document.getElementById("comment"), document.getElementById("commentCharacterCounter"));
	applyTextCounter(document.getElementById("sourceComment"), document.getElementById("sourceCommentCharacterCounter"));
	applyActionMenu(document.getElementById("specialNeedNoteActionMenuLink"), createSpecialNeedNotesActionMenuOnClick);
	assignOnClick();
	
	applyDatePicker(document.getElementById("date"));
	applyAddTagLink();
	applyRemoveTagLinks();
	if (document.getElementById("documentData")) {
		applyFileExtensionNamer();
	}
	document.getElementById("removeDocumentLink").onclick = function() {
		document.getElementById("documentCategory").selectedIndex = 0;
		document.getElementById("title").value = "";
		document.getElementById("date").value = "";
		var documentTagRowElts = document.getElementsByClassName("documentTagRow");
		for(var x =0; x < documentTagRowElts.length; x++) {
			if (documentTagRowElts[x].parentNode) {
				documentTagRowElts[x].parentNode.removeChild(documentTagRowElts[x]);
			}
		}
		var dataElt = document.getElementById("documentData");
		dataElt.removeAttribute("disabled");
		ui.removeClass(dataElt, "hidden");
		dataElt.value = "";
		ui.addClass(document.getElementById("specialNeedDocumentDownloadLink"), "hidden");
		document.getElementById("data").setAttribute("disabled", "disabled");
		var documentItemOperationElt = document.getElementById("documentItemOperation");
		if (documentItemOperationElt.value == "UPDATE") {
			documentItemOperationElt.value = "REMOVE";
			ui.addClass(document.getElementById("specialNeedDocument"), "remove");
		}
		return false;
	};
	document.getElementById("documentCategory").onchange = function() {
		var documentItemOperationElt = document.getElementById("documentItemOperation");
		if (documentItemOperationElt.value == "REMOVE") {
			documentItemOperationElt.value = "CREATE";
			ui.removeClass(document.getElementById("specialNeedDocument"), "remove");
		}
	};
	document.getElementById("title").onchange = function() {
		var documentItemOperationElt = document.getElementById("documentItemOperation");
		if (documentItemOperationElt.value == "REMOVE") {
			documentItemOperationElt.value = "CREATE";
			ui.removeClass(document.getElementById("specialNeedDocument"), "remove");
		}
	};
	document.getElementById("date").onchange = function() {
		var documentItemOperationElt = document.getElementById("documentItemOperation");
		if (documentItemOperationElt.value == "REMOVE") {
			documentItemOperationElt.value = "CREATE";
			ui.removeClass(document.getElementById("specialNeedDocument"), "remove");
		}
	};
});

function applyFileExtensionNamer() {
	var documentData = document.getElementById("documentData");
	var fileExtension = document.getElementById("dataFileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
		var documentItemOperationElt = document.getElementById("documentItemOperation");
		if (documentItemOperationElt.value == "REMOVE") {
			documentItemOperationElt.value = "CREATE";
			ui.removeClass(document.getElementById("specialNeedDocument"), "remove");
		}
	}
}