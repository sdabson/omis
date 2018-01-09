package omis.offender.beans.factory.impl;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.dao.OffenderDao;
import omis.offender.domain.Offender;

/**
 * Implementation of factory that produces property editors for offenders.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 23, 2013)
 * @since OMIS 3.0
 */
public class OffenderPropertyEditorFactoryImpl
		implements OffenderPropertyEditorFactory {

	private final  OffenderDao offenderDao;
	
	/**
	 * Instantiates an implementation of property editor factory for offenders
	 * with the specified resources.
	 * 
	 * @param offenderDao data access object for offenders
	 */
	public OffenderPropertyEditorFactoryImpl(
			final  OffenderDao offenderDao) {
		this.offenderDao = offenderDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public PropertyEditor createOffenderPropertyEditor() {
		return new PropertyEditorSupport() {
			
			/** {@inheritDoc} */
			@Override
			public void setAsText(final String text) {
				if (text != null && text.length() > 0) {
					Offender offender = OffenderPropertyEditorFactoryImpl
						.this.offenderDao.findById(Long.valueOf(text), false);
					setValue(offender);
				} else {
					setValue(null);
				}
			}
			
			/** {@inheritDoc} */
			@Override
			public String getAsText() {
				Offender offender = (Offender) this.getValue();
				if (offender != null) {
					return String.valueOf(offender.getId());
				} else {
					return null;
				}
			}
		};
	}
}