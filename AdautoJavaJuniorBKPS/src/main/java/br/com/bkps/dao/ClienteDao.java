package br.com.bkps.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.bkps.modelo.Cliente;
import br.com.bkps.util.JPAUtil;

public class ClienteDao implements Serializable{

	private static final long serialVersionUID = 1L;

	public void adiciona(Cliente cliente) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(cliente);
		
		manager.getTransaction().commit();
		manager.close();
	}


	public void remove(Cliente cliente) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.remove(manager.merge(cliente));

		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Cliente cliente) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.merge(cliente);
		
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Cliente> buscaPorNome(String nome) {

		EntityManager manager = new JPAUtil().getEntityManager();

		String jpql = "select c from Cliente c where "
				+ " lower(c.nome) like :nome order by c.nome";

		List<Cliente> lista = manager.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome + "%").getResultList();

		manager.close();
		
		return lista; 
	}

	public List<Cliente> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		
		CriteriaQuery<Cliente> query = manager.getCriteriaBuilder().createQuery(Cliente.class);
		query.select(query.from(Cliente.class));

		List<Cliente> lista = manager.createQuery(query).getResultList();

		manager.close();
		
		return lista; 
	}
	
	public Cliente buscaPorId(Long id) {
		EntityManager manager = new JPAUtil().getEntityManager();

		Cliente produto = manager.find(Cliente.class, id);

		manager.close();

		return produto;
	}
}