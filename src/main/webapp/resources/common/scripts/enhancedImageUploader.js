/**
 * Java script for enhanced image uploader.
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (September 10, 2018)
 * Since: OMIS 3.0
 */

/**
 * Setup enhanced image editing within a modal container.
 * 
 * @param clickableElt clickable element to launch the modal window
 * @param imgSrc source of the image to be edited
 * @param desiredW desired width of the final image
 * @param desiredH desired height of the final image
 * @param resultCallback callback function to use the resulting data
 */
function enhancedImageEditingModalOnClickSetup(clickableElt, imgSrc, desiredW, desiredH, resultCallback) {
	
	if(!clickableElt.id) {
		console.log("Clickable element has no ID");
	} else {
		clickableElt.addEventListener("click", clickableEltOnclick);
		function clickableEltOnclick() {
			
			if(clickableElt.id) {
				let modalContainer;
				if(document.getElementById(clickableElt.id + "ModalEnhancedImageEditorContainer") instanceof Element) {
					modalContainer = document.getElementById(clickableElt.id + "ModalEnhancedImageEditorContainer");
				} else {
					modalContainer = createModalContainer(clickableElt.id + "ModalEnhancedImageEditorContainer");
				}
				//initial modal inner creation, maybe change to element appends and change name of wrapper
				modalContainer.innerHTML = "<span class=\"modalCloseWrapper\"><a class=\"modalClose\" id=\""+clickableElt.id+"ModalClose\"href=\"#\"/></span>";
				//Modal image wrapper
				let modalEditingWrapper = document.createElement("span");
				modalEditingWrapper.id = modalContainer.id + "ModalEditingWrapper";
				modalEditingWrapper.classList.add("modalEditingWrapper");
				modalEditingWrapper.classList.add("modalRow");
				modalContainer.appendChild(modalEditingWrapper);
				//Apply image editor functionality within the modal editing wrapper
				applyImageEditor(null, modalEditingWrapper, imgSrc, desiredW, desiredH, resultCallback);
				//modal interaction wrapper
				let modalInteractionWrapper = document.createElement("span");
				modalInteractionWrapper.classList.add("modalInteractionWrapper");
				modalInteractionWrapper.classList.add("modalRow");
				modalContainer.appendChild(modalInteractionWrapper);
				
				modalContainer.classList.remove("hidden");
				let modalClose = document.getElementById(clickableElt.id+"ModalClose");
				modalClose.onclick = function() {
					modalContainer.parentNode.removeChild(modalContainer);
					return false;
				}
				let modalAccept = document.createElement("a");
				modalAccept.id = clickableElt.id + "ModalAccept";
				modalAccept.classList.add("imageEditAccept");
				modalAccept.href= "#";
				modalInteractionWrapper.appendChild(modalAccept);
				
				modalAccept.onclick = function() {
					let data = document.getElementById(modalContainer.id+"ModalEditingWrapperAnchorPreviewCanvas").toDataURL('image/jpeg', 0.5);
					
					modalContainer.parentNode.removeChild(modalContainer);
					clickableElt.removeEventListener("click", clickableEltOnclick);
					clickableElt.parentNode.replaceChild(document.getElementById(clickableElt.id), clickableElt);
					resultCallback(data);
					enhancedImageEditingModalOnClickSetup(clickableElt, data, desiredW, desiredH, resultCallback);
					return false;
				}
			} 
		}// End on click
	}
}

/**
 * Dynamically generates HTML elements, and defines the update functionality for selecting image areas
 * to handle enhanced image upload presentation.
 */
