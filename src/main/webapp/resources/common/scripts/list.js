/**
 * List screen functionality.
 * 
 * <p>Requires:
 * 
 * <ul>
 *   <li>ServerConfig.js
 *   <li>Minimum jQuery Library
 * </ul>
 *
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2013)
 * @since OMIS 3.0
 */

/**
 * Applies asynchronous functionality to reload content when an element is
 * clicked.
 * 
 * @param link element that will be clicked to reload the content
 * @param content content element
 * @param url URL to invoke when the element is clicked - uses a GET request
 * and expects HTML content
 * @param params parameters to pass on URL invocation
 */
function asyncContentReloader(link, content, url, params) {
	$(link).click(function() {
		$.ajax({
			type: "GET",
			async: true,
			url: url,
			data: params,
			beforeSend: function() {
				$(content).html("<img src='"
						+ config.ServerConfig.getContextPath()
						+ "/resources/common/images/ajaxLoader.gif'/>");
			},
			success: function(data) {
				$(content).html(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$(content).html(jqXHR.responseText);
			}
		});
	});
}

function applyCommentBehavior(table, elementClassname, commentHeadingClassname) {
	var commentLinks = $(table).find("."+elementClassname);
	var noteHeadings = table.getElementsByTagName("th");
	var noteHeading = null;
	
	for (var x = 0; x < noteHeadings.length; x++) {
		if (noteHeadings[x].className.indexOf(commentHeadingClassname) >= 0) {
			noteHeading = noteHeadings[x];
		}
	}
	
	for (var y = 0; y < commentLinks.length; y++) {
		var commentLink = commentLinks[y];
		ui.Lists.LINK_COMMENT_BEHAVIOR(commentLink, elementClassname, noteHeading, commentHeadingClassname);
	}
}