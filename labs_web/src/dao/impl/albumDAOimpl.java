package dao.impl;

import dao.DAO;
import dao.albumDAO;
import model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class albumDAOimpl implements albumDAO {
    private DAO dao;

    public albumDAOimpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void addAlbum(Album album) {
        String query = "INSERT INTO album (album_name, genre, id) VALUES " +
                "(?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, album.getAlbum_name());
            preparedStatement.setString(2, album.getGenre());
            preparedStatement.setInt(3, album.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Album getAlbum(String album_name) {
        String query = "SELECT * FROM album WHERE album_name = ?";
        Album team = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, album_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String genre = resultSet.getString(GENRE);
                team = new Album(name, genre, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public Album getAlbum(int id) {
        String query = "SELECT * FROM album WHERE id = ?";
        Album team = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id1 = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String genre = resultSet.getString(GENRE);
                team = new Album(name, genre, id1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public void updateAlbum(Album album) {
        String query = "UPDATE album SET album_name = ? , genre  = ? , id = ?  " +
                "CURRENT_STANDING = ? " +
                "WHERE id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, album.getAlbum_name());
            preparedStatement.setString(2, album.getGenre());
            preparedStatement.setInt(3, album.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbum(Album album) {
        String query = "DELETE match WHERE album_name =? OR id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, album.getAlbum_name());
            preparedStatement.setInt(2, album.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbum(int id) {
        String query = "DELETE FROM album WHERE id=? ";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
