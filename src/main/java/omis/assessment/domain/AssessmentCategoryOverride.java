/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.assessment.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.staff.domain.StaffAssignment;

/**
 * Assessment category override.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Jan 31, 2019)
 * @since OMIS 3.0
 */
public interface AssessmentCategoryOverride extends Creatable, Updatable {

	/**
	 * Sets the ID of the assessment category override.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the assessment category override.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the assessment category score.
	 * @param assessmentCategoryScore assessment category score
	 */
	void setAssessmentCategoryScore(
			AssessmentCategoryScore	assessmentCategoryScore);
	
	/**
	 * Returns the assessment category score.
	 * 
	 * @return assessment category score
	 */
	AssessmentCategoryScore	getAssessmentCategoryScore();
	
	/**
	 * Sets the assessment rating.
	 * 
	 * @param assessmentRating assessment rating
	 */
	void setAssessmentRating(AssessmentRating assessmentRating);
	
	/**
	 * Returns the assessment rating.
	 * 
	 * @return assessment rating
	 */
	AssessmentRating getAssessmentRating();
	
	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the approved staff assignment.
	 * 
	 * @param approvedStaffAssignment approved staff assignment
	 */
	void setApprovedStaffAssignment(StaffAssignment approvedStaffAssignment);
	
	/**
	 * Returns the approved staff assignment.
	 * 
	 * @return approved staff assignment
	 */
	StaffAssignment getApprovedStaffAssignment();
	
	/**
	 * Returns the Date for the Assessment Category Override.
	 * @return date - Date
	 */
	Date getDate();
	
	/**
	 * Sets the Date for the Assessment Category Override.
	 * @param date - Date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the Reason for the Assessment Category Override.
	 * @return reason - Reason
	 */
	CategoryOverrideReason getReason();
	
	/**
	 * Sets the Reason for the Assessment Category Override.
	 * @param reason - Reason
	 */
	void setReason(CategoryOverrideReason reason);
}