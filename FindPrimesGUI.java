package assignment4;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
	
	public class FindPrimesGUI extends JFrame {
		public final JButton getPrimesJButton = new JButton("Get Primes");
		public final JTextArea displayPrimesJTextArea = new JTextArea();
		public final JButton cancelJButton = new JButton("Cancel");
		public final JProgressBar progressJProgressBar = new JProgressBar();
		public final JLabel statusJLabel = new JLabel();
		public final JTextField highestPrimeJTextField = new JTextField();
		
		JMenuBar menuBar = new JMenuBar();
		private JMenuItem mntmLoad;
		private JMenuItem mntmSave;
		private JMenuItem mntmExit;
		private JMenu mnOperations;
		private JMenuItem mntmOrderA;
		private JMenuItem mntmOrderD;
		private JMenuItem mntmShuffle;
		private ArrayList<Integer> PrimeList;
		int []array = new int[1000];
		ArrayList <Integer> list= new ArrayList<>();
		int countNumber = 0;
		
		public FindPrimesGUI() {

			super("Finding Primes");
			setTitle("Finding Primes Application");
			PrimeList = new ArrayList<Integer>();
			getContentPane().setLayout(new BorderLayout());

			JPanel northJPanel = new JPanel();
			setJMenuBar(menuBar);
			JMenu mnFile = new JMenu("File");
			menuBar.add(mnFile);
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						JFileChooser fs = new JFileChooser(new File("c:\\"));
						fs.setDialogTitle("Save File");
						int result = fs.showSaveDialog(null);
						if(result == fs.APPROVE_OPTION) {
							String content = displayPrimesJTextArea.getText();
							File fi = fs.getSelectedFile();
						try {
							PrintWriter writer = new PrintWriter(fi.getPath());
							writer.println(displayPrimesJTextArea.getText());
							writer.close();
							
						
							 
						}catch(IOException e1)
					{e1.printStackTrace();}
						JOptionPane.showMessageDialog(null, "File Saved");}}
			});
			mnFile.add(mntmSave);

			mntmLoad = new JMenuItem("Load");
			mntmLoad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fs = new JFileChooser(new File("c:\\"));
					fs.setDialogTitle("Load File");
					int result = fs.showOpenDialog(null);
					if(result == fs.APPROVE_OPTION) {
						File fi = fs.getSelectedFile();
					try {
						FileInputStream inputFile = new FileInputStream(fi.getPath());
						Scanner reader = new Scanner(inputFile);
						String totalFileContents="";
						while (reader.hasNext() )
						{
							totalFileContents += reader.nextLine()+"\n";
													};
													displayPrimesJTextArea.setText(totalFileContents);						
					}catch(Exception e1) {
						e1.printStackTrace();
					}}
				}
			});
			
			mnFile.add(mntmLoad);
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() 
				{ public void actionPerformed(ActionEvent arg0) 
				{ System.exit(0); 
				} 
				});

			
			mnFile.add(mntmExit);
			mnOperations = new JMenu("Operations ");
			menuBar.add(mnOperations);
			mntmOrderA = new JMenuItem("Order ascending");
			mntmOrderA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					displayPrimesJTextArea.setText(null);
					
					for(int p=0;p<=Integer.parseInt(highestPrimeJTextField.getText()); p++) {
						if(array[p]!=0) {
						displayPrimesJTextArea.setText(displayPrimesJTextArea.getText() + String.valueOf(array[p])+'\n');
						}}
				}
			});
			mnOperations.add(mntmOrderA);
			mntmOrderD = new JMenuItem("Order descending ");
			mntmOrderD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					displayPrimesJTextArea.setText(null);
					
					for(int p=Integer.parseInt(highestPrimeJTextField.getText()); p>=0; p--) {
						if(array[p]!=0) {
						displayPrimesJTextArea.setText(displayPrimesJTextArea.getText() + String.valueOf(array[p])+'\n');
						}}
				}
			});
			mnOperations.add(mntmOrderD);
			
			mntmShuffle = new JMenuItem("Shuffle ");
			mntmShuffle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					displayPrimesJTextArea.setText(null);
					Collections.shuffle(list);
					for (int indexx : list) {
					displayPrimesJTextArea.setText(displayPrimesJTextArea.getText() + String.valueOf(indexx)+'\n');}
					
				}
			});
			mnOperations.add(mntmShuffle);
			northJPanel.add(new JLabel("Find primes less than: "));
			
			highestPrimeJTextField.setColumns(5);
			northJPanel.add(highestPrimeJTextField);
			
			getPrimesJButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					displayPrimesJTextArea.setText(null);
					getPrimesJButton.setEnabled(false);
					cancelJButton.setEnabled(true);
					countNumber = 0;
					ArrayList <Integer> list = new ArrayList<>();
					list.clear();
					if(CheckNumber(highestPrimeJTextField.getText())==true)
					{
						start();
						}
					else
					{JOptionPane aa = new JOptionPane();
					aa.showMessageDialog(null, "You can only enter numbers");
					getPrimesJButton.setEnabled(true);
					cancelJButton.setEnabled(false);
					}
					
				};
			});
			northJPanel.add(getPrimesJButton);

			displayPrimesJTextArea.setEditable(false);
			getContentPane().add(new JScrollPane(displayPrimesJTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));

			JPanel southJPanel = new JPanel();
			GridBagLayout gbl_southJPanel = new GridBagLayout();
			gbl_southJPanel.rowHeights = new int[] { 16, 0 };
			gbl_southJPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
			gbl_southJPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			southJPanel.setLayout(gbl_southJPanel);
			progressJProgressBar.setStringPainted(true);
			GridBagConstraints gbc_progressJProgressBar = new GridBagConstraints();
			gbc_progressJProgressBar.weightx = 0.6;
			gbc_progressJProgressBar.gridwidth = 2;
			gbc_progressJProgressBar.fill = GridBagConstraints.BOTH;
			gbc_progressJProgressBar.insets = new Insets(0, 0, 0, 1);
			gbc_progressJProgressBar.gridx = 0;
			gbc_progressJProgressBar.gridy = 0;
			southJPanel.add(progressJProgressBar, gbc_progressJProgressBar);
			

			getContentPane().add(northJPanel, BorderLayout.NORTH);
			
			
			cancelJButton.setEnabled(false);
			cancelJButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try { Thread.sleep(1000); } 
					catch (InterruptedException e) {} 
					Thread.interrupted();

			}
			});
			northJPanel.add(cancelJButton);

			getContentPane().add(southJPanel, BorderLayout.SOUTH);
			GridBagConstraints gbc_statusJLabel = new GridBagConstraints();
			gbc_statusJLabel.weightx = 0.3;
			gbc_statusJLabel.anchor = GridBagConstraints.EAST;
			gbc_statusJLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_statusJLabel.gridx = 2;
			gbc_statusJLabel.gridy = 0;
			southJPanel.add(statusJLabel, gbc_statusJLabel);
			setSize(442, 415);
			setVisible(true);
		
		}
		private void start() {
			list.clear();
			SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>(){
				
			@Override
			protected Boolean doInBackground() throws Exception{
			
				for(int i=2; i<=(Integer.parseInt(highestPrimeJTextField.getText()));i++) {
				
				boolean isPrime= true;
				
				for(int j=2; j*j<=i; j++) { 
					if(i % j == 0) { 
					isPrime = false; 
					try
					{Thread.sleep(100);
						progressJProgressBar.setValue(100 * i / Integer.parseInt(highestPrimeJTextField.getText()));
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break; 
					}; 
				};
				if(isPrime) { 
					
					array[i] = i;
					countNumber ++;
					list.add(i);
					publish(i);
				}
				
			
				}; 			
				
			
			return true;
			}
		@Override
			protected void process(List<Integer> chunks) {
		for (int index : chunks) {	//변수타입, 변수이름, 배열이름
			try {
				Thread.sleep(300);	
				displayPrimesJTextArea.setText(displayPrimesJTextArea.getText() + String.valueOf(index)+'\n');
					
					}
				 catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}			
			
		}//중간에 GUI업데이트하는 것
		@Override
			protected void done() {
			statusJLabel.setText("Found "+ String.valueOf(countNumber)+" primes.");
			getPrimesJButton.setEnabled(true);
			cancelJButton.setEnabled(false);
			};//GUI업데이트
			
		};
		worker.execute();
		}
		
		
		public static void main(String[] args) {
			FindPrimesGUI application = new FindPrimesGUI();
			application.setDefaultCloseOperation(EXIT_ON_CLOSE);
		} // end main
		public boolean CheckNumber(String str){
			char check;
			
			if(str.equals(""))
			{
				return false;
			}
			
			for(int k = 0; k<str.length(); k++){
				check = str.charAt(k);
				if( check < 48 || check > 58)
				{
					return false;
				}
				
			}		
			return true;
		}
		
		
	} 
