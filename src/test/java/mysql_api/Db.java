package mysql_api;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Db {
   private static int c=1;
	public  void insert(PreparedStatement s,Object o) {
		try {
			s.setString(c, o.toString());
			c++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
