import dao.BaseDAO;
import dao.PostgresDAO;
import model.Mainclass;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Control {
    private BaseDAO dao;
    Mainclass mainclass;
    List<Mainclass> mainclassList = new ArrayList();
    List<String[]> res = new ArrayList();

    public Control() {
    }

    public void init() {
        this.dao = new PostgresDAO();
        this.dao.setURL("localhost", "web_interfaces", 5432);
        this.dao.connect("postgres", "postgres");
    }

    public String[][] selectAll() {
        ResultSet rs = this.dao.executeSQLquery("SELECT * FROM track");
        this.processResult(rs);
        return (String[][])this.res.toArray(new String[0][0]);
    }

    public String[][] executeSelect(int time, String singer_name) {
        ResultSet rs = this.dao.executePreparedSelect("SELECT * FROM track WHERE time > ? and singer_id != ?", time, this.dao.getIdByName(singer_name));
        this.processResult(rs);
        return (String[][])this.res.toArray(new String[0][0]);
    }


    private void processResult(ResultSet rs) {
        while(true) {
            try {
                if (rs.next()) {
                    this.mainclass = new Mainclass();
                    this.mainclass.setId(rs.getInt("id"));
                    this.mainclass.setTrack_name(rs.getString("track_name"));
                    this.mainclass.setTime(rs.getInt("time"));
                    this.mainclass.setGenre(this.dao.getGenreByAlbumId(rs.getInt("id_album")));
                    this.mainclass.setId_singer(rs.getInt("id_singer"));
                    this.mainclassList.add(this.mainclass);
                    String[] tmp = new String[]{this.mainclass.getId().toString(), this.mainclass.getTrack_name(), this.mainclass.getTime().toString(), this.mainclass.getGenre(),this.mainclass.getId_album().toString(), this.mainclass.getId_singer().toString()};
                    this.res.add(tmp);
                    continue;
                }

                rs.close();
                this.dao.disconnect(this.dao.getConnection());
            } catch (SQLException var3) {
                var3.printStackTrace();
            }

            return;
        }
    }

    public String[][] executeInsert(String track_name, int time, int id,int id_album , int id_singer) {
        this.dao.executePreparedInsert("INSERT INTO track VALUES (?,?,?,?,?)", track_name, time,id,id_album ,id_singer );
        return this.selectAll();
    }

    public String[][] executeUpdate(String name, int id_singer) {
        this.dao.executePrepared("UPDATE track set track_name = ? WHERE id_singer=?", id_singer,name);
        return this.selectAll();
    }

    public String[][] executeDelete( int id_singer, String track_name) {
        this.dao.executePrepared("DELETE FROM track WHERE singer_id = ? and title = ? ", id_singer, track_name);
        return this.selectAll();
    }
}
