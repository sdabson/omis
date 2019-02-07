var scale=1.00;
var originalWindowWidth;
var originalCanvasWidth, originalCanvasHeight;

window.onload = function() {
	var rows = document.getElementsByClassName('actionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
			applyLinkConfirmation("reassessLink", "reassessConfirmMessage", "omis.assessment.msgs.assessmentRating");
		});
	}
	
	
	if (graphData.length > 0) {
		var c = document.getElementById("assessmentGraph"),
			c2 = document.getElementById("assessmentGraphScaled"),
			dragging = false,
			lastX,
			marginLeft = 0,
			resolver = new common.MessageResolver("omis.assessment.msgs.assessmentRating");
		document.getElementById("toggleGraph").onclick = function(e){
			if (c.classList.contains("hidden")) {
				c.classList.remove("hidden");
				c2.classList.add("hidden");
				e.target.innerHTML = resolver.getMessage("togglePercentageLabel", null);
			} else {
				c.classList.add("hidden");
				c2.classList.remove("hidden");
				e.target.innerHTML = resolver.getMessage("toggleScoreLabel", null);
			}
		};
		
		
		//If this window in within a hidden iframe, canvas cannot draw, so wait til the iframe is not hidden (resized) to draw.
		if (c.parentElement.clientWidth == 0) {
			var loaded = false;
			window.addEventListener("resize", tryLoad);
			function tryLoad() {
				if (c.parentElement.clientWidth > 0 && !loaded) {
					readyGraphs();
					loaded = true;
				}
			}
		} else {
			readyGraphs();
		}
		
		document.getElementById("graphError").onclick = function(e) {
			readyGraphs();
		};
	}
	
	function readyGraphs() {
		try{
			if(document.getElementById("assessmentGraphContainer").getBoundingClientRect().width > 0) {
				if (document.getElementById("toggleGraph").classList.contains("hidden")) {
					document.getElementById("toggleGraph").classList.remove("hidden");
					document.getElementById("graphError").classList.add("hidden");
				}
				
				fillCanvas(c, graphData);
				fillCanvas(c2, graphData, true);
				originalWindowWidth = window.innerWidth;
				originalCanvasWidth = c.width;
				originalCanvasHeight = c.height;
				c2.classList.remove("hiddenGraph");
				c2.classList.add("hidden");
				applyListeners();
				
			} else {
				document.getElementById("toggleGraph").classList.add("hidden");
				document.getElementById("graphError").classList.remove("hidden");
			}
		} catch (e) {
			document.getElementById("toggleGraph").classList.add("hidden");
			document.getElementById("graphError").classList.remove("hidden");
		};
	}
	
	function debounce(func, wait, immediate) {
		var timeout;
		return function() {
			var context = this, args = arguments;
			var later = function() {
				timeout = null;
				if (!immediate) func.apply(context, args);
			};
			var callNow = immediate && !timeout;
			clearTimeout(timeout);
			timeout = setTimeout(later, wait);
			if (callNow) func.apply(context, args);
		};
	}
	
	function applyListeners() {
		var resizeCanvas = debounce(function() {
			if ((originalCanvasHeight * (window.innerWidth / originalWindowWidth)) > (window.innerHeight - 100)) {
				if (originalCanvasHeight * scale < (window.innerHeight - 100)) {
					while (originalCanvasHeight * scale < (window.innerHeight - 100)) {
						scale += .001;
					}
				} else if (originalCanvasHeight * scale > (window.innerHeight - 100)) {
					while (originalCanvasHeight * scale > (window.innerHeight - 100)) {
						scale -= .001;
					}
				}
			} else {
				scale = window.innerWidth / originalWindowWidth;
			}
			c.style.width = (originalCanvasWidth * scale) + "px";
			c2.style.width = (originalCanvasWidth * scale) + "px";
		}, 250);
		window.addEventListener("resize", resizeCanvas);
		
		c.parentElement.addEventListener('mousedown', function(e) {
			var evt = e || event;
			dragging = true;
			lastX = evt.clientX;
			e.preventDefault();
		}, false);
		
		window.addEventListener('mousemove', function(e) {
			var evt = e || event;
			if (dragging) {
				var delta = evt.clientX - lastX;
				lastX = evt.clientX;
				marginLeft += delta;
				if (Math.abs(marginLeft + delta) > Math.abs(getWidth(c) - c.parentElement.clientWidth)) {
					marginLeft = -(getWidth(c) - c.parentElement.clientWidth);
				} else if ((marginLeft + delta) > 0) {
					marginLeft = 0;
				}
				if (getWidth(c) > c.parentElement.clientWidth
						|| (getWidth(c) <= c.parentElement.clientWidth
								&& (getMargin(c) < 0 || getMargin(c) > Math.abs(getWidth(c) - c.parentElement.clientWidth)))) {
					c.style.marginLeft = marginLeft + "px";
					c2.style.marginLeft = marginLeft + "px";
				}
			}
			e.preventDefault();
		}, false);
		
		window.addEventListener('mouseup', function() {
			dragging = false;
		}, false);
	}
}
	
