package omis.web.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.web.domain.FormFieldTip;

/**
 * Data access object for form field tip.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public interface FormFieldTipDao
		extends GenericDao<FormFieldTip> {

	/**
	 * Returns form field tips by form.
	 * 
	 * @param form form
	 * @return form field tips by form
	 */
	List<FormFieldTip> findByForm(String form);
	
	/**
	 * Returns form field tip.
	 * 
	 * @param form form
	 * @param fieldName field name
	 * @return form field tip
	 */
	FormFieldTip find(String form, String fieldName);
}