package omis.substanceuse.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DateRangeException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substanceuse.dao.SubstanceUseDao;
import omis.substanceuse.dao.UseAffirmationDao;
import omis.substanceuse.dao.UseFrequencyDao;
import omis.substanceuse.dao.UseMeasurementDao;
import omis.substanceuse.dao.UseTermDao;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAffirmation;
import omis.substanceuse.domain.UseAffirmationSource;
import omis.substanceuse.domain.UseAllotment;
import omis.substanceuse.domain.UseFrequency;
import omis.substanceuse.domain.UseMeasurement;
import omis.substanceuse.domain.UseTerm;
import omis.substanceuse.domain.UseTermSource;
import omis.substanceuse.service.SubstanceUseService;

/**
 * Substance use service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseServiceImpl
	implements SubstanceUseService {

	/* Data access objects. */
	private SubstanceUseDao substanceUseDao;
	private UseAffirmationDao useAffirmationDao;
	private UseFrequencyDao useFrequencyDao;
	private UseMeasurementDao useMeasurementDao;
	private UseTermDao useTermDao;
	
	/* Instance factories. */
	final InstanceFactory<SubstanceUse> substanceUseInstanceFactory;
	final InstanceFactory<UseTerm> useTermInstanceFactory;
	final InstanceFactory<UseAffirmation> useAffirmationInstanceFactory;
	
	/* Component retrievers. */
	final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a substance use service implementation.
	 * 
	 * @param substanceUseDao substance use data access objects
	 * @param useAffirmationDao use affirmation data access objects
	 * @param useFrequencyDao use frequency data access objects
	 * @param useMeasurementDao use measurement data access object
	 * @param useTermDao use term data access object
	 * @param substanceUseInstanceFactory substance use instance factory
	 * @param useTermInstanceFactory use term instance factory
	 * @param useAffirmationFactory use affirmation instance factory
	 * @param useTermUseInstanceFactory use term 
	 * @param auditComponentRetriever
	 */
	public SubstanceUseServiceImpl(final SubstanceUseDao substanceUseDao,
			final UseAffirmationDao useAffirmationDao,
			final UseFrequencyDao useFrequencyDao,
			final UseMeasurementDao useMeasurementDao,
			final UseTermDao useTermDao,
			final InstanceFactory<SubstanceUse> substanceUseInstanceFactory,
			final InstanceFactory<UseTerm> useTermInstanceFactory,
			final InstanceFactory<UseAffirmation> useAffirmationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.substanceUseDao = substanceUseDao;
		this.useAffirmationDao = useAffirmationDao;
		this.useFrequencyDao = useFrequencyDao;
		this.useMeasurementDao = useMeasurementDao;
		this.useTermDao = useTermDao;
		this.substanceUseInstanceFactory = substanceUseInstanceFactory;
		this.useTermInstanceFactory = useTermInstanceFactory;
		this.useAffirmationInstanceFactory = useAffirmationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public SubstanceUse create(final Offender offender, 
			final Substance substance) 
			throws DuplicateEntityFoundException {
		if (this.substanceUseDao.find(offender, substance) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance use found");
		}
		SubstanceUse use = this.substanceUseInstanceFactory.createInstance();
		use.setOffender(offender);
		use.setSubstance(substance);
		use.setCreationSignature(this.newCreationSignature());
		use.setUpdateSignature(this.newUpdateSignature());
		return this.substanceUseDao.makePersistent(use);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SubstanceUse use) {
		this.substanceUseDao.makeTransient(use);
	}

	/** {@inheritDoc} */
	@Override
	public UseTerm createUseTerm(final DateRange dateRange, 
			final UseFrequency frequency, final UseAllotment allotment,
			final UseTermSource source, final SubstanceUse use)
		throws DuplicateEntityFoundException, DateRangeException {
		if (this.useTermDao.find(DateRange.getStartDate(dateRange), use, source) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate use term found");
		}
		if (this.useTermDao.findByUseInDateRange(use, dateRange.getStartDate(),
				dateRange.getEndDate()) != null) {
			throw new DateRangeException("Use term found within date range");
		}
		UseTerm term = this.useTermInstanceFactory.createInstance();
		this.populateUseTerm(term, dateRange, frequency, allotment, source);
		term.setUse(use);
		term.setCreationSignature(this.newCreationSignature());
		return this.useTermDao.makePersistent(term);
	}

	/** {@inheritDoc} */
	@Override
	public UseTerm updateUseTerm(final UseTerm term, final DateRange dateRange,
			final UseAllotment allotment, final UseFrequency frequency,
			final UseTermSource source)
		throws DuplicateEntityFoundException, DateRangeException {
		SubstanceUse use = term.getUse();
		if (this.useTermDao.findExcluding(term, dateRange.getStartDate(),
				use, source) != null) {
			throw new DuplicateEntityFoundException("Duplicate use term found");
		}
		if (this.useTermDao.findByUseInDateRange(use, dateRange.getStartDate(),
				dateRange.getEndDate()) != null) {
			throw new DateRangeException("Use term found within date range");
		}
		this.populateUseTerm(term, dateRange, frequency, allotment, source);
		return this.useTermDao.makePersistent(term);
	}

	/** {@inheritDoc} */
	@Override
	public void removeUseTerm(final UseTerm term) {
		this.useTermDao.makeTransient(term);
	}

	/** {@inheritDoc} */
	@Override
	public UseAffirmation createUseAffirmation(
			final Date date, final UseAllotment allotment,
			final UseAffirmationSource source,
			final SubstanceUse use)
			throws DuplicateEntityFoundException {
		if (this.useAffirmationDao.find(date, use, source) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate use affirmation found");
		}
		UseAffirmation affirmation 
			= this.useAffirmationInstanceFactory.createInstance();
		affirmation.setDate(date);
		affirmation.setAllotment(allotment);
		affirmation.setUse(use);
		return this.useAffirmationDao.makePersistent(affirmation);
	}

	/** {@inheritDoc} */
	@Override
	public UseAffirmation updateUseAffirmation(
			final UseAffirmation affirmation, 
			final Date date, final UseAllotment allotment,
			final UseAffirmationSource source)
			throws DuplicateEntityFoundException {
		if (this.useAffirmationDao.findExcluding(affirmation, date, 
				affirmation.getUse(), source) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate use affirmation found");
		}
		this.populateUseAffirmation(affirmation, date, allotment, source);
		return this.useAffirmationDao.makePersistent(affirmation);
	}

	/** {@inheritDoc} */
	@Override
	public void removeUseAffirmation(final UseAffirmation affirmation) {
		this.useAffirmationDao.makeTransient(affirmation);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseAffirmation> findUseAffirmationsBySubstanceUse(
			final SubstanceUse substanceUse) {
		return this.useAffirmationDao.findBySubstanceUse(substanceUse);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseTerm> findUseTermBySubstanceUse(
			final SubstanceUse substanceUse) {
		return this.useTermDao.findUseTermsBySubstanceUse(substanceUse);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceUse> findSubstanceUsesByOffender(
			final Offender offender) {
		return this.substanceUseDao.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseMeasurement> findUseMeasurements() {
		return this.useMeasurementDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<UseFrequency> findUseFrequencies() {
		return this.useFrequencyDao.findAll();
	}
	
	/* Helper methods. */

	/*
	 * Returns a new creation signature.
	 * 
	 * @return new creation signature
	 */
	private CreationSignature newCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	/*
	 * Returns a new update signature.
	 *  
	 * @return new update signature
	 */
	private UpdateSignature newUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	/*
	 * Populates the specified use term.
	 * 
	 * @param term use term
	 * @param dateRange date range
	 * @param frequency use frequency
	 * @param allotment use allotment
	 * @param source use term source
	 * @return populated use term
	 */
	private UseTerm populateUseTerm(final UseTerm term,
			final DateRange dateRange, final UseFrequency frequency,
			final UseAllotment allotment, final UseTermSource source) {
		term.setDateRange(dateRange);
		term.setFrequency(frequency);
		term.setAllotment(allotment);
		term.setSource(source);
		term.setUpdateSignature(this.newUpdateSignature());
		return term;
	}
	
	/*
	 * Populates the specified use affirmation.
	 * 
	 * @param affirmaiton use affirmation
	 * @param date date
	 * @param allotment use allotment
	 * @param source use affirmation source
	 * @return populated use affirmation source
	 */
	private UseAffirmation populateUseAffirmation(
			final UseAffirmation affirmation, final Date date,
			final UseAllotment allotment, final UseAffirmationSource source) {
		affirmation.setDate(date);
		affirmation.setAllotment(allotment);
		affirmation.setSource(source);
		affirmation.setUpdateSignature(this.newUpdateSignature());
		return affirmation;
	}
}