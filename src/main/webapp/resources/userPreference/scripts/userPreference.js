/**
 * Behavior for user preference screen.
 *
 * Author: Joel Norris
 * Version: 0.1.0
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyHueOnClick(document.getElementById("backgroundHue"), document.getElementById("backgroundSaturation"));
	applySaturationOnClick(document.getElementById("backgroundSaturation"), document.getElementById("backgroundHue"));
	applyHueOnClick(document.getElementById("foregroundHue"), document.getElementById("foregroundSaturation"));
	applySaturationOnClick(document.getElementById("foregroundSaturation"), document.getElementById("foregroundHue"));
	applyHueOnClick(document.getElementById("accentHue"), document.getElementById("accentSaturation"));
	applySaturationOnClick(document.getElementById("accentSaturation"), document.getElementById("accentHue"));
	applyHueValue(document.getElementById("backgroundHue").value, document.getElementById("backgroundSaturation"));
	applyHueValue(document.getElementById("foregroundHue").value, document.getElementById("foregroundSaturation"));
	adjustPreview(document.getElementById("foregroundHue").value, document.getElementById("foregroundSaturation").value, "foreground");
	adjustPreview(document.getElementById("backgroundHue").value, document.getElementById("backgroundSaturation").value, "background");
	applyHueValue(document.getElementById("accentHue").value, document.getElementById("accentSaturation"));
	adjustPreview(document.getElementById("accentHue").value, document.getElementById("accentSaturation").value, "accent");
	document.getElementById("whiteBackground").onclick = function() {
		if (document.getElementById("whiteBackground").checked) {
			document.getElementById("previewBackground").style.backgroundColor = "hsla(0, 0%, 100%, 1)";
		} else {
			document.getElementById("previewBackground").style.backgroundColor = "hsla(" + document.getElementById("backgroundHue").value  + ", " + document.getElementById("backgroundSaturation").value + "%, 90%, 1)";
		}
	}
}