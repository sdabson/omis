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
package omis.presentenceinvestigation.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Report object for presentence investigation summary.
 * 
 * @author Ryan Johns
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.5 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long presentenceInvestigationRequestId;
	
	private final Long offenderId;
	
	private final List<String> docketValues;
	
	private final Date requestDate;
	
	private final String assignedUserFirstName;
	
	private final String assignedUserLastName;
	
	private final String assignedUserUserName;
	
	private final Date expectedCompletionDate;
	
	private final String offenderFirstName;
	
	private final String offenderLastName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final Date sentenceDate;
	
	private final String category;
	
	private final Long completedTaskCount;
	
	private final Long totalTaskCount;
	
	private final Date submissionDate;
	
	private final PresentenceInvestigationRequestStatus status;
	
	/** 
	 * Constructor.
	 * 
	 * @param presentenceInvestigationRequstId presentence investigation
	 * request id
	 * @param offenderId offender id
	 * @param requestDate request date
	 * @param assignedUserFirstName assigned user first name
	 * @param assignedUserLastName assigned user last name
	 * @param assignedUserUserName assigned user user name
	 * @param expectedCompletionDate expected completion date
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param sentenceDate sentence Date
	 * @param category presentence investigation category
	 * @param completedTaskCount completed task count
	 * @param totalTaskCount total task count
	 * @param status presentence investigation request status
	 */
	public PresentenceInvestigationRequestSummary(
			final Long presentenceInvestigationRequestId, final Long offenderId,
			final Date requestDate, final String assignedUserFirstName, 
			final String assignedUserLastName, 
			final String assignedUserUserName, final Date expectedCompletionDate,
			final String offenderFirstName, final String offenderLastName, 
			final String offenderMiddleName, final Integer offenderNumber, 
			final Date sentenceDate, final String category, 
			final Long completedTaskCount, final Long totalTaskCount, 
			final Date submissionDate, final Long delayCount) {
		this(presentenceInvestigationRequestId, offenderId, 
				new ArrayList<String>(), requestDate, assignedUserFirstName, 
				assignedUserLastName, assignedUserUserName, 
				expectedCompletionDate, offenderFirstName, offenderLastName, 
				offenderMiddleName, offenderNumber, sentenceDate, category, 
				completedTaskCount, totalTaskCount, submissionDate, delayCount);
	}
	
	/** 
	 * Constructor.
	 * 
	 * @param presentenceInvestigationRequstId presentence investigation
	 * request id
	 * @param offenderId offender id
	 * @param docketValues docket values
	 * @param requestDate request date
	 * @param assignedUserFirstName assigned user first name
	 * @param assignedUserLastName assigned user last name
	 * @param assignedUserUserName assigned user user name
	 * @param expectedCompletionDate expected completion date
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param sentenceDate sentence Date
	 * @param category presentence investigation category
	 * @param completedTaskCount completed task count
	 * @param totalTaskCount total task count
	 * @param status presentence investigation request status
	 */
	public PresentenceInvestigationRequestSummary(
			final Long presentenceInvestigationRequestId, final Long offenderId,
			final List<String> docketValues, final Date requestDate,
			final String assignedUserFirstName, final String assignedUserLastName,
			final String assignedUserUserName, final Date expectedCompletionDate,
			final String offenderFirstName, final String offenderLastName, 
			final String offenderMiddleName, final Integer offenderNumber, 
			final Date sentenceDate, final String category, 
			final Long completedTaskCount, final Long totalTaskCount, 
			final Date submissionDate, final Long delayCount) {
		this.presentenceInvestigationRequestId = presentenceInvestigationRequestId;
		this.offenderId = offenderId;
		this.docketValues = docketValues;
		this.requestDate = requestDate;
		this.assignedUserFirstName = assignedUserFirstName;
		this.assignedUserLastName = assignedUserLastName;
		this.assignedUserUserName = assignedUserUserName;
		this.expectedCompletionDate = expectedCompletionDate;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.sentenceDate = sentenceDate;
		this.category = category;
		this.completedTaskCount = completedTaskCount;
		this.totalTaskCount = totalTaskCount;
		this.submissionDate = submissionDate;
		if (submissionDate != null) {
			this.status = PresentenceInvestigationRequestStatus.SUBMITTED;
		} else if (delayCount > 0) {
			this.status = PresentenceInvestigationRequestStatus.DELAYED;
		} else {
			this.status = PresentenceInvestigationRequestStatus.IN_PROGRESS;
		}
	}

	/**
	 * Returns the presentence investigation request id.
	 * 
	 * @return presentence investigation request id 
	 */
	public Long getPresentenceInvestigationRequestId() {
		return this.presentenceInvestigationRequestId;
	}
	
	/**
	 * Returns the offender id.
	 * 
	 * @return offender id
	 */
	public Long getOffenderId() {
		return this.offenderId;
	}

	/**
	 * Returns a list of docket values.
	 * 
	 * @return list of docket values
	 */
	public List<String> getDocketValues() {
		return this.docketValues;
	}

	/**
	 * Returns the the request date.
	 * 
	 * @return request date
	 */
	public Date getRequestDate() {
		return this.requestDate;
	}

	/**
	 * Returns the assigned user first name.
	 * 
	 * @return assigned user first name
	 */
	public String getAssignedUserFirstName() {
		return this.assignedUserFirstName;
	}

	/**
	 * Returns the assigned user last name.
	 * 
	 * @return assigned user last name
	 */
	public String getAssignedUserLastName() {
		return this.assignedUserLastName;
	}
	
	/**
	 * Returns the assigned user user name.
	 * 
	 * @return assigned user user name
	 */
	public String getAssignedUserUserName() {
		return this.assignedUserUserName;
	}

	/**
	 * Returns the expected completion date.
	 * 
	 * @return expected completion date
	 */
	public Date getExpectedCompletionDate() {
		return this.expectedCompletionDate;
	}

	/**
	 * Returns the offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns the offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return offenderMiddleName;
	}

	/**
	 * Returns the sentence date.
	 * 
	 * @return sentence date
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	
	
	/** 
	 * Returns the completed task count.
	 * 
	 * @return completed task count
	 */
	public Long getCompletedTaskCount() {
		return this.completedTaskCount;
	}
	
	/** 
	 * Returns the total task count.
	 * 
	 * @return total task count
	 */
	public Long getTotalTaskCount() {
		return this.totalTaskCount;
	}
	
	/** 
	 * Gets Submission date.
	 * 
	 * @return submission date
	 */
	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	/**
	 * Returns the status.
	 *
	 * @return status
	 */
	public PresentenceInvestigationRequestStatus getStatus() {
		return status;
	}
}