function getMargin(el) {
	return el.style.marginLeft.split("px")[0];
}

function getWidth(el) {
	return el.style.width.split("px")[0];
}
	
function fillCanvas (c, data, scaled) {
	var resolver = new common.MessageResolver("omis.assessment.msgs.assessmentRating");
	if (c.parentElement.clientWidth * (data.length / 5) <= c.parentElement.clientWidth) {
		c.width = c.parentElement.getBoundingClientRect().width * .9;
		c.style.width = c.parentElement.getBoundingClientRect().width * .9 + "px";
	} else {
		c.width = c.parentElement.getBoundingClientRect().width * (data.length / 5);
		c.style.width = c.parentElement.getBoundingClientRect().width * (data.length / 5) + "px";
	}
	c.height = c.parentElement.clientWidth;
	var ctx = c.getContext("2d");
	
	var xMargin = 20,
		yMargin = 50,
		cRight = c.width - xMargin,
		cBottom = c.height - yMargin,
		cLeft = xMargin,
		cTop = yMargin;
	ctx.beginPath();
	
	var maxGraphScore = 0;
	var minGraphScore = 0;
	if (!scaled) {
		for (var i = 0; i < data.length; i++) {
			for (var j = 0; j < data[i].ratings.length; j++) {
				if (maxGraphScore < parseInt(data[i].ratings[j].max)) {
					maxGraphScore = parseInt(data[i].ratings[j].max);
				}
			}
		}
		minGraphScore = maxGraphScore;
		for (var i = 0; i < data.length; i++) {
			for (var j = 0; j < data[i].ratings.length; j++) {
				if (minGraphScore > parseInt(data[i].ratings[j].min)) {
					minGraphScore = parseInt(data[i].ratings[j].min);
				}
			}
		}
	} else {
		maxGraphScore = 100;
	}
	
	if (!scaled) {
		if ((maxGraphScore) % 10 != 0) {
			maxGraphScore += 10 - (Math.abs(maxGraphScore) % 10);
		}
		if ((minGraphScore) % 10 != 0) {
			minGraphScore -= 10 - (Math.abs(minGraphScore) % 10);
		}
	}
	
	var graphScoreRange = maxGraphScore - minGraphScore,
		noOfGrids = graphScoreRange / 5,
		axisY = cBottom + minGraphScore / graphScoreRange * (cBottom - cTop);
//	Min Ticks
	ctx.lineWidth = 0.25;
	var vGridDiff = (cBottom - cTop) / noOfGrids,
		yValue;
	ctx.save();
	for (var i = 0; i <= noOfGrids; i++) {
		yValue = cBottom - i * vGridDiff;
		ctx.moveTo(cLeft, yValue);
		ctx.fillStyle = "rgba(0, 0, 0, .25)";
		ctx.lineTo(cRight, yValue);
		ctx.font = "8px 'Arial'";
		ctx.fillStyle = "rgba(0, 0, 0, 1)";
		ctx.fillText(((i * graphScoreRange / noOfGrids) + minGraphScore), 5, yValue);
	}
	ctx.stroke();
	ctx.restore();
	
//	Title
	//ctx.textAlign = "center";
	ctx.save();
	ctx.font ="14px 'Arial'";
	ctx.fillText(assessmentTitle, 30, 20);
	ctx.restore();
	
//	Y Label
	var yAxisLabel;
	if (scaled) {
		yAxisLabel = resolver.getMessage("percentLabel", null);
	} else {
		yAxisLabel = resolver.getMessage("scoreLabel", null);
	}
	ctx.save();
	ctx.font ="10px 'Arial'";
	ctx.translate(15, c.height);
	ctx.rotate(-Math.PI / 2);
	ctx.fillText(yAxisLabel, 10, 0);
	ctx.restore();
	
	var barWidth = (c.width - cLeft) / (data.length + 1) / 2,
		padding = ((c.width - cLeft) - (barWidth * data.length)) / (data.length + 1),
		maxBarHeight = Math.round((cBottom - cTop) * 1.0);
	
	for (var i = 0; i < data.length; i++) {
		var curMaxScore = maxGraphScore,
			curMinScore = curMaxScore;
		for (var j = 0; j < data[i].ratings.length; j++) {
			if (curMinScore > parseInt(data[i].ratings[j].min)) {
				curMinScore = parseInt(data[i].ratings[j].min);
			}
		}
		if (scaled) {
			curMaxScore = 0;
			for (var j = 0; j < data[i].ratings.length; j++) {
				if (curMaxScore < parseInt(data[i].ratings[j].max)) {
					curMaxScore = parseInt(data[i].ratings[j].max);
				}
			}
		}
		
		var xBar = cLeft+(padding*(i+1))+(barWidth*i),
			scoreHeight = 0;
			if (!scaled) {
				scoreHeight = cBottom - (parseInt(data[i].score) / graphScoreRange * maxBarHeight)
					+ (minGraphScore / graphScoreRange * maxBarHeight);
			} else {
				scoreHeight = cBottom - (parseInt(data[i].score) / curMaxScore * maxBarHeight)
					+ (curMinScore / curMaxScore * maxBarHeight);
			}
		var scoreRegion = {x:xBar-10, y:scoreHeight-10, w:barWidth+20, h:20},
			scoreColor = "gold",
			curBarTop = cBottom - ((curMinScore - minGraphScore) / graphScoreRange * maxBarHeight);
		if(scaled) {
			curBarTop = cBottom;
		}
		for (var j=0; j < data[i].ratings.length; j++) {
			if (parseInt(data[i].ratings[j].max) == parseInt(data[i].ratings[j].min) && !scaled) {
				continue;
			}
			var r = (255 * (j*10)),
				g = (100 - (j*40)) + 100,
				color = "rgba(" + r + ", " + g + ", 0, 1)",
				curRatingHeight = (parseInt(data[i].ratings[j].max)
						/ graphScoreRange * maxBarHeight) 
						- (parseInt(data[i].ratings[j].min - 1) / graphScoreRange * maxBarHeight);
			if (scaled) {
				curRatingHeight = (parseInt(data[i].ratings[j].max - data[i].ratings[j].min)
						/ (curMaxScore - curMinScore)) * maxBarHeight;
				if (j > 0) {
					curRatingHeight = (parseInt((data[i].ratings[j].max + 1) - data[i].ratings[j].min)
							/ (curMaxScore - curMinScore)) * maxBarHeight;
				}
			}
			curBarTop -= curRatingHeight;
			var region = {
						x: xBar,
						y: curBarTop,
						w: barWidth,
						h: curRatingHeight
					};
			new ToolTip(c, region,
					resolver.getMessage("ratingRangeDisplayLabel",
							[data[i].ratings[j].rating, data[i].ratings[j].min, data[i].ratings[j].max]),
					80, 1500, color, scoreRegion);
			drawRect(ctx, region, color);
		}
		
		//Score 
		drawScore(ctx, xBar, barWidth, scoreHeight);
		new ToolTip(c, scoreRegion, resolver.getMessage("scoreDisplayLabel", [data[i].score]),
				75, 3000, scoreColor);
		
		//Labels
		ctx.save();
		var fontSize = 12,
			width = ctx.measureText(data[i].section).width;
		//while (ctx.measureText(data[i].section).width >= (barWidth + padding) && fontSize > 8) {
			//fontSize--;
		//}
		ctx.font = fontSize + "px 'Arial'";
		ctx.translate(xBar, cBottom+10);
		ctx.textBaseline = 'middle';
		ctx.textAlign = "left";
		wrapText(ctx, data[i].section, 0, 0, (barWidth + padding - 7), fontSize);
		ctx.restore();
	}
	
	/* Graph Axes */
	ctx.lineWidth = 1;
//	Horizontal Axis
	ctx.moveTo(cLeft, axisY);
	ctx.lineTo(cRight, axisY);
//	Vertical Axis
	ctx.moveTo(cLeft, cBottom);
	ctx.lineTo(cLeft, cTop);
	ctx.stroke();
}
	
