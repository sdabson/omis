package omis.commitstatus.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.commitstatus.domain.CommitStatus;

/**
 * Commit status form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Jun 6, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private CommitStatus commitStatus;
	private Date startDate;
	private Date endDate;
	
	/**
	 * Instantiates a default instance of commit status form.
	 */
	public CommitStatusForm() {
		//Default constructor.
	}

	public CommitStatus getCommitStatus() {
		return commitStatus;
	}

	public void setCommitStatus(CommitStatus commitStatus) {
		this.commitStatus = commitStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}