/**
 * AJAX request and response handler and associated functionality.
 * @author Stephen Abson
 * @version 0.1, 20111030
 */
var req = new Object();

/** Namespace for AJAX request method types. */
req.Method = new Object();

/** A get request type. */
req.Method.GET = "get";

/** A post request type. */
req.Method.POST = "post";

/** Namespace for AJAX data type handling. */
req.DataType = new Object();

/** JSON data type. */
req.DateType.JSON = "json";

/** XML data type. */
req.DataType.XML = "xml";

/** Text data type. */
req.DateType.TEXT = "text";

/**
 * Given a request, process and return its results as the specified data type.
 * @param dataType AJAX data type
 * @param request request whose results to process
 * @return results of request processed as specified AJAX data type
 */
req.DataType.processData = function (dataType, request) {
	if (dataType === req.DataType.JSON) {
		return eval("(" + request.responseText + ")");
	} else if (dataType === req.DataType.XML) {
		return request.responseXML;
	} else if (dataType === req.DataType.TEXT) {
		return request.responseText;
	} else {
		throw new Error("Unknown data type: " + dataType);
	}
};

/** Name space for AJAX requests. */
req.Request = new Object();

/**
 * Opens, sends and handles an asynchronous AJAX request with the specified
 * URL, content and data type.
 * @param url URL to call
 * @param content content to send
 * @param dataType type of response data
 */
req.Request.loadContent = function (url, content, dataType) {
	var onsuccess = null, onerror = null, onwait = null;
	
	/**
	 * Sets the success handler function.
	 * @param onsuccessArg handler function which accepts a single parameter
	 * of processed response data
	 */
	this.setOnsuccess = function (onsuccessArg) {
		onsuccess = onsuccessArg;
	};
	
	/**
	 * Sets the error handler function.
	 * @param onerrorArg handler function which accepts a parameter of processed
	 * response data and a parameter indicating the status code
	 */
	this.setOnerror = function (onerrorArg) {
		onerror = onerrorArg;
	};
	
	/**
	 * Sets the wait state handler function.
	 * @param onwaitArg parameterless wait handler function
	 */
	this.setOnwait = function (onwaitArg) {
		onwait = onwaitArg;
	};
	
	/**
	 * Sends an asynchronous HTTP request.
	 */
	this.send = function () {
		// Default error handler
		if (onerror === undefined || onerror === null) {
			onerror = function (data, status) {
				alert("Error status code: " + status + "; response: " + data);
			};
		}
		req.Request.sendRequest(req.Method.POST, url, true, content, dataType, onsuccess, onerror, onwait);
	};
};

/**
 * Returns an AJAX request object.
 * @return AJAX request object
 */
req.Request.getRequestObject = function () {
	if (window.XMLHttpRequest) {
		return new window.XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new window.ActiveXObject("Microsoft.XMLHTTP");
	} else {
		return null;
	}
};

/**
 * Returns an opened AJAX request object.
 * @param method HTTP method to use
 * @param url URL to call
 * @param async whether the call should be asynchronous
 * @return opened AJAX request object 
 */
req.Request.openRequest = function (method, url, async) {
	var request = req.Request.getRequestObject();
	request.open(method, url, async);
	return request;
};

/**
 * Opens and sends an AJAX request. If the request is not asynchronous, the
 * request object used is returned.
 * @param method HTTP method to use
 * @param url URL to call
 * @param async whether the call should be asynchronous
 * @param content content to send
 * @param onsuccess if the call is asynchronous, call this method on success
 * passing the resulting response data processed according to the data type;
 * if the call is not asynchronous, ignore this parameter
 * @param onerror if the call is asynchronous, call this method on error passing
 * the resulting response data processed according to the data type; if the call
 * is not asynchronous, ignore this parameter 
 * @param onwait call this method during the asynchronous wait state; if the
 * call is not asynchronous, ignore this parameter
 * @param dataType the type of the response data; if the call is not
 * asynchronous; this parameter is ignored
 */
req.Request.sendRequest = function (method, url, async, content, dataType, onsuccess, onerror, onwait) {
	var request = req.Request.openRequest(method, url, async);
	if (method === req.Method.POST) {
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	} else {
		url = url + "?" + content;
	}
	if (async) {
		request.onreadystatechange = function () {
			if (onsuccess === undefined || onsuccess === null) {
				throw new Error("Success handler method not set or null");
			}
			if (onerror === undefined || onerror === null) {
				throw new Error("Error handler method not set or null");
			}
			if (request.readyState === 4) {
				if (request.status === 200) {
					onwait.call(req.DataType.processData(dataType, request));
				} else {
					onerror.call(req.DataType.processData(dataType, request), request.status);
				}
			} else {
				if (onwait !== undefined && onwait !== null) {
					onwait.call();
				}
			}
		};
	}
	request.send(content);
	if (!async) {
		return request;
	}
};