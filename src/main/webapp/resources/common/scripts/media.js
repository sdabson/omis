/**
 * Native JS for client media interactions.
 * 
 * @author: Joel Norris
 * @version: 0.1.0 (October 31, 2018)
 * @since OMIS 3.0
 */

/**
 * Applies on click functionality for file change with images.
 * 
 * @param fieldGroupElt field group element
 * @param previewElt preview element
 * @param fileInputElt file input element
 * @param inputElt input element
 * @param refreshPhotoCallback call back to refresh photo after click
 */
function applyOnImageFileChange(fieldGroupElt, previewElt, fileInputElt, inputElt, refreshPhotoCallback, enhancedImageEditorFunction, maxWidth, maxHeight) {
	if (previewElt.getAttribute("src") == null || previewElt.getAttribute("src") == "") {
		if (!ui.hasClass(fieldGroupElt, "hidden")) {
			ui.addClass(fieldGroupElt, "hidden");
		}
	}
	if (window.FileReader) {
		fileInputElt.onchange = function(event) {
			var reader = new FileReader();
			let lookUpMessageResolver = new common.MessageResolver("omis.msgs.form");
			reader.onload = function(innerEvent) {
				if(isCanvasSupported()) {
					if(maxWidth && maxHeight) {
						convertImage(innerEvent.target.result, 'image/jpeg', 0.5, maxWidth, maxHeight, refreshPhotoCallback);
					} else {
						convertDataURLMimeType(innerEvent.target.result, 'image/jpeg', 0.5, refreshPhotoCallback);
					}
					previewElt.onload = function() {
						if(typeof enhancedImageEditorFunction === 'function') {
							enhancedImageEditorFunction();
						}
					}
					if (ui.hasClass(fieldGroupElt, "hidden")) {
						ui.removeClass(fieldGroupElt, "hidden");
					}
				}
				fileInputElt.value = null;
			};
			if (event.target.files[0] != null) {
				if(event.target.files[0].type.indexOf("image") === -1 || event.target.files[0].type === "") {
					let fileTypeValidationMessage = lookUpMessageResolver.getMessage("fileTypeMismatch", ['image']);
					alert(fileTypeValidationMessage);
				} else {
					if(!isCanvasSupported()) {
						if (event.target.files[0].type != 'image/jpeg') {
							let fileTypeValidationMessage = lookUpMessageResolver.getMessage("fileTypeMismatch", ['image/jpeg']);
							alert(fileTypeValidationMessage);
						} else {
							refreshOffenderPhoto(event.target.files[0]);
							if (ui.hasClass(fieldGroupElt, "hidden")) {
								ui.removeClass(fieldGroupElt, "hidden");
							}
						}
					}
				}
				reader.readAsDataURL(event.target.files[0]);
			}
		};
	}
}

/**
 * Converts the specified data URL into another of the specified MIME type,
 * with the specified compression.
 * 
 * @param dataURL Data Uniform Resource Locator
 * @param mimeType MIME type
 * @param compression compression rate
 * @param callback callback function to execute with the converted DATA URL
 */
function convertDataURLMimeType(dataURL, mimeType, compression, callback) {
	//mimeType suggestion: 'image/jpeg'
	//compression suggestion: 0.5
	let convertingCanvas = document.createElement('canvas');
	let convertingCanvasContext = convertingCanvas.getContext('2d');
	srcImage = new Image();
	srcImage.onload = function() {
		convertingCanvas.width = srcImage.naturalWidth;
		convertingCanvas.height = srcImage.naturalHeight;
		convertingCanvas.style.width = srcImage.naturalWidth;
		convertingCanvas.style.height = srcImage.naturalHeight;
		convertingCanvasContext.clearRect(0, 0, convertingCanvas.width, convertingCanvas.height);
		convertingCanvasContext.drawImage(srcImage, 0, 0, srcImage.width, srcImage.height);
		callback(convertingCanvas.toDataURL(mimeType, compression));
	};
	srcImage.src = dataURL;
}

/**
 * Resizes an image to be within the bounds of the specified maximum width and
 * maximum height.
 * 
 * @param dataURL data uniform resource locator
 * @param mimeType MIME type
 * @param compression compression
 * @param maxWidth maximum width
 * @param maxHeight maximum height
 * @param callback callback function
 */
