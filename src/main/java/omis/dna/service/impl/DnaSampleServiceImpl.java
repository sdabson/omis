package omis.dna.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.dna.dao.DnaSampleDao;
import omis.dna.domain.DnaSample;
import omis.dna.service.DnaSampleService;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Implementation of service for dna sample related operations.
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.2 (February 20, 2015)
 * @since OMIS 3.0
 */

public class DnaSampleServiceImpl implements DnaSampleService {
	
	/* Data access objects. */
	
	private DnaSampleDao dnaSampleDao;
	
	/* Instance factories. */
	
	private InstanceFactory<DnaSample> dnaSampleInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever; 
	
	/**
	 * Instantiates a DNA sample service implementation with the specified
	 * data access objects, instance factories, and component retriever(s).
	 * 
	 * @param dnaSampleDao DNA sample data access object 
	 * @param dnaSampleInstanceFactory DNA sample instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public DnaSampleServiceImpl(final DnaSampleDao dnaSampleDao,
			final InstanceFactory<DnaSample> dnaSampleInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.dnaSampleDao = dnaSampleDao;
		this.dnaSampleInstanceFactory = dnaSampleInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public List<DnaSample> findByOffender(final Offender offender) {
		return dnaSampleDao.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final DnaSample offenderDnaSample) {
		dnaSampleDao.makeTransient(offenderDnaSample);
	}


	/** {@inheritDoc} */
	@Override
	public DnaSample create(final Offender offender, 
			final String collectionEmployee, final String comments, 
			final String location, final String witness, final Date date, 
			final Date time)
			throws DuplicateEntityFoundException {
		if (this.dnaSampleDao.find(offender, collectionEmployee, date, location) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate DNA sample found");
		}
		DnaSample sample = this.dnaSampleInstanceFactory.createInstance();
		sample.setOffender(offender);
		sample.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.populateDnaSample(sample, collectionEmployee, comments, location, 
				witness, date, time);
		return this.dnaSampleDao.makePersistent(sample);
	}

	/** {@inheritDoc} */
	@Override
	public DnaSample update(final DnaSample dnaSample,
			final String collectionEmployee, final String comments, 
			final String location, final String witness, final Date date, 
			final Date time)
			throws DuplicateEntityFoundException {
		if (this.dnaSampleDao.findExcluding(dnaSample, dnaSample.getOffender(),
				collectionEmployee, date, location) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate DNA sample found");
		}
		this.populateDnaSample(dnaSample, collectionEmployee, comments, 
				location, witness, date, time);
		return this.dnaSampleDao.makePersistent(dnaSample);
	}

	/* Helper methods. */

	/*
	 * Populates the specified DNA sample.
	 * 
	 * @param sample DNA sample
	 * @param collectionEmployee collection employee person
	 * @param comments comments
	 * @param location location
	 * @param witness witness person
	 * @param date date
	 * @param time time
	 * @return populated DNA sample
	 */
	private DnaSample populateDnaSample(final DnaSample sample, 
			final String collectionEmployee, final String comments, 
			final String location, final String witness, final Date date,
			final Date time) {
		sample.setCollectionEmployee(collectionEmployee);
		sample.setComment(comments);
		sample.setDate(date);
		sample.setTime(time);
		sample.setLocation(location);
		sample.setWitness(witness);
		sample.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return sample;
	}

}