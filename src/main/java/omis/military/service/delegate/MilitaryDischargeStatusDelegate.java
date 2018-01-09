package omis.military.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.military.dao.MilitaryDischargeStatusDao;
import omis.military.domain.MilitaryDischargeStatus;

/**
 * Military branch delegate.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Jul 8, 2016)
 * @param <DischargeStatus>
 * @since OMIS 3.0
 */

	/* Constructor. */

public class MilitaryDischargeStatusDelegate {

	/* Instance factories. */
	private final InstanceFactory<MilitaryDischargeStatus> 
		militaryDischargeStatusInstanceFactory;

	/* DAOs. */
	private MilitaryDischargeStatusDao militaryDischargeStatusDao;

	public List<MilitaryDischargeStatus> findAll() {
		return this.militaryDischargeStatusDao.findAll();
	}

	public MilitaryDischargeStatusDelegate (
			final MilitaryDischargeStatusDao militaryDischargeStatusDao,
			final InstanceFactory<MilitaryDischargeStatus> 
				militaryDischargeStatusInstanceFactory){
		this.militaryDischargeStatusDao = militaryDischargeStatusDao;
		this.militaryDischargeStatusInstanceFactory = 
				militaryDischargeStatusInstanceFactory;
	}

	/** Creates a military discharge status.
	 * @param Name - name
	 * @param Valid - valid
	 * @param  - .
	 * @return Military discharge status. 
	 * @throws DuplicateEntityFoundException - when status already exists. */
	public MilitaryDischargeStatus create(
			final String name,
			final Boolean valid) 
				throws DuplicateEntityFoundException {
					if (this.militaryDischargeStatusDao.find(name, valid) 
						!= null) {
					throw new DuplicateEntityFoundException(
						"Duplicate discharge status found");
		}
		
			MilitaryDischargeStatus dischargeStatus = this.
					militaryDischargeStatusInstanceFactory.createInstance();
			dischargeStatus.setName(name);
			dischargeStatus.setValid(valid);
					return this.militaryDischargeStatusDao.makePersistent(
							dischargeStatus);
		}
}