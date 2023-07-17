package api.models;

import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import api.connection.ConnectionManager;
import api.connection.IConnection;
import api.connection.impl.SQLBuildManager;
import api.exception.ColumnsIsNullException;
import api.models.enums.TransactionType;
import api.models.statements.Create;
import api.models.statements.Delete;
import api.models.statements.Insert;
import api.models.statements.Result;
import api.models.statements.Row;
import api.models.statements.Update;
import api.models.utils.Checkers;

 abstract class PerformTransaction {
	
	private static boolean debugEnabled = false;
	private static Exception exception = null;
	public PerformTransaction() {
		
	}
	
	public PerformTransaction(boolean debugEnable) {
		
		debugEnabled = debugEnable;
	}
	
	private static void printDebbug(PreparedStatement preparedStament) {
		
		if(debugEnabled) {
			Logger.getLogger("apimysql").info("[DEBUG DB_API] "+preparedStament.toString());
			System.out.println("[DEBUG DB_API] "+preparedStament.toString());
			
		}
	}
	
	private static void printDebbug(Statement stament) {
		
		if(debugEnabled) {
			Logger.getLogger("apimysql").info("[DEBUG DB_API] "+stament.toString());
			
		}
	}
	
	public boolean createTable(String sql,IConnection<?> Iconnection) {
		Connection connection = Iconnection.openConnection();
		
		try {
			PreparedStatement preparedStament = connection.prepareStatement(sql);
			
			printDebbug(preparedStament);
			
			preparedStament.execute();
			connection.close();
			return true;
		} catch (SQLException e) {
			exception = e;
			e.printStackTrace();
			return false;
		}
		
	
	}
	public boolean createTable(Create create) {
		
		Connection conection = ConnectionManager.getConnection();
		try {
			if(!Checkers.isObjectNotNull(create.getPrimaryKey())) {
				throw new ColumnsIsNullException("The Primakey Key is not defined");
			}
			if(create.getColums().isEmpty() && create.getPrimaryKey() == null) {
				throw new NullPointerException("Tabelas sem colunas,adicione as colunas primeiro");
				
			}
			//System.out.println("SQL: "+SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.CREATE_TABLE, create));
			PreparedStatement preparedStament = conection.prepareStatement(SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.CREATE_TABLE, create));
			printDebbug(preparedStament);
			preparedStament.execute();
			 conection.close();
			return true;
			
		} catch (SQLException e) {
			exception = e;
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean tableInsert(String table, String columns, String... data) {
		Connection conection = ConnectionManager.getConnection();
        String sqldata = "";
        int i = 0;
        for (String d : data) {
            sqldata = sqldata + "'" + d + "'";
            i++;
            if(i != data.length) {
                sqldata = sqldata + ", ";
            }
        }


        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + sqldata + ");";
        Statement stmt = null;
        try {
            stmt = conection.createStatement();
            printDebbug(stmt);
            stmt.execute(sql);

        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
	
    public boolean tableInsert(Insert... builders) {
        String sql = "";
        
        Connection conection = ConnectionManager.getConnection();
        
        if(builders == null) {
        	
        	throw new NullPointerException("Objeto da classe Insert necessario!");
        }
        if(!(builders.length <=1)) {
        	
        	throw new NullPointerException("Objeto da classe Insert necessario!");
        	
        }
        if(builders.length == 1) {
        	
             sql= SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.INSERT, builders[0]);
            // System.out.println("INSET: "+sql);
            // sql=sql.replace("?",builders[0].getTable().toLowerCase());
        }else{
        	
        	 for(Insert insert: builders) {
             	
             	sql= SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.INSERT, insert);
             	sql=sql+";";
             }
        }
       
       /* for (Insert b : builders) {
            String sqldata = "";
            int i = 0;
            for (String d : b.getData()) {
                sqldata = sqldata + "'" + d + "'";
                i++;
                if(i != b.getData().length) {
                    sqldata = sqldata + ", ";
                }
            }
           */
       //System.out.println("sql: "+sql);
      //  = null;
        try {
        	 PreparedStatement  stmt = conection.prepareStatement(sql);
        	 int counter = 1;
        	 
        	 List<Object>valuesFields= new ArrayList<>();

        	 for(Insert builder:builders) {
        	  
        		 
        		 
        	  for(int id =0;id<builder.getData().length;id++) {
        		 
        		  valuesFields.add(builder.getData()[id]);
        		 stmt.setObject(counter,""+builder.getData()[id]+"");
        		  counter++;
        		
        	  }
        	  
        	  
        	
          }
        	 printDebbug(stmt);
        	 registerTransactionLog(sql, valuesFields, TransactionType.INSERT);
        	
        	 
        	    
          	return stmt.executeUpdate() > 0;
          		

        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
        } /*finally {
            if(stmt != null) {
                try {
                    stmt.close();
                    conection.close();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
        return false;
    }
/*
    public boolean rowUpdate(String table, UpdateValue value, String filter) {
        String change = "";
        int i = 0;
        for(String key : value.getKeys()) {
            change = change + key + " = '" + value.get(key) + "'";
            i++;
            if(i != value.getKeys().size()) {
                change = change + ", ";
            }
        }
        String sql = "UPDATE " + table + " SET " + change + " WHERE " + filter + ";";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }*/
    public boolean rowUpdate(Update... builders) {
    		
    	String sql ="";
        
        Connection conection = ConnectionManager.getConnection();
        
        if(builders == null) {
        	
        	throw new NullPointerException("Objeto da classe Update necessario!");
        }
       
        if(builders.length == 0) {
        	
        	throw new NullPointerException("Objeto da classe Update necessario!");
        	
        }
        if(builders.length == 1) {
        	
             sql= SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.UPDATE, builders[0]);
        
        }else{
        	
        	 for(Update update: builders) {
        		 
             	sql=sql+SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.UPDATE, update)+";";
             
             }
        }
        //System.out.println(sql);
        try(PreparedStatement stament = conection.prepareStatement(sql)) {
        	
        	int counter = 1;
        	List<Object> valuesFields = new ArrayList<>();
            for(Update builder:builders) {
          	  
          	  for(String column: builder.getValue().getKeys()) {
          		  
          		  valuesFields.add(builder.getValue().get(column));
          		  stament.setObject(counter,""+builder.getValue().get(column)+"");
          		  counter++;
          		
          	  }
          	  
          	  
            }
            
            printDebbug(stament);
			
         
            if(stament.executeUpdate() >0) {
            	registerTransactionLog(sql, valuesFields, TransactionType.UPDATE);
            	return true;
            }
            
            
        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
        
        }
        
        return false;
    }
    
    public boolean rowDelete(Delete... builders) {
		
    	String sql ="";
        
        Connection conection = ConnectionManager.getConnection();
        
        if(builders == null) {
        	
        	throw new NullPointerException("Objeto da classe Delete necessario!");
        }
       
        if(builders.length == 0) {
        	
        	throw new NullPointerException("Objeto da classe Delete necessario!");
        	
        }
        if(builders.length == 1) {
        	
             sql= SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.DELETE, builders[0]);
        
        }else{
        
        	 for(Delete delete: builders) {
        			
             	sql=sql+SQLBuildManager.buildSQL(ConnectionManager.getConnectionType(),TransactionType.DELETE, delete)+";";
             
             }
        }
        
        try(PreparedStatement statement = conection.prepareStatement(sql)) {
        	
        	List<Object> valuesFields = new ArrayList<>();
            for(Delete builder:builders) {
            	 valuesFields.add(builder.getFilter());
           
            }
           
            if(statement.executeUpdate() >0) {
            	registerTransactionLog(sql,valuesFields, TransactionType.DELETE);
            	return true;
            }
          
           
          
        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
        
        }
        
        return false;
    }
    
    public Result rowSelect(String table, String columns, String filter,boolean uselogTransaction) {
    	if(uselogTransaction) {
    		return this.rowSelect(ConnectionManager.getLogConnecton(), table, columns, filter);
    	}
    	return this.rowSelect(ConnectionManager.getConnection(), table, columns, filter);
    }
    private Result rowSelect(Connection connection,String table, String columns, String filter) {
    	
    	if(columns == null || columns.equals("")) {
            columns = "*";
        }
        String sql = "SELECT " + columns + " FROM " + table;
        if(filter != null && !filter.equals("")) {
            sql = sql + " WHERE " + filter;
        }
        sql = sql + ";";
        Statement stmt;
        ResultSet res;
        try {
            stmt = connection.createStatement();
            res = stmt.executeQuery(sql);
            ResultSetMetaData resmeta = res.getMetaData();
            Result result = new Result();
           // int t = 0;
            while(res.next()) {
                Row row = new Row();
                int i = 1;
                boolean bound = true;
                while (bound) {
                    try {
                        row.addcolumn(resmeta.getColumnName(i), res.getObject(i));
                    } catch (SQLException e) {
                        bound = false;
                    }

                    i++;
                }
                result.addrow(row);
               // t++;
            }
            connection.close();
            return result;

        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
            try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				exception = e1;
				e1.printStackTrace();
			}
            return new Result();
        }
    }
    public Result rowSelect(String table, String columns, String filter) {
    	return this.rowSelect(ConnectionManager.getConnection(), table, columns, filter);
    }
   
    private static boolean registerTransactionLog(final String sql, final List<Object>list,final TransactionType transactionType) {
    	
    	Checkers.isNotEmpty(sql);
    	Checkers.validadeObjectNotNull(transactionType,"TransactionType");
    	
    	if(transactionType != TransactionType.DELETE) {
    	
        	Checkers.valideListNotEmpty(list,"list (Referente a tabela transaction_values)");
        	
    	}
    	
    	Connection connection = ConnectionManager.getLogConnecton();
    	try {
    		
    		long time=System.currentTimeMillis();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transactions(sql,transaction_type,milliseconds)values(?,?,?)");
			preparedStatement.setString(1, sql);
			preparedStatement.setString(2,transactionType.toString().toUpperCase());
			preparedStatement.setLong(3,time);
			
			printDebbug(preparedStatement);
			if(preparedStatement.executeUpdate() == 0) {
				
				throw new ConnectException("ocorreu um erro ao salvar a transação nos logs na tabela transactions");
				
			}
			
			preparedStatement.close();
			preparedStatement=null;
			preparedStatement = connection.prepareStatement("SELECT id FROM transactions WHERE sql=? AND transaction_type=? AND milliseconds=?");
			preparedStatement.setString(1, sql);
			preparedStatement.setString(2,transactionType.toString().toUpperCase());
			preparedStatement.setLong(3,time);
			printDebbug(preparedStatement);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result ==null) {
				
				throw new NullPointerException("Resultado dos log sem resultado para consulta: "+sql);
			}
			int id=result.getInt("id");
			
			connection.close();
			connection = null;
			
			if(Checkers.isListEmpty(list)) {
				return true;
			}
			
			int counter = 1;
			for(Object object:list) {
				
				if(!insertTransactionLog(object,id, time,counter)) {
					throw new ConnectException("Erro ao tentar inserir o objeto "+object.toString());
				}
				counter++;
			}
			
			return true;
			
			
		} catch (SQLException | ConnectException e) {
			 exception = e;
			e.printStackTrace();
			return false;
		}
    	
    }
  
    private static boolean insertTransactionLog(final Object object, final int transactionId,final long time,final int parameterOrder) {
    	
    	Connection connection  =  ConnectionManager.getLogConnecton();
    	PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO transactions_values(transactionId,parameterOrder,field)values(?,?,?)");
			
			
	    	
			
				
			    preparedStatement.setInt(1, transactionId);
		
			    preparedStatement.setInt(2, parameterOrder);
				preparedStatement.setObject(3,object);
				
			
			
			printDebbug(preparedStatement);
			
			if(preparedStatement.executeUpdate() == 0) {
				
				throw new ConnectException("ocorreu um erro ao salvar a transação nos logs na tabela transactions_values");
				
				
			}
			
			preparedStatement.close();
			connection.close();
			
			
		} catch (SQLException e) {
			exception = e;
			Logger.getLogger("apimysql").error("Erro ao inserir um registro na tabela transactions_values");
			e.printStackTrace();
			return false;
		} catch (ConnectException e) {
			exception = e;
			//Logger.getLogger("apimysql").error("Erro ao tentar abrir conexao com sqlite (log.db)");
			e.printStackTrace();
			return false;
		}
		
		 insertTransactionLatestLog(time,transactionId);
	
    	
    	return true;
    }
    
    private static boolean insertTransactionLatestLog(long time,final int transactionId) {
    	
    	try {
    		Connection connection  =  ConnectionManager.getLogConnecton();
        	PreparedStatement preparedStatement;
        	
    		preparedStatement = null;
    		connection= null;
    		
    		connection  =  ConnectionManager.getLogConnecton();
    		
    		
    			preparedStatement = connection.prepareStatement("INSERT INTO lastest_transaction(transactionId,milliseconds)VALUES(?,?)");
    		
    			preparedStatement.setInt(1,transactionId);
    			preparedStatement.setLong(2,time);
    			printDebbug(preparedStatement);
    			if(preparedStatement.executeUpdate() == 0) {
    				
    				throw new ConnectException("ocorreu um erro ao salvar a transação nos logs na tabela lastest_transaction");
    				
    			}
    			
    			
    		} catch (SQLException e) {
    			exception = e;
    			Logger.getLogger("apimysql").error("Erro ao inserir um registro na tabela lastest_transaction");
    			
    			e.printStackTrace();
    
    			return false;
    		} catch (ConnectException e) {
    			exception = e;
    			//Logger.getLogger("apimysql").error("Erro ao tentar abrir conexao com sqlite (log.db)");
    			e.printStackTrace();
    			return false;
    		}
    		
    	return true;
    }
    
    /*
    public Result rowSelect(Select s) {
        String sql = "";
        String columns;
        String lsql;
        if(s.getColumns() == null || s.getColumns().equals("")) {
            columns = "*";
        } else {
            columns = s.getColumns();
        }
        lsql = "SELECT " + columns + " FROM " + s.getTable();
        if(s.getFilter() != null && !s.getFilter().equals("")) {
            lsql = lsql + " WHERE " + s.getFilter();
        }
        lsql = lsql + "; ";
        sql = sql + lsql;

        Statement stmt;
        ResultSet res;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            ResultSetMetaData resmeta = res.getMetaData();
            Result result = new Result();
            while(res.next()) {
                Row row = new Row();
                int i = 1;
                boolean bound = true;
                while (bound) {
                    try {
                        row.addcolumn(resmeta.getColumnName(i), res.getObject(i));
                    } catch (SQLException e) {
                        bound = false;
                    }
                    i++;
                }
                result.addrow(row);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result();
        }
        
    }
    */
    public boolean custom(String sql) {
    	
    	Connection conection = ConnectionManager.getConnection();
        Statement stmt = null;
        try {
            stmt = conection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
        	exception = e;
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

	public Exception getGetErrorException() {
		return exception;
	}
    
}
