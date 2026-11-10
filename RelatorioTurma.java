import java.util.Locale;
import java.util.Scanner;

public class RelatorioTurma {
    private static final double MEDIA_MINIMA = 7.0;

    static double calcularMedia(double[] valores) {
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }

    static boolean estaAprovado(double media) {
        return media >= MEDIA_MINIMA;
    }

    static int contarAprovados(double[] medias) {
        int aprovados = 0;
        for (double media : medias) {
            if (estaAprovado(media)) {
                aprovados++;
            }
        }
        return aprovados;
    }

    static void exibirRelatorio(String[] nomes, double[] medias) {
        System.out.println("\nRelatório da turma");
        for (int indice = 0; indice < nomes.length; indice++) {
            double media = medias[indice];
            String situacao = estaAprovado(media) ? "Aprovado" : "Reprovado";
            System.out.printf(Locale.ROOT, "%s: %.2f (%s)%n", nomes[indice], media, situacao);
        }

        int aprovados = contarAprovados(medias);
        System.out.printf(Locale.ROOT, "Média da turma: %.2f%n", calcularMedia(medias));
        System.out.printf("Aprovados: %d de %d%n", aprovados, medias.length);
        System.out.printf("Reprovados: %d%n", medias.length - aprovados);
    }

    static int lerQuantidade(Scanner entrada, String pergunta) {
        System.out.print(pergunta);
        while (true) {
            try {
                int quantidade = Integer.parseInt(entrada.nextLine().trim());
                if (quantidade > 0) {
                    return quantidade;
                }
            } catch (NumberFormatException erro) {
                // A mesma mensagem vale para texto e para valores fora do intervalo.
            }
            System.out.print("Informe um número inteiro maior que zero: ");
        }
    }

    static double lerNota(Scanner entrada, int numero) {
        System.out.printf("Nota %d: ", numero);
        while (true) {
            try {
                double nota = Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
            } catch (NumberFormatException erro) {
                // Uma entrada inválida deve ser repetida antes de seguir para a próxima nota.
            }
            System.out.print("Informe uma nota entre 0 e 10: ");
        }
    }

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            int total = lerQuantidade(entrada, "Quantos alunos há na turma? ");
            int quantidadeNotas = lerQuantidade(entrada, "Quantas notas por aluno? ");
            String[] nomes = new String[total];
            double[] medias = new double[total];

            for (int indice = 0; indice < total; indice++) {
                System.out.printf("%nNome do aluno %d: ", indice + 1);
                String nome = entrada.nextLine().trim();
                while (nome.isEmpty()) {
                    System.out.print("Informe o nome do aluno: ");
                    nome = entrada.nextLine().trim();
                }
                nomes[indice] = nome;

                double[] notas = new double[quantidadeNotas];
                for (int nota = 0; nota < notas.length; nota++) {
                    notas[nota] = lerNota(entrada, nota + 1);
                }
                medias[indice] = calcularMedia(notas);
            }

            exibirRelatorio(nomes, medias);
        }
    }
}
