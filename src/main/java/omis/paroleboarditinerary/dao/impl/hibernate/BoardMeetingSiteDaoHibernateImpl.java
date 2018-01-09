package omis.paroleboarditinerary.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.paroleboarditinerary.dao.BoardMeetingSiteDao;
import omis.paroleboarditinerary.domain.BoardMeetingSite;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;

/**
 * Hibernate implementation of the board meeting site data access object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 20, 2017)
 * @since OMIS 3.0
 */
public class BoardMeetingSiteDaoHibernateImpl 
		extends GenericHibernateDaoImpl<BoardMeetingSite>
		implements BoardMeetingSiteDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findBoardMeetingSite";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findBoardMeetingSiteExcluding";
	
	private static final String FIND_BY_BOARD_ITINERARY_PARAM_NAME =
			"findBoardMeetingSiteByBoardItinerary";
	
	/* Parameters. */
	
	private static final String BOARD_ITINERARY_PARAM_NAME = "boardItinerary";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String ORDER_PARAM_NAME = "order";
	
	private static final String EXCLUDED_BOARD_MEETING_SITE_PARAM_NAME = 
			"excludedBoardMeetingSite";

	/* Property names. */
	
	private static final String ORDER_PROPERTY_NAME = "order";
	
	/** Instantiates a hibernate implementation of the data access object for 
	 *  board meeting site.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BoardMeetingSiteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public BoardMeetingSite find(final ParoleBoardItinerary boardItinerary, 
			final Location location, final Date date, final Integer order) {
		BoardMeetingSite meetingSite = (BoardMeetingSite) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(ORDER_PARAM_NAME, order, 
						this.getEntityPropertyType(ORDER_PROPERTY_NAME))
				.uniqueResult();
		return meetingSite;
	}

	/** {@inheritDoc} */
	@Override
	public BoardMeetingSite findExcluding(
			final ParoleBoardItinerary boardItinerary, final Location location, 
			final Date date, final Integer order, 
			final BoardMeetingSite excludedSite) {
		BoardMeetingSite meetingSite = (BoardMeetingSite) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(ORDER_PARAM_NAME, order, 
						this.getEntityPropertyType(ORDER_PROPERTY_NAME))
				.setParameter(EXCLUDED_BOARD_MEETING_SITE_PARAM_NAME, 
						excludedSite)
				.uniqueResult();
		return meetingSite;
	}

	/** {@inheritDoc} */
	@Override
	public List<BoardMeetingSite> findBoardMeetingSitesByBoardItinerary(
			final ParoleBoardItinerary boardItinerary) {
		@SuppressWarnings("unchecked")
		List<BoardMeetingSite> meetingSites = (List<BoardMeetingSite>) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_BOARD_ITINERARY_PARAM_NAME)
				.setParameter(BOARD_ITINERARY_PARAM_NAME, boardItinerary)
				.list();
		return meetingSites;
	}
}
