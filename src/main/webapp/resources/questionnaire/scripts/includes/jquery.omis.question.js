/*
 * I prefaces these all with "single" to make it clear that these are 
 * meant for the questionEdit, in which a SINGLE question is being edited,
 * in contrast to questionAnswerEdit, where a LIST of questions are being
 * edited. 
 * 
 * I need to work on my naming conventions...
 * 
 * -aj
 * 
 */
function singleAllowedAnswerItemsCreateOnClick() {
	$.ajax(config.ServerConfig.getContextPath() + "/questionnaire/addSingleAllowedAnswerItem.html",
	   {
			type: "GET",
			async: false,
			data: {allowedAnswerItemIndex: currentAllowedAnswerItemIndex},
			success: function(data) {
				$("#allowedAnswerItemsBody").append(data);
				singleAllowedAnswerItemMenuOnClick(currentAllowedAnswerItemIndex);
				singleShowHideNewAnswerCreate(currentAllowedAnswerItemIndex);
				singleApplyAnswerValueSearch(currentAllowedAnswerItemIndex);
				singleApplyShowHideNewAnswerCreate(currentAllowedAnswerItemIndex);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
					+ errorThrown);
				$("#allowedAnswerItemsBody").html(jqXHR.responseText );
			}
		});
	currentAllowedAnswerItemIndex++;
	return false;
};

function singleAllowedAnswerItemMenuOnClick(allowedAnswerEditItemIndex) {
	$("#removeAnswerLink\\[" + allowedAnswerEditItemIndex + "\\]").click(function() {
		if ($("#allowedAnswerOperation\\[" + allowedAnswerEditItemIndex + "\\]").val() == "UPDATE") {
			$("#allowedAnswerOperation\\[" + allowedAnswerEditItemIndex + "\\]").val("REMOVE");
			$("#answerSet\\[" + allowedAnswerEditItemIndex + "\\]").addClass("removeSet");
		} else if($("#allowedAnswerOperation\\[" + allowedAnswerEditItemIndex + "\\]").val() == "REMOVE") {
			$("#allowedAnswerOperation\\[" + allowedAnswerEditItemIndex + "\\]").val("UPDATE");
			$("#answerSet\\[" + allowedAnswerEditItemIndex + "\\]").removeClass("removeSet");
		} else {
			$("#answerSet\\[" + allowedAnswerEditItemIndex + "\\]").remove();
		}
	});
};

function singleShowHideAnswerCreate(){
	switch($('#questionCategory').find(":selected").val()){
		case 'TRUE_FALSE':
		case 'ESSAY':
		case 'DATE':
		case 'PHONE_NUMBER':
		case 'CURRENCY':
		case 'WHOLE_NUMBER':
		case 'DECIMAL_NUMBER':
		case 'SHORT_ANSWER':
			$("#answerCreate").addClass('hidden');
			break;
		case 'MULTIPLE_CHOICE':
		case 'MULTIPLE_CHOICE_ESSAY':
			$("#answerCreate").removeClass('hidden');
			$("#answerCardinality").removeClass('hidden');
			break;
		case 'SELECT_FROM_LIST':
			$("#answerCreate").removeClass('hidden');
			$("#answerCardinality").addClass('hidden');
			break;
		default:
			$("#answerCreate").addClass('hidden');
			break;
	}
};

function singleApplyShowHideQuestionCreate(){
	$('input[type=radio][name=existingQuestion]').change(function(){
		singleShowHideQuestionCreate();
		singleClearQuestionFields();
	});
};

function singleApplyShowHideNewAnswerCreate(aaIndex){
	$('input[type=radio][name=allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]').change(function(){
		singleShowHideNewAnswerCreate(aaIndex);
		singleClearAnswerFields(aaIndex);
	});
};

function singleApplyCreateAnswerOnClick(){
	$('#answerAddItemLink').click(function(){
		singleAllowedAnswerItemsCreateOnClick();
	});
}

