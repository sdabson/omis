package omis.need.report;

import java.util.Date;

import omis.need.domain.ObjectiveSource;

/**
 * Case plan objective summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 10, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveSummary {

	private final Long id;
	
	private final String objectiveName;
	
	private final ObjectiveSource source;
	
	private final Date date;
	
	private final String needDomainName;
	
	/**
	 * Instantiates an instance of case plan objective summary.
	 * 
	 * @param id case plan objective id
	 * @param objectiveName objective name
	 * @param source objective source
	 * @param date date
	 * @param needDomainName need domain name
	 */
	public CasePlanObjectiveSummary(final Long id,
			final String objectiveName, final ObjectiveSource source,
			final Date date, final String needDomainName) {
		this.id = id;
		this.objectiveName = objectiveName;
		this.source = source;
		this.date = date;
		this.needDomainName = needDomainName;
	}

	/**
	 * Returns the case plan objective id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the objective name.
	 * 
	 * @return objective name
	 */
	public String getObjectiveName() {
		return this.objectiveName;
	}

	/**
	 * Returns the objective source.
	 * 
	 * @return objective source
	 */
	public ObjectiveSource getSource() {
		return this.source;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns the need domain name.
	 * 
	 * @return need domain name
	 */
	public String getNeedDomainName() {
		return this.needDomainName;
	}
}