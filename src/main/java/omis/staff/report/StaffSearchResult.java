package omis.staff.report;

import omis.search.report.PersonSearchResult;

public class StaffSearchResult extends PersonSearchResult {
	private static final long serialVersionUID = 1L;
	final private Long staffId;
	final private String titleName;
	final private String organizationName;


	/** Constructor. */
	public StaffSearchResult(final Long staffId, final String titleName,
			final String organizationName, final Long nameId,
			final Long personId, final String firstName,
			final String middleName, final String lastName, 
			final String suffixName) {
		super(nameId, personId, firstName, middleName, lastName, suffixName);
		this.staffId = staffId;
		this.titleName = titleName;
		this.organizationName = organizationName;
	}

	/** Gets staff id.
	 * @return staffId. */
	public Long getStaffId() { return this.staffId; }

	/** Gets title name.
	 * @return title name. */
	public String getTitleName() { return this.titleName; }

	/** Gets organization name.
	 * @return organization name. */
	public String getOrganizationName() { return this.organizationName; }
}
