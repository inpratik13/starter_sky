/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core.db.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.vyantech.sky.core.db.dao.GenericDAO;


public class GenericDAOImpl<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	protected final SessionFactory sessionFactory;
	protected final Class<T> entityClass;
	protected final Class<ID> idClass;

	public GenericDAOImpl(SessionFactory sessionFactory,
			Class<T> entityClass,
			Class<ID> idClass) {

		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
		this.idClass = idClass;
	}

	@Override
	public T getById(ID id) {
		return sessionFactory.getCurrentSession().get(entityClass, id);
	}

	@Override
	public void save(T obj) {
		sessionFactory.getCurrentSession()
				.save(obj);
	}

}
