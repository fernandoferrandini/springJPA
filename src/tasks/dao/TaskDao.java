package tasks.dao;

//import tasks.jdbc.ConnectionFactory;
import tasks.modelo.Task;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {

	private final Connection connection;

	@Autowired
	public TaskDao(DataSource dataSource) {
//	public TaskDao(Connection connection) {
//		try {
//			this.connection = new ConnectionFactory().getConnection();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		this.connection = connection;
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public void inserir(Task task) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(task);
		manager.getTransaction().commit();
		manager.close();
		
	}


	public List<Task> getTasks() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		List<Task> tasks = manager.createQuery("select t from Task as t").getResultList();
	
		manager.close();
		factory.close();
		
		return tasks;
	}


/*	private Task populaTask(ResultSet rs) throws SQLException {
		Task task = new Task();
		//popula o objeto task
		task.setId(rs.getLong("id"));
		task.setDescricao(rs.getString("descricao"));
		task.setFinalizada(rs.getBoolean("finalizada"));
		//popula a data de finalizacao da task, fazendo a conversao
		Date data = rs.getDate("dataFinalizacao");
		if(data != null) {
			Calendar dataFinalizacao = Calendar.getInstance();
			dataFinalizacao.setTime(data);
			task.setDataFinalizacao(dataFinalizacao);
		}
		return task;
	}
*/

	public void exclui(Task task) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		task = manager.find(Task.class, task.getId());
		
		manager.getTransaction().begin();
		manager.remove(task);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}


	public Task getById(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		Task task = manager.find(Task.class, id);
		
		if(task == null) { throw new IllegalStateException("Nenhum registro com o id " + id + " encontrado.");}
		
		manager.close();
		factory.close();
		
		return task;
	}


	public void edita(Task task) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}


	public void finaliza(Long id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tarefas");
		EntityManager manager = factory.createEntityManager();
		
		Task task = manager.find(Task.class, id);
		
		task.setDataFinalizacao(Calendar.getInstance());
		task.setFinalizada(true);
		
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
}