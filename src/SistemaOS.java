import java.util.List;
import java.util.Scanner;

public class SistemaOS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<OrdemServico> listaOrdens = GerenciadorArquivos.carregarOrdens();
        int opcao = 0;

        int proximoId = 1;
        for (OrdemServico os : listaOrdens) {
            if (os.getId() >= proximoId) {
                proximoId = os.getId() + 1;
            }
        }

        System.out.println("\n  GERENCIADOR DE O.S.  ");

        while (opcao != 5) {
            System.out.println("\n - MENU PRINCIPAL -");
            System.out.println("1. Criar Nova Ordem de Serviço");
            System.out.println("2. Listar Todas as Ordens");
            System.out.println("3. Atualizar Status de uma Ordem");
            System.out.println("4. Excluir Ordem de Serviço");
            System.out.println("5. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Descrição do Serviço: ");
                    String descricao = scanner.nextLine();
                    
                    OrdemServico novaOS = new OrdemServico(proximoId, cliente, descricao, StatusOS.PENDENTE);
                    listaOrdens.add(novaOS);
                    proximoId++; 
                    
                    GerenciadorArquivos.salvarOrdens(listaOrdens);
                    System.out.println("✔ Ordem de Serviço criada com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE ORDENS DE SERVIÇO ---");
                    if (listaOrdens.isEmpty()) {
                        System.out.println("Nenhuma ordem cadastrada no momento.");
                    } else {
                        // For-each tradicional, muito bem aceito em POO I
                        for (OrdemServico os : listaOrdens) {
                            System.out.println(os);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID da ordem que deseja atualizar: ");
                    int idAtualizar = Integer.parseInt(scanner.nextLine());
                    OrdemServico osEncontrada = null;

                    for (OrdemServico os : listaOrdens) {
                        if (os.getId() == idAtualizar) {
                            osEncontrada = os;
                            break;
                        }
                    }

                    if (osEncontrada != null) {
                        System.out.println("Status atuais: 1-PENDENTE, 2-EM_ANDAMENTO, 3-CONCLUIDO");
                        System.out.print("Escolha o novo status (1-3): ");
                        int escolhaStatus = Integer.parseInt(scanner.nextLine());
                        
                        if (escolhaStatus == 1) osEncontrada.setStatus(StatusOS.PENDENTE);
                        else if (escolhaStatus == 2) osEncontrada.setStatus(StatusOS.EM_ANDAMENTO);
                        else if (escolhaStatus == 3) osEncontrada.setStatus(StatusOS.CONCLUIDO);
                        else System.out.println("Opção inválida. Status não alterado.");

                        GerenciadorArquivos.salvarOrdens(listaOrdens);
                        System.out.println("Status atualizado com sucesso!");
                    } else {
                        System.out.println("Ordem de Serviço não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID da ordem que deseja excluir: ");
                    int idExcluir = Integer.parseInt(scanner.nextLine());
                    OrdemServico osParaRemover = null;

                    for (OrdemServico os : listaOrdens) {
                        if (os.getId() == idExcluir) {
                            osParaRemover = os;
                            break;
                        }
                    }

                    if (osParaRemover != null) {
                        listaOrdens.remove(osParaRemover);
                        GerenciadorArquivos.salvarOrdens(listaOrdens);
                        System.out.println("Ordem de Serviço removida com sucesso!");
                    } else {
                        System.out.println("Ordem de Serviço não encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o sistema e salvando sessões. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
