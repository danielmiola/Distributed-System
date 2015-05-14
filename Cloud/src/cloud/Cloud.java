package cloud;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud.Arquivo;
import cloud.PMF;

@SuppressWarnings("serial")
public class Cloud extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		String method = req.getParameter("method");
		if (method.equals("save"))
			save(req, res);
		if (method.equals("return"))
			ret(req, res);		
	}
	
	// metodo gera id e salva descricao do video na cloud	
	public void save(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		String descricao = req.getParameter("descricao");
		Arquivo arquivo = new Arquivo(descricao);

		PersistenceManager pm =	PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(arquivo);
			
			res.setContentType("text/html");
			res.setHeader("id", arquivo.getId().toString());
			res.getWriter().println(arquivo.getId());
		} finally {
			pm.close();
		}
	}
	
	// metodo retorna descricao do vido pelo id salvo na cloud
	public void ret(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		Long id = Long.parseLong(req.getParameter("id"));
		Arquivo arquivo = new Arquivo(id);

		PersistenceManager pm =	PMF.get().getPersistenceManager();
		try {			
			Query query = pm.newQuery(Arquivo.class);
			query.setFilter("id == idb");
			query.declareParameters("Long idb");
			List<Arquivo> arquivos = null;
			
			try{
				arquivos = (List<Arquivo>)query.execute(arquivo.getId());
			}finally{
				query.closeAll();
			}
			
			Arquivo a = arquivos.get(0);
			
			res.setContentType("text/html");
			res.setHeader("id", a.getId().toString());
			res.getWriter().println(a.getId());
			
			res.setContentType("text/html");
			res.setHeader("descricao",a.getDescricao());
			res.getWriter().println(a.getDescricao());
		} finally {
			pm.close();
		}
	}
}
