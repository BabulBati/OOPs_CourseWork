package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionsViewGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuestionsViewGUI frame = new QuestionsViewGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void loadQuestions() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String sql = "SELECT * FROM Questions";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("questionID"),
                        rs.getString("questionText"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("correctOption"),
                        rs.getString("difficulty")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading questions: " + e.getMessage());
        }
    }

    public QuestionsViewGUI() {
    	setBackground(Color.GRAY);
        setTitle("View Questions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 500);

        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("All Questions", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(250, 10, 400, 30);
        contentPane.add(lblTitle);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 60, 780, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(new Color(50, 50, 50));
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(0, 0, 0));
        table.getTableHeader().setBackground(new Color(180, 200, 255));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Question", "Option A", "Option B", "Option C", "Option D", "Correct", "Difficulty" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        table.getColumnModel().getColumn(0).setPreferredWidth(30);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Question
        table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Option A
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Option B
        table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Option C
        table.getColumnModel().getColumn(5).setPreferredWidth(100);  // Option D
        table.getColumnModel().getColumn(6).setPreferredWidth(55);   // Correct
        table.getColumnModel().getColumn(7).setPreferredWidth(100);  // Difficulty

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        loadQuestions();

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.PLAIN, 16));
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(60, 380, 120, 30);
        btnUpdate.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(QuestionsViewGUI.this, "Select a question to update!");
                return;
            }
            int questionID = (int) table.getValueAt(selectedRow, 0);
            new QuestionUpdateGUI(questionID, QuestionsViewGUI.this).setVisible(true);
        });
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 16));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(192, 380, 120, 30);
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(QuestionsViewGUI.this, "Select a question to delete!");
                return;
            }
            int questionID = (int) table.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(QuestionsViewGUI.this,
                    "Are you sure you want to delete this question?", "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Questions WHERE questionID = ?")) {
                    pstmt.setInt(1, questionID);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(QuestionsViewGUI.this, "Question deleted!");
                    loadQuestions();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(QuestionsViewGUI.this, "Error deleting question: " + ex.getMessage());
                }
            }
        });
        contentPane.add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 16));
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(324, 380, 120, 30);
        btnBack.addActionListener(e -> dispose());
        contentPane.add(btnBack);
    }
}
