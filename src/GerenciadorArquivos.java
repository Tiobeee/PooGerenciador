import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String CAMINHO_ARQUIVO = "ordens.txt";

    public static void salvarOrdens(List<OrdemServico> ordens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (OrdemServico os : ordens) {
                writer.write(os.formatarParaArquivo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    public static List<OrdemServico> carregarOrdens() {
        List<OrdemServico> ordens = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        if (!arquivo.exists()) {
            return ordens;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    int id = Integer.parseInt(dados[0]);
                    String cliente = dados[1];
                    String descricao = dados[2];
                    StatusOS status = StatusOS.valueOf(dados[3]);

                    ordens.add(new OrdemServico(id, cliente, descricao, status));
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Erro ao ler o arquivo de dados: " + e.getMessage());
        }
        return ordens;
    }
}
