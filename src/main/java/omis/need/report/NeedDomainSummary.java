package omis.need.report;

import java.io.Serializable;

/**
 * Need domain summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 13, 2015)
 * @since OMIS 3.0
 */
public class NeedDomainSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	final private Long id;
	
	final private String domainName;
	
	final private Long objectiveCount;
	
	final private String priorityName;
	
	final private Boolean criminogenic;
	
	/**
	 * Instantiates an instance of need domain summary.
	 * 
	 * @param id id
	 * @param domainName domain name
	 * @param objectiveCount objective count
	 * @param priorityName priority name
	 * @param criminogenic criminogenic
	 */
	public NeedDomainSummary(final Long id, final String domainName,
			final Long objectiveCount, final String priorityName,
			final Boolean criminogenic) {
		this.id = id;
		this.domainName = domainName;
		this.objectiveCount = objectiveCount;
		this.priorityName = priorityName;
		this.criminogenic = criminogenic;
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the domain name.
	 * 
	 * @return domain name
	 */
	public String getDomainName() {
		return this.domainName;
	}

	/**
	 * Returns the objective count.
	 * 
	 * @return objective count
	 */
	public Long getObjectiveCount() {
		return this.objectiveCount;
	}

	/**
	 * Returns the priority name.
	 *  
	 * @return priority name
	 */
	public String getPriorityName() {
		return this.priorityName;
	}

	/**
	 * Returns whether criminogenic applies.
	 * 
	 * @return criminogenic
	 */
	public Boolean getCriminogenic() {
		return this.criminogenic;
	}
}