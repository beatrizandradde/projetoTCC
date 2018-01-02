package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;

import DAO.FalecidoDAO;
import DAO.RequerenteDAO;
import Entidades.Falecido;
import Entidades.Requerente;

import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class TelaListaSepultamentos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableSepultamentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaSepultamentos frame = new TelaListaSepultamentos(null);
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
	public TelaListaSepultamentos(ArrayList<Falecido> falecidos) {
		setBounds(100, 100, 593, 438);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 556, 337);
		getContentPane().add(scrollPane);
		
		String[] colunas = new String[] {"Nome", "Número do Processo de Óbito"};
		Object[][] linhas = new String[falecidos.size()][2];

		for (int i = 0; i < falecidos.size(); i++) {
			Falecido f = falecidos.get(i);
			linhas[i][0] = f.getNome_falecido();
			linhas[i][1] = String.valueOf(f.getNumero_processo_obito());
		}

		Font f = new Font("Cambria", Font.PLAIN, 18);
		
		tableSepultamentos = new JTable(linhas, colunas);
		tableSepultamentos.setFont(f);
		tableSepultamentos.getTableHeader().setFont(f);
		tableSepultamentos.setRowHeight(30);
		this.setTitle("Sepultamentos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
		this.setVisible(true);
		scrollPane.setViewportView(tableSepultamentos);
		
		Button button = new Button("Detalhar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = tableSepultamentos.getSelectedRow();
				
				FalecidoDAO faldao = new FalecidoDAO();
				String numProc = tableSepultamentos.getValueAt(linha, 1).toString();
				Falecido falecido = faldao.buscarNumeroProcesso(numProc);
				
				RequerenteDAO reqdao = new RequerenteDAO();
				Requerente r = reqdao.buscarPorCpf(faldao.buscarCPFRequerente(numProc));

				if (f == null) {
					JOptionPane.showMessageDialog(null, "O número de processo não foi encontrado no sistema!");
				} else {
					TelaAtualizarSepultamento tlAtualizarSepultamento;
					try {
						tlAtualizarSepultamento = new TelaAtualizarSepultamento(falecido, r);
						TelaPrincipal.desktopPane_1.add(tlAtualizarSepultamento);
						tlAtualizarSepultamento.setVisible(true);		
						tlAtualizarSepultamento.setPosicao();
						dispose();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(298, 359, 148, 39);
		getContentPane().add(button);
		
		Button button_1 = new Button("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_1.setBounds(130, 359, 148, 39);
		getContentPane().add(button_1);
		
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}
