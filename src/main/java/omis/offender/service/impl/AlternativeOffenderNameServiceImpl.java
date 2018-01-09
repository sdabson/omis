package omis.offender.service.impl;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.AlternativeOffenderNameService;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.PersonName;
import omis.person.domain.Suffix;
import omis.person.service.delegate.AlternativeNameAssociationDelegate;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.person.service.delegate.PersonNameDelegate;
import omis.person.service.delegate.SuffixDelegate;

/**
 * Implementation of service for alternative offender names.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 24, 2014)
 * @since OMIS 3.0
 */
public class AlternativeOffenderNameServiceImpl
		implements AlternativeOffenderNameService {
	
	/* Data access objects. */
	
	private final AlternativeNameAssociationDelegate
		alternativeNameAssociationDelegate;
	
	private final PersonNameDelegate personNameDelegate;
	
	private final AlternativeNameCategoryDelegate
		alternativeNameCategoryDelegate;
	
	private final SuffixDelegate suffixDelegate;

	/**
	 * Instantiates implementation of service fore alternative offender names.
	 * 
	 * @param alternativeNameAssociationDelegate Delegate for
	 * alternative person name associations
	 * @param personNameDelegate Delegate for person names
	 * @param alternativeNameCategoryDelegate Delegate for alternative
	 * name categories
	 * @param suffixDelegate Delegate for suffixes
	 * @param alternativeNameAssociationInstanceFactory alternative name
	 * association instance factory
	 * @param personNameInstanceFactory person name instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public AlternativeOffenderNameServiceImpl(
			final AlternativeNameAssociationDelegate
				alternativeNameAssociationDelegate,
			final PersonNameDelegate personNameDelegate,
			final AlternativeNameCategoryDelegate 
				alternativeNameCategoryDelegate,
			final SuffixDelegate suffixDelegate) {
		this.alternativeNameAssociationDelegate 
			= alternativeNameAssociationDelegate;
		this.personNameDelegate = personNameDelegate;
		this.alternativeNameCategoryDelegate = alternativeNameCategoryDelegate;
		this.suffixDelegate = suffixDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public AlternativeNameAssociation associate(final Offender offender,
			final String lastName, final String firstName, 
			final String middleName, final String suffix, 
			final DateRange dateRange, final AlternativeNameCategory category)
			throws DuplicateEntityFoundException {
		PersonName name = this.personNameDelegate
				.create(offender, lastName, firstName, middleName, suffix);
		AlternativeNameAssociation association = 
				this.alternativeNameAssociationDelegate
					.create(name, dateRange, category);
		
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public AlternativeNameAssociation updateAssociation(
			AlternativeNameAssociation association, String lastName,
			String firstName, String middleName, String suffix,
			DateRange dateRange, AlternativeNameCategory category)
			throws DuplicateEntityFoundException {
		PersonName name = this.personNameDelegate
			.update(association.getName(), lastName, firstName, middleName, 
					suffix);
		
		return this.alternativeNameAssociationDelegate
				.update(association, name, dateRange, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(
			final AlternativeNameAssociation alternativeNameAssociation) {
		PersonName personName = alternativeNameAssociation.getName();
		this.alternativeNameAssociationDelegate.remove(
				alternativeNameAssociation);
		this.personNameDelegate.remove(personName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PersonName> findAlternativeNames(
			final Offender offender) {
		return this.personNameDelegate.findAlternativeNames(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<AlternativeNameCategory> findCategories() {
		return this.alternativeNameCategoryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Suffix> findSuffixes() {
		return this.suffixDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<AlternativeNameAssociation> findAssociations(
			final Offender offender) {
		return this.alternativeNameAssociationDelegate.findByPerson(offender);
	}
}