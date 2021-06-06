package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import errors.LexicalError;
import errors.SyntaxError;
import handler.Analyzer;

public class AnalyzerFrame extends JFrame {

	private JPanel contentPane;

	private String errorsAmountStr = "Errors: 0  ";
	private String fileSelectedStr = "File: No selected  ";
	private Analyzer analyzer = new Analyzer();
	private String selectedFile, fullFilename;

	private JLabel lblErrorsAmount, lblCurrentFile;
	private JTree errorsTree;

	private ArrayList<LexicalError> lexErrors;
	private ArrayList<SyntaxError> synErrors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzerFrame frame = new AnalyzerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnalyzerFrame() {

		setTitle("CMiniCompiler - Lexical and Syntactical Analyzer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnLoadFile = new JButton("Select file");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectFile();

					fileSelectedStr = "File: " + getSelectedFile() + "   ";
					lblCurrentFile.setText(fileSelectedStr);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnLoadFile);

		lblCurrentFile = new JLabel(this.fileSelectedStr);
		panel.add(lblCurrentFile);

		JSeparator separator = new JSeparator();
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					analyzer.compile(fullFilename, "");
					populateLists();
					updateTags();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnRun);

		lblErrorsAmount = new JLabel(this.errorsAmountStr);
		panel.add(lblErrorsAmount);

		errorsTree = new JTree();
		contentPane.add(errorsTree, BorderLayout.CENTER);
		errorsTree.setModel(null);
	}

	private void populateLists() {

		this.lexErrors = this.analyzer.getLexicalErrors();
		this.synErrors = this.analyzer.getSyntaxErrors();

		createTree();

		updateTags();
	}

	private void updateTags() {
		this.errorsAmountStr = "Errors:  " + (this.lexErrors.size() + this.synErrors.size()) + "  ";
		this.fileSelectedStr = "File: " + this.getSelectedFile() + "   ";

		this.lblErrorsAmount.setText(this.errorsAmountStr);
		this.lblCurrentFile.setText(this.fileSelectedStr);
	}

	private void createTree() {

		DefaultMutableTreeNode errorsNode = new DefaultMutableTreeNode("Errors");

		DefaultMutableTreeNode lexicalNode = new DefaultMutableTreeNode("Lexical errors");
		for (LexicalError e : this.lexErrors)
			lexicalNode.add(new DefaultMutableTreeNode(e.getErrorMessage()));

		DefaultMutableTreeNode syntacticalNode = new DefaultMutableTreeNode("Syntactical errors");
		for (SyntaxError e : this.synErrors)
			syntacticalNode.add(new DefaultMutableTreeNode(e.getErrorMessage()));

		this.errorsTree.setModel(null);
		DefaultTreeModel model = new DefaultTreeModel(errorsNode);
		model.insertNodeInto(lexicalNode, errorsNode, 0);
		model.insertNodeInto(syntacticalNode, errorsNode, 1);
		this.errorsTree.setModel(model);
	}

	public void selectFile() throws IOException, Exception {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			this.setSelectedFile(selectedFile.getName());
			this.fullFilename = selectedFile.getCanonicalPath();
			this.analyzer.setFileInputName(this.fullFilename);
		}
	}

	public String getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}

}
