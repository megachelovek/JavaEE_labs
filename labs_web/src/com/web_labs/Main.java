package com.web_labs;

import dao.DAO;
import dao.impl.DAOimpl;
import java.sql.*;

public class Main {

    public static void main(String[] args) {



        DAO dao = new DAOimpl();
        dao.setURL(DAOimpl.DEFAULT_HOST, DAOimpl.DEFAULT_DATABASE, DAOimpl.DEFAULT_PORT);
        dao.connect(DAOimpl.DEFAULT_LOGIN, DAOimpl.DEFAULT_PASSWORD);

        int artistId = 2222222;

        //проверка первого запроса
        System.out.println("----1----");
        String firstQue = "SELECT track_name || ','|| time as \"Song_details\" " +
                "FROM track";
        try(Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(firstQue);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String track;
                track = "{" + resultSet.getString("Song_details") + "}";
                System.out.println(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("----1----Girls");
        String secondQue = "Select track_name, time from track where track_name = ? ";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(secondQue);
            preparedStatement.setString(1,"Girls");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{");
                stringBuilder.append(rs.getString("track_name")).append(",");
                stringBuilder.append(rs.getString("time")).append("}");
                System.out.println(stringBuilder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //добавлениe
        System.out.println("----1----добавление");
        String trhirdQue = "INSERT INTO singer(name) VALUES (?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(trhirdQue);
            preparedStatement.setString(1, "TestSinger");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //редактированиe
        System.out.println("----1----редактирование");
        String fourthQue = "UPDATE singer SET name = ? WHERE name =?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(fourthQue);
            preparedStatement.setString(1, "SuperTestSinger");
            preparedStatement.setString(2, "TestSinger");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //удалениe
        System.out.println("----1----удаление");
        String fifthQue = "DELETE FROM singer WHERE name = ? OR name =?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(fifthQue);
            preparedStatement.setString(1, "TestSinger");
            preparedStatement.setString(2, "SuperTestSinger");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //2 задание
        System.out.println("----2----");
        String sixthQue = "SELECT sing.name, alb.album_name, alb.genre, trck.track_name, trck.time " +
                "FROM track trck, singer sing, album alb " +
                "WHERE sing.id_album = alb.id " +
                "AND alb.id = trck.id_album " +
                "AND sing.name = ?";
        try (Connection connection = dao.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sixthQue);
            preparedStatement.setString(1,"Valera");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{");
                stringBuilder.append(rs.getString("name")).append(", ");
                stringBuilder.append(rs.getString("album_name")).append(", ");
                stringBuilder.append(rs.getString("genre")).append(", ");
                stringBuilder.append(rs.getString("track_name")).append(", ");
                stringBuilder.append(rs.getString("time")).append("}");
                System.out.println(stringBuilder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //3 задание
        System.out.println("----3----");
        try (Connection connection = dao.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rsDB = databaseMetaData.getTables("web_interfaces","public","%", types);
            while (rsDB.next()){
                System.out.println(rsDB.getString(3));
            }
            System.out.println("---таблица Track---");
            String query = "SELECT * FROM track";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSetMetaData metaData = preparedStatement.executeQuery().getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i+1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
