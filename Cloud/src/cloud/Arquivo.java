package cloud;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION) public class Arquivo {	
	
	@PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;	
	@Persistent	private String descricao;		
	
	public Arquivo(String descricao){
		this.descricao = descricao;
	}
	
	public Arquivo(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}