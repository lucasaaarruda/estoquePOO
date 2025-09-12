import java.sql.*;

public class GerenciadorEstoque {
    private Connection conexao;
    
    public GerenciadorEstoque() {
        this.conexao = DatabaseConnection.getConnection();
    }
    
    public boolean cadastrarProduto(String nome, double preco, int quantidade) {
        try {
            String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, quantidade);
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean incrementarQuantidade(int id, int quantidade) {
        try {
            String sql = "UPDATE produto SET quantidade = quantidade + ? WHERE id_produto = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro ao incrementar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean decrementarQuantidade(int id, int quantidade) {
        try {
            String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE id_produto = ? AND quantidade >= ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            stmt.setInt(3, quantidade);
            
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if (linhasAfetadas > 0) {
                return true;
            } else {
                System.out.println("Quantidade insuficiente em estoque!");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao decrementar: " + e.getMessage());
            return false;
        }
    }
    
    public void listarProdutos() {
        try {
            String sql = "SELECT * FROM produto";
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("\n=== PRODUTOS EM ESTOQUE ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_produto") +
                                   " | Nome: " + rs.getString("nome") +
                                   " | Preço: R$" + rs.getDouble("preco") +
                                   " | Quantidade: " + rs.getInt("quantidade"));
            }
            System.out.println("===========================");
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }
    
    public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}