function convertImage(dataURL, mimeType, compression, maxWidth, maxHeight, callback) {
	let resizeCanvas = document.createElement('canvas');
	let resizeCanvasContext = resizeCanvas.getContext('2d');
	srcImage = new Image();
	srcImage.onload = function() {
		let imageScale;
		let resizeWidth;
		let resizeHeight;
		if(srcImage.naturalWidth > maxWidth || srcImage.naturalHeight > maxHeight) {
			if(srcImage.naturalWidth > srcImage.naturalHeight) {
				imageScale = srcImage.naturalHeight / srcImage.naturalWidth;
				resizeWidth = maxWidth;
				resizeHeight = maxWidth * imageScale;
			} else {
				imageScale = srcImage.naturalWidth / srcImage.naturalHeight;
				resizeHeight = maxHeight;
				resizeWidth = maxHeight * imageScale;
			}
			resizeCanvas.height =  resizeHeight;
			resizeCanvas.width = resizeWidth;
			resizeCanvasContext.drawImage(srcImage, 0, 0, srcImage.naturalWidth, srcImage.naturalHeight, 0, 0, resizeWidth, resizeHeight);
		} else {
			resizeCanvas.height =  srcImage.naturalHeight;
			resizeCanvas.width = srcImage.naturalWidth;
			resizeCanvasContext.drawImage(srcImage, 0, 0, resizeCanvas.width, resizeCanvas.height);
		}
		callback(resizeCanvas.toDataURL(mimeType, compression));
	};
	srcImage.src = dataURL;
}

/**
 * Creates a side by side image out of 2 image sources. The parameters "image1src",
 * "image2src" should be URLs or data URL's that will be stitched together in
 * their respective order, left to right. The parameter "callback" should be
 * the function that is desired to run after the image is stitched together,
 * taking the resulting data URL as an argument. The "mimeType" and
 * "compression" represents the arguments for the canvas.toDataURL() function
 * that is called within this function. This decides what format/compression
 * rate the final image should be. It is recommended that 'image/jpeg' be used
 * for "mimeType" and 0.5 be used for compression.
 * 
 * @param image1src first image source
 * @param image2src second image source
 * @param callback callback function that takes resulting data url as an
 * argument
 * @param mimeType MIME type of resulting stitched image
 * @param compression compression of resulting stitched image
 */
function stitchImage(image1src, image2src, callback, mimeType, compression) {
	let c = document.createElement('canvas');
	let ctx = c.getContext('2d');
	img1 = new Image();
	img2 = new Image();
	img1.onload = function() {
		img2.onload = function() {
			let stitchedWidth = img1.naturalWidth + img2.naturalWidth;
			let stitchedHeight = (function() {
				let higher;
				if(img1.naturalHeight > img2.naturalHeight) {
					higher = img1.naturalHeight;
				} else {
					higher = img2.naturalHeight;
				}
				return higher;
			})();
			c.width = stitchedWidth;
			c.height = stitchedHeight;
			c.style.width = stitchedWidth;
			c.style.height = stitchedHeight;
			ctx.clearRect(0, 0, c.width, c.height);
			ctx.drawImage(img1, 0, 0, img1.width, img1.height);
			ctx.drawImage(img2, img1.width, 0, img2.width, img2.height);
			callback(c.toDataURL(mimeType, compression));
		}
		img2.src = image2src;
	}
	img1.src = image1src;
}


/**
 * Creates a wrapper of the specified type around the specified element.
 * 
 * @param el element to wrap
 * @param wrapper DOM element to be used as the wrapper
 */
function wrapElement(el, wrapper) {
	el.parentNode.insertBefore(wrapper, el);
	wrapper.appendChild(el);
}

/**
 * Returns whether canvas is supported on the current document.
 * 
 * @returns true or false
 */
function isCanvasSupported(){
	  var elem = document.createElement('canvas');
	  return !!(elem.getContext && elem.getContext('2d'));
}

/* !!! Offender photo join functionality !!!*/

/**
 * Applies image join functionality to the specified photo preview
 * with the specified photo data.
 * 
 * @param numberOfImages number of images
 * @param photoPreviewEle photo preview element
 * @param photoData photo data
 */
