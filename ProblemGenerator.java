import java.util.Random;

public class ProblemGenerator {
    private Random random = new Random();

    // Gera um problema de matemática e retorna o objeto MathProblem
    public MathProblem generateProblem() {
        int num1 = random.nextInt(50) + 10; // Números entre 10 e 59
        int num2 = random.nextInt(20) + 5;  // Números entre 5 e 24
        int operator = random.nextInt(4); // 0:+, 1:-, 2:*, 3:/

        String question;
        double answer;

        switch (operator) {
            case 0: // Soma
                question = num1 + " + " + num2;
                answer = num1 + num2;
                break;
            case 1: // Subtração
                // Garante que o resultado não seja negativo
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                question = num1 + " - " + num2;
                answer = num1 - num2;
                break;
            case 2: // Multiplicação
                num1 = random.nextInt(15) + 2; // Números menores para não ficar muito difícil
                num2 = random.nextInt(15) + 2;
                question = num1 + " * " + num2;
                answer = num1 * num2;
                break;
            case 3: // Divisão (com resultado exato)
                int result = random.nextInt(10) + 2;
                num2 = random.nextInt(10) + 2;
                num1 = num2 * result;
                question = num1 + " / " + num2;
                answer = result;
                break;
            default: // Caso inesperado
                question = "1 + 1";
                answer = 2;
                break;
        }

        return new MathProblem(question, answer);
    }
}