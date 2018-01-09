package omis.substancetest.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Sample request data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 8, 2016)
 * @since OMIS 3.0
 */
public interface SampleRequestDao extends GenericDao<SampleRequest> {
	
	/**
	 * Returns the sample request associated with the substance test sample.
	 * 
	 * @param sample substance test sample
	 * @return sample request
	 */
	public SampleRequest findBySample(final SubstanceTestSample sample);

	/**
	 * Returns the request with the specified offender, date, and resolution,
	 * excluding the specified request.
	 * 
	 * @param request sample request
	 * @param offender offender
	 * @param date date
	 * @return
	 */
	public SampleRequest findExcluding(SampleRequest request, Offender offender,
			Date date);
	
	/**
	 * Returns an unresolved sample request for the specified offender.
	 * 
	 * @param offender offender
	 * @return unresolved sample request
	 */
	public SampleRequest findUnresolvedByOffender(Offender offender);
}