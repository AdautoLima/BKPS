package br.com.bkps.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdautoJavaJuniorBKPS");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}