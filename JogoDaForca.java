import java.util.Scanner;

public class JogoDaForca {
    private static final String[] PALAVRAS = {"cachorro", "gato", "elefante", "girafa", "macaco"};
    private static final int MAX_TENTATIVAS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavraSecreta = escolherPalavraSecreta();
        char[] letrasReveladas = new char[palavraSecreta.length()];
        int tentativasRestantes = MAX_TENTATIVAS;

        while (tentativasRestantes > 0 && !palavraRevelada(letrasReveladas)) {
            System.out.println("\nPalavra: " + exibirPalavra(letrasReveladas));
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.print("Digite uma letra: ");
            char letra = scanner.nextLine().toLowerCase().charAt(0);

            if (!tentativaValida(letra, letrasReveladas)) {
                System.out.println("Por favor, digite uma letra válida que ainda não foi tentada.");
                continue;
            }

            if (palavraSecreta.contains(String.valueOf(letra))) {
                atualizarLetrasReveladas(letra, palavraSecreta, letrasReveladas);
                System.out.println("Letra correta!");
            } else {
                tentativasRestantes--;
                System.out.println("Letra incorreta! Tente novamente.");
            }
        }

        if (palavraRevelada(letrasReveladas)) {
            System.out.println("\nParabéns! Você ganhou! A palavra era: " + palavraSecreta);
        } else {
            System.out.println("\nQue pena! Você perdeu! A palavra era: " + palavraSecreta);
        }
    }

    private static String escolherPalavraSecreta() {
        return PALAVRAS[(int) (Math.random() * PALAVRAS.length)];
    }

    private static boolean palavraRevelada(char[] letrasReveladas) {
        for (char letra : letrasReveladas) {
            if (letra == 0) {
                return false;
            }
        }
        return true;
    }

    private static String exibirPalavra(char[] letrasReveladas) {
        StringBuilder palavra = new StringBuilder();
        for (char letra : letrasReveladas) {
            palavra.append(letra == 0 ? "_" : letra);
            palavra.append(" ");
        }
        return palavra.toString();
    }

    private static boolean tentativaValida(char letra, char[] letrasReveladas) {
        return Character.isLetter(letra) && !letraJaTentada(letra, letrasReveladas);
    }

    private static boolean letraJaTentada(char letra, char[] letrasReveladas) {
        for (char l : letrasReveladas) {
            if (l == letra) {
                return true;
            }
        }
        return false;
    }

    private static void atualizarLetrasReveladas(char letra, String palavraSecreta, char[] letrasReveladas) {
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                letrasReveladas[i] = letra;
            }
        }
    }
}
