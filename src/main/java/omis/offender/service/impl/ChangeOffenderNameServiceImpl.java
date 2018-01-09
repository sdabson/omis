package omis.offender.service.impl;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.ChangeOffenderNameService;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.PersonName;
import omis.person.domain.Suffix;
import omis.person.service.delegate.AlternativeNameAssociationDelegate;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.person.service.delegate.PersonNameDelegate;
import omis.person.service.delegate.SuffixDelegate;

/**
 * ChangeOffenderNameServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 8, 2017)
 *@since OMIS 3.0
 *
 */
public class ChangeOffenderNameServiceImpl implements ChangeOffenderNameService {
	
	private final AlternativeNameAssociationDelegate
			alternativeNameAssociationDelegate;
	
	private final PersonNameDelegate personNameDelegate;
	
	private final OffenderDelegate offenderDelegate;
	
	private final AlternativeNameCategoryDelegate
			alternativeNameCategoryDelegate;
	
	private final SuffixDelegate suffixDelegate;
	
	

	/**
	 * @param alternativeNameAssociationDelegate
	 * @param personNameDelegate
	 * @param offenderDelegate
	 * @param alternativeNameCategoryDelegate
	 * @param suffixDelegate
	 */
	public ChangeOffenderNameServiceImpl(
			final AlternativeNameAssociationDelegate
					alternativeNameAssociationDelegate,
			final PersonNameDelegate personNameDelegate,
			final OffenderDelegate offenderDelegate,
			final AlternativeNameCategoryDelegate alternativeNameCategoryDelegate,
			final SuffixDelegate suffixDelegate) {
		this.alternativeNameAssociationDelegate =
				alternativeNameAssociationDelegate;
		this.personNameDelegate = personNameDelegate;
		this.offenderDelegate = offenderDelegate;
		this.alternativeNameCategoryDelegate = alternativeNameCategoryDelegate;
		this.suffixDelegate = suffixDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public Offender change(
			final Offender offender, final String lastName,
			final String firstName, final String middleName, final String suffix,
			final Date effectiveDate,
			final AlternativeNameCategory previousNameCategory)
					throws DuplicateEntityFoundException {
		
		PersonName previousName = offender.getName();
		
		this.alternativeNameAssociationDelegate.create(previousName,
				new DateRange(null, effectiveDate), previousNameCategory);
		
		PersonName newName = this.personNameDelegate
				.create(offender, lastName, firstName, middleName, suffix);
		
		return this.offenderDelegate.updateOffender(offender, newName,
				offender.getIdentity());
	}

	/**{@inheritDoc} */
	@Override
	public List<AlternativeNameCategory> findAlternativeNameCategories() {
		return this.alternativeNameCategoryDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<Suffix> findSuffixes() {
		return this.suffixDelegate.findAll();
	}

}
