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
package omis.sentence.calculator;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import omis.datatype.DateRange;
import omis.term.domain.component.Term;
import omis.term.service.delegate.TermCalculatorDelegate;

/**
 * Calculates sentence.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (1 March, 2019)
 * @since OMIS 3.0
 */
public class SentenceCalculator {
	
	private final TermCalculatorDelegate termCalculatorDelegate;
	
	private SentenceCalculator previous;
	
	private SentenceCalculator next;
	
	// Whether consecutive to *PREVIOUS* calculation
	private final boolean consecutive;
	
	private final Date offenseDate;
	
	private final Date sentenceDate;
	
	private final Date turnSelfInDate;
	
	private final Term prisonTerm;
	
	private final Term probationTerm;
	
	private final Date sentenceCommencementDate;
	
	private final int jailTime;

	private final int deadTime;	
	
	/**
	 * Instantiates sentence calculator.
	 * 
	 * @param termCalculatorDelegate delegate to calculate term
	 * @param consecutive whether consecutive to previous calculation
	 * @param offenseDate offense date
	 * @param sentenceDate sentence date
	 * @param turnSelfInDate turn self in date
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param sentenceCommencementDate sentence commencement date
	 * @param jailTime jail time
	 * @param deadTime dead time
	 */
	private SentenceCalculator(
			final TermCalculatorDelegate termCalculatorDelegate,
			final boolean consecutive,
			final Date offenseDate,
			final Date sentenceDate,
			final Date turnSelfInDate,
			final Term prisonTerm,
			final Term probationTerm,
			final Date sentenceCommencementDate,
			final Integer jailTime,
			final Integer deadTime) {
		this.termCalculatorDelegate = termCalculatorDelegate;
		this.consecutive = consecutive;
		this.offenseDate = offenseDate;
		this.sentenceDate = sentenceDate;
		this.turnSelfInDate = turnSelfInDate;
		this.prisonTerm = prisonTerm;
		this.probationTerm = probationTerm;
		this.sentenceCommencementDate = sentenceCommencementDate; 
		this.jailTime = jailTime;
		this.deadTime = deadTime;
	}
	
	/**
	 * Builder for sentence calculators.
	 * 
	 * <p>Build allows jail time credit to be set by calculating terms of
	 * start and end dates and by setting explicitly. If information is provided
	 * in both forms, the information is combined.
	 * 
	 * <p>Sentence calculators are built in order of dependency (the next
	 * calculator built, for instance, is consecutive to the previous when built
	 * consecutively).
	 */
	public static class Builder {
		
		private final TermCalculatorDelegate termCalculatorDelegate;
		
		// First builder in a chain - if this is first, first is set to this
		private final Builder first;
		
		/// 
		private final boolean consecutive;
		
		// Next builder in chain
		private Builder next;
		
		private Date offenseDate;
		
		private Date sentenceDate;
		
		private Date turnSelfInDate;
		
		private Term prisonTerm;
		
		private Term probationTerm;
		
		private Date sentenceCommencementDate;
		
		private Integer jailTime;
		
		private Integer deadTime;
		
		private final Set<InnerTerm> innerTerms = new HashSet<>();
		
		// Inner term such as a jail term or good time
		private static class InnerTerm {
			
			private final DateRange dateRange;
			
			private final boolean credit;
			
			/** Instantiates inner term. */
			public InnerTerm(final Date startDate, final Date endDate,
					final boolean credit) {
				this.dateRange = new DateRange(startDate, endDate);
				this.credit = credit;
			}
			
			/**
			 * Returns date range.
			 * 
			 * @return date range
			 */
			public DateRange getDateRange() {
				return this.dateRange;
			}
			
			/**
			 * Returns whether term is credited to sentence. If credited, it is
			 * removed from the total time, if not, it is added.
			 * 
			 * @return whether term is credited to sentence
			 */
			public boolean getCredit() {
				return this.credit;
			}
		}
		
