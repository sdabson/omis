package omis.warrant.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.service.delegate.FacilityDelegate;
import omis.jail.domain.Jail;
import omis.jail.service.delegate.JailDelegate;
import omis.person.domain.Person;
import omis.region.domain.County;
import omis.region.service.delegate.CountyDelegate;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;
import omis.warrant.service.WarrantReleaseService;
import omis.warrant.service.delegate.WarrantReleaseDelegate;

/**
 * WarrantReleaseServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseServiceImpl implements WarrantReleaseService {
	
	private final WarrantReleaseDelegate warrantReleaseDelegate;
	
	private final CountyDelegate countyDelegate;
	
	private final JailDelegate jailDelegate;
	
	private final FacilityDelegate facilityDelegate;
	
	
	
	/**
	 * @param warrantReleaseDelegate
	 * @param countyDelegate
	 * @param jailDelegate
	 * @param facilityDelegate
	 */
	public WarrantReleaseServiceImpl(
			final WarrantReleaseDelegate warrantReleaseDelegate,
			final CountyDelegate countyDelegate,
			final JailDelegate jailDelegate,
			final FacilityDelegate facilityDelegate) {
		this.warrantReleaseDelegate = warrantReleaseDelegate;
		this.countyDelegate = countyDelegate;
		this.jailDelegate = jailDelegate;
		this.facilityDelegate = facilityDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease create(
			final Warrant warrant, final String instructions,
			final County county, final Facility facility,
			final Date releaseTimestamp, final String addressee,
			final Person clearedBy, final Date clearedByDate)
					throws DuplicateEntityFoundException {
		return this.warrantReleaseDelegate.create(
				warrant, instructions, county, facility, releaseTimestamp,
				addressee, clearedBy, clearedByDate);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease update(
			final WarrantRelease warrantRelease, final String instructions,
			final County county, final Facility facility,
			final Date releaseTimestamp, final String addressee,
			final Person clearedBy, final Date clearedByDate)
					throws DuplicateEntityFoundException {
		return this.warrantReleaseDelegate.update(
				warrantRelease, instructions, county, facility, releaseTimestamp,
				addressee, clearedBy, clearedByDate);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final WarrantRelease warrantRelease) {
		this.warrantReleaseDelegate.remove(warrantRelease);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease findByWarrant(final Warrant warrant) {
		return this.warrantReleaseDelegate.findByWarrant(warrant);
	}

	/**{@inheritDoc} */
	@Override
	public List<County> findAllCounties() {
		return this.countyDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<Jail> findAllJails() {
		return this.jailDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<Facility> findAllFacilities() {
		return this.facilityDelegate.findAll();
	}

}
