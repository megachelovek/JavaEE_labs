package model;

public class Track {

    private String track_name;
    private int id;
    private int time;
    private int id_album;

    public Track(String track_name,int id,int time,int id_album){
        this.id_album = id_album;
        this.id = id;
        this.track_name = track_name;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }
}
