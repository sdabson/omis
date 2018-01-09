/* Document tag functionality.
 * author: Ryan Johns
 * version: 0.1.0 (Dec 22, 2015)
 * since: OMIS 3.0 */

function applyRemoveTagLinks() {
	var removeLinks = document.getElementsByClassName("tagRemoveLink");
	for(var x = 0; x < removeLinks.length; x++) {
		if (removeLinks[x].className.indexOf("removeable") == -1) {
			removeLinks[x].className += " removeable";
			removeLinks[x].onclick = removeTag;
		}
	}
}

function applyAddTagLink() {
	var createTagLink = document.getElementsByClassName("createTagLink")[0];
	
	createTagLink.onclick = function() {
		var index = document.getElementsByClassName("documentTagRow").length;
		var url = this.href + "?index="+index;
		
		ajax(url, document.getElementsByClassName("documentTagBody")[0], function(html) {
			var body = document.getElementsByClassName("documentTagBody")[0];
			var div = document.createElement("div");
			div.innerHTML = html;
			body.appendChild(div.firstChild);
			applyRemoveTagLinks();
		});
		return false;
	};
	
}

function removeTag(event) {
	var link = event.target;
	var id = link.id;
	var operation = document.getElementById(id + "_operation");
	var row = document.getElementById(id + "_row");
	var name = document.getElementById(id+ "_name");
	var value = operation.value;
	if (value == "CREATE") {
		document.getElementsByClassName("documentTagBody")[0].removeChild(row);
	} else if (value == "UPDATE") {
		row.className +=" restricted";
		operation.value = "REMOVE";
		name.className += " disabled";
		name.disabled = true;
	} else if (value == "REMOVE") {
		row.className = row.className.replace(" restricted", "");
		operation.value = "UPDATE";
		name.className = name.className.replace(" disabled", "");
		name.disabled = false;
	}
	return false;
}

function reindexDocumentTags() {
	var tagItemRows = document.getElementsByClassName("documentTagRow");
	for(var x = 0; x < tagItemRows.length; x++) {
		var documentTagItemID = tagItemRows[x].id.split("_")[0];
		var documentTag = document.getElementById(documentTagItemID + "_documentTag");
		var documentTagName = document.getElementById(documentTagItemID + "_name");
		var documentTagOperation = document.getElementById(documentTagItemID + "_operation");
		reindexTagName(documentTag, x);
		reindexTagName(documentTagName, x);
		reindexTagName(documentTagOperation, x);
	}
function reindexTagName(element, index) {
	var indexExpression = /[\d+]/;
	var replacementString = index;
	element.name = element.name.replace(indexExpression, replacementString);
}
}