/**
 * Java script for interactive tables.
 * 
 * Dependencies: interactiveTable.css
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (5/11/2017)
 */

//Assign event listener to on load event of the window.
//window.addEventListener("load", assignSortableTableInteractions);

/**
 * Sorts the specified tables rows in either an ascending or descending order.
 * 
 * Based off of w3c example - https://www.w3schools.com/howto/howto_js_sort_table.asp
 * 
 * @param n column (nth td child of every row) number
 * @param tableElt table element
 */
function sortTable(n, tableElt) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = tableElt;
	switching = true;
	//Set the sorting direction to ascending:
	dir = "asc"; 
	/*Make a loop that will continue until
	no switching has been done:*/
	while (switching) {
		//start by saying: no switching is done:
		switching = false;
		rows = table.getElementsByTagName("TR");
		/*Loop through all table rows (except the
		first, which contains table headers):*/
		for (i = 1; i < (rows.length - 1); i++) {
			//start by saying there should be no switching:
			shouldSwitch = false;
			/*Get the two elements you want to compare,
			one from current row and one from the next:*/
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/*check if the two rows should switch place,
			based on the direction, asc or desc:*/
			//TODO: If somehow X or Y is undefined, sorting doesn't happen
			//correctly. figure out how undefined cells are happening!
			if (x !== undefined && y !== undefined) {
				if (dir == "asc") {
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						//if so, mark as a switch and break the loop:
						shouldSwitch= true;
						break;
					}
				} else if (dir == "desc") {
					if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
						//if so, mark as a switch and break the loop:
						shouldSwitch= true;
						break;
					}
				}
			}
		}
		if (shouldSwitch) {
			/*If a switch has been marked, make the switch
			and mark that a switch has been done:*/
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			//Each time a switch is done, increase this count by 1:
			switchcount ++;      
		} else {
			/*If no switching has been done AND the direction is "asc",
			set the direction to "desc" and run the while loop again.*/
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}

/**
 * Looks for listTable elements and applies the ability to sort tables on header clicks for each column.
 * Assumes, by convention, that a table header with the class "operations" should be excluded"
 */
function assignSortableTableInteractions() {
	var tables = document.getElementsByClassName("listTable");
	for(var i=0, table; table = tables[i]; i++) {
		if (table.tagName.toLowerCase() == 'table') {
			var headers = table.getElementsByTagName('th');
			for(var h=0, header; header = headers[h]; h++) {
				var thNumber = h;
				if (!header.classList.contains("operations")) {
					header.classList.add("sortable");
					header.onclick = (function(thNumber, table) { 
						return function() {
							sortTable(thNumber, table);
						};
					})(thNumber, table);
				}
			}
		}
	}
}