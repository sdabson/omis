package omis.citation.service.impl;

import java.util.List;

import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.citation.service.MisdemeanorCitationService;
import omis.citation.service.delegate.MisdemeanorCitationDelegate;
import omis.citation.service.delegate.MisdemeanorOffenseDelegate;
import omis.datatype.Month;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;

/**
 * Implementation of service for misdemeanor citations.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationServiceImpl
		implements MisdemeanorCitationService {

	private final MisdemeanorCitationDelegate misdemeanorCitationDelegate;
	
	private final CityDelegate cityDelegate;
	
	private final StateDelegate stateDelegate;
	
	private final MisdemeanorOffenseDelegate misdemeanorOffenseDelegate;
	
	
	/**
	 * Instantiates an implementation of service for misdemeanor citations with
	 * the specified data access object.
	 * 
	 * @param misdemeanorCitationDao data access object for misdemeanor citations.
	 * @param misdemeanorCitationInstanceFactory instance factory for misdemeanor
	 * citations
	 * @param misdemeanorOffenseDao data access object for misdemeanor offenses
	 * @param auditComponentRetriever retriever for audit components
	 */
	public MisdemeanorCitationServiceImpl(
			final MisdemeanorCitationDelegate misdemeanorCitationDelegate,
			final CityDelegate cityDelegate, final StateDelegate stateDelegate,
			final MisdemeanorOffenseDelegate misdemeanorOffenseDelegate) {
		this.misdemeanorCitationDelegate = misdemeanorCitationDelegate;
		this.cityDelegate = cityDelegate;
		this.stateDelegate = stateDelegate;
		this.misdemeanorOffenseDelegate = misdemeanorOffenseDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public MisdemeanorCitation save(final Offender offender, final State state, 
			final City city, final Integer day, final Month month, 
			final Integer year, final Integer counts, 
			final MisdemeanorOffense offense,
			final MisdemeanorDisposition disposition) 
					throws DuplicateEntityFoundException {
		return this.misdemeanorCitationDelegate.save(offender, state, city, 
				day, month, year, counts, offense, disposition);
	}

	/** {@inheritDoc} */
	@Override
	public MisdemeanorCitation update(
			final MisdemeanorCitation citation, 
			final State state, final City city, final Integer day,
			final Month month, final Integer year, 
			final Integer counts,
			final MisdemeanorOffense offense, 
			final MisdemeanorDisposition disposition) 
				throws DuplicateEntityFoundException {
		return this.misdemeanorCitationDelegate.update(citation, 
				state, city, day, month, year, counts, offense, disposition);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final MisdemeanorCitation citation) {
		this.misdemeanorCitationDelegate.remove(citation);
	}

	/** {@inheritDoc} */
	@Override
	public List<MisdemeanorCitation> findByOffender(Offender offender) {
		return this.misdemeanorCitationDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<MisdemeanorOffense> findOffenses() {
		return this.misdemeanorOffenseDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findStates() {
		return this.stateDelegate.findByCountry(
				this.stateDelegate.findHomeState().getCountry());
	}
	
	/** {@inheritDoc} */
	@Override
	public State findHomeState() {
		return this.stateDelegate.findHomeState();
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDelegate.findByState(state);
	}

	/** {@inheritDoc} */
	@Override
	public MisdemeanorOffense createOffense(String name, Boolean valid) 
			throws DuplicateEntityFoundException {
		return this.misdemeanorOffenseDelegate.create(name, valid);
	}

}
