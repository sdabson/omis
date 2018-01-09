package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationRequestNoteDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;

/**
 * PresentenceInvestigationRequestNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 16, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<PresentenceInvestigationRequestNote>
	implements PresentenceInvestigationRequestNoteDao {
	
	public static final String
		FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTE_QUERY_NAME =
			"findPresentenceInvestigationRequestNote";
	
	public static final String
	FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTE_EXCLUDING_QUERY_NAME =
		"findPresentenceInvestigationRequestNoteExcluding";
	
	public static final String
	FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTES_BY_REQUEST_QUERY_NAME =
		"findPresentenceInvestigationRequestNotesByPresentenceInvestigationRequest";
	
	public static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	public static final String DESCRIPTION_PARAM_NAME = "description";
	
	public static final String DATE_PARAM_NAME = "date";
	
	public static final String PRESENTENCE_INVESTIGATION_REQUEST_NOTE_PARAM_NAME =
			"presentenceInvestigationRequestNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationRequestNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestNote find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description, final Date date) {
		PresentenceInvestigationRequestNote note =
				(PresentenceInvestigationRequestNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTE_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestNote findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description, final Date date,
			final PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNoteExcluding) {
		PresentenceInvestigationRequestNote note =
				(PresentenceInvestigationRequestNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_NOTE_PARAM_NAME, 
						presentenceInvestigationRequestNoteExcluding)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationRequestNote>
		findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationRequestNote> notes =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_PRESENTENCE_INVESTIGATION_REQUEST_NOTES_BY_REQUEST_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.list();
		
		return notes;
	}

}
