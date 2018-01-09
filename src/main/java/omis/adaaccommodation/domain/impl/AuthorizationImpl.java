package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;

/**
 * Implementation of ADA authorization.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public class AuthorizationImpl
	implements Authorization {
	
	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private AuthorizationSourceCategory authorizationSource;
	
	private Accommodation accommodation;
	
	private String comments;
	
	private AuthorizationSignature authorizationSignature;
	
	private DateRange authorizationTerm;

	/** Instantiates an implementation of the ADA authorization. */
	public AuthorizationImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

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
	public void setAuthorizationSource(
			final AuthorizationSourceCategory authorizationSource) {
		this.authorizationSource = authorizationSource;
	}

	/** {@inheritDoc} */
	@Override
	public AuthorizationSourceCategory getAuthorizationSource() {
		return this.authorizationSource;
	}

	/** {@inheritDoc} */
	@Override
	public void setAccommodation(final Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizationSignature(
			final AuthorizationSignature authorizationSignature) {
		this.authorizationSignature = authorizationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public AuthorizationSignature getAuthorizationSignature() {
		return this.authorizationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizationTerm(final DateRange authorizationTerm) {
		this.authorizationTerm = authorizationTerm;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getAuthorizationTerm() {
		return this.authorizationTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Authorization)) {
			return false;
		}
		Authorization that = (Authorization) obj;
		if (this.getAuthorizationSource() == null) {
			throw new IllegalStateException("Authorization source required");
		}
		if (!this.getAuthorizationSource().equals(
				that.getAuthorizationSource())) {
			return false;
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		if (!this.getAccommodation().equals(that.getAccommodation())) {
			return false;
		}
		//may need to break down by person and date
		if (this.getAuthorizationSignature() == null) {
			throw new IllegalStateException("Authorization signature required");
		}
		if (!this.getAuthorizationSignature().equals(
				that.getAuthorizationSignature())) {
			return false;
		}
		if (this.getAuthorizationTerm().getStartDate() == null) {
			throw new IllegalStateException(
					"Authorization term start date required");
		}
		if (this.getAuthorizationTerm() != null) {
			if (that.getAuthorizationTerm() != null) {
				if (this.getAuthorizationTerm().getStartDate() != null) {
					if (!this.getAuthorizationTerm().getStartDate().equals(
							that.getAuthorizationTerm().getStartDate())) {
						return false;
					}
				} else if (that.getAuthorizationTerm().getStartDate() != null) {
					return false;
				}
				if (this.getAuthorizationTerm().getEndDate() != null) {
					if (!this.getAuthorizationTerm().getEndDate().equals(
							that.getAuthorizationTerm().getEndDate())) {
						return false;
					}
				} else if (that.getAuthorizationTerm().getEndDate() != null) {
					return false;
				}
			} else {
				return false;
			}
		} else if (that.getAuthorizationTerm() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAuthorizationSource() == null) {
			throw new IllegalStateException("Authorization source required");
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}		
		if (this.getAuthorizationSignature() == null) {
			throw new IllegalStateException("Authorization signature required");
		}
		if (this.getAuthorizationTerm().getStartDate() == null) {
			throw new IllegalStateException(
					"Authorization term start date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAuthorizationSource().hashCode();
		hashCode = 29 * hashCode + this.getAccommodation().hashCode();
		hashCode = 29 * hashCode + this.getAuthorizationSignature().hashCode();
		if (this.getAuthorizationTerm() != null) {
			if (this.getAuthorizationTerm().getStartDate() != null) {
				hashCode = 29 * hashCode + this.getAuthorizationTerm()
						.getStartDate().hashCode();
			}
			if (this.getAuthorizationTerm().getEndDate() != null) {
				hashCode = 31 * hashCode + this.getAuthorizationTerm()
						.getEndDate().hashCode();
			}
		}
		return hashCode;
	}
}