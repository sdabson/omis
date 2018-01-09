package omis.substancetest.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.substancetest.dao.LabResultDao;
import omis.substancetest.dao.SampleCollectionMethodDao;
import omis.substancetest.dao.SampleRequestDao;
import omis.substancetest.dao.SampleRequestStatusReasonDao;
import omis.substancetest.dao.SubstanceTestDao;
import omis.substancetest.dao.SubstanceTestReasonDao;
import omis.substancetest.dao.SubstanceTestResultDao;
import omis.substancetest.dao.SubstanceTestSampleDao;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SampleRequestStatusReason;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestReason;
import omis.substancetest.domain.SubstanceTestResult;
import omis.substancetest.domain.SubstanceTestSample;
import omis.substancetest.domain.component.SampleRequestResolution;
import omis.substancetest.service.SubstanceTestSampleService;

/**
 * Substance test service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (April 14, 2015)
 * @since OMIS 3.0
 */
public class SubstanceTestSampleServiceImpl 
	implements SubstanceTestSampleService {

	/* Data access objects. */
	
	private SubstanceTestSampleDao substanceTestSampleDao;
	
	private SampleCollectionMethodDao sampleCollectionMethodDao;
	
	private SubstanceTestReasonDao substanceTestReasonDao;
	
	private SubstanceTestDao substanceTestDao;
	
	private SubstanceTestResultDao substanceTestResultDao;
	
	private LabResultDao labResultDao;
	
	private SampleRequestStatusReasonDao sampleRequestStatusReasonDao;
	
	private SampleRequestDao sampleRequestDao;
	
	/* Instance factories. */
	
	private InstanceFactory<SubstanceTestSample> 
	substanceTestSampleInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a substance test sample service implementation with the
	 * specified data access object.
	 * 
	 * @param substanceTestSampleDao substance test sample data access object
	 * @param sampleCollectionMethodDao sample collection method data access
	 * object
	 * @param substanceTestReasonDao substance test reason data access object
	 * @param substanceTestDao substance test data access object
	 * @param substanceTestResultDao substance test result data access object
	 * @param labResultDao crime lab result data access object
	 * @param substanceTestSampleRequestDao substance test sample request
	 * data access object
	 * @param sampleRequestStatusReasonDao sample request status reason data
	 * access object
	 * @param sampleRequestDao sample request data access object
	 * @param substanceTestSampleInstanceFactory substance test sample instance
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SubstanceTestSampleServiceImpl(
			final SubstanceTestSampleDao substanceTestSampleDao,
			final SampleCollectionMethodDao sampleCollectionMethodDao,
			final SubstanceTestReasonDao substanceTestReasonDao,
			final SubstanceTestDao substanceTestDao,
			final SubstanceTestResultDao substanceTestResultDao,
			final LabResultDao labResultDao,
			final SampleRequestStatusReasonDao sampleRequestStatusReasonDao,
			final SampleRequestDao sampleRequestDao,
			final InstanceFactory<SubstanceTestSample> 
			substanceTestSampleInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.substanceTestSampleDao = substanceTestSampleDao;
		this.sampleCollectionMethodDao = sampleCollectionMethodDao;
		this.substanceTestReasonDao = substanceTestReasonDao;
		this.substanceTestDao = substanceTestDao;
		this.substanceTestResultDao = substanceTestResultDao;
		this.labResultDao = labResultDao;
		this.sampleRequestStatusReasonDao = sampleRequestStatusReasonDao;
		this.sampleRequestDao = sampleRequestDao;
		this.substanceTestSampleInstanceFactory 
			= substanceTestSampleInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample save(
			final SubstanceTestSample substanceTestSample) {
		return this.substanceTestSampleDao.makePersistent(substanceTestSample);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestSample> findAll() {
		return this.substanceTestSampleDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestSample> findSamplesByOffender(
			final Offender offender) {
		return this.substanceTestSampleDao.findSamplesByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample create(final Offender offender, 
			final Date collectionDate, final Person collectionEmployee,
			final SampleCollectionMethod collectionMethod,
			final SubstanceTestReason reason, final Boolean random)
		throws DuplicateEntityFoundException {
		if (this.substanceTestSampleDao
				.find(offender, collectionMethod, collectionDate) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test sample found");
		}
		SubstanceTestSample sample = this.substanceTestSampleInstanceFactory
				.createInstance();
		sample.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateSubtanceTestSample(sample, offender, collectionDate, 
				collectionEmployee, collectionMethod, reason, random);
		this.substanceTestSampleDao.makePersistent(sample);
		return sample;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample update(final SubstanceTestSample sample,
			final Offender offender, final Date collectionDate, 
			final Person collectionEmployee,
			final SampleCollectionMethod collectionMethod,
			final SubstanceTestReason reason, final Boolean random,
			final SampleRequestStatusReason statusReason)
		throws DuplicateEntityFoundException {
		if (this.substanceTestSampleDao.findExcluding(sample,
				offender, collectionMethod, collectionDate) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test sample found");
		}
		this.populateSubtanceTestSample(sample, offender, collectionDate, 
				collectionEmployee, collectionMethod, reason, random);
		return this.substanceTestSampleDao.makePersistent(sample);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SubstanceTestSample substanceTestSample) {
		SampleRequest sampleRequest 
			= this.sampleRequestDao.findBySample(
					substanceTestSample);
		if (sampleRequest != null) {
			sampleRequest.setResolution(null);
			this.sampleRequestDao.makePersistent(sampleRequest);
		}
		this.substanceTestSampleDao.makeTransient(substanceTestSample);
	}

	/** {@inheritDoc} */
	@Override
	public List<SampleCollectionMethod> findCollectionMethods() {
		return this.sampleCollectionMethodDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestReason> findSubstanceTestReasons() {
		return this.substanceTestReasonDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest findSubstanceTestBySample(
			final SubstanceTestSample substanceTestSample) {
		return this.substanceTestDao.find(substanceTestSample);
	}

	/** {@inheritDoc} */
	@Override
	public void removeSubstanceTest(final SubstanceTest substanceTest) {
		List<SubstanceTestResult> testResults = this.substanceTestResultDao
				.findBySubstanceTest(substanceTest);
		for(SubstanceTestResult result : testResults) {
			this.substanceTestResultDao.makeTransient(result);
		}
		List<LabResult> crimeLabResults = this.labResultDao
				.findBySubstanceTest(substanceTest);
		for (LabResult crimeLabResult : crimeLabResults) {
			this.labResultDao.makeTransient(crimeLabResult);
		}
		this.substanceTestDao.makeTransient(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public List<SampleRequestStatusReason> findStatusReasons() {
		return this.sampleRequestStatusReasonDao.findStatusReasons();
	}
	
	/** {@inheritDoc} */
	@Override
	public SampleRequest findRequestBySample(
			final SubstanceTestSample substanceTestSample) {
		return this.sampleRequestDao.findBySample(substanceTestSample);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified substance test sample.
	 * 
	 * @param sample substance test sample
	 * @param offender offender
	 * @param collectionDate collection date
	 * @param collectionEmployee collection employee
	 * @param collectionMethod sample collection method
	 * @param reason substance test reason
	 * @param random random
	 * @param statusReason sample status reason
	 * @return populated substance test sample
	 */
	private SubstanceTestSample populateSubtanceTestSample(
			final SubstanceTestSample sample,
			final Offender offender, final Date collectionDate, 
			final Person collectionEmployee,
			final SampleCollectionMethod collectionMethod,
			final SubstanceTestReason reason, final Boolean random) {
		sample.setOffender(offender);
		sample.setCollectionDate(collectionDate);
		sample.setCollectionEmployee(collectionEmployee);
		sample.setSampleCollectionMethod(collectionMethod);
		if(random != null && random == true) {
			sample.setSubstanceTestReason(null);
		} else {
			sample.setSubstanceTestReason(reason);
		}
		sample.setRandom(random);
		sample.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return sample;
	}
	
	/** {@inheritDoc} */
	@Override
	public SampleRequest updateRequestResolution(final SampleRequest request,
			final SampleRequestResolution resolution)
		throws DuplicateEntityFoundException {
		if (this.sampleRequestDao.findExcluding(request, request.getOffender(),
				request.getDate()) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate sample request found.");
		}
		request.setResolution(resolution);
		return this.sampleRequestDao.makePersistent(request);
	}

	/** {@inheritDoc} */
	@Override
	public SampleRequest resolveRequest(final SampleRequest request,
			final SampleRequestResolution resolution)
		throws DuplicateEntityFoundException {
		if (this.sampleRequestDao.findExcluding(request, request.getOffender(),
				request.getDate()) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate sample request found.");
		}
		request.setResolution(resolution);
		return this.sampleRequestDao.makePersistent(request);
	}
}