package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Entidades.Falecido;

public class FalecidoDAO extends AbstractDAO {

	public boolean cadastrar(Falecido umFalecido, String sexo, String raca, String estado, String umCpf) {
		int n = 0;

		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"insert into falecido (numero_processo_obito, orgao_emissor, obito_data, obito_hora, nome_falecido, nome_pai,"
					+ "nome_mae, idade, sexo, raca_cor, estado_civil, causa_morte, medico_nome, medico_crm, requerente_cpf)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, umFalecido.getNumero_processo_obito());
			stmt.setString(2, umFalecido.getOrgao_emissor());
			stmt.setTimestamp(3, new Timestamp(umFalecido.getObito_data().getTime()));
			stmt.setString(4, umFalecido.getHora());
			stmt.setString(5, umFalecido.getNome_falecido());
			stmt.setString(6, umFalecido.getNome_pai());
			stmt.setString(7, umFalecido.getNome_mae());
			stmt.setInt(8, umFalecido.getIdade());
			stmt.setString(9, sexo);
			stmt.setString(10, raca);
			stmt.setString(11, estado);
			stmt.setString(12, umFalecido.getCausa_morte());
			stmt.setString(13, umFalecido.getMedico_nome());
			stmt.setInt(14, umFalecido.getMedico_crm());
			stmt.setString(15, umCpf);

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			n = 0;
			e.printStackTrace();
		}

		return n == 1;
	}
	
	public boolean atualizar(Falecido umFalecido, String sexo, String raca, String estado, String umCpf) {
		int n = 0;

		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"update falecido set orgao_emissor = ?, obito_data = ?, obito_hora = ?, nome_falecido = ?, nome_pai = ?,"
					+ "nome_mae = ?, idade = ?, sexo = ?, raca_cor = ?, estado_civil = ?, causa_morte = ?, medico_nome = ?, "
					+ "medico_crm = ?, requerente_cpf = ? where numero_processo_obito = ?");

			stmt.setString(1, umFalecido.getOrgao_emissor());
			stmt.setTimestamp(2, new Timestamp(umFalecido.getObito_data().getTime()));
			stmt.setString(3, umFalecido.getHora());
			stmt.setString(4, umFalecido.getNome_falecido());
			stmt.setString(5, umFalecido.getNome_pai());
			stmt.setString(6, umFalecido.getNome_mae());
			stmt.setInt(7, umFalecido.getIdade());
			stmt.setString(8, sexo);
			stmt.setString(9, raca);
			stmt.setString(10, estado);
			stmt.setString(11, umFalecido.getCausa_morte());
			stmt.setString(12, umFalecido.getMedico_nome());
			stmt.setInt(13, umFalecido.getMedico_crm());
			stmt.setString(14, umCpf);
			stmt.setInt(15, umFalecido.getNumero_processo_obito());

			n = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			n = 0;
			e.printStackTrace();
		}

		return n == 1;
	}
	
 
	public Falecido buscarNumeroProcesso(String numProc) {
		Falecido f = null;

		try {
			PreparedStatement stmt = conexao.prepareStatement("select * from falecido where numero_processo_obito = ?");

			stmt.setString(1, numProc);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				f = new Falecido();
				
				f.setNumero_processo_obito(rs.getInt("numero_processo_obito"));
				f.setOrgao_emissor(rs.getString("orgao_emissor"));
				f.setObito_data(rs.getDate("obito_data"));
				f.setHora(rs.getString("obito_hora"));
				f.setNome_falecido(rs.getString("nome_falecido"));
				f.setNome_pai(rs.getString("nome_pai"));
				f.setNome_mae(rs.getString("nome_mae"));
				f.setIdade(rs.getInt("idade"));
				f.setSexo(rs.getString("sexo"));
				f.setRaca_cor(rs.getString("raca_cor"));
				f.setEstado_civil(rs.getString("estado_civil"));
				f.setCausa_morte(rs.getString("causa_morte"));
				f.setMedico_nome(rs.getString("medico_nome"));
				f.setMedico_crm(rs.getInt("medico_crm"));
				f.setRequerente_cpf(rs.getString("requerente_cpf"));
				
	}
		} catch (SQLException e) {
			f = null;
			System.out.println("Erro ao buscar número de processo: \n\t" + e);
		}

		return f;
	}
	
	public String buscarCPFRequerente(String numProc) {
		String cpf = null;

		try {
			PreparedStatement stmt = conexao.prepareStatement("select requerente_cpf from falecido where numero_processo_obito = ?");

			stmt.setString(1, numProc);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				
				cpf = (rs.getString("requerente_cpf"));
				
	}
		} catch (SQLException e) {
			cpf = null;
			System.out.println("Erro ao buscar número de processo: \n\t" + e);
		}

		return cpf;
	}
	
	public ArrayList<Falecido> buscarPorData(Date dateObito) {
		Falecido f = null;
		ArrayList<Falecido> falecidos = new ArrayList<Falecido>();

		try {
			PreparedStatement stmt = conexao.prepareStatement("select nome_falecido, numero_processo_obito from falecido where obito_data = ?");

			stmt.setDate(1, new java.sql.Date (dateObito.getTime()));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				f = new Falecido();
				
				f.setNome_falecido(rs.getString("nome_falecido"));
				f.setNumero_processo_obito(rs.getInt("numero_processo_obito"));
				falecidos.add(f);
			
	}
		} catch (SQLException e) {
			falecidos = null;
			System.out.println("Erro ao buscar sepultamentos: \n\t" + e);
		}

		return falecidos;
	}
	
}
