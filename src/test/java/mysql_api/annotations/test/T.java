package mysql_api.annotations.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

import com.mysql.cj.jdbc.DatabaseMetaData;

import api.connection.ConnectionManager;
import api.connection.impl.pools.MysqlConnection;
import api.connection.impl.pools.SqliteConnection;
import api.models.Transaction;
import api.models.annotations.processor.ForeiKeyProcessor;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 User user = new User();
	        user.setId(1);
//
//	        Plans plan1 = new Plans();
//	        plan1.setId(101);
//	        plan1.setUserId(1);
//
//	        Plans plan2 = new Plans();
//	        plan2.setId(102);
//	        plan2.setUserId(1);

//	 //       user.setPlans(Arrays.asList(plan1, plan2));
	        ConnectionManager a = new ConnectionManager("teste");
	        MysqlConnection mysql = new MysqlConnection("mysql");
	        mysql.setAdress("localhost");
	        mysql.setDatabase("test");
	        mysql.setUsername("root");
	        
	        a.addConnection(mysql);
	     //  System.out.println(convertPredicateToSql(u-> user.getId()==1));
	       List<User> u = new Transaction().select().setConnectionManager(a).setTable("user").queryList(User.class);
	       u.toString();
	       
	}
	
	 private static StringBuilder sqlQuery;

	    // ... (métodos anteriores)
	 private static <T> String convertPredicateToSql(Predicate<T> predicate) {
	        if (predicate == null) {
	            return "";
	        }

	        StringBuilder sqlClause = new StringBuilder("WHERE ");

	        if (predicate instanceof Predicate<?>) {
	            Predicate<?> simplePredicate = (Predicate<?>) predicate;

	            try {
	                Field field = getFieldFromPredicate(simplePredicate);

	                if (field != null) {
	                    // Obtém o nome do campo
	                    String fieldName = field.getName();

	                    // Adiciona a cláusula SQL para igualdade
	                    sqlClause.append(fieldName).append(" = ?");
	                }
	            } catch (NoSuchFieldException | SecurityException e) {
	                e.printStackTrace(); // Lide com as exceções apropriadamente em sua aplicação
	            }
	        }

	        return sqlClause.toString();
	    }

	    private static <T> Field getFieldFromPredicate(Predicate<T> predicate)
	            throws NoSuchFieldException, SecurityException {
	        // Usa reflexão para obter o campo do predicado
	        Field field = null;

	        if (predicate != null && predicate instanceof Predicate<?>) {
	            Predicate<?> simplePredicate = (Predicate<?>) predicate;

	            try {
	                Field[] fields = simplePredicate.getClass().getDeclaredFields();

	                if (fields.length > 0) {
	                    // Supondo que o campo é o primeiro campo declarado na classe do predicado
	                    field = fields[0];
	                    field.setAccessible(true);
	                }
	            } catch (SecurityException e) {
	                throw e;
	            }
	        }

	        return field;
	    }

}
