/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core.db.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vyantech.sky.core.CoreIntegrationTest;
import com.vyantech.sky.core.db.dao.AddressDAO;
import com.vyantech.sky.core.db.model.Address;

public class AddressDAOImplTest extends CoreIntegrationTest {

	@Autowired
	private AddressDAO addressDao;

	@Test
	public void testCreation() {
		Address adr = new Address();
		adr.setName("test3");
		addressDao.save(adr);
	}
}