/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core.db.dao;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface GenericDAO<T, ID extends Serializable> {
	T getById(ID id);

	@Transactional(readOnly = false)
	void save(T obj);
}
