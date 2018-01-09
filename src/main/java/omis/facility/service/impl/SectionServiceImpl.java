/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.SectionDao;
import omis.facility.domain.Facility;
import omis.facility.domain.Section;
import omis.facility.service.SectionService;

/**
 * Section service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class SectionServiceImpl implements SectionService {

	private SectionDao sectionDao;
	
	/**
	 * Instantiates a section service implementation with the specified data
	 * access object.
	 * 
	 * @param sectionDao section DAO
	 */
	public SectionServiceImpl(final SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	/** {@inheritDoc} */
	@Override
	public Section save(final Section section) {
		return this.sectionDao.makePersistent(section);
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findByFacility(final Facility facility) {
		return this.sectionDao.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Section section) {
		this.sectionDao.makeTransient(section);
	}
}