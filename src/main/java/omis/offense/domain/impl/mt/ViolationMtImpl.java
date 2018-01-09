package omis.offense.domain.impl.mt;

import omis.offense.domain.Violation;

/**
 * Montana specific implementation of violation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2013)
 * @since OMIS 3.0
 */
public class ViolationMtImpl
		implements Violation {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String code;
	
	private String title;
	
	private String chapter;
	
	private String subChapter;

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setCode(final String code) {
		this.code = code;
	}

	/** {@inheritDoc} */
	@Override
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Sets the title.
	 * 
	 * @param title title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/**
	 * Returns the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Sets the chapter.
	 * 
	 * @param chapter chapter
	 */
	public void setChapter(final String chapter) {
		this.chapter = chapter;
	}

	/**
	 * Returns the chapter.
	 * 
	 * @return chapter
	 */
	public String getChapter() {
		return this.chapter;
	}
	
	/**
	 * Sets the sub chapter.
	 * 
	 * @param subChapter sub chapter
	 */
	public void setSubChapter(final String subChapter) {
		this.subChapter = subChapter;
	}
	
	/**
	 * Returns the sub chapter.
	 * 
	 * @return sub chapter
	 */
	public String getSubChapter() {
		return this.subChapter;
	}
}