
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDAO extends BaseDAO {
    private Connection connection = null;
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_DATABASE = "music_store";
    public static final String DEFAULT_LOGIN = "postgres";
    public static final String DEFAULT_PASSWORD = "1234";
    public static final int DEFAULT_PORT = 5432;

    public PostgresDAO() {
        super("org.postgresql.Driver");
    }

    public void setURL(String host, String database, int port) {
        if (database.length() > 0) {
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        } else {
            this.url = "jdbc:postgresql://" + host + ":" + port;
        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect(String login, String password) {
        super.connect(login, password);

        try {
            this.connection = DriverManager.getConnection(this.url, this.properties);
        } catch (SQLException var4) {
            this.connection = null;
        }

    }

    public String getNameById(Integer singer_id) {
        ResultSet rs = this.executeSQLquery(String.format("select name from singer where id = '%1$s'", singer_id));

        try {
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public Integer getIdByName(String name) {
        ResultSet rs = this.executeSQLquery(String.format("select id from singer where name = '%1$s'", name));

        try {
            if (rs.next()) {
                return rs.getInt("id_singer");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return Integer.valueOf(-1);
    }

    public String getGenreByAlbumId(Integer album_id) {
        ResultSet rs = this.executeSQLquery(String.format("select genre from album where id = '%1$s'", album_id));

        try {
            if (rs.next()) {
                return rs.getString("genre");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }
}
