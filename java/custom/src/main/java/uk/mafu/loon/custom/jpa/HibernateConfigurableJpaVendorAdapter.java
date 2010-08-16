/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Modified by Bryan Hunt for use by Emak Mafu Ltd
 */
package uk.mafu.loon.custom.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;

/**
 * {@link org.springframework.orm.jpa.JpaVendorAdapter} implementation for
 * Hibernate EntityManager. Developed and tested against Hibernate 3.2.
 * 
 * <p>
 * Exposes Hibernate's persistence provider and EntityManager extension
 * interface, and supports {@link AbstractJpaVendorAdapter}'s common
 * configuration settings.
 * 
 * @author Juergen Hoeller
 * @author Rod Johnson
 * @author Bryan
 * @since 2.0
 * @see org.hibernate.ejb.HibernatePersistence
 * @see org.hibernate.ejb.HibernateEntityManager
 */
public class HibernateConfigurableJpaVendorAdapter extends
		AbstractJpaVendorAdapter {
	private final PersistenceProvider persistenceProvider = new HibernatePersistence();
	private Map<String, String> jpaPropertyMap = new HashMap<String, String>();

	public void setJpaPropertyMap(Map<String, String> jpaPropertyMap) {
		//Clear out any empty keys before setting.
		Map<String, String> tmp = new HashMap<String, String>();
		for (String key : jpaPropertyMap.keySet()) {
			if (jpaPropertyMap.get(key) != null
					&& jpaPropertyMap.get(key).length() != 0) {
				tmp.put(key, jpaPropertyMap.get(key));
			}
		}
		this.jpaPropertyMap = tmp;
	}

	public PersistenceProvider getPersistenceProvider() {
		return this.persistenceProvider;
	}

	public String getPersistenceProviderRootPackage() {
		return "org.hibernate";
	}

	public Map<String, String> getJpaPropertyMap() {
		return this.jpaPropertyMap;
	}

	public Class<? extends EntityManagerFactory> getEntityManagerFactoryInterface() {
		return HibernateEntityManagerFactory.class;
	}

	public Class<? extends EntityManager> getEntityManagerInterface() {
		return HibernateEntityManager.class;
	}

	public JpaDialect getJpaDialect() {
		return null;
	}
}
