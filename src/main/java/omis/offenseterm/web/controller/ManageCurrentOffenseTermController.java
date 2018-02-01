/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.offenseterm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.conviction.domain.Conviction;
import omis.offenseterm.service.CurrentOffenseService;
import omis.person.domain.Person;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to manage current offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/offenseTerm")
public class ManageCurrentOffenseTermController {
	
	/* Redirects. */
	
	private static final String REDIRECT = "redirect:/offenseTerm"
			+ "/listCurrentOffenses.html?person=%d";
	
	/* Message keys. */
	
	private static final String CONNECTED_SENTENCE_EXISTS_MESSAGE_KEY
		= "currentOffenseTerm.connectedSentenceExists";
	
	/* Error bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.offenseterm.msgs.form";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("convictionPropertyEditorFactory")
	private PropertyEditorFactory convictionPropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("currentOffenseService")
	private CurrentOffenseService currentOffenseService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	/* Constructors. */
	
	/** Instantiates controller to manage current offense terms. */
	public ManageCurrentOffenseTermController() {
		// Default instantiation
	}

	/**
	 * Removes current offense.
	 * 
	 * <p>This includes conviction and all sentences of conviction.
	 * 
	 * @param conviction conviction
	 * @return redirect to listing screen
	 * @throws ConnectedSentenceExistsException if the conviction has an
	 * associated active sentence that is connected to by other sentences
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/removeCurrentOffense.html",
			method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction)
						throws ConnectedSentenceExistsException {
		Person person = conviction.getCourtCase().getDocket().getPerson();
		this.currentOffenseService.remove(conviction);
		return new ModelAndView(String.format(REDIRECT, person.getId()));
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code ConnectedSentenceExistsException}.
	 * 
	 * @param connectedSentenceExistsException exception thrown
	 * @return handler for {@code ConnectedSentenceExistsException}
	 */
	@ExceptionHandler
	public ModelAndView handleConnectedSentenceExistsException(
			final ConnectedSentenceExistsException
				connectedSentenceExistsException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(
						CONNECTED_SENTENCE_EXISTS_MESSAGE_KEY,
						ERROR_BUNDLE_NAME,
						connectedSentenceExistsException);
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder data binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Conviction.class,
				this.convictionPropertyEditorFactory.createPropertyEditor());
	}
}