package omis.military.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.military.dao.MilitaryBranchDao;
import omis.military.domain.MilitaryBranch;

/**
 * Military branch delegate.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Jul 8, 2016)
 * @since OMIS 3.0
 */

public class MilitaryBranchDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<MilitaryBranch>
	militaryBranchInstanceFactory;
	
	/* DAOs. */
	
	private final MilitaryBranchDao militaryBranchDao;

	/* Constructor. */
	
	/**
	 * Instantiates delegate for military branches.
	 * 
	 * @param militaryBranchInstanceFactory instance factory for
	 * military branches
	 * @param militaryBranchDao data access object for military branches
	 */
	public MilitaryBranchDelegate (
			final MilitaryBranchDao militaryBranchDao,
			final InstanceFactory<MilitaryBranch> militaryBranchInstanceFactory) {
		this.militaryBranchDao = militaryBranchDao;
		this.militaryBranchInstanceFactory = militaryBranchInstanceFactory;
		
	}
	
	public List<MilitaryBranch> findAll() {
		return this.militaryBranchDao.findAll();
	}
	
	/** Creates a military branch.
	 * @param Name - name
	 * @param Short Name - sname
	 * @param Valid - valid
	 * @param  - .
	 * @return Military branch. 
	 * @throws DuplicateEntityFoundException - when branch already exists. */
		public MilitaryBranch create(
			final String name,
			final String sname, 
			final Boolean valid) 
				throws DuplicateEntityFoundException {
					if (this.militaryBranchDao.find(name, sname, valid) 
						!= null) {
					throw new DuplicateEntityFoundException(
						"Duplicate military branch found");
		}
		
			MilitaryBranch militaryBranch = this.militaryBranchInstanceFactory
				.createInstance();
			militaryBranch.setName(name);
			militaryBranch.setShortName(sname);
			militaryBranch.setValid(valid);
					return this.militaryBranchDao.makePersistent(militaryBranch);
		}

}

