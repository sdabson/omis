function questionAnswerItemsCreateOnClick(qaIndex) {
	$.ajax(config.ServerConfig.getContextPath() + "/questionnaire/addQuestionAnswerItem.html",
		{
			type: "GET",
			async: false,
			data: {questionAnswerEditItemIndex: currentQuestionAnswerEditItemIndex},
			success: function(data) {
				if(qaIndex == 'last'){
					$("#questionAnswerEditItemsBody").append(data);
				}
				else{
					document.getElementById("questionAnswerSet[" + qaIndex + "]").insertAdjacentHTML('afterend',data);
				}
				applyQuestionSetFunctions(currentQuestionAnswerEditItemIndex);
				$(".questionAnswerSet").each(function(sortOrder){
					$(this).find('.sortOrder').val(sortOrder);
				});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#questionAnswerEditItemsBody").html(jqXHR.responseText );
			}
		});
	currentQuestionAnswerEditItemIndex++;
	currentAllowedAnswerItemIndexes.push(0);
	return false;
};

function questionAnswerItemMenuOnClick(qaIndex) {
	$("#removeQuestionAnswerLink" + qaIndex).click(function() {
		if ($('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.operation').val() == "UPDATE") {
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.operation').val("REMOVE");
			$("#questionAnswerSetDiv\\[" + qaIndex + "\\]").addClass("removeSet");
		} else if($('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.operation').val() == "REMOVE") {
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.operation').val("UPDATE");
			$("#questionAnswerSetDiv\\[" + qaIndex + "\\]").removeClass("removeSet");
		} else {
			$("#questionAnswerSet\\[" + qaIndex + "\\]").remove();
		}
		return false;
	});
};

