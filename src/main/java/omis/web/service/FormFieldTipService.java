package omis.web.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.web.domain.FormFieldTip;

/**
 * Service for form field tips.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 18, 2015)
 * @since OMIS 3.0
 */
public interface FormFieldTipService {

	/**
	 * Creates form field tip.
	 * 
	 * @param form form
	 * @param fieldName field name
	 * @param value value
	 * @return created form field tip
	 * @throws DuplicateEntityFoundException if form field tip exists
	 */
	FormFieldTip create(String form, String fieldName, String value)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates form field tip.
	 * 
	 * @param tip tip to update
	 * @param value value
	 * @return updated form field tip
	 */
	FormFieldTip update(FormFieldTip tip, String value);
	
	/**
	 * Removes form field tip.
	 * 
	 * @param tip tip to remove
	 */
	void remove(FormFieldTip tip);
	
	/**
	 * Returns tips for form.
	 * 
	 * @param form form
	 * @return tips for form
	 */
	List<FormFieldTip> findByForm(String form);
}