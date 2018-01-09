/**
 * Applies on click functionality for a hue range input.
 * 
 * @param hueEle hue range input
 * @param saturationEle saturation range input
 */
function applyHueOnClick(hueEle, saturationEle) {
	hueEle.onchange = function() {
		applyHueValue(hueEle.value, saturationEle);
		hueId = hueEle.id
		adjustPreview(hueEle.value, saturationEle.value, hueId.replace("Hue", ""));
	};
}

/**
 * Applies on click functionality for a saturation range input.
 * 
 * @param saturationEle saturation range input
 * @param hueEle hue range input
 */
function applySaturationOnClick(saturationEle, hueEle) {
	saturationEle.onchange = function() {
		hueId = hueEle.id
		adjustPreview(hueEle.value, saturationEle.value, hueId.replace("Hue", ""));
	};
}

/**
 * Applies the specified value for hue to the specified saturation control,
 * and preview elements.
 * 
 * @param value hue value
 * @param saturationControl saturation control
 */
function applyHueValue(value, saturationControl) {
	document.getElementById(saturationControl.id).style.background = "linear-gradient(to right, hsl("+ value +",0%,50%),hsl("+ value +",100%, 50%))";
	document.getElementById(saturationControl.id).style.backgroundImage = "linear-gradient(to right, hsl("+ value +",0%,50%),hsl("+ value +",100%, 50%))";
}

/**
 * Adjusts the preview to match the specified hue and saturation values.
 * 
 * @param hue hue value
 * @param saturation saturation value
 * @param previewArea are of preview to adjust
 */
function adjustPreview(hue, saturation, previewArea) {
	if (previewArea === "foreground") {
		document.getElementById("previewFieldSet").style.backgroundColor = "hsla(" + hue  + ", " + saturation + "%, 91%, 1)";
		document.getElementById("previewFieldSet").style.borderColor = "hsla(" + hue  + ", " + saturation + "%, 91%, 1)";
		document.getElementById("previewFieldLegend").style.backgroundColor = "hsla(" + hue  + ", " + saturation + "%, 80%, 1)";
	}
	if (!document.getElementById("whiteBackground").checked && previewArea === "background") {
		document.getElementById("previewBackground").style.backgroundColor = "hsla(" + hue  + ", " + saturation + "%, 90%, 1)";
	}
	if (previewArea === "accent") {
		document.getElementById("previewTableHeader").style.backgroundColor = "hsla(" + hue  + ", " + saturation + "%, 40%, 1)";
	}
}