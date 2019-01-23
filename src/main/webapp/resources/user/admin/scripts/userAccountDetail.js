/**
 * User account detail screen functionality.
 * @author Stephen Abson
 * @version 0.1.0 (Oct 22, 2012)
 * @since OMIS 3.0
 */
$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	if ($("#groups") != null) {
		$(".groups").change(function() {
			var groups = { 'groups': [] };
			$(".groups:checked").each(function() {
				groups['groups'].push($(this).val());
			});
			$.get(config.ServerConfig.getContextPath() 
					+ "/user/admin/userRole/findByUserGroups.html",
				groups,
				function(data) {
					$("#roleSpans").html(data);
				}
			);
		});
	}
	if ($("#passwordNeverExpires") != null) {
		$("#passwordNeverExpires").change(function() {
			$("#passwordExpirationDate").prop("disabled", $("#passwordNeverExpires").is(":checked"));
		});
	}
	if ($("#passwordExpirationDate") != null) {
		$("#passwordExpirationDate").attr("autocomplete", "off");
		$("#passwordExpirationDate").datepicker({
			changeMonth: true,
			changeYear: true
		});
	}
});