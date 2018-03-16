package dao;

import model.Album;

public interface albumDAO {
    String NAME = "name";

    String ALBUM_ID = "album_id";

    String GENRE = "genre";

    String ID = "id";


    void addAlbum(Album album);

    Album getAlbum(String teamName);

    Album getAlbum(int teamId);

    void updateAlbum(Album team);

    void deleteAlbum(Album team);

    void deleteAlbum(int teamId);
}