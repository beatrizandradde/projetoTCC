package GUI;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DAO.FalecidoDAO;
import Entidades.Falecido;

public class TelaBuscarPorData extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 TelaBuscarPorData frame = new TelaBuscarPorData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
}
	
	/**
	 * Fecha o JInternalFrame a partir da chamada do método
	 */
	private void disposeOnClosed() {
		this.dispose();
	}
	/**
	 * Fecha o JInternalFrame a partir da chamada do botão fechar
	 */
	private void closeInternalFrame() {
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Create the frame.
	 */
	public TelaBuscarPorData() {
		setTitle("Buscar Por Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformeO = new JLabel("Data de Sepultamento:");
		lblInformeO.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformeO.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformeO.setBounds(10, 31, 233, 14);
		contentPane.add(lblInformeO);
		
		JDateChooser dateObito = new JDateChooser();
		dateObito.setBounds(55, 61, 148, 26);
		contentPane.add(dateObito);
		
		Button btnBuscar = new Button("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 
						FalecidoDAO faldao = new FalecidoDAO();
						ArrayList<Falecido> falecidos = faldao.buscarPorData(dateObito.getDate());
						TelaListaSepultamentos tlListaSepultamentos = new TelaListaSepultamentos(falecidos);
						TelaPrincipal.desktopPane_1.add(tlListaSepultamentos);
						tlListaSepultamentos.setVisible(true);
						tlListaSepultamentos.setPosicao();
						closeInternalFrame();
						disposeOnClosed();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}	
		});
		btnBuscar.setBounds(131, 116, 112, 39);
		contentPane.add(btnBuscar);

		Button button = new Button("Cancelar");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setBounds(10, 116, 112, 39);
		contentPane.add(button);
		
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

}