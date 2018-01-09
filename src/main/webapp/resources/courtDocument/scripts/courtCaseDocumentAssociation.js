/* Court Case document association behaviors.
 * author Ryan Johns
 * version 0.1.0 (Dec 7, 2015)
 * since OMIS 3.0 */ 
eventRunner.addEvent(function($) {
	applyCourtCaseDocumentActionMenus();
	applyDatePickers();
	if (document.getElementById("documentData")) {
		applyFileExtensionNamer();
	}
	applyFormBehavior();
	applyRemoveTagLinks();
	applyAddTagLink();
	applyDownloadLink();
});

function applyCourtCaseDocumentActionMenus() {
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	for (var x=0; x < actionMenus.length; x++) {
		applyActionMenu(actionMenus[x]);
	}
}

function applyDatePickers() {
	var dateInputs = document.getElementsByClassName("date");
	for (var k=0; k <dateInputs.length; k++) {
		applyDatePicker(dateInputs[k]);
	}
}
function applyFileExtensionNamer() {
	var documentData = document.getElementById("documentData");
	var fileExtension = document.getElementById("dataFileExtension");
	documentData.onchange = function() {
		var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
		fileExtension.value = fileExtensionValue;
	}
}
function applyFormBehavior() {
	var form = document.getElementById("form");
	form.onsubmit = function() {
		reindexDocumentTags();
	};
	applyFormUpdateChecker(form);
}

function applyDownloadLink() {
	var link = document.getElementById("courtCaseDocumentDownloadLink");
	link.onclick = function(event) {
		var iframe = document.getElementById("downloadFrame");
		if (iframe == null) {
			iframe = document.createElement("iframe");
			iframe.id="downloadFrame";
			iframe.className += " hide";
			document.getElementsByTagName("body")[0].appendChild(iframe);
		}
		iframe.src = event.target.href;
		return false;
	};
}