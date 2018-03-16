import dao.DAO;
import dao.impl.albumDAOimpl;

import javax.swing.*;
import java.awt.event.*;

public class UI_web_labs extends JDialog {
    private JPanel contentPane;
  //  private JButton buttonOK;
  //  private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextPane textPane1;
    private JButton GetTableButton;

    public UI_web_labs() {
        setContentPane(contentPane);
        setModal(true);
      //  DAO al = new DAO();
//        getRootPane().setDefaultButton(buttonOK);

//        buttonOK.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onOK();
//            }
//        });

//        buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });

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
               // albumDAOimpl al = new albumDAOimpl();
                //String ss=get;
                //textPane1.append;
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
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