function allowedAnswerItemsCreateOnClick(qaIndex) {
	var aaIndex = currentAllowedAnswerItemIndexes[qaIndex];
	$.ajax(config.ServerConfig.getContextPath() + "/questionnaire/addAllowedAnswerItem.html",
	   {
			type: "GET",
			async: false,
			data: {questionAnswerEditItemIndex: qaIndex,
				   allowedAnswerItemIndex: aaIndex},
			success: function(data) {
				$("#allowedAnswerItemsBody\\[" + qaIndex + "\\]").append(data);
				allowedAnswerItemMenuOnClick(qaIndex, aaIndex);
				showHideNewAnswerCreate(qaIndex, aaIndex);
				applyAnswerValueSearch(qaIndex, aaIndex);
				applyShowHideNewAnswerCreate(qaIndex, aaIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#allowedAnswerItemsBody[" + qaIndex + "]").html(jqXHR.responseText );
			}
		});
	currentAllowedAnswerItemIndexes[qaIndex]++;
	return false;
};

function allowedAnswerItemMenuOnClick(questionAnswerEditItemIndex, allowedAnswerEditItemIndex) {
	$("#removeAnswerLink\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").click(function() {
		if ($("#allowedAnswerOperation\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").val() == "UPDATE") {
			$("#allowedAnswerOperation\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").val("REMOVE");
			$("#answerSet\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").addClass("removeSet");
		} else if($("#allowedAnswerOperation\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").val() == "REMOVE") {
			$("#allowedAnswerOperation\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").val("UPDATE");
			$("#answerSet\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").removeClass("removeSet");
		} else {
			$("#answerSet\\[" + questionAnswerEditItemIndex + "\\]\\[" + allowedAnswerEditItemIndex + "\\]").remove();
		}
	});
};

function showHideAnswerCreate(qaIndex){
	switch($('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionCategory').find(":selected").val()){
		case 'TRUE_FALSE':
		case 'ESSAY':
		case 'DATE':
		case 'PHONE_NUMBER':
		case 'CURRENCY':
		case 'WHOLE_NUMBER':
		case 'DECIMAL_NUMBER':
		case 'SHORT_ANSWER':
			$("#answerCreate\\[" + qaIndex + "\\]").addClass('hidden');
			break;
		case 'MULTIPLE_CHOICE':
		case 'MULTIPLE_CHOICE_ESSAY':
			$("#answerCreate\\[" + qaIndex + "\\]").removeClass('hidden');
			$("#answerCardinality\\[" + qaIndex + "\\]").removeClass('hidden');
			break;
		case 'SELECT_FROM_LIST':
			$("#answerCreate\\[" + qaIndex + "\\]").removeClass('hidden');
			$("#answerCardinality\\[" + qaIndex + "\\]").addClass('hidden');
			break;
		default:
			$("#answerCreate\\[" + qaIndex + "\\]").addClass('hidden');
			break;
	}
};

function applyShowHideQuestionCreate(qaIndex){
	$('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.existingQuestion]').change(function(){
		showHideQuestionCreate(qaIndex);
		clearQuestionFields(qaIndex);
	});
};

function applyShowHideNewAnswerCreate(qaIndex, aaIndex){
	$('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]').change(function(){
		showHideNewAnswerCreate(qaIndex, aaIndex);
		clearAnswerFields(qaIndex, aaIndex);
	});
};

function applyCreateAnswerOnClick(qaIndex){
	$('#answerAddItemLink\\[' + qaIndex + '\\]').click(function(){
		allowedAnswerItemsCreateOnClick(qaIndex);
	});
}

function applyCreateQuestionOnClick(qaIndex){
	$('#questionAddItemLink\\[' + qaIndex + '\\]').click(function(){
		questionAnswerItemsCreateOnClick(qaIndex);
	});
};

function clearQuestionFields(qaIndex){
	switch($('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.existingQuestion]:checked').val()){
		default:
		case 'true':
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionText').val('') ;
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.valid').prop('checked', false);
			$("#questionAnswerEditItems\\[" + qaIndex + "\\]\\.questionCategory > option[value='']").attr("selected", true);
			showHideAnswerCreate(qaIndex);
		break;
		case 'false':
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionQuery').val('');
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.question').val('');
		break;
	}
};

function clearAnswerFields(qaIndex, aaIndex){
	switch($('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]:checked').val()){
		default:
		case 'true':
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.description').val('') ;
		break;
		case 'false':
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.answerQuery').val('') ;
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.answerValue').val('') ;
		break;
	}
};

function showHideQuestionCreate(qaIndex){
	switch($('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.existingQuestion]:checked').val()){
		case 'true':
			$("#questionCreate\\[" + qaIndex + "\\]").addClass('hidden');
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionQuery').removeAttr('disabled');
			break;
		case 'false':
			$("#questionCreate\\[" + qaIndex + "\\]").removeClass('hidden');
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionQuery').attr('disabled', true);
			break;
		default:
			$("#questionCreate\\[" + qaIndex + "\\]").addClass('hidden');
			$('#questionAnswerEditItems\\[' + qaIndex + '\\]\\.questionQuery').removeAttr('disabled');
			break;
	}
};

function showHideNewAnswerCreate(qaIndex, aaIndex){
	switch($('input[type=radio][name=questionAnswerEditItems\\[' + qaIndex + '\\]\\.allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]:checked').val()){
		case 'true':
			$("#createNewAnswer\\[" + qaIndex + "\\]\\[" + aaIndex + "\\]").addClass('hidden');
			$("#questionAnswerEditItems\\[" + qaIndex + "\\]\\.allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").removeAttr('disabled');
			break;
		case 'false':
			$("#createNewAnswer\\[" + qaIndex + "\\]\\[" + aaIndex + "\\]").removeClass('hidden');
			$("#questionAnswerEditItems\\[" + qaIndex + "\\]\\.allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").attr('disabled', true);
			break;
		default:
			$("#createNewAnswer\\[" + qaIndex + "\\]\\[" + aaIndex + "\\]").addClass('hidden');
			$("#questionAnswerEditItems\\[" + qaIndex + "\\]\\.allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").removeAttr('disabled');
			break;
	}
};

function applyAnswerSetFunctions(qaIndex){
	$("#questionAnswerSet\\[" + qaIndex + "\\]").find($(".answerSet")).each(function(i){
		showHideNewAnswerCreate(qaIndex, i);
		applyAnswerValueSearch(qaIndex, i);
		allowedAnswerItemMenuOnClick(qaIndex, i);
		applyShowHideNewAnswerCreate(qaIndex, i);
	});
	
};

function applyQuestionSetFunctions(qaIndex){
	showHideAnswerCreate(qaIndex);
	showHideQuestionCreate(qaIndex);
	applyAnswerSetFunctions(qaIndex);
	applyQuestionSearch(qaIndex);
	questionAnswerItemMenuOnClick(qaIndex);
	applyShowHideQuestionCreate(qaIndex);
	applyCreateAnswerOnClick(qaIndex);
	applyCreateQuestionOnClick(qaIndex);
}

function applyQuestionSearch(qaIndex){
	var queryInput = document.getElementById("questionAnswerEditItems[" + qaIndex + "].questionQuery");
	$(queryInput).autocomplete({
		source: function(request, response) {
			$.ajax({
				url: config.ServerConfig.getContextPath() + "/questionnaire/questionSearch.json",
				dataType: "json",
				type: "GET",
				data: request,
				cache: false,
				success: function(data) {
					response(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status text " + textStatus + "; status " + jqXHR.status + "; URL: " + url);
				}
			})
		},
		select: function(event, ui) {
			queryInput.value = ui.item.label;
			document.getElementById("questionAnswerEditItems[" + qaIndex + "].question").value = ui.item.id;
			$("#questionAnswerEditItems\\[" + qaIndex + "\\]\\.questionCategory > option[value=" + ui.item.category + "]").attr("selected", true);
			showHideAnswerCreate(qaIndex);
			return false;
		},
		focus: function(event, ui) {
			queryInput.value = ui.item.label;
			return false;
		}
	});
	
	document.getElementById("clearQuestionLink[" + qaIndex + "]").onclick = function() {
		document.getElementById("questionAnswerEditItems[" + qaIndex + "].question").value = "";
		document.getElementById("questionAnswerEditItems[" + qaIndex + "].questionQuery").value = "";
		return false;
	};
};

function applyAnswerValueSearch(qaIndex, aaIndex){
	applyValueLabelAutoComplete(
			document.getElementById("questionAnswerEditItems[" + qaIndex + "].allowedAnswerItems[" + aaIndex + "].answerQuery"),  
			document.getElementById("questionAnswerEditItems[" + qaIndex + "].allowedAnswerItems[" + aaIndex + "].answerValue"), 
			config.ServerConfig.getContextPath() + "/questionnaire/answerValueSearch.json");
	document.getElementById("clearAnswerLink[" + qaIndex + "][" + aaIndex + "]").onclick = function() {
		document.getElementById("questionAnswerEditItems[" + qaIndex + "].allowedAnswerItems[" + aaIndex + "].answerValue").value = "";
		document.getElementById("questionAnswerEditItems[" + qaIndex + "].allowedAnswerItems[" + aaIndex + "].answerQuery").value = "";
		return false;
	};
};