function applyImageEditor(anchorElt, container, imgSrc, desiredW, desiredH, resultCallback) {
	//Declare function specific variables
	let srcImage;
	let anchorId;
	srcImage = new Image();
	//Create Image and wait for it to load. Because applying the source to an image object does not
	//immediately give it natural height/width nor allows it to be used for data applicable to an HTML5 canvas.
	srcImage.onload = function() {
		//Declare function specific variables.
		let wrapper;
		let canvas;
		let resultImage;
		let imageScale;
		let previewCanvas;
		//Create or select wrapper.
		if(!container) {
			if(anchoreElt) {
				if (document.getElementById(anchorId + "EnhancedImageUploaderWrapper") == null) {
					if (!anchorElt.id) {
						anchorElt.id = "enhancedImageUploaderAnchor";
					}
					anchorId = anchorElt.id;
					wrapper = interactiveImageWrap(anchorElt);
					wrapper.id = anchorId + "EnhancedImageUploaderWrapper";
					wrapper.classList.add("interactiveImageWrapper");
					wrapper.classList.add("enhancedImageUploaderWrapper");
				} else {
					wrapper = document.getElementById(anchorId + "EnhancedImageUploaderWrapper");
					anchorId = wrapper.id+"Anchor"
				}
			} else {
				alert("NO CONTAINER OR ANCHOR ELEMENT");
				//SOMETHING WENT WRONG, EXPLAIN IT, no anchor or container
			}
		} else {
			wrapper=container;
			anchorId=container.id+"Anchor";
		}
		//manual styling assignments, get rid of anything that can be referenced via CSS
		canvas = document.createElement('canvas');
		canvas.id = anchorId + "ResizingCanvas";
		canvas.classList.add("enhancedImageUploaderResizingCanvas");
		canvas.classList.add("enhancedImageUploaderCanvas");
		canvas.width = srcImage.naturalWidth;
		canvas.height = srcImage.naturalHeight;
		canvas.style.width = srcImage.naturalWidth;
		canvas.style.height = srcImage.naturalHeight;
		if(document.getElementById(anchorId + "ResizingCanvas") == null) {
			canvasWrapper = document.createElement('div');
			canvasWrapper.classList.add("modalCanvasWrapper");
			canvasWrapper.id = anchorId + "CanvasWrapper";
			wrapper.appendChild(canvas);
			wrapElement(canvas, canvasWrapper);
		} else {
			document.getElementById(anchorId + "CanvasWrapper").replaceChild(canvas, document.getElementById(anchorId + "ResizingCanvas"));
		}
		let c = canvas.getContext("2d");
		getImageScale();
		function getImageScale() {
			if(!canvas.getBoundingClientRect()) {
				window.requestAnimationFrame(getImageScale);
			} else {
				if (srcImage.naturalWidth > canvas.getBoundingClientRect().width || srcImage.naturalHeight >canvas.getBoundingClientRect().height) {
					if(srcImage.naturalWidth > srcImage.naturalHeight) {
						imageScale = canvas.getBoundingClientRect().width / srcImage.naturalWidth;
					} else {
						imageScale = canvas.getBoundingClientRect().height / srcImage.naturalHeight;
					}
				} else {
					imageScale = 0;
				}
			}
		}
		
		if(document.getElementById(anchorId + "PreviewCanvas") == null){
			previewCanvas = document.createElement('canvas');
			previewCanvas.id = anchorId + "PreviewCanvas";
			previewCanvas.classList.add("enhancedImageUploaderCanvas");
			wrapper.appendChild(previewCanvas);
			previewCanvasWrapper = document.createElement('div');
			previewCanvasWrapper .classList.add("modalCanvasWrapper");
			wrapElement(previewCanvas, previewCanvasWrapper );
		} else {
			previewCanvas = document.getElementById(anchorId + "PreviewCanvas");
		}
		previewCanvas.width = desiredW;
		previewCanvas.height = desiredH;
		previewCanvas.style.width = desiredW;
		previewCanvas.style.height = desiredH;
		
		let previewContext = previewCanvas.getContext('2d');
		let captureScale = previewCanvas.height / previewCanvas.width;
		let editableWidth;
		let editableHeight;
		let editableX;
		let editableY;
		
		//Check if desired capture area completely fits within editor area.
		if (previewCanvas.width <= canvas.width && previewCanvas.height <= canvas.height) {
			editableWidth = previewCanvas.width;
			editableHeight = previewCanvas.height;
			editableX = (canvas.width - editableWidth)/2;
			editableY = (canvas.height - editableHeight)/2;
		} else {
			if (previewCanvas.width >= previewCanvas.height && canvas.height > (canvas.width * captureScale)) {
				editableWidth = canvas.width;
				editableHeight = editableWidth * captureScale;
				editableX = 0;
				editableY = (canvas.height - editableHeight) / 2;
			} else {
				editableHeight = canvas.height;
				editableWidth = editableHeight / captureScale;
				editableX = (canvas.width - editableWidth) / 2;
				editableY = 0;
			}
		}
		
		let mouseX = 0,
			mouseY = 0,
			mousePressed = dragging = false;
		
		let	editableRec = null;
			editableRec = new EditableRectangle(0, 100, 50, editableX, editableY, editableWidth, editableHeight);
		
		//Removes any pre-existing event handlers for the canvas.
		wrapper.removeEventListener("mousemove", canvasOnMouseMove);
		canvas.removeEventListener("mousedown", canvasOnMouseDown);
		wrapper.removeEventListener("mouseup", documentOnMouseUp);
		
		//Add event listeners to the canvas for mouse down, and mouse move. Add listener to the DOCUMENT for mouse up to clear any variables and stop interaction.
		wrapper.addEventListener("mousemove", canvasOnMouseMove);
		canvas.addEventListener("mousedown", canvasOnMouseDown);
		wrapper.addEventListener("mouseup", documentOnMouseUp);
		
		//Define functionality for mouse movement, assignable in an event listener
		function canvasOnMouseMove(e) {
			let rect = canvas.getBoundingClientRect();
			if(imageScale && imageScale != 0) {
				mouseX = (e.clientX - rect.left) / imageScale;
				mouseY = (e.clientY - rect.top) / imageScale;
			} else {
				mouseX = (e.clientX - rect.left);
				mouseY = (e.clientY - rect.top);
			}
			if(mousePressed) {
				editableRec.update();
			}
		}
		
		//Define mouse down functionality, assignable in an event listener
		function canvasOnMouseDown() {
			mousePressed = true;
		}
		
		//Define mouse up functionality, assignable in an event listener
		function documentOnMouseUp() {
			mousePressed = false;
			dragging = false;
			drag = false;
			dragTL = false;
			dragBL = false;
			dragTR = false;
			dragBR = false;
			editableRec.update();
		}
		editableRec.update();
		
		//Define the EditableRectangle closure function.
		function EditableRectangle(hue, saturation, lightness, x, y, w, h) {
			
			//Initialize starting coordinates, resize values, and the state of drag variables.
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			let that = this;
			let startX = 0,
				startY = 0,
				startResizeX = x,
				startResizeY = y,
				closeEnough = 10,
				drag = dragTL = dragBL = dragTR = dragBR = false;
			let left = that.x;
			let right = that.x + that.w;
			let top = that.y;
			let bottom = that.y + that.h;
			//Define the EditableRectangle.update() process.
			this.update = function() {
				left = that.x;
				right = that.x + that.w;
				top = that.y;
				bottom = that.y + that.h;
				if (mousePressed) {
						if (!drag) {
							startX = mouseX - that.x;
							startY = mouseY - that.y;
						}
						//Check if any resizing drag points are active, this helps from switching points accidentally when two points are close while resizing
						// 1. top left
						if ((!dragTR && !dragBL && !dragBR) && (dragTL || checkCloseEnough(mouseX, left, closeEnough, imageScale) && checkCloseEnough(mouseY, top, closeEnough, imageScale))) {
							dragTL = true;
							dragTR = false;
							dragBL = false;
							dragBR = false;
						}
						// 2. top right
						else if ((!dragTL && !dragBL && !dragBR) && (dragTR || checkCloseEnough(mouseX, right, closeEnough, imageScale) && checkCloseEnough(mouseY, top, closeEnough, imageScale))) {
							dragTR = true;
							dragTL = false;
							dragBL = false;
							dragBR = false;
						}
						// 3. bottom left
						else if ((!dragTL && !dragTR && !dragBR) && (dragBL || checkCloseEnough(mouseX, left, closeEnough, imageScale) && checkCloseEnough(mouseY, bottom, closeEnough, imageScale))) {
							dragBL = true;
							dragTR = false;
							dragTL = false;
							dragBR = false;
						}
						// 4. bottom right
						else if ((!dragTL && !dragBL && !dragTR) && (dragBR || checkCloseEnough(mouseX, right, closeEnough, imageScale) && checkCloseEnough(mouseY, bottom, closeEnough, imageScale))) {
							dragBR = true;
							dragTR = false;
							dragBL = false;
							dragTL = false;
						}
						// 5. none of them
						else {
							if (mouseX < right && mouseX > left && mouseY < bottom && mouseY > top) {
								if (!dragging){
									dragging = true;
									drag = true;
								}
							}
						}	
				} else {
					drag = false;
				}
				
				//Drag functionality
				if (drag) {
					if((mouseY - startY > 0) && (mouseY - startY + that.h < canvas.height)) {
						that.y = mouseY - startY;
						startResizeY = that.y;
					} else {
						if(mouseY - startY <= 0) {
							that.y = 0;
							startResizeY = 0;
						}
						if (mouseY - startY + that.h >= canvas.height) {
							that.y = canvas.height - that.h;
							startResizeY = canvas.height - that.h;
						}
					}
					if ((mouseX - startX > 0) && (mouseX - startX + that.w < canvas.width)) {
						that.x = mouseX - startX;
						startResizeX = that.x;
					} else {
						if(mouseX - startX < 0) {
							that.x = 0;
							startResizeX = 0;
						}
						if (mouseX - startX + that.w > canvas.width) {
							that.x = canvas.width - that.w;
							startResizeX = canvas.width - that.w;
						}
					}
				} else if (dragTL) {
					if (mouseX != startResizeX && mouseX < right  - ((bottom - mouseY) / captureScale)) {
						if (mouseY < bottom) {
							//If mouse coords are within the bounds of the canvas
							if(mouseY >= 0 && mouseX >= 0) {
								that.y = mouseY;
							} else {
								//If the anticipated resize of the the rectangle would leave the x coordinate > 0
								if(right - (bottom / captureScale) > 0) {
									that.y = 0;
								} else {
									//Set the Y coordinate to the point that would allow the calculated width to make the
									//x coordinate at 0
									that.y = Math.abs(bottom -  (right * captureScale));
								}
							}
							that.h = bottom - that.y;
							that.w = that.h / captureScale;
							that.x = right - that.w;
							startResizeX = that.x;
							startResizeY = that.y;
						}
					} else if (mouseY != startResizeY  && mouseY < bottom  - ((right - mouseX) * captureScale)){
						//If the mouse is less than the right side
						if (mouseX < right) {
							//If mouse is within the canvas the canvas
							if(mouseX >= 0 && mouseY >= 0) {
								that.x = mouseX;
							} else {
								//If the anticipated resize of the the rectangle would leave the y coordinate > 0
								if(bottom - (right * captureScale) > 0) {
									that.x = 0;
								} else {
									//Set the X coordinate to the point that would allow the calculated width to make the
									//Y coordinate at 0
									that.x = Math.abs(right - (bottom / captureScale));
								}
							}
							that.w = right - that.x;
							that.h = that.w * captureScale;
							that.y = bottom - that.h;
							startResizeX = that.x;
							startResizeY = that.y;
						}
					}
				} else if (dragTR) {
					if (mouseX != startResizeX + that.w && mouseX > that.x + ((bottom - mouseY) / captureScale)) {
						if (mouseY < bottom) {
							//If mouse is inside the canvas
							if (mouseY < 0 || mouseX > canvas.width) {
								//If the anticipated resize of the rectangle to the top of the canvas, would leave the right side of
								//rectangle within the canvas, set Y to 0
								if(canvas.width - ((bottom / captureScale) + left) > 0) {
									that.y = 0;
								} else {
									//Set the Y coordinate of the rectangle to + needed to have the right side equal to the canvas.width
									that.y = Math.abs(bottom -  ((canvas.width - left) * captureScale));
								}
							} else {
								that.y = mouseY;
							}
							that.h = bottom - that.y;
							that.w = that.h / captureScale;
							startResizeY = mouseY;
						}
					} else if (mouseY != startResizeY  && mouseY < (that.h + that.y) - ((mouseX - that.x) * captureScale)){
						if (mouseX > left) {
							//If mouse is inside canvas
							if (mouseY < 0 || mouseX > canvas.width) {
								//If anticipated resize to the right of the canvas would leave the top within the canvas, set to canvas width.
								if(bottom - ((canvas.width - left) * captureScale) > 0) {
									that.w = canvas.width - that.x;
								} else {
									//Set the X coordinate to the point that would allow the calculated width to make the
									//Y coordinate at 0
									that.w = Math.abs(bottom / captureScale);
								}
								
							} else {
								that.w = mouseX - left;
							}
							that.h = that.w * captureScale;
							that.y = bottom - that.h;
							startResizeY = that.y;
						}
					}
				} else if (dragBL) {
					if (mouseX != startResizeX && mouseX < right - ((mouseY - that.y) / captureScale)) {
						if (mouseY > top) {
							if(mouseY <= canvas.height && mouseX >= 0) {
								that.h = mouseY - that.y;
							} else {
								//If the anticipated resize of the the rectangle would leave the x coordinate > 0
								if(right - ((canvas.height - that.y) / captureScale) > 0) {
									that.h = canvas.height - that.y;
								} else {
									//Set the Y coordinate to the point that would allow the calculated width to make the
									//x coordinate at 0
									that.h = Math.abs(right * captureScale);
								}
							}
							that.w = that.h / captureScale;
							that.x = right - that.w;
							startResizeX = that.x;
						}
					} else if (mouseY != startResizeY + that.h  && mouseY > startResizeY + ((right - mouseX) * captureScale)){
						if (mouseX < right) {
							if(mouseY <= canvas.height && mouseX >= 0) {
								that.x = mouseX;
							} else {
								//If the anticipated resize of the rectangle would leave the bottom within the bounds of the canvas.
								if(top + (right * captureScale) < canvas.height) {
									that.x = 0;
								} else {
									that.x = Math.abs(((canvas.height - top)/ captureScale) - right);
								}
							}
							
							that.w = right - that.x;
							that.h = that.w * captureScale;
							startResizeX = mouseX;
						}
					}
				} else if (dragBR) {
					if (mouseX != startResizeX + that.w && mouseX > that.x + ((mouseY - top) / captureScale)) {
						if (mouseY > top) {
							if(mouseY <= canvas.height && mouseX <= canvas.width) {
								that.h = mouseY - that.y;
							} else {
								//If the anticipated height of the rectangle would leave the right side  in the canvas.
								if(that.x + ((canvas.height - that.y) / captureScale) < canvas.width) {
									that.h = canvas.height - that.y;
								} else {
									that.h = (canvas.width - that.x) * captureScale;
								}
							}
							that.w = that.h / captureScale;
						}
					} else if (mouseY != startResizeY + that.h  && mouseY > startResizeY + ((mouseX - startResizeX) * captureScale)){
						if (mouseX > left) {
							if(mouseY <= canvas.height && mouseX <= canvas.width) {
								that.w = mouseX - startResizeX;
							} else {
								//If the anticipated width of the rectangle would leave the bottom in the canvas.
								if(that.y + ((canvas.width - that.x) * captureScale) < canvas.height) {
									that.w = canvas.width - that.x;
								} else {
									that.w = (canvas.height - that.y) / captureScale;
								}
							}
							that.h = that.w * captureScale;
						}
					}
				}
				
				draw(srcImage, 0, 0);
				
				previewContext.clearRect(0, 0, previewCanvas.width, previewCanvas.height);
				previewContext.drawImage(canvas, that.x, that.y, that.w, that.h, 0, 0, previewCanvas.width, previewCanvas.height);
				
				//Create modal area outside of capture area.
				c.globalAlpha = 0.4;
				c.fillStyle="#000000";
				c.fillRect(0,0,that.x,canvas.height);
				c.fillRect(that.x,0,that.w,that.y);
				c.fillRect(that.x + that.w,0,canvas.width - (that.x + that.w),canvas.height);
				c.fillRect(that.x,(that.h+that.y),that.w,(canvas.height-(that.y+that.h)));
				
				//Set opacity back to full for all other drawing to occur this update.
				c.globalAlpha = 1.0;
				drawCaptureArea(that.x, that.y, that.w, that.h, hue, saturation, lightness, c, imageScale);
				drawHandles(that.x, that.y, that.w, that.h, closeEnough, c, imageScale);
				
			}
			
		}
		
		//Draw the image.
		function draw(img, x, y) {
			c.clearRect(0, 0, canvas.width, canvas.height);
			c.drawImage(img, x, y, img.width, img.height);
		}
	}
	srcImage.src = imgSrc;

	/**
	 * Used to check if two values (p1 and p2) are within a range defined by "closeEnough".
	 * If image scale is not set to 0, it is included in calculation of defining what is close enough.
	 * Returns true or false.
	 */
	function checkCloseEnough(p1, p2, closeEnough, imageScale) {
		let targetRange;
		if(imageScale != 0) {
			targetRange = closeEnough/imageScale;
		} else {
			targetRange = closeEnough;
		}
		return Math.abs(p1 - p2) <= targetRange;
	}

	 /** 
	 * Draws a circle at the specified x and y coordinate, of the specified radius,
	 * within the context (ctx).
	 */
	function drawCircle(x, y, radius, ctx) {
		ctx.fillStyle = "#FF0000";
		ctx.beginPath();
		ctx.arc(x, y, radius, 0, 2 * Math.PI);
		ctx.fill();
	}

	/**
	 * Draws the capture area, according to the supplied values for coordinates, color, and scale
	 * for the specified context.
	 */
	function drawCaptureArea(x,y,w,h,hue,saturation,lightness, context, imageScale) {
			let lineWidth;
			context.beginPath();
			context.strokeStyle="hsl(" + hue + ", " + saturation + "%," + lightness + "%)";
			if (imageScale && imageScale != 0) {
				lineWidth = ""+4/imageScale+"";
			} else {
				lineWidth = ""+4+"";
			}
			context.lineWidth=lineWidth;
			context.rect(x,y,w,h);
			context.stroke();
	}

	/**
	 * Draws a combination of circles to be used as the corner handles for the capture area.
	 */
	function drawHandles(startX, startY, w, h, closeEnough, ctx, imageScale) {
			let handleSize;
			if(imageScale && imageScale != 0) {
				handleSize = closeEnough/imageScale;
			} else {
				handleSize = closeEnough;
			}
			drawCircle(startX, startY, handleSize, ctx);
			drawCircle(startX + w, startY, handleSize, ctx);
			drawCircle(startX + w, startY + h, handleSize, ctx);
			drawCircle(startX, startY + h, handleSize, ctx);
	}
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
