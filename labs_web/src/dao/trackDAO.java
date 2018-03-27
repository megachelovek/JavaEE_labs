package dao;

import model.Track;

public interface trackDAO {
    String NAME = "name";

    String TIME = "time";

    String ID = "id";

    String ID_ALBUM = "id_album";

    String ID_SINGER = "id_singer";

    void addTrack(Track track);

    Track getTrack(String teamName);

    Track getTrack(int id);

    void updateTrack(Track track);

    void updateTrackSingerId(int id,int id2);

    void deleteTrack(Track track);

    void deleteTrack(int id);
}
