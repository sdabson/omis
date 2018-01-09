/** Matrix implementation of a queue.
 * @param rows number of rows in matrix.
 * @param cols number of cols in Matrix.
 * @author Ryan Johns
 * @version 0.1.0 (Jun 20, 2013)
 */
queueMatrix = function(cols, rows) {
	this.rows = rows; 
	this.cols = cols;
	
	
	// array to matrix coord ( y = matrixX / cols, x = matrixX mod rows)
	// matrix to array coord ( cols * rows + cols )
	var matrix = new Array();
	
	/** Adds item to front.
	 * @param item item to be added. */
	this.enqueue = function(item) {
		matrix.unshift(item);
		
		if (matrix.length > cols * rows)
			return matrix.pop();
		
	};
	
	/** Removes item from end.
	 * @return item removed from matrix. */
	this.dequeue = function() {
			return matrix.pop();
	};
	
	/** Removes element from given index.
	 * @param row row of element to be removed.
	 * @param col column of element to be removed. */
	this.removeElement = function(row, col) {
		matrix = matrix.splice(cols * col + row, 1);
	};
	
	/** Removes given item.
	 * @param item item to be removed. */
	this.removeElement = function(item) {
		var result = null;
		
		if (matrix.indexOf(item) >= 0) 
			result = matrix.splice(matrix.indexOf(item),1);
		
		return result;
	};
	
	/** Return element with given indices.
	 * @param row row of item. 
	 * @param col column of item.
	 * @return item. */
	this.get = function(row, col) {
		return matrix[cols * col + row];
	};
	
	this.getIndexOf = function(navigationItem) {
		var ret = -1;
		for (var x = 0; x < matrix.length; x++) {
			if (navigationItem.getKey() == matrix[x].getKey()) {
				ret = x;
			}
		}
			
		return ret;
	};
	
	
	/** Given an item find the coordinates.
	 * @param item item to find.
	 * @param return coordinate */
	this.getMatrixCoordinates = function(item) {
		var result = null;
		
		var coordinate = new coordinates();
		
		var index = this.getIndexOf(item);
		if (index >= 0) {
			coordinate.x = (index % cols);
			coordinate.y = Math.floor(index / cols);
			result = coordinate;
		}
		return result;
	};
	
	/** Returns size of matrix.
	 * @return size. */
	this.size = function() { return matrix.length; };
	
	/** Gets data in an array.
	 * @return data. */
	this.toArray = function() { return matrix; };
	
	this.getMaxYCoordinates = function(x) {
		var lastX = ((matrix.length-1) % cols);
		var lastY = Math.floor((matrix.length-1) / cols);
		
		var resultY = 0;
		
		if (x < lastX)
			resultY = rows-1;
		else
			resultY = lastY;
		
		return resultY;
	};
	
	this.getMaxXCoordinates = function(y) {
		var lastX = ((matrix.length-1) % cols);
		var lastY = Math.floor((matrix.length-1) / cols);
		
		var resultX = 0;
		
		if (y < lastY)
			resultX = cols-1 ;
		else
			resultX = lastX;
		
		return resultX;
	};
};

coordinates = function(){
	var object = {};
	object.x = 0;
	object.y = 0;
	
	object.equals = function(other) {
		result = false;
		
		if (other.x == object.x && other.y == object.y)
			result = true;
		
		return result;
	};
	
	return object;
};