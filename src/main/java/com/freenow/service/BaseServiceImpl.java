package com.freenow.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.freenow.domainobject.BaseDO;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

public abstract class BaseServiceImpl<T extends BaseDO, ID extends Serializable> implements IBaseService<T, ID>
{

    private static final Logger LOG = LoggerFactory.getLogger(BaseServiceImpl.class);

    protected abstract JpaRepository<T, ID> getRepository();


    @Override
    public T create(T entity) throws ConstraintsViolationException
    {
        try
        {
            getRepository().save(entity);
        }
        catch (DataIntegrityViolationException ex)
        {
            LOG.warn("Some constraints have been violated during entity creation " + entity.getClass(), ex);
            throw new ConstraintsViolationException(ex.getMessage());
        }
        return entity;
    }


    @Override
    public void delete(ID id) throws EntityNotFoundException
    {
        T entity = find(id);
        entity.setDeleted(true);
    }


    @Override
    public T find(ID id) throws EntityNotFoundException
    {
        return getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find entity with ID: " + id));
    }


    @Override
    public List<T> findAll()
    {
        return getRepository().findAll();
    }

}
