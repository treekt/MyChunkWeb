package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.Role;

public interface IRoleDao {
    Role getByRole(String role);
}
