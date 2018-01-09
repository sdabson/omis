package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.component.LabWorkResults;
import omis.health.exception.LabWorkResultsException;
import omis.health.exception.LabWorkSampleException;
import omis.health.service.LabWorkResultsService;
import omis.offender.domain.Offender;

/**
 * Lab Work Results Service.
 *
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (Nov 1, 2014)
 * @since OMIS 3.0
 */
public class LabWorkResultsServiceImpl  implements LabWorkResultsService {
	/* Data Access Objects */
	
	private final LabWorkDao labWorkDao;
	
	private final LabDao labDao;
	
	/** Constructor.
	 * @param labWorkDao lab Work Dao.
	*/
	public LabWorkResultsServiceImpl(final LabWorkDao labWorkDao, 
			final LabDao labDao) {
		this.labWorkDao = labWorkDao;
		this.labDao = labDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByOffender(final Offender offender, 
			final Facility facility, final Date startDate, final Date endDate, 
			final Boolean sampleMustBeTaken){
		return this.labWorkDao.findIncompleteByOffender(offender, facility, 
				startDate, endDate, sampleMustBeTaken);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByOffenderBySearch(final Offender 
				offender, 
			final Facility facility, final Date startDate, final Date endDate, 
			final Boolean sampleMustBeTaken){
		return this.labWorkDao.findIncompleteByOffenderBySearch(offender, 
				facility, startDate, endDate, sampleMustBeTaken);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByFacility(final Facility facility, 
		final Date startDate, final Date endDate, 
		final Boolean sampleMustBeTaken) {
			return this.labWorkDao.findIncompleteByFacility(facility, startDate, 
				endDate, sampleMustBeTaken);
	}	
	
	/** {@inheritDoc} */
	@Override
	public List<Lab> findResultsLabs() {
		return this.labDao.findResultLabs();
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWork takeSample(final LabWork labWork, final String sampleNotes) 
	throws LabWorkSampleException {
		if((labWork.getSampleTaken()!=null)&&labWork.getSampleTaken())
		{
			throw new LabWorkSampleException("Sample already taken");
		}
		
		labWork.setSampleNotes(sampleNotes);
		labWork.setSampleTaken(true);
		return this.labWorkDao.makePersistent(labWork);
	}	

	/** {@inheritDoc} */
	@Override
	public LabWork updateResults(final LabWork labWork,  
			final LabWorkResults results) 
	throws LabWorkResultsException {
		if (labWork.getResults() != null && 
				labWork.getResults().getDate() != null) {
			throw new LabWorkResultsException("Results already taken");
		}
		labWork.setResults(results);
		
		// Sets status of health appointment to kept if the results date is not
		// null or null if the results date is null [- SA]
		if (results != null && results.getDate() != null) {
			labWork.getOffenderAppointmentAssociation().getAppointment()
				.setStatus(HealthAppointmentStatus.KEPT);
		} else {
			labWork.getOffenderAppointmentAssociation().getAppointment()
				.setStatus(null);
		}
		return this.labWorkDao.makePersistent(labWork);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final LabWork labWork) {
		this.labWorkDao.makeTransient(labWork);
	}

	/** {@inheritDoc} */
	@Override
	public LabWork updateSampleNotes(final LabWork labWork, 
			final String sampleNotes)throws LabWorkSampleException {
		if(!labWork.getSampleTaken()) {
			throw new LabWorkSampleException("Sample not taken");
		}
		labWork.setSampleNotes(sampleNotes);
		return this.labWorkDao.makePersistent(labWork);
	}	
}