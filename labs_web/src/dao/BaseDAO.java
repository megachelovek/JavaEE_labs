//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Properties;

public abstract class BaseDAO {
    protected String driver = null;
    protected String url = null;
    protected Properties properties = null;

    public BaseDAO(String driver) {
        this.driver = driver;
    }

    protected void RegisterDriverManager() {
        try {
            Class.forName(this.driver).newInstance();
        } catch (InstantiationException var2) {
            var2.printStackTrace();
        } catch (IllegalAccessException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

    }

    public abstract void setURL(String var1, String var2, int var3);

    public abstract Connection getConnection();

    public void connect(String login, String password) {
        this.RegisterDriverManager();
        this.properties = new Properties();
        this.properties.setProperty("password", password);
        this.properties.setProperty("user", login);
        this.properties.setProperty("useUnicode", "true");
        this.properties.setProperty("characterEncoding", "utf8");
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException var3) {
            ;
        }

    }

    public ResultSet executeSQLquery(String sql) {
        ResultSet result = null;

        try {
            if (this.getConnection() != null) {
                Statement statement = this.getConnection().createStatement();
                result = statement.executeQuery(sql);
            }
        } catch (SQLException var4) {
            System.err.println("SQLException : code = " + String.valueOf(var4.getErrorCode()) + " - " + var4.getMessage());
            System.err.println("\tSQL : " + sql);
        }

        return result;
    }

    public ResultSet executePreparedSelect(String sql, int time, int singer_id) {
        ResultSet result = null;

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(time).intValue());
            statement.setInt(2, Integer.valueOf(singer_id).intValue());
            result = statement.executeQuery();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return result;
    }

    public void executePreparedInsert(String sql, String track_name, int time, int id, int id_album, int id_singer) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            statement.setString(1, track_name);
            statement.setInt(2, Integer.valueOf(time).intValue());
            statement.setInt(3, Integer.valueOf(id).intValue());
            statement.setInt(4, Integer.valueOf(id_album).intValue());
            statement.setInt(5, Integer.valueOf(id_singer).intValue());
            statement.execute();
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

    }

    public void executePrepared(String sql, int singer_id, String track_name) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(singer_id).intValue());
            statement.setString(2, track_name);
            statement.execute();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    public abstract String getGenreByAlbumId(Integer var1);

    public abstract Integer getIdByName(String var1);

    public abstract String getNameById(Integer var1);
}
