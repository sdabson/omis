package omis.disciplinaryCode.service.impl;

import java.util.List;

import omis.datatype.DateRange;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.disciplinaryCode.service.DisciplinaryCodeService;
import omis.disciplinaryCode.service.delegate.DisciplinaryCodeDelegate;
import omis.disciplinaryCode.service.delegate.SupervisoryOrganizationCodeDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeServiceImpl implements DisciplinaryCodeService {
	
	/* Delegates */
	
	private final DisciplinaryCodeDelegate 
		disciplinaryCodeDelegate;
	
	private final SupervisoryOrganizationCodeDelegate 
		supervisoryOrganizationCodeDelegate;
	
	
	/**
	 * Constructor
	 * @param disciplinaryCodeDelegate - disciplinary code delegate
	 * @param supervisoryOrganizationCodeDelegate - supervisory organization code delegate
	 */
	public DisciplinaryCodeServiceImpl(
			final DisciplinaryCodeDelegate disciplinaryCodeDelegate,
			final SupervisoryOrganizationCodeDelegate 
				supervisoryOrganizationCodeDelegate) {
		this.disciplinaryCodeDelegate = disciplinaryCodeDelegate;
		this.supervisoryOrganizationCodeDelegate 
			= supervisoryOrganizationCodeDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCode createDisciplinaryCode(final String value, 
			final String description, final String extendedDescription)
					throws DuplicateEntityFoundException {
		return this.disciplinaryCodeDelegate.create(
				value, description, extendedDescription);
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCode updateDisciplinaryCode(
			final DisciplinaryCode disciplinaryCode, final String value, 
			final String description, final String extendedDescription)
					throws DuplicateEntityFoundException {
		return this.disciplinaryCodeDelegate.update(
				disciplinaryCode, value, description, extendedDescription);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDisciplinaryCode(final DisciplinaryCode disciplinaryCode) {
		this.disciplinaryCodeDelegate.remove(disciplinaryCode);
	}

	/**{@inheritDoc} */
	@Override
	public SupervisoryOrganizationCode createSupervisoryOrganizationCode(
			final SupervisoryOrganization supervisoryOrganization,
			final DateRange dateRange, final DisciplinaryCode code) 
					throws DuplicateEntityFoundException {
		return this.supervisoryOrganizationCodeDelegate.create(
				supervisoryOrganization, dateRange, code);
	}

	/**{@inheritDoc} */
	@Override
	public SupervisoryOrganizationCode updateSupervisoryOrganizationCode(
			final SupervisoryOrganizationCode supervisoryOrganizationCode, 
			final DateRange dateRange, final DisciplinaryCode code) 
					throws DuplicateEntityFoundException {
		return this.supervisoryOrganizationCodeDelegate.update(
				supervisoryOrganizationCode, dateRange, code);
	}

	/**{@inheritDoc} */
	@Override
	public void removeSupervisoryOrganizationCode(
			final SupervisoryOrganizationCode supervisoryOrganizationCode) {
		this.supervisoryOrganizationCodeDelegate.remove(supervisoryOrganizationCode);
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCode> findDisciplinaryCodeByValue(String value) {
		return this.disciplinaryCodeDelegate.findQuery(value);
	}

}
