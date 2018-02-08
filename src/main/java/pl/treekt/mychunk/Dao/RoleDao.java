package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IRoleDao;
import pl.treekt.mychunk.Entity.Web.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class RoleDao implements IRoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByRole(String role) {
        String query = "FROM Role WHERE role = '" + role + "'";
        return (Role)entityManager.createQuery(query).getSingleResult();
    }
}
