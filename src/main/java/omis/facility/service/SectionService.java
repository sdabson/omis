package omis.facility.service;

import java.util.List;

import omis.facility.domain.Facility;
import omis.facility.domain.Section;

/**
 * Section service.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public interface SectionService {
	
	/**
	 * Saves the specified section.
	 * 
	 * @param section section
	 * @return the section
	 */
	Section save(Section section);
	
	/**
	 * Returns a list of all sections from the specified facility.
	 * 
	 * @param facility facility 
	 * @return list of sections
	 */
	List<Section> findByFacility(Facility facility);
	
	/**
	 * Removes the specified section.
	 * 
	 * @param section section
	 */
	void remove(Section section);
}