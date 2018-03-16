package dao.impl;

import dao.DAO;
import dao.singerDAO;
import model.Singer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class singerDAOimpl implements singerDAO {
    private DAO dao;

    public singerDAOimpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void addSinger(Singer singer) {
        String query = "INSERT INTO singer (name, id) VALUES " +
                "(?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, singer.getName());
            preparedStatement.setInt(2, singer.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Singer getSinger(String name) {
        String query = "SELECT * FROM singer WHERE name = ?";
        Singer singer = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name1 = resultSet.getString(NAME);
                singer = new Singer(name1, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singer;
    }

    @Override
    public Singer getSinger(int id) {
        String query = "SELECT * FROM singer WHERE id = ?";
        Singer singer = null;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id1 = resultSet.getInt(ID);
                String name1 = resultSet.getString(NAME);
                singer = new Singer(name1, id1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singer;
    }

    @Override
    public void updateSinger(Singer singer) {
        String query = "UPDATE singer SET name = ? , id = ?  " +
                "CURRENT_STANDING = ? " +
                "WHERE id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, singer.getName());
            preparedStatement.setInt(3, singer.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSinger(Singer singer) {
        String query = "DELETE singer WHERE name =? OR id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, singer.getName());
            preparedStatement.setInt(2, singer.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSinger(int id) {
        String query = "DELETE FROM singer WHERE id=? ";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
