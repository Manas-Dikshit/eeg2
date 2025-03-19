package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Digitization;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Honza
 * Date: 24.2.12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDigitizationDao extends SimpleGenericDao<Digitization, Integer> implements DigitizationDao {

    public SimpleDigitizationDao() {
        super(Digitization.class);
    }

    @Override
    public Digitization getDigitizationByParams(float samplingRate, float gain, String filter) {
        Session session = getSessionFactory().getCurrentSession();
        String hqlQuery = "FROM Digitization d WHERE d.samplingRate = :samplingRate AND d.gain = :gain AND d.filter = :filter";
        Query<Digitization> query = session.createQuery(hqlQuery, Digitization.class);
        query.setParameter("samplingRate", samplingRate);
        query.setParameter("gain", gain);
        query.setParameter("filter", filter);
        return query.uniqueResult(); // Returns a single result or null
    }

    @Override
    public void createGroupRel(Digitization persistent, ResearchGroup researchGroup) {
        persistent.getResearchGroups().add(researchGroup);
        researchGroup.getDigitizations().add(persistent);
    }

    @Override
    public List<Digitization> getItemsForList() {
        Session session = getSessionFactory().getCurrentSession();
        String hqlQuery = "FROM Digitization dig ORDER BY dig.samplingRate";
        return session.createQuery(hqlQuery, Digitization.class).list();
    }

    @Override
    public List<Digitization> getRecordsByGroup(int groupId) {
        Session session = getSessionFactory().getCurrentSession();
        String hqlQuery = "SELECT dig FROM Digitization dig JOIN FETCH dig.researchGroups rg WHERE rg.researchGroupId = :groupId";
        Query<Digitization> query = session.createQuery(hqlQuery, Digitization.class);
        query.setParameter("groupId", groupId);
        return query.list();
    }

    @Override
    public boolean canDelete(int id) {
        Session session = getSessionFactory().getCurrentSession();
        String hqlQuery = "SELECT dig.experiments FROM Digitization dig WHERE dig.digitizationId = :id";
        Query<List<?>> query = session.createQuery(hqlQuery, List.class);
        query.setParameter("id", id);
        List<?> experiments = query.uniqueResult();
        return (experiments == null || experiments.isEmpty()); // Safe check to prevent issues
    }

    @Override
    public boolean hasGroupRel(int id) {
        Session session = getSessionFactory().getCurrentSession();
        String hqlQuery = "FROM Digitization dig WHERE dig.digitizationId = :id";
        Query<Digitization> query = session.createQuery(hqlQuery, Digitization.class);
        query.setParameter("id", id);
        List<Digitization> list = query.list();
        
        // Ensure we check for an empty list before accessing the first element
        return !list.isEmpty() && !list.get(0).getResearchGroups().isEmpty();
    }

    @Override
    public void deleteGroupRel(Digitization persistent, ResearchGroup researchGroup) {
        persistent.getResearchGroups().remove(researchGroup);
        researchGroup.getDigitizations().remove(persistent);
    }
}
