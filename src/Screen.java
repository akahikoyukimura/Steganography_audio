import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BusinessLogic.encodeDecodeService;
import BusinessLogic.encodeScript;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class Screen extends JFrame {

	private String pass;
	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	final JFrame fenetre = new JFrame();
	private JTextField lblNewLabel;
	private JTextField message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen frame = new Screen();
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
	public Screen() {
		
		this.openFileChooser = new JFileChooser();
		encodeScript enc=new encodeScript();
		//String pass=new String();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Encode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String msg =message.getText() ;
				String filePath = lblNewLabel.getText();
				String outPath = filePath.substring(0,filePath.length()-4)+"-Encoded.wav";
				
				encodeDecodeService t=new encodeDecodeService();
				t.codificaMensagem(msg, filePath, outPath);
				System.out.println("Successfully encoded the message into " + outPath);

				
			}
		});
		btnNewButton.setBounds(137, 10, 83, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Decode");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				encodeDecodeService t=new encodeDecodeService();
				System.out.println(t.decodificaMensagem(lblNewLabel.getText()));
				message.setText(t.decodificaMensagem(lblNewLabel.getText()));
		        
			}
		});
		btnNewButton_1.setBounds(334, 10, 83, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Browse");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 openFileChooser.setCurrentDirectory(new File("C:\\"));
				 openFileChooser.setFileFilter(new FileNameExtensionFilter("wav", "wav"));
				 
				 int returnVal=openFileChooser.showOpenDialog(fenetre);
				 
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		 
		                    lblNewLabel.setText(openFileChooser.getSelectedFile().toString());
		 
		            } else {
		 
		                lblNewLabel.setText("no file choosen!!");
		 
		            }
				
				
				
			}
		});
		btnNewButton_2.setBounds(26, 100, 83, 21);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel = new JTextField();
		lblNewLabel.setText("select file");
		lblNewLabel.setBounds(117, 101, 430, 19);
		contentPane.add(lblNewLabel);
		lblNewLabel.setColumns(10);
		
		message = new JTextField();
		message.setBounds(26, 156, 521, 139);
		contentPane.add(message);
		message.setColumns(10);
		
		JButton key = new JButton("Generate key");
		key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 SecretKey key;
				 final int KEY_SIZE = 256;
				 final int T_LEN = 128;
				 
				 KeyGenerator generator=null;
				try {
					generator = KeyGenerator.getInstance("AES");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				    generator.init(KEY_SIZE);
				    key = generator.generateKey();
				    System.out.println(key);
				    
			}
			
		});
		key.setBounds(26, 131, 83, 21);
		contentPane.add(key);
		
		
	}
}
