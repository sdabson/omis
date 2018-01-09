<%@ page contentType="text/css" %>

.spectrum {
    background: linear-gradient(to right, hsl(0,100%,50%),hsl(60,100%, 50%), hsl(120,100%, 50%), hsl(180, 100%, 50%), hsl(240,100%,50%),hsl(300,100%,50%), hsl(359, 1000%, 50%));
    background-image: linear-gradient(to right, hsl(0,100%,50%),hsl(60,100%, 50%), hsl(120,100%, 50%), hsl(180, 100%, 50%), hsl(240,100%,50%),hsl(300,100%,50%), hsl(359, 1000%, 50%));
}

.preferenceSlider {
	width: 400px;
    height: 15px;
    border-radius: 25px;
    padding: 5px;
}

#foregroundSaturation {
	background: linear-gradient(to right, hsl(${foregroundHue},0%,50%),hsl(${foregroundHue},100%, 50%));
    background-image: linear-gradient(to right, hsl(${foregroundHue},0%,50%),hsl(${foregroundHue},100%, 50%));	
}

#backgroundSaturation {
	background: linear-gradient(to right, hsl(${backgroundHue},0%,50%),hsl(${backgroundHue},100%, 50%));
    background-image: linear-gradient(to right, hsl(${backgroundHue},0%,50%),hsl(${backgroundHue},100%, 50%));	
}

input[type=range]{
    -webkit-appearance: none;
}

#previewContainer {
	border: solid 1px #000;
}

#previewContainer div#previewBackground p.buttons input,
#previewContainer p.buttons > button[type="submit"],
#previewContainer p.buttons > input[type="submit"] {
    float: right;
    margin-right: 20px;
}

#previewContainer div#previewBackground fieldset#previewFieldSet table#previewTable {
	width: 98%;
}

#previewContainer div#previewBackground fieldset#previewFieldSet table#previewTable tbody tr th#previewTableHeader {
	text-align: left;
	color: white;
}

#previewContainer > fieldset > table > tr {
	width: 100%;
}

#previewFieldSet {
	background-color: hsla(${foregroundHue}, ${foregroundSaturation}, 91%, 1);
	border-color: hsla(${foregroundHue}, ${foregroundSaturation}, 91%, 1);
}

#previewFieldLegend {
	background-color: hsla(${foregroundHue}, ${foregroundSaturation}, 80%, 1);
}

#previewBackground {
	background-color: hsla(${backgroundHue},${backgroundSaturation}, 100%, 1);
}

#previewTableHeader {
	background-color: hsla(${foregroundHue}, ${foregroundSaturation}, 35%, 1);
}