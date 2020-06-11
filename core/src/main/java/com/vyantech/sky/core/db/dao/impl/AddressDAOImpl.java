/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core.db.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vyantech.sky.core.db.dao.AddressDAO;
import com.vyantech.sky.core.db.model.Address;

@Repository
public class AddressDAOImpl
		extends GenericDAOImpl<Address, Long>
		implements AddressDAO {

	@Autowired
	public AddressDAOImpl(SessionFactory sf) {
		super(sf, Address.class, Long.class);
	}

}
