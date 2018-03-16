package model;

public class Album {
    private String album_name;
    private String genre;
    private int id;

    public Album(String al_name,String genre, int id){
        this.album_name=al_name;
        this.genre=genre;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
