import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
    static String host = "jdbc:mysql://localhost:3306/empData";
    static String user = "root";
    static String pass = "";

    private JTextField nameField;
    private JTextField roleField;

    public App() {
        setTitle("Insert Data");

        JLabel nameLabel = new JLabel("Name:");
        JLabel roleLabel = new JLabel("Role:");

        nameField = new JTextField(20);
        roleField = new JTextField(20);

        JButton insertButton = new JButton("Insert Data");

        setLayout(null);

        nameLabel.setBounds(20, 20, 80, 25);
        roleLabel.setBounds(20, 50, 80, 25);
        nameField.setBounds(100, 20, 150, 25);
        roleField.setBounds(100, 50, 150, 25);
        insertButton.setBounds(100, 80, 150, 25);

        add(nameLabel);
        add(roleLabel);
        add(nameField);
        add(roleField);
        add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });
    }

    private void insertData() {
        String name = nameField.getText();
        String role = roleField.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(host, user, pass);
            Statement stmt = con.createStatement();

            String query = "INSERT INTO emp (name, role) VALUES ('" + name + "', '" + role + "')";
            stmt.executeUpdate(query);

            con.close();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.setSize(300, 150);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
