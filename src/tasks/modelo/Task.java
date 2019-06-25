package tasks.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, message="A descrição deve ter pelo menos 3 caracteres")
	private String descricao;
	
	@Column(nullable = true)
	private boolean finalizada;
	
	@Column(nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataFinalizacao;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getDescricao() {return descricao;} 
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
	public boolean isFinalizada() {return finalizada;}
	public void setFinalizada(boolean finalizada) {this.finalizada = finalizada;}
	
	public Calendar getDataFinalizacao() {return dataFinalizacao;}
	public void setDataFinalizacao(Calendar dataFinalizacao) {this.dataFinalizacao = dataFinalizacao;}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", descricao=" + descricao + ", finalizado=" + finalizada + "]";
	}
}
