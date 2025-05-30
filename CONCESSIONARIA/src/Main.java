import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Concessionaria concessionaria = new Concessionaria();
        SistemaLogin sistemaLogin = new SistemaLogin();
        Scanner sc = new Scanner(System.in);

        try {
            concessionaria.carregarDados();
            System.out.println("Dados de veículos carregados.");
        } catch (Exception e) {
            System.out.println("Nenhum dado de veículo carregado ou erro: " + e.getMessage());
        }

        try {
            sistemaLogin.carregarUsuarios();
            System.out.println("Usuários carregados.");
        } catch (Exception e) {
            System.out.println("Nenhum usuário carregado ou erro: " + e.getMessage());
        }

        boolean logado = false;

        while (logado == false) {
            System.out.println("\n--- LOGIN ---");
            System.out.println("1. Entrar");
            System.out.println("2. Cadastrar");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcaoLogin = sc.nextInt();
            sc.nextLine();

            if (opcaoLogin == 1) {
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();

                if (sistemaLogin.autenticar(login, senha)) {
                    System.out.println("Login bem-sucedido.");
                    logado = true;
                } else {
                    System.out.println("Login ou senha incorretos.");
                }

            } else if (opcaoLogin == 2) {
                System.out.print("Novo login: ");
                String novoLogin = sc.nextLine();

                if (sistemaLogin.usuarioExiste(novoLogin)) {
                    System.out.println("Usuário já existe! Tente outro nome.");
                    continue; 
                }

                System.out.print("Nova senha: ");
                String novaSenha = sc.nextLine();

                sistemaLogin.cadastrarUsuario(new Usuario(novoLogin, novaSenha));
                try {
                    sistemaLogin.salvarUsuarios();
                    System.out.println("Usuário cadastrado e salvo.");
                } catch (Exception e) {
                    System.out.println("Erro ao salvar usuário: " + e.getMessage());
                }

            } else if (opcaoLogin == 0) {
                System.out.println("Saindo...");
                sc.close();
                return;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        while (true) {
            System.out.println("\n--- CONCESSIONÁRIA ---");
            System.out.println("1. Adicionar veículo");
            System.out.println("2. Listar veículos");
            System.out.println("3. Atualizar veículo");
            System.out.println("4. Remover veículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                if (concessionaria.buscarVeiculoPorId(id) != null) {
                    System.out.println("Já existe um veículo com esse ID. Operação cancelada.");
                    continue;
                }

                System.out.print("Modelo: ");
                String modelo = sc.nextLine();
                System.out.print("Marca: ");
                String marca = sc.nextLine();
                System.out.print("Ano: ");
                int ano = sc.nextInt();
                sc.nextLine();

                concessionaria.adicionarVeiculo(new Veiculo(id, modelo, marca, ano));

                try {
                    concessionaria.salvarDados();
                    System.out.println("Veículo adicionado e salvo.");
                } catch (Exception e) {
                    System.out.println("Erro ao salvar: " + e.getMessage());
                }

            } else if (opcao == 2) {
                concessionaria.listarVeiculos();

            } else if (opcao == 3) {
                System.out.print("ID do veículo a atualizar: ");
                int idAtualizar = sc.nextInt();
                sc.nextLine();

                Veiculo vAtualizar = concessionaria.buscarVeiculoPorId(idAtualizar);
                if (vAtualizar != null) {
                    System.out.print("Novo modelo (" + vAtualizar.getModelo() + "): ");
                    String novoModelo = sc.nextLine();
                    System.out.print("Nova marca (" + vAtualizar.getMarca() + "): ");
                    String novaMarca = sc.nextLine();
                    System.out.print("Novo ano (" + vAtualizar.getAno() + "): ");
                    int novoAno = sc.nextInt();
                    sc.nextLine();

                    vAtualizar.setModelo(novoModelo);
                    vAtualizar.setMarca(novaMarca);
                    vAtualizar.setAno(novoAno);

                    try {
                        concessionaria.salvarDados();
                        System.out.println("Veículo atualizado e salvo.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                } else {
                    System.out.println("Veículo não encontrado.");
                }

            } else if (opcao == 4) {
                System.out.print("ID do veículo a remover: ");
                int idRemover = sc.nextInt();
                sc.nextLine();

                if (concessionaria.removerVeiculo(idRemover)) {
                    try {
                        concessionaria.salvarDados();
                        System.out.println("Veículo removido e salvo.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                } else {
                    System.out.println("Veículo não encontrado.");
                }

            } else if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
