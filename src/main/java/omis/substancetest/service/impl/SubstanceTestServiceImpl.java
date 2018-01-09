package omis.substancetest.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.substance.dao.SubstanceDao;
import omis.substance.dao.SubstanceLabDao;
import omis.substance.domain.Substance;
import omis.substance.domain.SubstanceLab;
import omis.substancetest.dao.LabResultDao;
import omis.substancetest.dao.SubstanceTestDao;
import omis.substancetest.dao.SubstanceTestResultDao;
import omis.substancetest.dao.SubstanceTestResultValueDao;
import omis.substancetest.dao.TestKitDao;
import omis.substancetest.dao.TestKitParameterDao;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResult;
import omis.substancetest.domain.SubstanceTestResultValue;
import omis.substancetest.domain.SubstanceTestSample;
import omis.substancetest.domain.TestKit;
import omis.substancetest.domain.TestKitParameter;
import omis.substancetest.service.SubstanceTestService;

/**
 * Substance test service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Apr 15, 2015)
 * @since OMIS 3.0
 */
public class SubstanceTestServiceImpl implements SubstanceTestService {

	/* Data access objects. */
	
	private SubstanceTestDao substanceTestDao;
	
	private SubstanceTestResultDao substanceTestResultDao;
	
	private LabResultDao labResultDao;
	
	private SubstanceTestResultValueDao substanceTestResultValueDao;
	
	private TestKitDao testKitDao;
	
	private TestKitParameterDao testKitParameterDao;
	
	private SubstanceDao substanceDao;
	
	private SubstanceLabDao substanceLabDao;
	
	/* Instance factories. */
	
	private InstanceFactory<SubstanceTest> substanceTestInstanceFactory;
	
	private InstanceFactory<SubstanceTestResult> 
	substanceTestResultInstanceFactory;
	
	private InstanceFactory<LabResult> labResultInstanceFactory;
	
