import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RockPaperScissors extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4559351830981579732L;
	
	private JPanel contentPane;
	private JTextField textFieldPCChoose;
	
	public int playerTotalWins = 0, pcTotalWins = 0;
	public int playerRoundWins, pcRoundWins;
	private JTextArea textAreaResults;
	private JTextField textFieldResult;
	private JButton buttonStart;
	private JButton buttonScissors;
	private JButton buttonPaper;
	private JButton buttonRock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RockPaperScissors frame = new RockPaperScissors();
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
	public RockPaperScissors() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSide = new JPanel();
		contentPane.add(panelSide, BorderLayout.EAST);
		panelSide.setLayout(new BorderLayout(0, 0));
		
		JPanel panelScore = new JPanel();
		panelSide.add(panelScore);
		panelScore.setLayout(new BorderLayout(0, 0));
		
		textAreaResults = new JTextArea();
		textAreaResults.setColumns(10);
		textAreaResults.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textAreaResults);
		panelScore.add(scrollPane);
		
		JPanel panelGame = new JPanel();
		contentPane.add(panelGame, BorderLayout.CENTER);
		panelGame.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		panelGame.add(panelButtons, BorderLayout.CENTER);
		panelButtons.setLayout(new GridLayout(0, 3, 0, 0));
		
		buttonRock = new JButton("Rock");
		buttonRock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick(1);
			}
		});
		panelButtons.add(buttonRock);
		
		buttonPaper = new JButton("Paper");
		buttonPaper.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick(2);
			}
		});
		panelButtons.add(buttonPaper);
		
		buttonScissors = new JButton("Scissors");
		buttonScissors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick(3);
			}
		});
		panelButtons.add(buttonScissors);
		
		buttonStart = new JButton("Start game");
		buttonStart.setEnabled(false);
		buttonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldPCChoose.setText("");
				textFieldResult.setText("");
				playerRoundWins = 0;
				pcRoundWins = 0;
				buttonStart.setText("New Round");
				ToggleButtons();
			}
		});
		panelSide.add(buttonStart, BorderLayout.NORTH);
		
		JPanel panelPCChoose = new JPanel();
		panelGame.add(panelPCChoose, BorderLayout.SOUTH);
		panelPCChoose.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelPCChoose.add(panel);
		
		JLabel lblPcChose = new JLabel("PC chose:");
		panel.add(lblPcChose);
		
		textFieldPCChoose = new JTextField();
		panel.add(textFieldPCChoose);
		textFieldPCChoose.setEditable(false);
		textFieldPCChoose.setColumns(30);
		
		JPanel panel_1 = new JPanel();
		panelPCChoose.add(panel_1);
		
		JLabel lblResult = new JLabel("Result: ");
		panel_1.add(lblResult);
		
		textFieldResult = new JTextField();
		textFieldResult.setEditable(false);
		textFieldResult.setColumns(30);
		panel_1.add(textFieldResult);
		
		
		ToggleButtons();
	}
	
	private void onClick(int playerChoice) {
		/*	1 = Rock
		 * 	2 = Paper
		 * 	3 = Scissors
		 */	
		
		int pcChoice = ((new Random().nextInt(299)+1)%3) + 1;
		
		switch (pcChoice) {
		case 1:
			textFieldPCChoose.setText("Rock");
			break;
		case 2:
			textFieldPCChoose.setText("Paper");
			break;
		case 3:
			textFieldPCChoose.setText("Scissors");
			break;
		}
		
		if(pcChoice==playerChoice) {
			//Tie
			textFieldResult.setText("Tie");
		} else if (playerChoice+1==pcChoice || (playerChoice==2 && pcChoice==0)) {
			//Pc won
			pcRoundWins++;
			textFieldResult.setText("PC Won");
		} else {
			//Player won
			playerRoundWins++;
			textFieldResult.setText("Player won");
		}
		
		if(playerRoundWins > 2) {
			textAreaResults.append("Player: " + ++playerTotalWins + "  PC: " + pcTotalWins + "\n");
			playerRoundWins = 0;
			pcRoundWins = 0;
			ToggleButtons();
		} else if (pcRoundWins > 2) {
			textAreaResults.append("Player: " + playerTotalWins + "  PC: " + ++pcTotalWins + "\n");
			playerRoundWins = 0;
			pcRoundWins = 0;
			ToggleButtons();
		}
	}
	
	private void ToggleButtons() {
		buttonRock.setEnabled(!buttonRock.isEnabled());
		buttonPaper.setEnabled(!buttonPaper.isEnabled());
		buttonScissors.setEnabled(!buttonScissors.isEnabled());
		buttonStart.setEnabled(!buttonStart.isEnabled());
	}
}
