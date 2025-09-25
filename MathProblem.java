public class MathProblem {
    private String question;
    private double answer;

    public MathProblem(String question, double answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public double getAnswer() {
        return answer;
    }
}