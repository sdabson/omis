package omis.placement.web.controller.delegate;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Controller delegate for placement screens.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class PlacementControllerDelegate {

	/* Redirects. */
	
	private static final String REDIRECT
		= "redirect:/placement/home.html?offender=%d";
	
	/* Helper methods. */
	
	/**
	 * Builds redirect view name with dates.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return 
	 */
	public String buildRedirectViewNameWithDate(
			final Offender offender, final Date effectiveDate) {
		
		// TODO: Implement SA
		// Use something like this, time part might be wrong:
		//   redirect:/placement/home.html?offender=%1$d
		//     &effectiveDate=%2$tm/%2$td/%2$tY&effectiveTime=$3I:$3M+$3p
		throw new IllegalArgumentException("Not yet implemented");
	}
	
	/**
	 * Builds redirect view name.
	 * 
	 * @param offender offender
	 * @return redirect view name
	 */
	public String buildRedirectViewName(final Offender offender) {
		return String.format(REDIRECT, offender.getId());
	}
}