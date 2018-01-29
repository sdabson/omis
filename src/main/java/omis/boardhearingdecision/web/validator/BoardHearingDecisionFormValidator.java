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
package omis.boardhearingdecision.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.boardhearingdecision.web.form.BoardHearingDecisionForm;
import omis.boardhearingdecision.web.form.BoardMemberDecisionItem;
import omis.boardhearingdecision.web.form.HearingDecisionNoteItem;
import omis.boardhearingdecision.web.form.HearingDecisionNoteItemOperation;

/**
 * @author Josh Divine
 * @version 0.1.0 (Jan 23, 2018)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionFormValidator implements Validator {

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return BoardHearingDecisionForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		BoardHearingDecisionForm form = (BoardHearingDecisionForm) target;
		
		if (form.getCategory() == null) {
			errors.rejectValue("category", 
					"boardHearingDecision.category.empty");
		}
		
		if (form.getBoardMemberDecisionItems() != null) {
			for (int i = 0; i < form.getBoardMemberDecisionItems().size(); i++) {
				BoardMemberDecisionItem decisionItem = 
						form.getBoardMemberDecisionItems().get(i);
				if (decisionItem.getCategory() == null) {
					errors.rejectValue("boardMemberDecisionItems[" + i + 
							"].category", "boardMemberDecision.category.empty");
				}
				if (decisionItem.getDecisionReason() == null) {
					errors.rejectValue("boardMemberDecisionItems[" + i + 
							"].decisionReason", 
							"boardMemberDecision.decisionReason.empty");
				}
			}
		}
		if (form.getHearingDecisionNoteItems() != null) {
			for (int i = 0; i < form.getHearingDecisionNoteItems().size(); i++) {
				HearingDecisionNoteItem noteItem = 
						form.getHearingDecisionNoteItems().get(i);
				if (noteItem.getOperation() != null && 
						!HearingDecisionNoteItemOperation.REMOVE.equals(
								noteItem.getOperation())) {
					if (noteItem.getDate() == null) {
						errors.rejectValue("hearingDecisionNoteItems[" + i + 
								"].date", "hearingDecisionNote.date.empty");
					}
					if (noteItem.getValue() == null || 
							noteItem.getValue().isEmpty()) {
						errors.rejectValue("hearingDecisionNoteItems[" + i + 
								"].value", 
								"hearingDecisionNote.description.empty");
					}
				}
			}
		}
	}

}
