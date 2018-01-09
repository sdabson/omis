window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var boxes = document.getElementsByClassName('selectConditionCheckBox');
	document.getElementById("selectAll").onchange = function(event){
		for(var i = 0; i < boxes.length; i++){
			boxes[i].checked = event.target.checked;

			if(document.getElementById("conditionCategory").value == "SPECIAL"){
				var boxId = boxes[i].id.split("conditionItems")[1].split(".")[0];
				if(event.target.checked){
					document.getElementById("conditionClause"+boxId).className += " hidden";
					document.getElementById("conditionClauseTextarea"+boxId).classList.remove("hidden");
				}
				else{
					document.getElementById("conditionClauseTextarea"+boxId).className += " hidden";
					document.getElementById("conditionClause"+boxId).classList.remove("hidden");
				}
			}
		}
	};
	
	if(document.getElementById("conditionCategory").value == "SPECIAL"){
		for(var i = 0; i < boxes.length; i++){
			boxes[i].onchange = function(event){
				var id = event.target.id.split("conditionItems")[1].split(".")[0];
				if(event.target.checked){
					document.getElementById("conditionClause"+id).className += " hidden";
					document.getElementById("conditionClauseTextarea"+id).classList.remove("hidden");
				}
				else{
					document.getElementById("conditionClauseTextarea"+id).className += " hidden";
					document.getElementById("conditionClause"+id).classList.remove("hidden");
				}
			}
		}
	}
	
}