package com.freenow.domainobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class RoleDO extends BaseDO implements GrantedAuthority
{

    private static final long serialVersionUID = -6193485444874165977L;

    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<UserDO> users;

    public RoleDO(String authority)
    {
        super();
        this.authority = authority;
    }


    public RoleDO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public String getAuthority()
    {
        // TODO Auto-generated method stub
        return authority;
    }


    public Set<UserDO> getUsers()
    {
        return users;
    }


    public void setUsers(Set<UserDO> users)
    {
        this.users = users;
    }


    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }


    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

}
