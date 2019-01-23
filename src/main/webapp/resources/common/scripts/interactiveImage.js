/**
 * Java script for interactive images.
 * 
 * Dependencies: modal.js, modal.css, interactiveImage.css
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (4/6/2017)
 */

//Assign event listener to on load event of the window.
window.addEventListener("load", assignViewableImageInteractions);

/**
 * Checks for viewable image (img DOM elements with the class "viewableImage")
 * and uses the applyInteractiveImagePreview function to assign on click functionality. If
 * no viewable images are found, nothing is done.
 */
function assignViewableImageInteractions() {
	var viewableImages = document.getElementsByClassName("viewableImage");
	if (viewableImages.length > 0) {
		var modalContainer = createModalContainer("modalImageDisplayContainer");
		for(var i=0, len=viewableImages.length; i<len; i++) {
			applyInteractiveImagePreview(viewableImages[i], modalContainer);
		}
	}
}


/**
 * Creates a wrapper of the specified type around the specified element.
 * 
 * @param el element to wrap
 * @param wrapper DOM element to be used as the wrapper
 */
function wrap(el, wrapper) {
	el.parentNode.insertBefore(wrapper, el);
	wrapper.appendChild(el);
	return wrapper;
}

/*
 * Wraps the specified element in the specified wrapper, unless the parent element
 * is an interactive image wrapper. Returns the appropriate wrapper assignment.
 * 
 * @param el element to wrap
 * 
 * @param wrapper potential wrapper element
 * @returns wrapper element
 */
function interactiveImageWrap(el) {
	if(el.parentNode.classList.contains("interactiveImageWrapper")) {
		wrapper = el.parentNode;
		wrapper.innerHTML = '';
		wrapper.appendChild(el);
		return wrapper;
	} else {
		newWrapper = document.createElement('div');
		newWrapper.classList.add("interactiveImageWrapper");
		return wrap(el, newWrapper);
	}
}


/**
 * Preview the specified image in a modal photo previewer.
 * 
 * @param imgElt image element
 * @param modalContainer the container for the image preview
 */
function applyInteractiveImagePreview(imgElt, modalContainer) {
//	var modalContainer = document.getElementById("modalImageDisplayContainer");
	var body = document.body,
        html = document.documentElement;
	var documentHeight = Math.max( body.scrollHeight, body.offsetHeight, 
	                       html.clientHeight, html.scrollHeight, html.offsetHeight );
	var documentWidth = Math.max( body.scrollWidth, body.offsetWidth, 
            html.clientWidth, html.scrollWidth, html.offsetWidth);
	if (imgElt.tagName.toLowerCase() == 'img') {
		if (typeof imgElt.naturalWidth !== "undefined" && imgElt.naturalWidth !== 0 && imgElt.complete) {
			var wrapper = interactiveImageWrap(imgElt, wrapper);
			imgElt.classList.add("hoverableImage");
			
//			wrapper.classList.add("viewableImageWrapper");
		//	wrapper.classList.add("interactiveImageWrapper");
			var afterElement = document.createElement('div');
			afterElement.classList.add("hoverableImageAfter");
			afterElement.classList.add("viewableImageAfter");
			wrapper.appendChild(afterElement);
			afterElement.onclick = function() {
				var maxImageHeight;
				if (imgElt.naturalHeight > (documentHeight * .8)) {
					maxImageHeight = documentHeight * .8;
				} else {
					maxImageHeight = imgElt.naturalHeight
				}
				var maxImageWidth;
				if (imgElt.naturalWidth > (documentWidth * .8)) {
					maxImageWidth = documentWidth * .8;
				} else {
					maxImageWidth = imgElt.naturalWidth;
				}
				modalContainer.innerHTML = "<img style=\"max-height: " 
					+ maxImageHeight+ "px; max-width: "+ maxImageWidth 
					+ "px;\" class=\"modalImage modalContent\" src=\"" 
					+ imgElt.src + "\"/> <span class=\"modalCloseWrapper\"><a class=\"modalClose\" id=\"modalClose\"href=\"#\"/></span>"
				modalContainer.classList.remove("hidden");
				var modalClose = document.getElementById("modalClose");
				modalClose.onclick = function() {
					modalContainer.innerHTML = "";
					modalContainer.classList.add("hidden");
					return false;
				}
			}
		}
	}
}