		// Instantiates first builder - first built has first data member
		// set to itself, first builder cannot be consecutive as no previous
		// calculation will be generated
		private Builder(final TermCalculatorDelegate termCalculatorDelegate) {
			this.termCalculatorDelegate = termCalculatorDelegate;
			this.first = this;
			this.consecutive = false;
		}
		
		// Instantiates builder with first reference
		private Builder(final TermCalculatorDelegate termCalculatorDelegate,
				final Builder first, final boolean consecutive) {
			this.termCalculatorDelegate = termCalculatorDelegate;
			this.first = first;
			this.consecutive = consecutive;
		}
		
		/**
		 * Creates builder.
		 * 
		 * @param termCalculatorDelegate delegate for term calculator.
		 * @return builder
		 */
		public static Builder create(
				final TermCalculatorDelegate termCalculatorDelegate) {
			return new Builder(termCalculatorDelegate);
		}
		
		/**
		 * Sets offense date.
		 * 
		 * @param offense date
		 * @return {@code this}
		 */
		public Builder withOffenseDate(final Date offenseDate) {
			this.offenseDate = offenseDate;
			return this;
		}
		
		/**
		 * Sets sentence date.
		 *  
		 * @param sentenceDate sentence date
		 * @return {@code this}
		 */
		public Builder withSentenceDate(final Date sentenceDate) {
			this.sentenceDate = sentenceDate;
			return this;
		}
		
		/**
		 * Sets turn self in date.
		 * 
		 * @param turnSelfInDate turn self in date
		 * @return {@code this}
		 */
		public Builder withTurnSelfInDate(final Date turnSelfInDate) {
			this.turnSelfInDate = turnSelfInDate;
			return this;
		}
		
		/**
		 * Sets prison term. 
		 * 
		 * @param prisonTerm prison term
		 * @return {@code this}
		 */
		public Builder withPrisonTerm(final Term prisonTerm) {
			this.prisonTerm = prisonTerm;
			return this;
		}
		
		/**
		 * Sets probation term.
		 * 
		 * @param probationTerm probation term
		 * @return {@code this}
		 */
		public Builder withProbationTerm(final Term probationTerm) {
			this.probationTerm = probationTerm;
			return this;
		}
		
		/**
		 * Sets sentence commencement date.
		 * 
		 * @param sentenceCommencementDate sentence commencement date
		 * @return {@code this}
		 */
		public Builder withSentenceCommencementDate(
				final Date sentenceCommencementDate) {
			this.sentenceCommencementDate = sentenceCommencementDate;
			return this;
		}
		
		/**
		 * Sets jail time.
		 * 
		 * @param jailTime jail time
		 * @return {@code this}
		 */
		public Builder withJailTime(final Integer jailTime) {
			this.jailTime = jailTime;
			return this;
		}
		
		/**
		 * Sets dead time. 
		 * 
		 * @param deadTime dead time
		 * @return {@code this}
		 */
		public Builder withDeadTime(final Integer deadTime) {
			this.deadTime = deadTime;
			return this;
		}
		
		/**
		 * Adds jail term.
		 * 
		 * @param startDate start date
		 * @param endDate end date
		 * @return {@code this}
		 */
		public Builder withJailTerm(final Date startDate, final Date endDate) {
			this.innerTerms.add(new InnerTerm(startDate, endDate, true));
			return this;
		}
		
		// Calculates jail time
		private int calculateJailTime() {
			int jailTime = 0;
			if (this.jailTime != null) {
				jailTime += this.jailTime;
			}
			for (InnerTerm innerTerm : this.innerTerms) {
				if (innerTerm.getCredit()) {
					jailTime += DateRange.countDaysInclusively(
							innerTerm.getDateRange().getStartDate(),
							innerTerm.getDateRange().getEndDate());
				}
			}
			return jailTime;
		}
		
		// Calculates dead time
		private Integer calculateDeadTime() {
			if (this.deadTime != null) {
				return this.deadTime;
			} else {
				return 0;
			}
		}
		
		/**
		 * Builds and returns sentence calculator chain.
		 * 
		 * <p>Can be invoked on any builder in chain - returns first calculator
		 * in chain.
		 * 
		 * @return first calculator in chain
		 */
		public SentenceCalculator build() {
			return this.buildImpl();
		}
		
