package omis.contact.web.controller.delegate;

import org.springframework.ui.ModelMap;

import omis.contact.domain.TelephoneNumberCategory;

/**
 * Telephone number fields controller delegate.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 22, 2016)
 * @since OMIS 3.0
 */
public class TelephoneNumberFieldsControllerDelegate {
	
	private String TELEPHONE_NUMBER_CATEGORIES_MODEL_KEY 
		= "telephoneNumberCategories";

	/**
	 * Instantiates a default instance of telephone number item fields 
	 * controller delegate.
	 */
	public TelephoneNumberFieldsControllerDelegate() {
		// Default constructor.
	}
	
	/* Delegate methods. */	
	
	/**
	 * Prepare to edit telephone number fields.
	 * 
	 * @param map map	 
	 * @return map
	 */
	
	public ModelMap prepareEditTelephoneNumberFields(
			final ModelMap map) {		
		map.addAttribute(TELEPHONE_NUMBER_CATEGORIES_MODEL_KEY, 
				TelephoneNumberCategory.values());
		return map;		
	}
}