function drawScore(ctx, xBar, barWidth, scoreHeight) {
	ctx.fillStyle = "rgba(0, 0, 0, 1)"
		ctx.beginPath();
	ctx.lineWidth = .75;
	ctx.moveTo(xBar, scoreHeight);
	ctx.lineTo(xBar - 5, scoreHeight + 5);
	ctx.moveTo(xBar, scoreHeight);
	ctx.lineTo(xBar - 5, scoreHeight - 5);
	ctx.moveTo(xBar, scoreHeight);
	ctx.lineTo(xBar + barWidth, scoreHeight);
	ctx.lineTo(xBar + barWidth + 5, scoreHeight + 5);
	ctx.moveTo(xBar + barWidth, scoreHeight);
	ctx.lineTo(xBar + barWidth + 5, scoreHeight - 5);
	ctx.stroke();
}
	
function drawRect(ctx, region, color) {
	ctx.fillStyle = color;
	ctx.lineWidth = .5;
	ctx.beginPath();
	ctx.rect(region.x, region.y, region.w, region.h);
	ctx.stroke();
	ctx.fill();
}
	
//By Character
function wrapText(context, text, x, y, maxWidth, lineHeight) {
	var words = text;
	var line = '';
	
	for(var n = 0; n < words.length; n++) {
		var testLine = line + words[n] + '';
		var metrics = context.measureText(testLine);
		var testWidth = metrics.width;
		if (testWidth > maxWidth && n > 0) {
			context.fillText(line + "-", x, y);
			line = words[n] + '';
			y += lineHeight;
		}
		else {
			line = testLine;
		}
	}
	context.fillText(line, x, y);
}
	