		// Implementation of builder method
		private SentenceCalculator buildImpl() {
			Builder currentBuilder = this.first;
			SentenceCalculator first = null;
			SentenceCalculator current = null;
			while (currentBuilder != null) {
				SentenceCalculator previous = current;
				current = new SentenceCalculator(
					currentBuilder.termCalculatorDelegate,
					currentBuilder.consecutive,
					currentBuilder.offenseDate, currentBuilder.sentenceDate,
					currentBuilder.turnSelfInDate,
					currentBuilder.prisonTerm, currentBuilder.probationTerm,
					currentBuilder.sentenceCommencementDate,
					currentBuilder.calculateJailTime(),
					currentBuilder.calculateDeadTime());
				if (previous != null) {
					current.previous = previous;
					previous.next = current;
				}
				if (first == null) {
					first = current;
				}
				currentBuilder = currentBuilder.next;
			};
			return first;
		}
		
		// Implementation of iterator
		private Iterator<SentenceCalculator> iteratorImpl() {
			return new Iterator<SentenceCalculator>() {

				private SentenceCalculator current = Builder.this.buildImpl();
				
				/** {@inheritDoc} */
				@Override
				public boolean hasNext() {
					return this.current != null;
				}

				/** {@inheritDoc} */
				@Override
				public SentenceCalculator next() {
					SentenceCalculator current = this.current;
					this.current = this.current.next;
					return current;
				}
			};
		}

		/**
		 * Returns an iterable chain of sentence calculators.
		 * 
		 * @return iterable chain of sentence calculators
		 */
		public Iterable<SentenceCalculator> iterable() {
			return new Iterable<SentenceCalculator>() {

				/** {@inheritDoc} */
				@Override
				public Iterator<SentenceCalculator> iterator() {
					return Builder.this.iteratorImpl();
				}
			};
		}
		
		/**
		 * Creates a new builder and makes it the next builder of {@code this}.
		 * 
		 * <p>The builder represents a consecutive sentence to {@code this}.
		 * 
		 * @return next builder consecutive to {@code this}
		 */
		public Builder nextConsecutive() {
			Builder next = new Builder(
					this.termCalculatorDelegate, this.first, true);
			this.next = next;
			return next;
		}
		
		/**
		 * Creates a new builder and makes it the next builder of {@code this}.
		 * 
		 * <p>The builder represents a concurrent sentence to {@code this}.
		 * 
		 * @return next builder concurrent to {@code this}
		 */
		public Builder nextConcurrent() {
			Builder next = new Builder(
					this.termCalculatorDelegate, this.first, false);
			this.next = next;
			return next;
		}
	}
	
	/**
	 * Calculates and returns prison discharge date.
	 * 
	 * @return prison discharge date
	 */
	public Date calculatePrisonDischargeDate() {
		return this.calculateTermDischargeDate(this.prisonTerm,
				this.sentenceCommencementDate);
	}
	
	/**
	 * Calculates and returns probation discharge date.
	 * 
	 * @return probation discharge date
	 * @throws IllegalStateException if insufficient information is supplied
	 * to perform the calculation
	 */
	public Date calculateProbationDischargeDate() {
		
		// Assumes sentence commencement date of first calculation is set and
		// uses it
		// This assumption might require a check prior to beginning the
		// calculation
		Date prisonDischargeDate;
		if (this.sentenceCommencementDate != null) {
			prisonDischargeDate = this.sentenceCommencementDate;
		} else if (this.turnSelfInDate != null) {
			prisonDischargeDate = this.turnSelfInDate;
		} else {
			throw new IllegalStateException(
				"Turn self in date required to calculate probation discharge"
				+ " date");
		}
		
		// Calculates prison discharge date - this might eventually be put into
		// and implementation function and used in calculatePrisonDischargeDate
		// also - SA
		SentenceCalculator current = this;
		while (current != null) {
			if (!current.isInitial()
					&& current.sentenceCommencementDate != null) {
				
				// If supplied, uses commencement date of subsequence (not
				// initial) calculations for prison discharge date
				prisonDischargeDate = current.sentenceCommencementDate;
			} else if (current.isConsecutive()) {
				
				// If commencement date is not supplied, uses previous prison
				// discharge date and add jail time
				prisonDischargeDate = this.termCalculatorDelegate
							.addDays(-current.jailTime, prisonDischargeDate);
			}
			if (current.isConsecutive() || current.isInitial()) {
				
				// Adds term to prison discharge date when initial or
				// consecutive
				prisonDischargeDate
					= this.calculateTermDischargeDate(current.prisonTerm,
							prisonDischargeDate);
				current = current.next;
			} else {
				
				// TODO Implement concurrent calculation - SA
				throw new UnsupportedOperationException(
						"Concurrent sentence calculators not yet implemented");
			}
		}
		
		// Calculates probation discharge date of this sentence starting on
		// prison discharge date
		Date probationDischargeDate
			= this.calculateTermDischargeDate(this.probationTerm,
					prisonDischargeDate);
		return probationDischargeDate;
	}

