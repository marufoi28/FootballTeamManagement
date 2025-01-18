package dao.field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.db.DatabaseConnection;
import model.field.Field;
import util.JdbcUtil;
import util.SqlFileLoader;

public class FieldDAO {
	public List<Field> getFields(){
		List<Field> fields = new ArrayList<>();
		JdbcUtil.loadJDBCDriver();
		
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = SqlFileLoader.getSqlQuery("select", "sql/field");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Field field = new Field();
				field.setFieldId(rs.getInt("field_id"));
				field.setFieldName(rs.getString("field_name"));
				fields.add(field);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return fields;
	}
}
