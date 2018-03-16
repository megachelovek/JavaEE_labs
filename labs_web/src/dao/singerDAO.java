package dao;

import model.Singer;

public interface singerDAO {
    String NAME = "name";

    String ID = "id";


    void addSinger(Singer album);

    Singer getSinger(String teamName);

    Singer getSinger(int teamId);

    void updateSinger(Singer team);

    void deleteSinger(Singer team);

    void deleteSinger(int teamId);
}
