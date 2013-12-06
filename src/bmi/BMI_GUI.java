package bmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.UIManager;



public class BMI_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTezina;
	private JTextField txtBMI;
	private JTextField txtVisina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI_GUI frame = new BMI_GUI();
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
	public BMI_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setTitle("Body Mass Index");
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JLabel lblTezina = new JLabel("Tezina:");
		lblTezina.setBounds(44, 41, 52, 15);
		contentPane.add(lblTezina);
		
		JLabel lblVisina = new JLabel("Visina:");
		lblVisina.setBounds(48, 80, 48, 15);
		contentPane.add(lblVisina);
		
		txtTezina = new JTextField();
		txtTezina.setBounds(114, 39, 114, 19);
		contentPane.add(txtTezina);
		txtTezina.setColumns(10);
		
		txtBMI = new JTextField();
		txtBMI.setBounds(114, 124, 114, 19);
		contentPane.add(txtBMI);
		txtBMI.setColumns(10);
		
		txtVisina = new JTextField();
		txtVisina.setBounds(114, 78, 114, 19);
		contentPane.add(txtVisina);
		txtVisina.setColumns(10);
		
		JLabel lblBmi = new JLabel("BMI:");
		lblBmi.setBounds(47, 126, 30, 15);
		contentPane.add(lblBmi);
		
		final JLabel lblStanje = new JLabel("",SwingConstants.CENTER);
		lblStanje.setBounds(274, 128, 150, 15);
		contentPane.add(lblStanje);
		
		final JLabel lblSlika = new JLabel("");
		lblSlika.setBounds(328, 41, 40, 78);
		contentPane.add(lblSlika);	
		
		JButton btnNewButton = new JButton("Izracunaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean visinaUneta = txtVisina.getText().isEmpty();
				boolean tezinaUneta = txtTezina.getText().isEmpty();
				
				if (visinaUneta || tezinaUneta ){
					lblStanje.setText("Unesite sve podatke");
				}	
				
				double tezina = Double.parseDouble(txtTezina.getText());
				double visina = Double.parseDouble(txtVisina.getText());
				
				if ((visina > 2.4 || visina < 1.3) || (tezina > 160 || tezina < 40))
					lblStanje.setText("Neipsravni podaci");
				else {
				
					double index = BMI_Calculator.izracunajBMI(visina, tezina);
					DecimalFormat df = new DecimalFormat("#.#####");
					String bmi = df.format(index);
					txtBMI.setText(bmi);
				
					if (index <= 18.5){
						lblStanje.setText("ANOREKSIJA");
						lblStanje.setForeground(Color.red);
						lblSlika.setIcon(new ImageIcon("src/bmi/crveno.jpg"));
					}
					else if (index > 18.5 &&  index < 25){
						lblStanje.setText("NORMALNA TEZINA");
						lblStanje.setForeground(Color.DARK_GRAY);
						lblSlika.setIcon(new ImageIcon("src/bmi/zelena.jpg"));
					
					}
					else if (index >= 25 && index < 30 ){
						lblStanje.setText("PREVELIKA TEZINA");
						lblStanje.setForeground(Color.yellow);
						lblSlika.setIcon(new ImageIcon("src/bmi/zuto.jpg"));
					}
					else if (index >=30){
						lblStanje.setText("GOJAZNOST");
						lblStanje.setForeground(Color.red);
						lblSlika.setIcon(new ImageIcon("src/bmi/crveno.jpg"));
					}	
				}
			}
		});
		btnNewButton.setBounds(44, 186, 99, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("(kg)");
		lblNewLabel.setBounds(246, 41, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblm = new JLabel("(m)");
		lblm.setBounds(246, 80, 70, 15);
		contentPane.add(lblm);
		
		JButton btnIzadji = new JButton("Izadji");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnIzadji.setBounds(298, 186, 99, 25);
		contentPane.add(btnIzadji);
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtVisina.setText(null);
				txtTezina.setText(null);
				txtBMI.setText(null);
				lblStanje.setText(null);
				lblSlika.setIcon(new ImageIcon(""));
			}
		});
		btnObrisi.setBounds(171, 186, 99, 25);
		contentPane.add(btnObrisi);
		
	
	
		
		
	}
}
