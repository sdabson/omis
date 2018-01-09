/* Profile related behavior.
 author: Ryan Johns
 version: 0.1.0 (Dec 2, 2015)
 since: OMIS 3.0 */
eventRunner.addEvent(function($) {
	applyDocumentLinks();
	applyDocumentActionLinks();
});

function applyDocumentLinks() {
	var links = document.getElementsByClassName("documentLink");
	for(var x=0; x < links.length; x++) {
		ajaxLink(links[x], document.getElementById("documents"), function(html) {
			document.getElementById("documents").innerHTML = html;
			applyDocumentActionLinks();
		});
	}
	if (!documentAssociationType) {
		documentAssociationType = "all";
	}
	runDocumentLink();
}

function applyDocumentActionLinks() {
	var actionMenus = document.getElementsByClassName("actionMenuItem");
	for (var k=0; k < actionMenus.length; k++) {
		var actionMenu = actionMenus[k];
		applyActionMenu(actionMenu, function() {
			applyRemoveLinkConfirmation();
		});
	}
}

function runDocumentLink() {
	var documentLink = document.getElementById(documentAssociationType);
	ajax(documentLink.href, document.getElementById("documents"), 
			function(html) {
		document.getElementById("documents").innerHTML = html;
		applyDocumentActionLinks();
	});
}