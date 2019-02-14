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
package omis.conviction.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.offense.domain.Offense;

/**
 * Fields for convictions.
 * 
 * <p>Note that amendments are not accounted for in these fields. Rather,
 * this is to be accounted for by the component using the fields. This is to
 * allow different modules to managed charges (the original offense) differently
 * according to module scope.
 * 
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ConvictionFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offense offense;
	
	private OffenseSeverity severity;
	
	private Integer counts;
	
	private Date date;
	
	private Boolean violentOffense;
	
	private Boolean sexualOffense;
	
	private Boolean paroleIneligible;
	
	private Boolean supervisedReleaseIneligible;
	
	/** Instantiates conviction fields. */
	public ConvictionFields() {
		// Default instantiation
	}
	
	/**
	 * Instantiates conviction fields from conviction.
	 * 
	 * @param conviction conviction
	 */
	public ConvictionFields(final Conviction conviction) {
		this.offense = conviction.getOffense();
		this.date = conviction.getDate();
		this.counts = conviction.getCounts();
		this.severity = conviction.getSeverity();
		if (conviction.getFlags() != null) {
			this.violentOffense = conviction.getFlags().getViolentOffense();
			this.sexualOffense = conviction.getFlags().getSexualOffense();
			this.paroleIneligible = conviction.getFlags().getParoleIneligible();
			this.supervisedReleaseIneligible
					= conviction.getFlags()
						.getSupervisedReleaseIneligible();
		}
	}
	
	/**
	 * Sets offense of which a person is convicted.
	 * 
	 * @param offense offense of which person is convicted
	 */
	public void setOffense(final Offense offense) {
		this.offense = offense;
	}
	
	/**
	 * Returns offense of which a person is convicted.
	 * 
	 * @return offense of which a person is convicted
	 */
	public Offense getOffense() {
		return this.offense;
	}
	
	/**
	 * Sets severity of offense.
	 * 
	 * @param severity severity of offense
	 */
	public void setSeverity(final OffenseSeverity severity) {
		this.severity = severity;
	}
	
	/**
	 * Returns severity of offense.
	 * 
	 * @return severity of offense
	 */
	public OffenseSeverity getSeverity() {
		return this.severity;
	}
	
	/**
	 * Sets counts.
	 * 
	 * @param counts counts
	 */
	public void setCounts(final Integer counts) {
		this.counts = counts;
	}
	
	/**
	 * Returns counts.
	 * 
	 * @return counts
	 */
	public Integer getCounts() {
		return this.counts;
	}
	
	/**
	 * Sets date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Sets whether a violent offense.
	 * 
	 * @param violentOffense whether violent offense
	 */
	public void setViolentOffense(final Boolean violentOffense) {
		this.violentOffense = violentOffense;
	}
	
	/**
	 * Returns whether a violent offense.
	 * 
	 * @return whether violent offense
	 */
	public Boolean getViolentOffense() {
		return this.violentOffense;
	}
	
	/**
	 * Sets whether a sexual offense.
	 * 
	 * @param sexualOffense whether sexual offense
	 */
	public void setSexualOffense(final Boolean sexualOffense) {
		this.sexualOffense = sexualOffense;
	}
	
	/**
	 * Returns whether a sexual offense.
	 * 
	 * @return whether sexual offense
	 */
	public Boolean getSexualOffense() {
		return this.sexualOffense;
	}
	
	/**
	 * Sets whether parole ineligible.
	 * 
	 * @param paroleIneligible whether parole ineligible
	 */
	public void setParoleIneligible(final Boolean paroleIneligible) {
		this.paroleIneligible = paroleIneligible;
	}
	
	/**
	 * Returns whether parole ineligible.
	 * 
	 * @return whether parole ineligible
	 */
	public Boolean getParoleIneligible() {
		return this.paroleIneligible;
	}
	
	/**
	 * Sets whether supervised release ineligible.
	 * 
	 * @param supervisedReleaseIneligible whether supervised release ineligible
	 */
	public void setSupervisedReleaseIneligible(
			final Boolean supervisedReleaseIneligible) {
		this.supervisedReleaseIneligible = supervisedReleaseIneligible;
	}
	
	/**
	 * Returns whether supervised release ineligible.
	 * 
	 * @return whether supervised release ineligible
	 */
	public Boolean getSupervisedReleaseIneligible() {
		return this.supervisedReleaseIneligible;
	}
}