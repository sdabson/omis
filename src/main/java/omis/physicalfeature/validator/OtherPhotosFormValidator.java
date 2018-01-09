package omis.physicalfeature.validator;

import omis.physicalfeature.domain.ProcessStatus;
import omis.physicalfeature.web.form.OtherPhotosForm;
import omis.physicalfeature.web.form.OtherPhysicalFeaturePhotoAssociationItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for other photos form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 7, 2014)
 * @since OMIS 3.0
 */
public class OtherPhotosFormValidator implements Validator {
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OtherPhotosForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OtherPhotosForm form = (OtherPhotosForm) target;
		int countOPFPIA = 0;
		for (OtherPhysicalFeaturePhotoAssociationItem item 
				: form.getoPFPAItems()) {
			if (item.getStatus() != null && item.getStatus()
					.equals(ProcessStatus.ASSOCIATE)) {
				countOPFPIA++;
			}
		}
		if (form.getPhysicalFeatureAssociation() == null) {
			if (countOPFPIA > 0) {
				errors.rejectValue("physicalFeatureAssociation", 
						"opf.physicalFeatureAssociation.empty");
			}
		} else {
			if (countOPFPIA < 1) {
				errors.rejectValue("oPFPAItems", 
						"opf.OPFPPIAItems.associate.empty");
			}
		}
	}
}