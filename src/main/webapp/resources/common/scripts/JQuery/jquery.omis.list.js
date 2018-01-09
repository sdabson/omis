/** OMIS jquery ui.tools implementations.
 * @author Ryan Johns
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0 */

if (typeof ui === 'undefined') {
	var ui = new Object();
}

ui.Lists = new function() {
	this.LINK_COMMENT_BEHAVIOR = function(element, className, tableHeading, tableHeadingClass) {
		element.onclick = function(e) {
			if(this.innerHTML.length <= 0 && this.title.length > 0) {
				$(this).replaceWith(this.title);
				this.title = "";
				$(tableHeading).removeClass(tableHeadingClass);
				$(tableHeading).addClass("expanded");
				
				$(tableHeading).css({"width":""});
				$(this).removeClass(className);
			}
			return false;
		};
	};
};