function applyImageJoin(numberOfImages, photoPreviewEle, photoData) {
	let wrapper = document.createElement("div");
	wrapper.classList.add("interactiveImageWrapper");
	wrapper.id = photoPreviewEle.id + "Wrapper";
	wrapElement(photoPreviewEle, wrapper);
	let clickableAreas = [];
	for(i=0;i<numberOfImages;i++) {
		clickableAreas[i] = createClickableImageArea(i, photoPreviewEle.width/numberOfImages);
		wrapper.appendChild(clickableAreas[i]);
		
	}
	if(photoData.value != "") {
		assignPhotoPreviewMouseOver(photoPreview, clickableAreas);
	}
	assignPhotoPreviewMouseOut(clickableAreas);
}

/*
 * Creates a clickable image area to be appended within an immediate container
 * that surrounds an image. This assumes that there can be one, or more, sections
 * that will be of the same width and extend the full height of the container.
 * 
 * @param index index
 * @param sectionWidth section width
 * @returns newly created DOM element to overlay on an image.
 */
function createClickableImageArea(index, sectionWidth) {
	let afterElement = document.createElement('div');
	afterElement.classList.add("hoverableImageAfter");
	afterElement.classList.add("editableImageAfter");
	afterElement.classList.add("photoJoinPreviewHoverArea");
	let left;
	if(index == 0) {
		left = 0 + "px";
	} else {
		left = sectionWidth * index + "px";
	}
	afterElement.style.left=left;
	afterElement.style.width=sectionWidth + "px";
	afterElement.id = "clickableImageArea"+index;
	return afterElement;
}

/*
 * Assigns photo preview mouse over functionality to show clickable areas
 * depending on the number of sections assigned to the photo
 * 
 * @param photoPreview the preview photo
 * @param clickableAreas clickable area
 */
function assignPhotoPreviewMouseOver(photoPreview, clickableAreas){
	photoPreview.onmouseover = function(event) {
		let x = event.pageX - this.parentNode.offsetLeft;
		let joinSectionsWidth = photoPreview.clientWidth/clickableAreas.length;
		//X is off by 1 too high to be in line with section math
		let currentSection = Math.floor((x-1)/joinSectionsWidth);
		clickableAreas[currentSection].classList.add("show");
	}
}

/*
 * Assign mouse out functionality to all specified clickable areas.
 * The clickable areas will lose a class that makes them visible upon mouse
 * out.
 * 
 * @param clickableAreas clickable areas
 */
function assignPhotoPreviewMouseOut(clickableAreas) {
	for(i=0;i<clickableAreas.length;i++) {
		clickableAreas[i].onmouseout = function() {
			this.classList.remove("show");
		}
	}
}

/*
 * Updates a section of the photo at the specified index.
 * 
 * @param data dataURL
 * @param photoPreview photo preview
 * @param index index
 * @param sectionWidth section width
 * @param callback callback function
 */
function updatePreviewSection(data, photoPreview, index, sectionWidth, desiredWidth, desiredHeight, callback) {
	updatePreviewImage(photoPreview ,sectionWidth*index, 0, data, 'image/jpeg', 0.5, desiredWidth, desiredHeight, callback);
}

/*
 * Update preview image with the specified image source at the designated x
 * and y coordinate.
 * 
 * @param previewImage the image to update
 * @param x x axis coordinate
 * @param y y axis coordinate
 * @param photoItemSource photo item image source
 * @param mimeType MIME type
 * @param compression compression rate
 * @param callback callback function
 */
function updatePreviewImage(previewImage, x, y, photoItemSource, mimeType, compression, desiredWidth, desiredHeight, callback) {
	let joinCanvas = document.createElement('canvas');
	joinCanvas.width = desiredWidth;
	joinCanvas.height = desiredHeight;
	let joinCanvasContext = joinCanvas.getContext('2d');
	let updateImage = new Image();
	updateImage.onload = function() {
		joinCanvasContext.drawImage(previewImage, 0, 0, joinCanvas.width, joinCanvas.height);
		joinCanvasContext.drawImage(updateImage, x, y, joinCanvas.width/2, joinCanvas.height);
		callback(joinCanvas.toDataURL(mimeType, compression));
	}
	updateImage.src = photoItemSource;
}


