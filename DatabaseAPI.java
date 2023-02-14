package jhonatastomaz.bot.api.database.jhonatas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author JH�NATAS
 *
 */
public class DatabaseAPI {


	public DatabaseAPI() {

		
	}
	
	public static boolean ExecutorSql(String comandoSql) {
		boolean check=false;
		Connection conexao = ConexaosqLite.conectarSqLite();
		try {
			PreparedStatement statement = conexao.prepareStatement(comandoSql);
			statement.execute();
			check=true;
		} catch (SQLException e) {
			Logger.getLogger("Erro ao executar o comado: "+comandoSql);
			e.printStackTrace();
		}
		return check;
	}
	public static boolean criarTabela(String tabela, List<String> campos) {
		boolean check=false;
		if (campos == null) {
			Logger.getLogger("OS campos nao podem ser nulos");
			
			
			return check;
		} else if (campos.isEmpty()) {
			Logger.getLogger("Lista de campos esta vazia");
			
			return check;
		}

		Connection conexao = ConexaosqLite.conectarSqLite();
		String listacampos = "";
		int controle = campos.size();
		for (String linha : campos) {
			if (controle == 1) {
				listacampos = listacampos + linha;
			} else {
				listacampos = listacampos + linha + ",";
			}
			controle--;
		}

		String criar = "Create table if not exists /tabela/(?)";
		criar = criar.replace("/tabela/", tabela);
		criar = criar.replace("?", listacampos);
		

		try {
			PreparedStatement statement = conexao.prepareStatement(criar);
			check=statement.execute();
			
			conexao.close();

		} catch (SQLException e) {
			
			Logger.getLogger("Erro ao criar a tabela");

			e.printStackTrace();
		}finally{
			ConexaosqLite.desconetarSqLite(conexao);
		}
		return check;
	}

	public static boolean inserirDados(String tabela, String campos, List<Dados> dados) {
		boolean check=false;
		String subs = "";
		for (int controle = 0; controle < dados.size(); controle++) {

			if (dados.size() - 1 > controle) {

				subs = subs + "?,";
			} else {
				subs = subs + "?";
			}

		}
		String inserir = "Insert or ignore into %table%(%campos%)values(%subs%)";
		

		inserir = inserir.replace("%table%", tabela);
		inserir = inserir.replace("%campos%", campos);
		inserir = inserir.replace("%subs%", subs);
		int id = 1;
		System.out.println("query inserir:"+inserir);
		Connection conexao = ConexaosqLite.conectarSqLite();
		try {
			
			PreparedStatement statement = conexao.prepareStatement(inserir);

			for (Dados dado : dados) {
				Transformador transf = new Transformador(dado.getObjeto());

				switch (dado.getTipoprimitivo()) {
				case STRING:

					statement.setString(id, transf.toString());
					break;

				case INTEGER:

					statement.setInt(id, transf.toInteger());
					break;
				case SHORT:
					statement.setShort(id, transf.toShort());
					break;
				case DOUBLE:
					statement.setDouble(id, transf.toDouble());
					break;
				case FLOAT:
					statement.setFloat(id, transf.toFloat());
					break;
				case LONG:
					statement.setLong(id, transf.toLong());
					break;
				case BOOLEAN:
					statement.setBoolean(id, transf.toBoolean());
					break;

				}
				id++;
			}

			int idstatus=statement.executeUpdate();
			check=idstatus==1;
			System.out.println("status inserir:"+idstatus);
			System.out.println("check inserir: "+check);

		} catch (SQLException e) {
			
			Logger.getLogger("Erro ao inserir o dado na tabela");
			e.printStackTrace();
		}finally{
			ConexaosqLite.desconetarSqLite(conexao);
		}
		return check;
	}

	public static ResultSet pesquisarDado(String tabela, String campos, String argumentoWhere) {
		ResultSet resultado=null;
		String consulta = "";
		boolean check = false;
		check = campos == null ? false : true;
		if (check) {
			if (campos.isEmpty()) {
				consulta = "Select *";
			} else {
				consulta = "Select " + campos;
			}
			
		} else {
			consulta = "Select *";
		}
		consulta = consulta + " from " + tabela;
		
		check = argumentoWhere == null ? false : true;
		
		
		if (check && !argumentoWhere.isEmpty()) {
			String[] argumentos = argumentoWhere.split(",");
			for (int id = 0; id < argumentos.length; id++) {
				if(argumentos.length==1) {
					consulta=consulta+" where "+argumentos[id];
					break;
				}
				if (id < argumentos.length - 1) {
					
					consulta = consulta + " where " + argumentos[id]+" and ";
				
				} else {
					consulta = consulta + argumentos[id];
					

				}
				
			}
		}
		
		Connection conexao = ConexaosqLite.conectarSqLite();
		System.out.println("query select:"+consulta);
		try {
			
			PreparedStatement statement = conexao.prepareStatement(consulta);

			 resultado = statement.executeQuery();
			
		
		} catch (SQLException e) {
			Logger.getLogger("Erro ao pesquisar o dado na tabela");
			e.printStackTrace();
			return resultado;
		}
		
		return resultado;

	}

	public static boolean update(String tabela,String campos,String argumento_where) {
		boolean check=false;
		
		Connection conexao = ConexaosqLite.conectarSqLite();
		try {
			String update="update "+tabela+" set "+campos+" where "+argumento_where;
			
			
			System.out.println("query update: "+update);
			PreparedStatement statement = conexao.prepareStatement(update);
			int status=statement.executeUpdate();
			check=status==1;
			
		}catch (SQLException e) {
			
			Logger.getLogger("Erro ao atualizar os dados");
			e.printStackTrace();
		
	}finally{
			ConexaosqLite.desconetarSqLite(conexao);
		}
		return check;
	}
	
	public static boolean deletar(String tabela,String argumento_where) {
		boolean check=false;
		Connection conexao = ConexaosqLite.conectarSqLite();
		try {
			String update="delete from "+tabela+" where "+argumento_where;
		
			System.out.println("deletar: "+update);
			PreparedStatement statement = conexao.prepareStatement(update);
			int status =statement.executeUpdate();
			check=status==1;
		}catch (SQLException e) {
			
			Logger.getLogger("Erro ao deletar o dado");
			e.printStackTrace();
		
	}finally{
		ConexaosqLite.desconetarSqLite(conexao);
	}
		
	return check;
		
	}
	public static boolean truncate(String tabela) {
		boolean check=false;
		Connection conexao = ConexaosqLite.conectarSqLite();
		try {
			String truncate="truncate "+tabela;
		
			
			PreparedStatement statement = conexao.prepareStatement(truncate);
			int status =statement.executeUpdate();
			check=status==1;
		}catch (SQLException e) {
			
			Logger.getLogger("Erro ao atualizar os dados");
			e.printStackTrace();
	}finally{
		ConexaosqLite.desconetarSqLite(conexao);
	}
		return check;
		
	}
}
