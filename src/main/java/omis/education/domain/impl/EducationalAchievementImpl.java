package omis.education.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;
import omis.education.domain.EducationalAchievementCategory;

/**
 * EducationalAchievementImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementImpl implements EducationalAchievement {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Date date;
	
	private String description;
	
	private EducationalAchievementCategory category;
	
	private EducationTerm educationTerm;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	public EducationalAchievementImpl(){
		//Default Constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievementCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public EducationTerm getEducationTerm() {
		return this.educationTerm;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(EducationalAchievementCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public void setEducationTerm(EducationTerm educationTerm) {
		this.educationTerm = educationTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof EducationalAchievement)){
			return false;
		}
		
		EducationalAchievement that = (EducationalAchievement) obj;
		
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		if(this.getEducationTerm() == null){
			throw new IllegalStateException("Education Term required.");
		}
		
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (!this.getEducationTerm().equals(that.getEducationTerm())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getEducationTerm() == null){
			throw new IllegalStateException("Education Term required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getEducationTerm().hashCode();
	
		return hashCode;
	}
}
