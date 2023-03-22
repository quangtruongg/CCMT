/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author My Laptop
 */
public class ChatForm extends JFrame{
     private JButton btnFile;
	private JButton btnSend;
	private JScrollPane chatPanel;
	private JLabel lbReceiver = new JLabel(" ");
	private JPanel contentPane;
	private JTextField txtMessage;
	private JTextPane chatWindow;
	JComboBox<String> onlineUsers = new JComboBox<String>();

	private String username;
	private DataInputStream dis;
	private DataOutputStream dos;

	private HashMap<String, JTextPane> chatWindows = new HashMap<String, JTextPane>();

	Thread receiver;
        public ChatForm() {
                setTitle("CLG CHAT");	
		
		
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(230, 240, 247));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(160, 190, 223));
		
		txtMessage = new JTextField();
		txtMessage.setEnabled(false);
		txtMessage.setColumns(10);
		
		btnSend = new JButton("");
		btnSend.setEnabled(false);
		btnSend.setIcon(new ImageIcon("data\\icon\\component\\send.png"));
		
		chatPanel = new JScrollPane();
		chatPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(230, 240, 247));
		
		btnFile = new JButton("");
		
		btnFile.setEnabled(false);
		btnFile.setIcon(new ImageIcon("data\\icon\\component\\attach.png"));
		
		JPanel emojis = new JPanel();
		emojis.setBackground(new Color(230,240,247));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(header, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(emojis, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtMessage, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFile, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(header, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(emojis, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFile, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMessage, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)))
		);
		
		JLabel smileIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\smile.png"));
		emojis.add(smileIcon);
		
		JLabel bigSmileIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\big-smile.png"));
		emojis.add(bigSmileIcon);
		
		JLabel happyIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\happy.png"));
		emojis.add(happyIcon);
		
		JLabel loveIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\love.png"));
		emojis.add(loveIcon);
		
		JLabel sadIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\sad.png"));
		emojis.add(sadIcon);
		
		JLabel madIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\mad.png"));
		emojis.add(madIcon);
		
		JLabel suspiciousIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\suspicious.png"));
		emojis.add(suspiciousIcon);
		
		JLabel angryIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\angry.png"));
		emojis.add(angryIcon);
		
		JLabel confusedIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\confused.png"));
		emojis.add(confusedIcon);
		
		JLabel unhappyIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\unhappy.png"));
		emojis.add(unhappyIcon);
		
		JLabel appleIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\apple.png"));
		emojis.add(appleIcon);
		
		JLabel orangeIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\orange.png"));
		emojis.add(orangeIcon);
		
		JLabel cherryIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\cherry.png"));
		emojis.add(cherryIcon);
		
		JLabel cakeIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\cake.png"));
		emojis.add(cakeIcon);
		
		JLabel vietnamIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\vietnam.png"));
		emojis.add(vietnamIcon);
		
		JLabel usIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\us.png"));
		emojis.add(usIcon);
		
		JLabel ukIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\uk.png"));
		emojis.add(ukIcon);
		
		JLabel canadaIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\canadaIcon.png"));
		emojis.add(canadaIcon);
		
		JLabel italyIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\italy.png"));
		emojis.add(italyIcon);
		
		JLabel spainIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\spainIcon.png"));
		emojis.add(spainIcon);
		
		JLabel egyptIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\egyptIcon.png"));
		emojis.add(egyptIcon);
		
		JLabel swedenIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\sweden.png"));
		emojis.add(swedenIcon);
		
		JLabel australiaIcon = new JLabel(new ImageIcon("data\\icon\\emoji\\australia.png"));
		emojis.add(australiaIcon);
		
		
		JLabel userImage = new JLabel(new ImageIcon("data\\icon\\component\\user.png"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,240,247));
		JLabel lblNewLabel_1 = new JLabel("CHAT WITH");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(userImage, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(25))
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(29))
				.addGroup(Alignment.TRAILING, gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(onlineUsers, 0, 101, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(userImage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(onlineUsers, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		
		JLabel lbUsername = new JLabel(this.username);
		lbUsername.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lbUsername);
		leftPanel.setLayout(gl_leftPanel);
		
		JLabel headerContent = new JLabel("CLG CHAT");
		headerContent.setFont(new Font("Poor Richard", Font.BOLD, 24));
		header.add(headerContent);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setBackground(new Color(230,240,247));
		chatPanel.setColumnHeaderView(usernamePanel);
		
		lbReceiver.setFont(new Font("Arial", Font.BOLD, 16));
		usernamePanel.add(lbReceiver);
		
		chatWindows.put(" ", new JTextPane());
		chatWindow = chatWindows.get(" ");
		chatWindow.setFont(new Font("Arial", Font.PLAIN, 14));
		chatWindow.setEditable(false);
		
		chatPanel.setViewportView(chatWindow);
		contentPane.setLayout(gl_contentPane);
		
		
		this.getRootPane().setDefaultButton(btnSend);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				try {
					dos.writeUTF("Log out");
					dos.flush();
					
					try {
						receiver.join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					if (dos != null) {
						dos.close();
					}
					if (dis != null) {
						dis.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        }
        
        public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatForm frame = new ChatForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
