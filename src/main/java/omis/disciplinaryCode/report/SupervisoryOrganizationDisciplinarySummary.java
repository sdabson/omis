package omis.disciplinaryCode.report;

import java.io.Serializable;

/**
 * SupervisoryOrganizationDisciplinarySummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class SupervisoryOrganizationDisciplinarySummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long organizationId;
	
	private final String name;

	/**
	 * Constructor
	 * @param organizationId - organization id
	 * @param name - name
	 */
	public SupervisoryOrganizationDisciplinarySummary(final Long organizationId,
			final String name) {
		this.organizationId = organizationId;
		this.name = name;
	}

	/**
	 * Returns the organization's id
	 * @return the organizationId
	 */
	public Long getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * Returns the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
