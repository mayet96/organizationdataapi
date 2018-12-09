package ru.id61890868.OrganizationDataApi.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.view.OfficeView;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) throws Exception {
        Office o = em.find(Office.class, id);
        if(o == null){
            throw new Exception("OfficeDao: not found");
        }
        return o;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Office> c = qb.createQuery(Office.class);
        Root<Office> p = c.from(Office.class);
        TypedQuery<Office> q = em.createQuery(c);
        return q.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) throws Exception {
        if(office.getId() == null){
            throw new Exception("OfficeDao: id can not be null");
        }
        Office upOffice = loadById(office.getId());
        if(office == null){
            throw new Exception("OfficeDao: office not found");
        }
        upOffice.setActive(office.getActive());
        upOffice.setAddress(office.getAddress());
        upOffice.setName(office.getName());
        upOffice.setOrgId(office.getOrgId());
        upOffice.setPhone(office.getPhone());
        em.flush();
    }

    /**
     * {@inheritDoc}
     * не используется
     */
    @Override
    public void override(Office office) throws Exception {
        if(office.getId() == null){
            throw new Exception("OfficeDao: id can not be null");
        }
        em.merge(office);
    }

}