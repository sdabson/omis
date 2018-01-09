package omis.citizenship.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.citizenship.dao.CitizenshipDao;
import omis.citizenship.domain.Citizenship;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * CitizenshipDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class CitizenshipDelegate {
	
	private final CitizenshipDao citizenshipDao;
	
	private final InstanceFactory<Citizenship> 
		citizenshipInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for CitizenshipDelegate
	 * @param citizenshipDao
	 * @param citizenshipInstanceFactory
	 * @param auditComponentRetriever
	 */
	public CitizenshipDelegate(
			final CitizenshipDao citizenshipDao,
			final InstanceFactory<Citizenship> 
				citizenshipInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.citizenshipDao = citizenshipDao;
		this.citizenshipInstanceFactory = citizenshipInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a citizenship
	 * @param offender
	 * @param country
	 * @param dateRange
	 * @return Created citizenship
	 */
	public Citizenship create(final Offender offender, final Country country, 
			final DateRange dateRange){
		
		Citizenship citizenship = 
				this.citizenshipInstanceFactory.createInstance();
		
		citizenship.setOffender(offender);
		citizenship.setCountry(country);
		citizenship.setDateRange(dateRange);
		citizenship.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		citizenship.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.citizenshipDao.makePersistent(citizenship);
	}
	
	/**
	 * Updates a citizenship
	 * @param citizenship - citizenship to update
	 * @param country
	 * @param dateRange
	 * @return Updated citizenship
	 */
	public Citizenship update(final Citizenship citizenship, 
			final Country country, final DateRange dateRange){
		
		citizenship.setCountry(country);
		citizenship.setDateRange(dateRange);
		citizenship.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.citizenshipDao.makePersistent(citizenship);
	}
	
	/**
	 * Removes a citizenship
	 * @param citizenship - citizenship to remove
	 */
	public void remove(final Citizenship citizenship){
		this.citizenshipDao.makeTransient(citizenship);
	}
	
	/**
	 * Finds citizenship by offender
	 * @param offender
	 * @return citizenship by specified offender
	 */
	public Citizenship findByOffender(final Offender offender) {
		return this.citizenshipDao.findByOffender(offender);
	}
	
	/**
	 * Returns a list of all citizenships by country
	 * @param country
	 * @return List of all citizenships by specified country
	 */
	public List<Citizenship> findByCountry(final Country country) {
		return this.citizenshipDao.findByCountry(country);
	}
	
	
}
