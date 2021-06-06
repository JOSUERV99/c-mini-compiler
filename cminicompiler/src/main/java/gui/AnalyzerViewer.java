package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import errors.LexicalError;
import errors.SyntaxError;
import handler.Analyzer;

public class AnalyzerViewer extends JFrame {

    private Analyzer analyzer;

    private JList<String> lexicalErrors, syntacticalErrors;
    private JScrollPane lexicalScroller, syntacticalScroller;
    private JLabel filenameTag;
    private JButton btnLoadFile, btnRunAnalyzer;

    private String selectedFile;
    private String fullnameFile;

    public AnalyzerViewer(Analyzer analyzer) {
        this.analyzer = analyzer;
        this.selectedFile = this.analyzer.getFileInputName();
        this.init();
    }

    private void init() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        this.getContentPane().setLayout(null);
        this.setVisible(true);

        this.refreshView();
    }

    private void populateLists() {

        ArrayList<LexicalError> lexErrors = this.analyzer.getLexicalErrors();
        ArrayList<SyntaxError> synErrors = this.analyzer.getSyntaxErrors();

        DefaultListModel<String> m1 = new DefaultListModel<String>();
        lexErrors.forEach(e -> {
            m1.addElement(e.getErrorMessage());
        });
        lexicalErrors.setModel(m1);

        DefaultListModel<String> m2 = new DefaultListModel<String>();
        synErrors.forEach(e -> {
            m2.addElement(e.getErrorMessage());
        });
        syntacticalErrors.setModel(m2);
    }

    public void refreshView() {

        JPanel panel = new JPanel(new BorderLayout());
        Font f1 = new Font("Times New Roman", Font.PLAIN, 15);

        lexicalErrors = new JList<String>();
        lexicalErrors.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lexicalErrors.setLayoutOrientation(JList.VERTICAL);
        lexicalErrors.setVisibleRowCount(-1);
        lexicalErrors.setFont(f1);

        lexicalScroller = new JScrollPane(lexicalErrors);
        lexicalScroller
                .setPreferredSize(new Dimension((int) this.getSize().getWidth() / 2, (int) this.getSize().getHeight()));

        syntacticalErrors = new JList<String>();
        syntacticalErrors.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        syntacticalErrors.setLayoutOrientation(JList.VERTICAL);
        syntacticalErrors.setVisibleRowCount(-1);
        syntacticalErrors.setFont(f1);

        syntacticalScroller = new JScrollPane(syntacticalErrors);
        syntacticalScroller
                .setPreferredSize(new Dimension((int) this.getSize().getWidth() / 2, (int) this.getSize().getHeight()));

        panel.add(lexicalScroller, BorderLayout.WEST);
        panel.add(syntacticalScroller, BorderLayout.EAST);

        JPanel panel1 = new JPanel(new BorderLayout());

        filenameTag = new JLabel(
                "Working with: (" + (this.selectedFile == null ? "No file selected" : this.selectedFile) + ")");
        filenameTag.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(filenameTag, BorderLayout.CENTER);

        btnLoadFile = new JButton();
        btnLoadFile.setText("Select file");
        btnLoadFile.setFont(f1);
        btnLoadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        panel1.add(btnLoadFile, BorderLayout.EAST);

        btnRunAnalyzer = new JButton();
        btnRunAnalyzer.setText("RUN");
        btnRunAnalyzer.setFont(f1);
        btnRunAnalyzer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    analyzer.compile(fullnameFile);
                    refreshView();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        panel1.add(btnRunAnalyzer, BorderLayout.WEST);
        panel.add(panel1, BorderLayout.NORTH);

        this.populateLists();

        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void selectFile() throws IOException, Exception {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            this.fullnameFile = selectedFile.getCanonicalPath();
            this.selectedFile = selectedFile.getName();
            this.analyzer.setFileInputName(selectedFile.getCanonicalPath());
            this.refreshView();
        }
    }
}
