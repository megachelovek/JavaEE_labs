import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import dao.DAO;
import dao.impl.DAOimpl;
import dao.impl.*;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Mainclass;

public class UI_web_labs extends JDialog {
    private JPanel contentPane;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JButton GetTableButton;
    private JTextArea textArea1;
    private JEditorPane pane = new JEditorPane();
    public DAO dao;



    public UI_web_labs() {
        dao = new DAOimpl();
        dao.setURL(DAOimpl.DEFAULT_HOST, DAOimpl.DEFAULT_DATABASE, DAOimpl.DEFAULT_PORT);
        dao.connect(DAOimpl.DEFAULT_LOGIN, DAOimpl.DEFAULT_PASSWORD);

        setContentPane(contentPane);
        setModal(true);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        GetTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        ActionListener GetQueryActionListener1 = new GetQueryActionListener();
        GetTableButton.addActionListener(GetQueryActionListener1);


    }
    //Вставка в таблицы
    public class GetQueryActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StyledDocument doc = textPane1.getStyledDocument();
            StringBuilder stringBuilder = new StringBuilder();
            if (comboBox1.getSelectedItem() == "singer") {
                try {
                    String sixthQue = "SELECT sing.name, alb.album_name, alb.genre, trck.track_name, trck.time " +
                            "FROM track trck, singer sing, album alb " +
                            "WHERE sing.id_album = alb.id " +
                            "AND alb.id = trck.id_album " +
                            "AND sing.name = ?";
                    try (Connection connection = dao.getConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(sixthQue);
                        preparedStatement.setString(1, textArea1.getText());
                        ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
                            stringBuilder.append("\n");
                            stringBuilder.append(rs.getString("name")).append(", ");
                            stringBuilder.append(rs.getString("album_name")).append(", ");
                            stringBuilder.append(rs.getString("genre")).append(", ");
                            stringBuilder.append(rs.getString("track_name")).append(", ");
                            stringBuilder.append(rs.getString("time")).append("\n");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    doc.insertString(doc.getLength(), stringBuilder.toString(), null);
                    //textPane1.setText(stringBuilder.toString());
                } catch (Exception ex) { }
            } else try{ {doc.insertString(doc.getLength(), "Неправильные данные", null);}}catch (Exception ex) { }
        }
    }
    //Получение таблиц
    public class GetTableActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
    //Получение таблиц
    public class DeleteTableActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        UI_web_labs dialog = new UI_web_labs();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
