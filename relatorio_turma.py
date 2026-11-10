MEDIA_MINIMA = 7.0


def calcular_media(valores):
    return sum(valores) / len(valores)


def esta_aprovado(media):
    return media >= MEDIA_MINIMA


def contar_aprovados(resultados):
    return sum(esta_aprovado(media) for _, media in resultados)


def exibir_relatorio(resultados):
    print("\nRelatório da turma")
    for nome, media in resultados:
        situacao = "Aprovado" if esta_aprovado(media) else "Reprovado"
        print(f"{nome}: {media:.2f} ({situacao})")

    media_turma = calcular_media([media for _, media in resultados])
    aprovados = contar_aprovados(resultados)
    print(f"Média da turma: {media_turma:.2f}")
    print(f"Aprovados: {aprovados} de {len(resultados)}")
    print(f"Reprovados: {len(resultados) - aprovados}")


def ler_quantidade(pergunta):
    while True:
        try:
            quantidade = int(input(pergunta))
            if quantidade > 0:
                return quantidade
        except ValueError:
            pass
        print("Informe um número inteiro maior que zero.")


def ler_nota(numero):
    while True:
        try:
            nota = float(input(f"Nota {numero}: ").replace(",", "."))
            if 0 <= nota <= 10:
                return nota
        except ValueError:
            pass
        print("Informe uma nota entre 0 e 10.")


def main():
    total = ler_quantidade("Quantos alunos há na turma? ")
    quantidade_notas = ler_quantidade("Quantas notas por aluno? ")
    resultados = []

    for numero in range(1, total + 1):
        nome = input(f"\nNome do aluno {numero}: ").strip()
        while not nome:
            nome = input("Informe o nome do aluno: ").strip()
        notas = [ler_nota(indice) for indice in range(1, quantidade_notas + 1)]
        resultados.append((nome, calcular_media(notas)))

    exibir_relatorio(resultados)


if __name__ == "__main__":
    main()
