package omis.courtdocument.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.document.domain.Document;

/** Implementation of court document association.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationImpl 
	implements CourtCaseDocumentAssociation {
	private static final long serialVersionUID = 1L;
	private static final String COURT_CASE_REQUIRED_MSG = 
			"Court case is required";
	private static final String DATE_REQUIRED_MSG = "Date is required";
	private static final String CATEGORY_REQUIRED_MSG = "Category is required";
	private static final String DOCUMENT_REQUIRED_MSG = "Document is required";
	private static final int[] HASHS = {3, 5, 7, 11};
	private Long id;	
	private CourtCase courtCase;
	private Date date;
	private CourtDocumentCategory courtDocumentCategory;
	private Document document;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
//--------------------------------Constructors----------------------------------
	/** Constructor. */
	public CourtCaseDocumentAssociationImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtCase getCourtCase() {
		return this.courtCase; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() { 
		return this.date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtDocumentCategory getCategory() {
		return this.courtDocumentCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature; 
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
//-----------------------------------Setters------------------------------------
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCourtCase(final CourtCase courtCase) { 
		this.courtCase = courtCase;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCategory(final CourtDocumentCategory category) { 
		this.courtDocumentCategory = category; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int x = 0;
		final int hash = HASHS[x] * this.getCategory().hashCode()
				+ HASHS[x++] * this.getDate().hashCode()
				+ HASHS[x++] * this.getDocument().hashCode();
		
		return hash;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		final boolean result;
		
		if (obj == this) {
			result = true;
		} else if (obj instanceof CourtCaseDocumentAssociation) {
			final CourtCaseDocumentAssociation that = 
					(CourtCaseDocumentAssociation) obj;
			
			if (this.getCategory().equals(that.getCategory())
					&& this.getDate().equals(that.getDate())
					&& this.getDocument().equals(that.getDocument())) {
				result = true;
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		
		return result;
	}
	
	/* Checks state. 
	 * @throws IllegalStateException - when required attributes are accounted
	 * for.*/
	private void checkState() {
		if (this.getCourtCase() == null) {
			throw new IllegalStateException(COURT_CASE_REQUIRED_MSG);
		}
		
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED_MSG);
		}
		
		if (this.getCategory() == null) {
			throw new IllegalStateException(CATEGORY_REQUIRED_MSG);
		}
		
		if (this.getDocument() == null) {
			throw new IllegalStateException(DOCUMENT_REQUIRED_MSG);
		}
	}
		
}
