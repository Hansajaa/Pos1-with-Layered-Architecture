package dao.util;

import DB.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static <T>T execute(String sql,Object... ob) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        if (sql.startsWith("SELECT") || sql.startsWith("select")){

            for (int i = 0; i < ob.length; i++) {
                pstm.setObject((i+1),ob[i]);
            }

            return (T) pstm.executeQuery();
        }

        for (int i = 0; i < ob.length; i++) {
            pstm.setObject((i+1),ob[i]);
        }

        return (T) (Boolean)(pstm.executeUpdate()>0);
    }
}
