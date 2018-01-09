package omis.citation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.citation.dao.MisdemeanorCitationDao;
import omis.citation.dao.MisdemeanorOffenseDao;
import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.datatype.Month;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.region.dao.CityDao;
import omis.region.dao.StateDao;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<MisdemeanorCitation>
	misdemeanorCitationInstanceFactory;
	
	/* DAOs. */
	
	private final MisdemeanorCitationDao misdemeanorCitationDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for citations.
	 * 
	 * @param misdemeanorCitationInstanceFactory instance factory for
	 * misdemeanor citations.
	 * @param misdemeanorCitationDao data access object for citations.
	 */
	public MisdemeanorCitationDelegate(
			final InstanceFactory<MisdemeanorCitation>
				misdemeanorCitationInstanceFactory,
			final MisdemeanorCitationDao misdemeanorCitationDao,
			final MisdemeanorOffenseDao misdemeanorOffenseDao,
			final StateDao stateDao,
			final CityDao cityDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.misdemeanorCitationInstanceFactory
			= misdemeanorCitationInstanceFactory;
		this.misdemeanorCitationDao = misdemeanorCitationDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a misdemeanor citation.
	 * @param Offender - offender
	 * @param State - state
	 * @param City - city
	 * @param Integer - day
	 * @param Integer - month
	 * @param Integer - year
	 * @param Integer - counts
	 * @param MisdemeanorOffense - offense
	 * @param  - 
	 * @return misdemeanor citation. 
	 * @throws DuplicateEntityFoundException - when citation already exists.
	 *  */
	public MisdemeanorCitation create(final Offender offender, 
			final State state,
			final City city, 
			final Integer day,
			final Month month, 
			final Integer year, 
			final Integer counts,
			final MisdemeanorOffense offense,
			final MisdemeanorDisposition disposition)
		throws DuplicateEntityFoundException {
		if (this.misdemeanorCitationDao.find(offender, offense, state,
				day, month, year, disposition) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate misdemeanor citation found");
		}
		
		MisdemeanorCitation misdemeanorCitation = 
			this.misdemeanorCitationInstanceFactory.createInstance();
				misdemeanorCitation.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
				misdemeanorCitation.setOffender(offender);
			this.populateMisdemeanorCitation(misdemeanorCitation, state, 
				city, day, month, year, counts, offense, disposition);
		return this.misdemeanorCitationDao.makePersistent(misdemeanorCitation);
	}
	
	/** Updates a misdemeanor citation.
	 * @param MisdemeanorCitation - misdemeanor citation
	 * @param State - state
	 * @param City - city
	 * @param Integer - day
	 * @param Integer - month
	 * @param Integer - year
	 * @param Integer - counts
	 * @param MisdemeanorOffense - misdemeanor offense
	 * @param Enumeration - disposition
	 * @param  - .
	 * @return Updates a misdemeanor citation.
	 * @throws DuplicateEntityFoundException - when citation already exists.
	 *  */
	public MisdemeanorCitation update(
			final MisdemeanorCitation citation,
			final State state, final City city,	final Integer day, 
			final Month month, final Integer year, final Integer counts,
			final MisdemeanorOffense offense,
			final MisdemeanorDisposition disposition) 
		throws DuplicateEntityFoundException {
			if (this.misdemeanorCitationDao.findExcluding(
					citation.getOffender(), state, offense, day, month, year, 
					disposition, citation) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate misdemeanor citation found");
		}
		this.populateMisdemeanorCitation(citation, state, city, day, month, 
				year, counts, offense, disposition);
		return this.misdemeanorCitationDao.makePersistent(citation);
	}
	
	public MisdemeanorCitation save(final Offender offender, final State state, 
			final City city, final Integer day, final Month month, 
			final Integer year, final Integer counts, 
			final MisdemeanorOffense offense, 
			final MisdemeanorDisposition disposition) 
					throws DuplicateEntityFoundException {
				if (this.misdemeanorCitationDao.find(offender, offense, state, 
						day, month, year, disposition) != null) {
					throw new DuplicateEntityFoundException(
							"Duplicate misdemeanor citation found");
	}
		MisdemeanorCitation citation = this.misdemeanorCitationInstanceFactory
				.createInstance();
		citation.setOffender(offender);
		citation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateMisdemeanorCitation(citation, state, city, day, month, 
				year, counts, offense, disposition);
		citation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.misdemeanorCitationDao.makePersistent(citation);
	}
	
	/** Removes a misdemeanor citation.
	 * @param MisdemeanorCitation - misdemeanor citation
	 * @param  - .
	 * @return Removes a misdemeanor citation.
	 * @throws DuplicateEntityFoundException - when citation already exists. 
	 * */
	public void remove(final MisdemeanorCitation misdemeanorCitation) {
		this.misdemeanorCitationDao.makeTransient(misdemeanorCitation);
	}

	public List<MisdemeanorCitation> findByOffender(Offender offender) {
		return this.misdemeanorCitationDao.findByOffender(offender);
	}
	
		/**
		 * Populates the specified misdemeanor citation.
		 * 
		 * @param MisdemeanorCitation - misdemeanor citation
		 * @param State - state
		 * @param City - city
		 * @param Integer - day
		 * @param Integer - month
		 * @param Integer - year
		 * @param Integer - counts
		 * @param MisdemeanorOffense - misdemeanor offense
		 * @param Enum - disposition
		 * @param 
		 * @return populated misdemeanor citation.
		 */
		private void populateMisdemeanorCitation(
				final MisdemeanorCitation citation,
				final State state, final City city, final Integer day,
				final Month month, final Integer year, final Integer counts,
				final MisdemeanorOffense offense,
				final MisdemeanorDisposition disposition) {
			citation.setState(state);
			citation.setCity(city);
			citation.setDay(day);
			citation.setMonth(month);
			citation.setYear(year);
			citation.setCounts(counts);
			citation.setOffense(offense);
			citation.setDisposition(disposition);
			citation.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
	}

}