function warrantNoteItemsCreateOnClick() {
	$("#createWarrantNoteItemLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/warrant/createWarrantNoteItem.html",
		   {
				type: "GET",
				async: false,
				data: {warrantNoteItemIndex: currentWarrantNoteItemIndex},
				success: function(data) {
					$("#warrantNoteTableBody").append(data);
					warrantNoteItemRowOnClick(currentWarrantNoteItemIndex);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$("#warrantNoteTableBody").html(jqXHR.responseText );
				}
			});
		currentWarrantNoteItemIndex++;
		return false;
	});
};

function warrantNoteItemRowOnClick(warrantNoteItemIndex) {
	assignDatePicker("warrantNoteItemDate" + warrantNoteItemIndex);
	$("#removeWarrantNoteLink" + warrantNoteItemIndex).click(function() {
		if ($("#warrantNoteOperation" + warrantNoteItemIndex).val() == "UPDATE") {
			$("#warrantNoteOperation" + warrantNoteItemIndex).val("REMOVE");
			$("#warrantNoteItemRow" + warrantNoteItemIndex).addClass("removeRow");
		} else if($("#warrantNoteOperation" + warrantNoteItemIndex).val() == "REMOVE") {
			$("#warrantNoteOperation" + warrantNoteItemIndex).val("UPDATE");
			$("#warrantNoteItemRow" +warrantNoteItemIndex).removeClass("removeRow");
		} else {
			$("#warrantNoteItemRow" + warrantNoteItemIndex).remove();
		}
		return false;
	});
};

function warrantCauseViolationItemsCreateOnClick() {
	var items =  document.getElementsByClassName("createWarrantCauseViolationItemLink");
	
	for(var i = 0; i < items.length; i++){
		var link = items[i].getElementsByTagName('a')[0];
		
		$(link).on("click", function() {
			var courtCase = this.getElementsByTagName('input')[0].value;
			$.ajax(config.ServerConfig.getContextPath() + "/warrant/createWarrantCauseViolationItem.html",
			   {
					type: "GET",
					async: false,
					data: {
						warrantCauseViolationItemIndex: currentWarrantCauseViolationItemIndex,
						courtCase: courtCase,
					},
					success: function(data) {
						$("#warrantCauseViolationTableBody").append(data);
						warrantCauseViolationItemRowOnClick(currentWarrantCauseViolationItemIndex);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#warrantCauseViolationTableBody").html(jqXHR.responseText );
					}
				});
			currentWarrantCauseViolationItemIndex++;
			return false;
		});
	}
};

function warrantCauseViolationItemRowOnClick(warrantCauseViolationItemIndex) {
	conditionOnSelect(warrantCauseViolationItemIndex);
	$("#removeWarrantCauseViolationLink" + warrantCauseViolationItemIndex).click(function() {
		if ($("#warrantCauseViolationOperation" + warrantCauseViolationItemIndex).val() == "UPDATE") {
			$("#warrantCauseViolationOperation" + warrantCauseViolationItemIndex).val("REMOVE");
			$("#warrantCauseViolationItemRow" + warrantCauseViolationItemIndex).addClass("removeRow");
		} else if($("#warrantCauseViolationOperation" + warrantCauseViolationItemIndex).val() == "REMOVE") {
			$("#warrantCauseViolationOperation" + warrantCauseViolationItemIndex).val("UPDATE");
			$("#warrantCauseViolationItemRow" +warrantCauseViolationItemIndex).removeClass("removeRow");
		} else {
			$("#warrantCauseViolationItemRow" + warrantCauseViolationItemIndex).remove();
		}
		return false;
	});
};


function displayConditionOptions(warrantCauseViolationItemIndex){
	var courtCase = $("#warrantCourtCaseId" + warrantCauseViolationItemIndex).val();
	var condition = $("#warrantCauseViolationItems\\[" + warrantCauseViolationItemIndex + "\\]\\.condition").val();
	$.ajax(config.ServerConfig.getContextPath() + "/warrant/displayConditions.html",
	{
		type: "GET",
		async: false,
		data: {
			courtCase: courtCase,
			condition: condition
		},
		success: function(data) {
			$("#warrantCondition" + warrantCauseViolationItemIndex).html(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#warrantCondition" + warrantCauseViolationItemIndex).html(jqXHR.responseText );
		}
	});
}

function conditionOnSelect(warrantCauseViolationItemIndex){
	$("#warrantCondition" + warrantCauseViolationItemIndex).change(function(){
		if(this.value){
			$("#warrantCauseViolationItems\\[" + warrantCauseViolationItemIndex + "\\]\\.condition").val(this.value);
		}
		else{
			$("#warrantCauseViolationItems\\[" + warrantCauseViolationItemIndex + "\\]\\.condition").val('');
		}
	});
}


function assignDatePicker(elementId) {
	$("#" + elementId).datepicker({
		changeMonth: true,
		changeYear: true
	});
};