	/* Component Retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a substance test service implementation with the
	 * specified data access objects, component retrievers, 
	 * and instance factories.
	 * 
	 * @param substanceTestDao substance test data access object
	 * @param substanceTestResultDao substance test result data access object
	 * @param labResultDao crime lab result data access object
	 * @param substanceTestResultValueDao substance test result value data
	 * access object
	 * @param testKitDao test kit data access object
	 * @param substanceLabDao substance lab data access object
	 * @param testKitParameterDao test kit parameter data access object
	 * @param substanceDao substance data access object
	 * @param auditComponentRetriever audit component retriever
	 * @param substanceTestInstanceFactory substance test instance factory
	 * @param substanceTestResultInstanceFactory substance test result
	 * instance factory
	 * @param labResultInstanceFactory crime lab result instance factory
	 */
	public SubstanceTestServiceImpl(final SubstanceTestDao substanceTestDao,
			final SubstanceTestResultDao substanceTestResultDao,
			final LabResultDao labResultDao,
			final SubstanceTestResultValueDao substanceTestResultValueDao,
			final TestKitDao testKitDao,
			final TestKitParameterDao testKitParameterDao,
			final SubstanceDao substanceDao,
			final SubstanceLabDao substanceLabDao,
			final AuditComponentRetriever auditComponentRetriever,
			final InstanceFactory<SubstanceTest> substanceTestInstanceFactory,
			final InstanceFactory<SubstanceTestResult> 
				substanceTestResultInstanceFactory,
			final InstanceFactory<LabResult> 
				labResultInstanceFactory) {
		this.substanceTestDao = substanceTestDao;
		this.substanceTestResultDao = substanceTestResultDao;
		this.labResultDao = labResultDao;
		this.substanceTestResultValueDao = substanceTestResultValueDao;
		this.testKitDao = testKitDao;
		this.testKitParameterDao = testKitParameterDao;
		this.substanceDao = substanceDao;
		this.substanceLabDao = substanceLabDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.substanceTestInstanceFactory = substanceTestInstanceFactory;
		this.substanceTestResultInstanceFactory 
			= substanceTestResultInstanceFactory;
		this.labResultInstanceFactory = labResultInstanceFactory;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest save(final SubstanceTest substanceTest) {
		return this.substanceTestDao.makePersistent(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTest> findAll() {
		return this.substanceTestDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest findTestBySample(final SubstanceTestSample sample) {
		return this.substanceTestDao.find(sample);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest create(final SubstanceTestSample sample, 
			final Date resultDate, final Boolean labInvolved, 
			final Date labResultDate, final Date labRequestDate,
			final String notes, final SubstanceLab lab,
			final String privateLabJustification, final Person authorizingStaff) 
		throws DuplicateEntityFoundException {
		if (this.substanceTestDao.find(sample) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test found");
		}
		SubstanceTest substanceTest = this.substanceTestInstanceFactory
				.createInstance();
		substanceTest.setCreationSignature(new CreationSignature(this.auditComponentRetriever.retrieveUserAccount(), this.auditComponentRetriever.retrieveDate()));
		this.populateSubstanceTest(substanceTest, sample, resultDate, 
				labInvolved, labResultDate, labRequestDate,
				notes, lab, privateLabJustification, authorizingStaff);
		return this.substanceTestDao.makePersistent(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTest update(final SubstanceTest substanceTest,
			final SubstanceTestSample sample, final Date resultDate,
			final Boolean labInvolved, final Date labResultDate,
			final Date labRequestDate, final String notes,
			final SubstanceLab lab, final String privateLabJustification,
			final Person authorizingStaff)
		throws DuplicateEntityFoundException {
		if (this.substanceTestDao.findExcluding(substanceTest, sample) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test found");
		}
		this.populateSubstanceTest(substanceTest, sample, resultDate, 
				labInvolved, labResultDate, labRequestDate,
				notes, lab, privateLabJustification, authorizingStaff);
		return this.substanceTestDao.makePersistent(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResult addResult(final SubstanceTest substanceTest,
			final Substance substance, final SubstanceTestResultValue value,
			final Boolean substanceUseAdmission, 
			final Boolean admissionPriorToTest)
		throws DuplicateEntityFoundException {
		if (this.substanceTestResultDao
				.find(substanceTest, substance) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test result found");
		}
		SubstanceTestResult result = this.substanceTestResultInstanceFactory
				.createInstance();
		result.setSubstanceTest(substanceTest);
		this.populateSubstanceTestResult(result, substance, 
				value, substanceUseAdmission, admissionPriorToTest);
		return this.substanceTestResultDao.makePersistent(result);
	}
	
	/** {@inheritDoc} */
	@Override
	public LabResult addLabResult(SubstanceTest substanceTest,
			Substance substance, SubstanceTestResultValue value)
		throws DuplicateEntityFoundException {
		if (this.labResultDao.find(substanceTest, substance) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate lab result found");
		}
		LabResult result = this.labResultInstanceFactory
				.createInstance();
		result.setSubstanceTest(substanceTest);
		this.populateLabResult(result, substance, value);
		return this.labResultDao.makePersistent(result);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResult updateResult(SubstanceTestResult result,
			Substance substance, SubstanceTestResultValue value,
			Boolean substanceUseAdmission, Boolean admissionPriorToTest) 
		throws DuplicateEntityFoundException {
		if (this.substanceTestResultDao.findExcluding(result, substance, 
				result.getSubstanceTest()) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate substance test result found");
		}
		this.populateSubstanceTestResult(result, substance, value, 
				substanceUseAdmission, admissionPriorToTest);
		return this.substanceTestResultDao.makePersistent(result);
	}

	/** {@inheritDoc} */
	@Override
	public LabResult updateLabResult(LabResult result,
			Substance substance, SubstanceTestResultValue value) 
		throws DuplicateEntityFoundException {
		if (this.labResultDao.findExcluding(result, substance, 
				result.getSubstanceTest()) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate crime lab result found");
		}
		this.populateLabResult(result, substance, value);
		return this.labResultDao.makePersistent(result);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SubstanceTest substanceTest) {
		this.substanceTestDao.makeTransient(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public void removeResult(SubstanceTestResult result) {
		this.substanceTestResultDao.makeTransient(result);
	}

	/** {@inheritDoc} */
	@Override
	public void removeLabResult(LabResult result) {
		this.labResultDao.makeTransient(result);	
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestResult> findResultsBySubstanceTest(
			SubstanceTest substanceTest) {
		return this.substanceTestResultDao.findBySubstanceTest(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabResult> findLabResultsBySubstanceTest(
			SubstanceTest substanceTest) {
		return this.labResultDao.findBySubstanceTest(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceTestResultValue> findResultValues() {
		return this.substanceTestResultValueDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<TestKit> findKitsBySampleCollectionMethod(
			final SampleCollectionMethod method) {
		return this.testKitDao.findBySampleCollectionMethod(method);
	}

	/** {@inheritDoc} */
	@Override
	public List<TestKitParameter> findParametersByTestKit(
			final TestKit testKit) {
		return this.testKitParameterDao.findByTestKit(testKit);
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestResult findResultBySubstanceAndTest(
			final SubstanceTest substanceTest, final Substance substance) {
		return this.substanceTestResultDao.find(substanceTest, substance);
	}

	/** {@inheritDoc} */
	@Override
	public LabResult findLabResultBySubstanceAndTest(
			final SubstanceTest substanceTest, final Substance substance) {
		return this.labResultDao.find(substanceTest, substance);
	}

	/** {@inheritDoc} */
	@Override
	public void removeResultsBySubstanceTestExcluding(
			final SubstanceTest substanceTest, 
			final SubstanceTestResult[] persistedResults) {
		this.substanceTestResultDao.removeBySubstanceTestExcluding(
				substanceTest, persistedResults);
	}

	/** {@inheritDoc} */
	@Override
	public void removeLabResultBySubstanceTestExcluding(
			final SubstanceTest substanceTest, 
			final LabResult[] persistedResults) {
		this.labResultDao.removeCrimeLabResultBySubstanceTestExcluding(
				substanceTest, persistedResults);
	}

	/** {@inheritDoc} */
	@Override
	public List<Substance> findSubstances() {
		return this.substanceDao.findTestable();
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeLabResultsBySubstanceTest(
			final SubstanceTest substanceTest) {
		this.labResultDao.removeResultsBySubstanceTest(substanceTest);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceLab> findSubstanceLabs() {
		return this.substanceLabDao.findAll();
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @param sample substance test sample
	 * @param resultDate result date
	 * @param labInvolved whether lab is involved
	 * @param labResultDate date of lab results
	 * @param labRequestDate date of lab request
	 * @param notes notes
	 * @param lab substance lab
	 * @param privateLabJustification private lab justification
	 * @param authorizingStaff authorizing staff
	 * @return populated substance test
	 */
	private SubstanceTest populateSubstanceTest(
			final SubstanceTest substanceTest, final SubstanceTestSample sample, 
			final Date resultDate, final Boolean labInvolved, 
			final Date labResultDate, final Date labRequestDate,
			final String notes, final SubstanceLab lab,
			final String privateLabJustification,
			final Person authorizingStaff) {
		substanceTest.setSubstanceTestSample(sample);
		substanceTest.setResultDate(resultDate);
		substanceTest.setLabInvolved(labInvolved);
		substanceTest.setLabResultDate(labResultDate);
		substanceTest.setLabRequestDate(labRequestDate);
		substanceTest.setTestComment(notes);
		substanceTest.setLab(lab);
		if (lab != null && lab.getPrivateLab()) {
			substanceTest.setPrivateLabJustification(privateLabJustification);
			substanceTest.setAuthorizingStaff(authorizingStaff);
		}
		substanceTest.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return substanceTest;
	}
	
	/*
	 * Populates the specified substance test result.
	 * 
	 * @param result substance test result
	 * @param substance substance
	 * @param value substance test result value
	 * @param substanceUseAdmission whether admission to use of substance 
	 * applies
	 * @param admissionPriorToTest whether admission occurred prior to test
	 * @return populates substance test result
	 */
	private SubstanceTestResult populateSubstanceTestResult(
			final SubstanceTestResult result,
			final Substance substance, final SubstanceTestResultValue value,
			final Boolean substanceUseAdmission, 
			final Boolean admissionPriorToTest) {		
		result.setSubstance(substance);
		result.setValue(value);
		result.setSubstanceUseAdmission(substanceUseAdmission);
		result.setAdmissionPriorToTest(admissionPriorToTest);
		return result;
	}
	
	/*
	 * Populates the specified lab result.
	 * 
	 * @param result lab result
	 * @param substance substance
	 * @param value substance test result value
	 * @return populated lab result
	 */
	private LabResult populateLabResult(
			final LabResult result,
			final Substance substance,
			final SubstanceTestResultValue value) {
		result.setSubstance(substance);
		result.setValue(value);
		return result;
	}
}