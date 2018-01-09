/*
 * Cache manager behavior.
 *
 * Author: Stephen Abson
 */
window.onload = function() {
	
	// Behavior to clear entities
	document.getElementById("clearEntity").onclick = function() {
		
		// Sets result content
		function setResultContent(content) {
			var result = document.getElementById("clearEntityResult");
			result.innerHTML = content;
		}
		
		// Clear content
		setResultContent("");
		
		// Handle results
		function readyStateChange(request) {			
			if (request.readyState == 4) {
				if (request.status == 200) {
					setResultContent(request.responseText);
				} else {
					alert("Error - status: " + request.status + "; text: " + request.statusText);
					setResultContent("");
				}
			}
		}

		// Make request
		var url = config.ServerConfig.getContextPath() + "/cache/clearEntity.html";
		var entityName = document.getElementById("entityName").value;
		var request = new XMLHttpRequest();
		request.open("POST", url + "?entityName=" + entityName, false);
		request.onreadystatechange = function() {
			readyStateChange(request);
		};
		request.send(null);
		return false;
	};

	// Behavior to clear regions
	document.getElementById("clearRegion").onclick = function() {
		
		// Sets result content
		function setResultContent(content) {
			var result = document.getElementById("clearRegionResult");
			result.innerHTML = content;
		}
		
		// Clear content
		setResultContent("");
		
		// Handle results
		function readyStateChange(request) {	
			if (request.readyState == 4) {
				if (request.status == 200) {
					setResultContent(request.responseText);
				} else {
					alert("Error - status: " + request.status + "; text: " + request.statusText);
					setResultContent("");
				}
			}
		}

		// Make request
		var url = config.ServerConfig.getContextPath() + "/cache/clearRegion.html";
		var regionName = document.getElementById("regionName").value;
		var request = new XMLHttpRequest();
		request.open("POST", url + "?regionName=" + regionName, false);
		request.onreadystatechange = function() {
			readyStateChange(request);
		};
		request.send(null);
		return false;
	};
	
	// Behavior to clear
	document.getElementById("clear").onclick = function() {
		
		// Set result content
		function setResultContent(content) {
			var result = document.getElementById("clearResult");
			result.innerHTML = content;
		}
		
		// Clear content
		setResultContent("");
		
		// Handle results
		function readyStateChange(request) {
			if (request.readyState == 4) {
				if (request.status == 200) {
					setResultContent(request.responseText);
				} else {
					alert("Error - status: " + request.status + "; text: " + request.statusText);
					setResultContent("");
				}
			}
		}

		// Make request
		var url = config.ServerConfig.getContextPath() + "/cache/clear.html";
		var request = new XMLHttpRequest();
		request.open("POST", url, false);
		request.onreadystatechange = function() {
			readyStateChange(request);
		};
		request.send(null);
		return false;
	};
};