function singleClearQuestionFields(){
	switch($('input[type=radio][name=existingQuestion]:checked').val()){
		default:
		case 'true':
			$('#questionText').val('') ;
			$('#valid').prop('checked', false);
			$("#questionCategory > option[value='']").attr("selected", true);
			singleShowHideAnswerCreate();
		break;
		case 'false':
			$('#questionQuery').val('');
			$('#question').val('');
		break;
	}
};

function singleClearAnswerFields(aaIndex){
	switch($('input[type=radio][name=allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]:checked').val()){
		default:
		case 'true':
			$('#allowedAnswerItems\\[' + aaIndex + '\\]\\.description').val('') ;
		break;
		case 'false':
			$('#allowedAnswerItems\\[' + aaIndex + '\\]\\.answerQuery').val('') ;
			$('#allowedAnswerItems\\[' + aaIndex + '\\]\\.answerValue').val('') ;
		break;
	}
};

function singleShowHideQuestionCreate(){
	switch($('input[type=radio][name=existingQuestion]:checked').val()){
		case 'true':
			$("#questionCreate").addClass('hidden');
			$('#questionQuery').removeAttr('disabled');
			break;
		case 'false':
			$("#questionCreate").removeClass('hidden');
			$('#questionQuery').attr('disabled', true);
			break;
		default:
			$("#questionCreate").addClass('hidden');
			$('#questionQuery').removeAttr('disabled');
			break;
	}
};

function singleShowHideNewAnswerCreate(aaIndex){
	switch($('input[type=radio][name=allowedAnswerItems\\[' + aaIndex + '\\]\\.existingAnswer]:checked').val()){
		case 'true':
			$("#createNewAnswer\\[" + aaIndex + "\\]").addClass('hidden');
			$("#allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").removeAttr('disabled');
			break;
		case 'false':
			$("#createNewAnswer\\[" + aaIndex + "\\]").removeClass('hidden');
			$("#allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").attr('disabled', true);
			break;
		default:
			$("#createNewAnswer\\[" + aaIndex + "\\]").addClass('hidden');
			$("#allowedAnswerItems\\[" + aaIndex + "\\]\\.answerQuery").removeAttr('disabled');
			break;
	}
};

function singleApplyAnswerSetFunctions(){
	$(".answerSet").each(function(i){
		singleShowHideNewAnswerCreate(i);
		singleApplyAnswerValueSearch(i);
		singleAllowedAnswerItemMenuOnClick(i);
		singleApplyShowHideNewAnswerCreate(i);
	});
	
};

function singleApplyQuestionSetFunctions(){
	singleShowHideAnswerCreate();
	singleShowHideQuestionCreate();
	singleApplyAnswerSetFunctions();
	singleApplyQuestionSearch();
	singleApplyShowHideQuestionCreate();
	singleApplyCreateAnswerOnClick();
}

function singleApplyQuestionSearch(){
	var queryInput = document.getElementById("questionQuery");
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
			document.getElementById("question").value = ui.item.id;
			$("#questionCategory > option[value=" + ui.item.category + "]").attr("selected", true);
			singleShowHideAnswerCreate();
			return false;
		},
		focus: function(event, ui) {
			queryInput.value = ui.item.label;
			return false;
		}
	});
	
	document.getElementById("clearQuestionLink").onclick = function() {
		document.getElementById("question").value = "";
		document.getElementById("questionQuery").value = "";
		return false;
	};
};

function singleApplyAnswerValueSearch(aaIndex){
	applyValueLabelAutoComplete(
			document.getElementById("allowedAnswerItems[" + aaIndex + "].answerQuery"),  
			document.getElementById("allowedAnswerItems[" + aaIndex + "].answerValue"), 
			config.ServerConfig.getContextPath() + "/questionnaire/answerValueSearch.json");
	document.getElementById("clearAnswerLink[" + aaIndex + "]").onclick = function() {
		document.getElementById("allowedAnswerItems[" + aaIndex + "].answerValue").value = "";
		document.getElementById("allowedAnswerItems[" + aaIndex + "].answerQuery").value = "";
		return false;
	};
};