package com.freenow.domainobject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDO extends BaseDO implements UserDetails
{
    @Column(nullable = false)
    protected String username;
    
    @Column(nullable = false)
    protected String password;
    
    @Column(nullable = false)
    private boolean isAccountExpired = false;
    
    @Column(nullable = false)
    private boolean isAccountLocked = false;
    
    @Column(nullable = false)
    private boolean isAccountEnabled = true;
    
    @Column(nullable = false)
    private boolean isCredentialsExpired = false;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected Set<RoleDO> roles = new HashSet<>();
    
    

    public UserDO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

    public UserDO(String username, Set<RoleDO> roles)
    {
        super();
        this.username = username;
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        // TODO Auto-generated method stub
        return getRoles();
    }

    public Set<RoleDO> getRoles()
    {
        return roles;
    }



    public void setRoles(Set<RoleDO> roles)
    {
        this.roles = roles;
    }



    @Override
    public String getPassword()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }



    

}
