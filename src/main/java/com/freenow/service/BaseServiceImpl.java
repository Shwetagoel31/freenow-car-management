package com.freenow.service;

import java.io.Serializable;
import java.util.List;

import com.freenow.domainobject.BaseDO;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

public abstract class BaseServiceImpl<T extends BaseDO, ID extends Serializable> implements IBaseService<T, ID>
{
    
    //protected

    @Override
    public T create(T entity) throws ConstraintsViolationException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(ID id) throws EntityNotFoundException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T find(ID id) throws EntityNotFoundException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> findAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
