package omis.web.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.web.dao.FormFieldTipDao;
import omis.web.domain.FormFieldTip;

/**
 * Delegate for form field tips.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public class FormFieldTipDelegate {

	/* DAOs. */
	
	private final FormFieldTipDao formFieldTipDao;
	
	/* Instance Factories. */
	
	private final InstanceFactory<FormFieldTip> formFieldTipInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Delegate for form field tips.
	 * 
	 * @param formFieldTipDao data access object for form field tip
	 * @param formFieldTipInstanceFactory data access object for form field tip
	 */
	public FormFieldTipDelegate(
			final FormFieldTipDao formFieldTipDao,
			final InstanceFactory<FormFieldTip> formFieldTipInstanceFactory) {
		this.formFieldTipDao = formFieldTipDao;
		this.formFieldTipInstanceFactory = formFieldTipInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Creates form field tip.
	 * 
	 * @param form form
	 * @param fieldName field name
	 * @param value value
	 * @return created form field tip
	 * @throws DuplicateEntityFoundException if tip exists
	 */
	public FormFieldTip create(final String form, final String fieldName,
			final String value)
					throws DuplicateEntityFoundException {
		if (this.formFieldTipDao.find(form, fieldName) != null) {
			throw new DuplicateEntityFoundException("Tip exists");
		}
		FormFieldTip tip = this.formFieldTipInstanceFactory.createInstance();
		tip.setForm(form);
		tip.setFieldName(fieldName);
		tip.setValue(value);
		return this.formFieldTipDao.makePersistent(tip);
	}
	
	/**
	 * Updates form field tip.
	 * 
	 * @param tip tip to update
	 * @param value value
	 * @return updated form field tip
	 */
	public FormFieldTip update(final FormFieldTip tip, final String value) {
		tip.setValue(value);
		return this.formFieldTipDao.makePersistent(tip);
	}
	
	/**
	 * Removes form field tip.
	 * 
	 * @param tip form field tip
	 */
	public void remove(final FormFieldTip tip) {
		this.formFieldTipDao.makeTransient(tip);
	}
	
	/**
	 * Returns tips for form.
	 * 
	 * @param form form
	 * @return tips for form
	 */
	public List<FormFieldTip> findByForm(final String form) {
		return this.formFieldTipDao.findByForm(form);
	}
}