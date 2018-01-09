/**
 * Sentence form behavior.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 20, 2013)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	$(".date").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#calculateLink").click(function() {
		var commencementDate = $("#commencementDate");
		if (commencementDate.val() == null) {
			var resolver = new common.MessageResolver("omis.sentence.msgs.sentence");
			alert(resolver.getMessage("mustSpecifyCommencementDateMessage"));
			commencementDate.focus();
		} else if (commencementDate.val().trim().length != 10) {
			var resolver = new common.MessageResolver("omis.sentence.msgs.sentence");
			alert(resolver.getMessage("invalidCommencementDateMessage"));
			commencementDate.focus();
		} else {
			// Load contents from href attribute of #calculateLink
			var url = $(this).attr("href") + "&commencementDate=" + commencementDate.val() + "&concurrent=" + $("#concurrent").val();
			$.ajax(
					url,
					{
						async: true,
						beforeSend: function() {
							$("#calculation").html("<img src='"
									+ config.ServerConfig.getContextPath()
									+ "/resources/common/images/ajaxLoader.gif'/>");
					},
					success: function(data) {
						$("#calculation").html(data);
						$("#prisonDischargeDate").datepicker({
							changeMonth: true,
							changeYear: true
						});
						$("#probationDischargeDate").datepicker({
							changeMonth: true,
							changeYear: true
						});
					},
					error: function(jqXHR, textStatus, errorThrown) {
						$("#calculation").html("Error: " + textStatus + "; " + errorThrown);
					},
					type: "GET"
					}
			);
		}
		return false;
	});
});