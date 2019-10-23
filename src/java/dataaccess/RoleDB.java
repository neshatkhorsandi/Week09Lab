package dataaccess;

import javax.persistence.EntityManager;
import models.Role;
import utilities.DBUtil;

public class RoleDB {
    public Role getRole(int roleID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role = em.find(Role.class, roleID);
            return role;
        } finally {
            em.close();
        }
    }
}
