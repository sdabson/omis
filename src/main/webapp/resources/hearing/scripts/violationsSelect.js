window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	document.getElementById("selectAll").onchange = function(event){
		var boxes = document.getElementsByClassName('selectViolationCheckBox');
		for(var i = 0; i < boxes.length; i++){
			boxes[i].checked = event.target.checked;
		}
	};
	
	var hideButtons = document.getElementsByClassName("hideOverflow");
	var showButtons = document.getElementsByClassName("showOverflow");
	var items = document.getElementsByClassName("violationItemRow");
	for(var i = 0; i < items.length; i++){
		var descriptionText = items[i].getElementsByClassName('violationDescriptionNoOverflow')[0];
		if(descriptionText.scrollWidth > descriptionText.offsetWidth){
			showButtons[i].style.display = 'inline-block';
			hideButtons[i].style.display = 'inline-block';
		}
	}
	for(var i = 0; i < showButtons.length; i++){
		showButtons[i].onclick = function() {
			this.parentElement.getElementsByClassName('violationDescriptionNoOverflow')[0].className = "violationDescriptionShow";
			this.style.display = 'none';
		}
		hideButtons[i].onclick = function(){
			this.parentElement.className = "violationDescriptionNoOverflow";
			this.parentElement.parentElement.getElementsByClassName("showOverflow")[0].style.display = 'inline-block';
		}
	}
}