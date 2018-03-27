package dao.impl;

import dao.DAO;
import dao.trackDAO;
import model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class trackDAOimpl implements trackDAO{

    private DAO dao;

    public trackDAOimpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void addTrack(Track track) {
        String query = "INSERT INTO track (track_name,time,id,id_album) VALUES " +
                "(?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, track.getTrack_name());
            preparedStatement.setInt(2, track.getTime());
            preparedStatement.setInt(3, track.getId());
            preparedStatement.setInt(4, track.getId_album());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Track getTrack(String name) {
        String query = "SELECT * FROM track WHERE track_name = ?";
        Track track = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int time = resultSet.getInt(TIME);
                int id_album = resultSet.getInt(ID_ALBUM);
                int id = resultSet.getInt(ID);
                String name1 = resultSet.getString(NAME);
                track = new Track(name1,time,id,id_album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public Track getTrack(int id) {
        String query = "SELECT * FROM singer WHERE id = ?";
        Track track = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int time = resultSet.getInt(TIME);
                int id_album = resultSet.getInt(ID_ALBUM);
                int id1 = resultSet.getInt(ID);
                String name1 = resultSet.getString(NAME);
                track = new Track(name1,time,id1,id_album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public void updateTrack(Track track) {
        String query = "UPDATE track SET track_name = ? , time = ?,id=?, id_album = ?  " +
                "CURRENT_STANDING = ? " +
                "WHERE id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, track.getTrack_name());
            preparedStatement.setInt(2, track.getTime());
            preparedStatement.setInt(3, track.getId());
            preparedStatement.setInt(4, track.getId_album());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateTrackSingerId(int id, int id2) {
        String query = "UPDATE track SET id_singer=?  " +
                "CURRENT_STANDING = ? " +
                "WHERE id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(4, id2);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrack(Track track) {
        String query = "DELETE track WHERE track_name =? OR id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, track.getTrack_name());
            preparedStatement.setInt(2, track.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrack(int id) {
        String query = "DELETE FROM track WHERE id=? ";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
