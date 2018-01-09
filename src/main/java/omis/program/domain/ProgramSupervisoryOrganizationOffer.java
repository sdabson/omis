package omis.program.domain;

import java.io.Serializable;

import omis.supervision.domain.SupervisoryOrganization;

/**
 * Offers program by supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 11, 2016)
 * @since OMIS 3.0
 */
public interface ProgramSupervisoryOrganizationOffer
		extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets program offered by supervisory organization.
	 * 
	 * @param program program offered by supervisory organization
	 */
	void setProgram(Program program);
	
	/**
	 * Returns program offered by supervisory organization.
	 * 
	 * @return program offered by supervisory organization
	 */
	Program getProgram();
	
	/**
	 * Sets supervisory organization that offers program.
	 * 
	 * @param supervisoryOrganization supervisory organization that offers
	 * program
	 */
	void setSupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Returns supervisory organization that offers program.
	 * 
	 * @return supervisory organization that offers program
	 */
	SupervisoryOrganization getSupervisoryOrganization();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}