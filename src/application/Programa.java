package application;

/*
Exercicio:
Fazer um programa para ler um número inteiro N e depois os dados (id, nome, salario) de N funcionários.
Não deve haver repetição de ID.

Em seguida, efetuar o aumento de X por cento no salário de um determinado funcionário.
Para isso, o programa deve ler um ID e o valor X.
Se o ID informado não existir, mostrar uma msn e abortar a operação.

Ao final, mostrar a listagem atualizada dos funcionarios confirme ex abaixo:

"Quantos empregados seram digitados: X:
Empregado 1:
Id: 123
Nome: Juca
Salario: 1000
(...)
Entre com a ID do funcionario que ganhara o aumento: 123
Entre com a porcentagem: 10%

Lista dos empregados:
(...)
123, Juca, 1100
(...)

Lembre-se de aplicar a técnica de enclapsulamento para não permitir que o salário possa ser mudado livremente.
Um salário só pode ser aumentado com base numa operação de aumento por porcentagem dada.

*/

import entities.Empregado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Lemtrete de SEMPRE fechar o scanner.close no final do cod.

        System.out.print("Digite a quantidade de Funcionarios para cadastro: ");
        int qtd = scanner.nextInt();

        List<Empregado> empregados = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            System.out.println("Empregado nª " + (i+1));
            System.out.print("ID: ");
            int id = scanner.nextInt();
            while (temId(empregados, id)) {
                System.out.print("ID já cadastrado! Digite novamente: ");
                id = scanner.nextInt();
            }
            System.out.print("Nome: ");
            scanner.nextLine(); // Limpando o buffer
            String nome = scanner.nextLine();
            System.out.print("Salario: ");
            Double salario = scanner.nextDouble();
            empregados.add(new Empregado(id, nome, salario));
        }


        System.out.print("Entre com a ID do funcionario que ganhara o aumento: ");
        int idAumento = scanner.nextInt();
        Integer possicao = validaId(empregados, idAumento);

        if (possicao == null) {
            System.out.println("Não existe funcionário com essa ID");

        } else {
            System.out.print("Entre com a porcentagem: ");
            double idPorcento = scanner.nextDouble();
            empregados.get(possicao).AumentarSalario(idPorcento);
        }

        System.out.println("-----------------");
        scanner.close();

        System.out.println("Lista de Empregados: ");
        for (Empregado emp: empregados) System.out.println(emp);

    }

    public static Integer validaId(List<Empregado> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static boolean temId(List<Empregado> list, int id) {
        Empregado emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }

}


