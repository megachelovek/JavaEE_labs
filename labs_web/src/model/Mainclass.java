
package model;

public class Mainclass {
    private Integer id;
    private String track_name;
    private Integer time;
    private String genre;
    private Integer id_singer;
    private Integer id_album;

    public Mainclass() {
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }

    public Integer getTime() {
        return time;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getId_singer() {
        return id_singer;
    }

    public Integer getId_album() {
        return id_album;
    }

    public void setId_singer(Integer id_singer) {
        this.id_singer = id_singer;
    }

}