package omis.courtdocument.web.form;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.document.web.form.DocumentForm;
import omis.document.web.form.DocumentTagItem;

/** Court document association form.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationForm implements DocumentForm {
	private static final long serialVersionUID = 1L;
	private byte[] data;
	private String title;
	private CourtCase courtCase;
	private Date date;
	private CourtDocumentCategory courtDocumentCategory;
	private String fileExtension;
	private List<DocumentTagItem> documentTagItems;
	
	/** Constructor. */
	public CourtCaseDocumentAssociationForm() { }
	
	/** Gets court case.
	 * @return court case. */
	public CourtCase getCourtCase() {
		return this.courtCase; 
	}
	
	/**{@inheritDoc} */
	@Override
	public Date getDate() { 
		return this.date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public byte[] getData() {
		return this.data;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/** Gets category.
	 * @return category. */
	public CourtDocumentCategory getCourtDocumentCategory() { 
		return this.courtDocumentCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFileExtension() {
		return this.fileExtension;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setData(final byte[] data) {
		this.data = data;
	}
	
	/** Sets court case. 
	 * @param courtCase - court case. */
	public void setCourtCase(final CourtCase courtCase) {
		this.courtCase = courtCase;
	}
	
	/** Sets date.
	 * @param date - date. */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/** Sets court case document category.
	 * @param courtDocumentCategory - court document category. */
	public void setCourtDocumentCategory(
			final CourtDocumentCategory courtDocumentCategory) {
		this.courtDocumentCategory = courtDocumentCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}
}
