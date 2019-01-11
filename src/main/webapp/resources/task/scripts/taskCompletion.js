window.onload = function() {
	var completionForm = document.getElementById("completionForm"),
		button = document.getElementById("toggleTaskFormButton");
	var timer;
	
	button.onclick = function() {
		if (completionForm.style.display == "none" || !completionForm.style.display) {
			completionForm.style.display = "block";
			button.classList.add("taskMenuButtonActive");
			button.classList.remove("taskMenuButtonInactive");
			
			completionForm.onmouseout = function() {
				clearTimeout(timer);
				timer = setTimeout(function(){ hideForm(completionForm, button); }, 2000);
				completionForm.classList.remove("taskFormActive");
				completionForm.classList.add("taskFormInactive");
			}
			
			completionForm.onblur = function() {
				clearTimeout(timer);
				timer = setTimeout(function(){ hideForm(completionForm, button); }, 2000);
				completionForm.classList.remove("taskFormActive");
				completionForm.classList.add("taskFormInactive");
			}
			
			button.onmouseout = function() {
				clearTimeout(timer);
				timer = setTimeout(function(){ hideForm(completionForm, button); }, 2000);
				completionForm.classList.remove("taskFormActive");
				completionForm.classList.add("taskFormInactive");
			}
			
			button.onblur = function() {
				clearTimeout(timer);
				timer = setTimeout(function(){ hideForm(completionForm, button); }, 2000);
				completionForm.classList.remove("taskFormActive");
				completionForm.classList.add("taskFormInactive");
			}
			
			completionForm.onmouseover = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
			completionForm.onmouseenter = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
			completionForm.onfocus = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
			button.onmouseover = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
			button.onmouseenter = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
			button.onfocus = function() {
				clearTimeout(timer);
				completionForm.classList.add("taskFormActive");
				completionForm.classList.remove("taskFormInactive");
			}
			
		} else if (completionForm.style.display == "block") {
			hideForm(completionForm, button)
		}
	}
}

function hideForm(completionForm, button) {
	completionForm.style.display = "none";
	button.classList.remove("taskMenuButtonActive");
	button.classList.add("taskMenuButtonInactive")
}