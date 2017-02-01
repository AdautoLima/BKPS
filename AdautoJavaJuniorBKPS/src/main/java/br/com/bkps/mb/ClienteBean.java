package br.com.bkps.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.bkps.dao.ClienteDao;
import br.com.bkps.modelo.Cliente;

@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;
			
	public void grava() {
		ClienteDao dao = new ClienteDao();
		if(cliente.getId() == null){
			dao.adiciona(cliente);
		} else {
			dao.atualiza(cliente);
		}
		this.cliente = new Cliente();
		this.clientes = dao.listaTodos();		
	}

	public List<Cliente> getClientes() {
		if(clientes == null){
			clientes = new ClienteDao().listaTodos();
		}
		return clientes;
	}
	
	public void remove(Cliente cliente){
		ClienteDao dao = new ClienteDao();
		dao.remove(cliente);
		this.clientes = dao.listaTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

}