//By word (proper word wrap)
//via https://www.html5canvastutorials.com/tutorials/html5-canvas-wrap-text-tutorial/
/*function wrapText(context, text, x, y, maxWidth, lineHeight) {
	var words = text.split(' ');
	var line = '';
	
	for(var n = 0; n < words.length; n++) {
		var testLine = line + words[n] + ' ';
		var metrics = context.measureText(testLine);
		var testWidth = metrics.width;
		if (testWidth > maxWidth && n > 0) {
			context.fillText(line, x, y);
			line = words[n] + ' ';
			y += lineHeight;
		}
		else {
			line = testLine;
		}
	}
	context.fillText(line, x, y);
}*/

//via https://stackoverflow.com/a/29490892, modified
//all hail the stackoverflow overlords. 
function ToolTip(canvas, region, text, width, timeout, color, excludingRegion) {
	var me = this,                                // self-reference for event handlers
		div = document.createElement("div"),      // the tool-tip div
		parent = canvas.parentNode,               // parent node for canvas
		visible = false;                          // current status
		
	// set some initial styles, can be replaced by class-name etc.
	div.style.cssText =
			"position: fixed;" +
			"padding: 7px;" +
			"border-style: solid;" +
			"border-color: black;" +
			"border-width: 1px;" +
			"background: " + color + ";" +
			"pointer-events: none;" +
			"width: " + width + "px";
	
	div.innerHTML = text;
	
	// show the tool-tip
	this.show = function(pos) {
		if (!visible && !canvas.classList.contains("hiddenGraph")) {// ignore if already shown (or reset time), only if the canvas is visible.
			visible = true;                           // lock so it's only shown once
			setDivPos(pos);                           // set position
			parent.appendChild(div);                  // add to parent of canvas
			//setTimeout(hide, timeout);                // timeout for hide
		}
	}
	
	// hide the tool-tip
	function hide() {
		visible = false;                            // hide it after timeout
		if (parent.contains(div)){
			parent.removeChild(div);                    // remove from DOM
		}
	}
	
	// check mouse position, add limits as wanted... just for example:
	function check(e) {
		var pos = getPos(e),
		posAbs = {x: e.clientX, y: e.clientY};  // div is fixed, so use clientX/Y
		
		if(!excludingRegion) {
			if (!visible &&
					pos.x >= region.x && pos.x < region.x + region.w &&
					pos.y >= region.y && pos.y < region.y + region.h) {
				me.show(posAbs);                          // show tool-tip at this pos
			}
			else if(pos.x >= region.x && pos.x < region.x + region.w &&
					pos.y >= region.y && pos.y < region.y + region.h) {
				setDivPos(posAbs);                     // otherwise, update position
			} else {
				hide();
			}
		} else {
			if (!visible &&
					pos.x >= region.x && pos.x < region.x + region.w &&
					pos.y >= region.y && pos.y < region.y + region.h &&
					!(pos.x >= excludingRegion.x && pos.x < excludingRegion.x + excludingRegion.w &&
					pos.y >= excludingRegion.y && pos.y < excludingRegion.y + excludingRegion.h)) {
				me.show(posAbs);                          // show tool-tip at this pos
			}
			else if(pos.x >= region.x && pos.x < region.x + region.w &&
					pos.y >= region.y && pos.y < region.y + region.h &&
					!(pos.x >= excludingRegion.x && pos.x < excludingRegion.x + excludingRegion.w &&
					pos.y >= excludingRegion.y && pos.y < excludingRegion.y + excludingRegion.h)) {
				setDivPos(posAbs);                     // otherwise, update position
			} else {
				hide();
			}
		}
	}
	
	// get mouse position relative to canvas
	function getPos(evt) {
		var r = canvas.getBoundingClientRect();
		var x = (evt.clientX - r.left) / scale;
		var y = (evt.clientY - r.top) / scale;
		
		return {
			x: x,
			y: y
		}
	}
	
	// update and adjust div position if needed (anchor to a different corner etc.)
	function setDivPos(pos) {
		if (visible){
			if (pos.x < 0) pos.x = 0;
			if (pos.y < 0) pos.y = 0;
			// other bound checks here
			div.style.left = (pos.x + 10) + "px";
			div.style.top = pos.y + "px";
			if (pos.x + width > (canvas.parentElement.offsetLeft + canvas.parentElement.clientWidth)) {
				div.style.left = (pos.x - width - 15) + "px";
				div.style.top = pos.y + "px";
			} else {
				div.style.left = (pos.x + 10) + "px";
				div.style.top = pos.y + "px";
			}
		}
	}
	
	// we need to use shared event handlers:
	canvas.addEventListener("mousemove", check);
	canvas.addEventListener("click", check);
}
