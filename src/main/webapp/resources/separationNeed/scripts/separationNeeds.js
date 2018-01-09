window.onload = function() {
	applyRemoveLinkConfirmation();
	applyActionMenu(document.getElementById("separationNeedsActionMenuLink"), function() {applySeparationNeedsActionMenuOnClick();});
	var separationNeedsTableBody = document.getElementById("separationNeeds");
	var rowLinks = separationNeedsTableBody.getElementsByTagName("a");
	for (var rowLinkIndex = 0; rowLinkIndex < rowLinks.length; rowLinkIndex++) {
		var rowLink = rowLinks[rowLinkIndex];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				applyRemoveLinkConfirmation();
			});
		}
	}
	var separationNeedsSubjectListTable = document.getElementById("separationNeeds");
	if (separationNeedsSubjectListTable != null) {
		applyCommentBehavior(separationNeedsSubjectListTable, "commentLink","notes");
	}
	var separationNeedsTargetListTable = document.getElementById("targetSeparationNeeds");
	if (separationNeedsTargetListTable != null) {
		applyCommentBehavior(separationNeedsTargetListTable, "commentLink","notes");
	}
	
};

/**
 * Applies on click functionality to links within the separation needs action menu.
 */
function applySeparationNeedsActionMenuOnClick() {
	var tableViewLinks = document.getElementsByClassName("tableViewLink");
	var rows = document.getElementsByClassName("separationNeedRow");
	//var rows = document.getElementsByClassName("separatioNeedRow");
	for(var i=0, tableViewLink; tableViewLink = tableViewLinks[i]; i++) {
		var classNames = tableViewLink.className.split(/\s+/);
		for(var t=0, className; className = classNames[t]; t++) {
			if(/filter.*/.test(className)) {
				className = className.replace("filter", "");
				className = lowerCaseFirstLetter(className);
				var filterClassName = className;
				tableViewLink.onclick = (function(filterClassName, rows) { 
					return function() {
						showElementsByClassName(filterClassName, rows);
					};
				})(filterClassName, rows);
			}
		}
	}
}

/**
 * Ensures that the first character of the specified string is lower case.
 *  
 * @param string string
 * @returns string with first character set to lower case
 */
function lowerCaseFirstLetter(string) {
    return string.charAt(0).toLowerCase() + string.slice(1);
}

/**
 * Take an array of elements, show those with the specified class name and add
 * the hidden class to the rest.
 * 
 * @param filterClassName class name to be shown 
 * @param elts collection of DOM elements to evaluate
 */
function showElementsByClassName(filterClassName, elts) {
	for(var i=0, elt; elt = elts[i]; i++) {
		if (elt.classList.contains(filterClassName)) {
			elt.classList.remove('hidden');
		} else {
			elt.classList.add('hidden');
		}
    }
}

/**
 * Removes the specified class from the specified elements.
 * 
 * @param elts array of HTML DOM elements
 * @param class class to remove
 * @returns elements with removed class
 */
//function removeClassFromElements(elts, class) {
//	for(var i=0, elt; elt = elts[i]; i++) {
//		elt.classList.remove(class);
//	}
//	return elts;
//}

/**
 * Adds the specified class to the specified elements.
 * 
 * @param elts array of HTML DOM elements
 * @param class class to add
 * @returns elements with added class
 */
//function addClassToElements(elts, class) {
//	for(var i=0, elt; elt = elts[i]; i++) {
//		elt.classList.add(class);
//	}
//	return elts;
//}

/**
 * Returns whether the specified element is a dom element.
 * 
 * @param elt potential DOM element.
 * @returns {Boolean} whether element is DOM element
 */
function isDomElement(elt) {
	if (elt != undefined) {
      do {
          if (elt == document.documentElement) {
              return true;
          }
      } while (elt = elt.parentNode)
    }
	return false;
}

function isHtmlElement(elt) {
	return elt instanceof HTMLElement;
}

function getNearestTableParent(htmlElementNode) {
    while (htmlElementNode) {
        htmlElementNode = htmlElementNode.parentNode;
        if (htmlElementNode.tagName.toLowerCase() === 'table') {
            return htmlElementNode;
        }
    }
    return undefined;
}