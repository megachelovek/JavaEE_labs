package dao;

import model.Track;

public interface trackDAO {
    String NAME = "name";

    String TIME = "time";

    String ID = "id";

    String ID_ALBUM = "id_album";

    void addTrack(Track track);

    Track getTrack(String teamName);

    Track getTrack(int id);

    void updateTrack(Track track);

    void deleteTrack(Track track);

    void deleteTrack(int id);
}