	/**
	 * Calculates and returns prison days.
	 * 
	 * @return prison days
	 */
	public int calculatePrisonDays() {
		return this.termCalculatorDelegate.calculateTotalDays(this.prisonTerm);
	}

	/**
	 * Calculates and returns probation days.
	 * 
	 * @return probation days
	 */
	public int calculateProbationDays() {
		return this.termCalculatorDelegate.calculateTotalDays(
				this.probationTerm);
	}
	
	// Offense date might not be required
	
	/**
	 * Returns offense date.
	 * 
	 * @return offense date
	 */
	public Date getOffenseDate() {
		return this.offenseDate;
	}
	
	// Sentence date might not be required
	
	/**
	 * Returns sentence date.
	 * 
	 * @return sentence date
	 */
	public Date getSentenceDate() {
		return this.sentenceDate;
	}
	
	/**
	 * Returns turn self in date.
	 * 
	 * <p>If provided, this is used as the sentence commencement date over
	 * calculations but not the sentence commencement date itself if set.
	 * 
	 * @return turn self in date.
	 */
	public Date getTurnSelfInDate() {
		return this.turnSelfInDate;
	}
	
	/**
	 * Returns prison term.
	 * 
	 * @return prison term
	 */
	public Term getPrisonTerm() {
		return this.prisonTerm;
	}
	
	/**
	 * Returns probation term.
	 * 
	 * @return probation term
	 */
	public Term getProbationTerm() {
		return this.probationTerm;
	}
	
	/**
	 * Returns sentence commencement date.
	 * 
	 * @return sentence commencement date
	 */
	public Date getSentenceCommencementDate() {
		return this.sentenceCommencementDate;
	}
	
	/**
	 * Returns jail time.
	 * 
	 * @return jail time
	 */
	public int getJailTime() {
		return this.jailTime;
	}
	
	/**
	 * Returns dead time.
	 * 
	 * @return dead time
	 */
	public int getDeadTime() {
		return this.deadTime;
	}
	
	/**
	 * Returns whether consecutive to previous sentence.
	 * 
	 * @return whether consecutive to previous sentence
	 */
	public boolean isConsecutive() {
		return this.previous != null && this.consecutive;
	}
	
	/**
	 * Returns whether concurrent to previous sentence.
	 * 
	 * @return whether concurrent to previous sentence
	 */
	public boolean isConcurrent() {
		return this.previous != null && !this.consecutive;
	}
	
	/**
	 * Returns whether initial sentence.
	 * 
	 * @return whether initial sentence
	 */
	public boolean isInitial() {
		return this.previous == null;
	}
	
	/* Helper methods. */
	
	// Calculates discharge date of term
	private Date calculateTermDischargeDate(
			final Term term, final Date commencementDate) {
		int totalDays = this.termCalculatorDelegate.calculateTotalDays(term); 
		return this.termCalculatorDelegate.addDays(
				totalDays, commencementDate);
	}

	/**
	 * Returns next calculator.
	 * 
	 * @return next calculator
	 */
	public SentenceCalculator next() {
		return this.next;
	}
}