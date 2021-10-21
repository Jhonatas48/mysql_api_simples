package jhonatastomaz.bot.api.database.jhonatas;

import java.sql.SQLException;
import java.util.logging.Logger;


import java.sql.DriverManager;
import java.io.File;
import java.sql.Connection;;

public class ConexaosqLite
{
   private static File file =null;
   private static Connection conexao;
    public static  Connection conectarSqLite() {
    	 fecharConexoesAntigas(conexao);
        conexao= null;
      
        iniciarDiretorio();
      final String url = "jdbc:sqlite:" + file;
      
        try {
           // Class.forName("org.sqlite.JDBC");
        	
            conexao = DriverManager.getConnection(url);
                
     
        }
        catch (Exception e) {
        	Logger.getLogger("Erro na conexao");
            e.printStackTrace();
           
        }
        return conexao;
    }
    
    
    public static void desconetarSqLite(final Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
            else {
            	System.out.println("a conexao ja esta fechada");
            }
        }
        catch (SQLException e) {
        	System.out.println("erro ao tentar fechar a conexao");
            e.printStackTrace();
        }
    }
    
    private static void fecharConexoesAntigas(Connection conexao) {
		if(!(conexao==null)) {
			ConexaosqLite.desconetarSqLite(conexao);
		}
	}
    private static void iniciarDiretorio() {
    	if(!file.exists()) {
    		file.mkdir();
    	}
    }
	public static File getFile() {
		return file;
	}
	

	public static void setFile(File file) {
		ConexaosqLite.file = file;
	}
    
    
}
