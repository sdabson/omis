package omis.visitation.web.delegate;

import org.springframework.ui.ModelMap;

import omis.visitation.domain.VisitationAssociationCategory;

/**
 * Visitation association fields controller delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 1, 2016)
 * @since OMIS 3.0
 */
public class VisitationAssociationControllerDelegate {

	/** Constructor. */
	
	/**
	 * Instantiates an instance of visitation association fields controller delegate.
	 */
	public VisitationAssociationControllerDelegate() {
		//Default constructor.
	}

	/* Delegate methods. */
	
	public ModelMap prepareEditVisitationAssociationFields(final ModelMap map,
			VisitationAssociationCategory category) {
		map.addAttribute("category", category);
		return map;
	}	
}