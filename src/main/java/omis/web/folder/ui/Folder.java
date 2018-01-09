package omis.web.folder.ui;

/**
 * Folder.
 * 
 * @author Jason Nelson
 * @author Ryan Johns
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (June 17, 2013)
 * @since OMIS 3.0
 *
 */
public final class Folder {
	
	private final String label;
	private final String page;
	
	/**
	 * Instantiates a folder with the specified label and page.
	 * @param label label
	 * @param page page
	 */
	public Folder(final String label, final String page) {
		this.label = label;
		this.page = page;
	}

	/** Returns the label.
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}

	/** Returns the page.
	 * @return page
	 */
	public String getPage() {
		return this.page;
	}

}
