package omis.personphoto.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Web controller for photos of people.
 *  
 * @author Stephen Abson
 * @version 0.1.1 (Feb 8, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/personPhoto")
@PreAuthorize("hasRole('USER')")
public class PersonPhotoController {
	
	/** Instantiates a default controller for person photos. */
	public PersonPhotoController() {
		// Default instantiation
	}
}