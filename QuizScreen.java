import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuizScreen extends JFrame {
    // Componentes da UI
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel timerLabel;
    private JLabel scoreLabel;

    // Lógica do Jogo
    private ProblemGenerator problemGenerator;
    private ScoreManager scoreManager;
    private MathProblem currentProblem;
    private Timer gameTimer;
    private int timeLeft = 30; // tempo inicial;
    private int score = 0;

    public QuizScreen() {
        super("Quiz de Matemática");
        problemGenerator = new ProblemGenerator();
        scoreManager = new ScoreManager();

        // Configuração da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centralizar na tela
        setLayout(new GridLayout(5, 1));

        // Inicialização dos componentes
        questionLabel = new JLabel("Pressione Start para começar", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 20));
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setEnabled(false); // Desabilitado até o jogo começar

        timerLabel = new JLabel("Tempo: 30", SwingConstants.CENTER);  // tempo na tela 
        scoreLabel = new JLabel("Pontos: 0", SwingConstants.CENTER);
        
        JButton startButton = new JButton("Start");

        // Adiciona componentes à janela
        add(timerLabel);
        add(scoreLabel);
        add(questionLabel);
        add(answerField);
        add(startButton);

        // Ação para o botão de start
        startButton.addActionListener(e -> startGame());

        // Ação para o campo de resposta (quando o usuário pressiona Enter)
        answerField.addActionListener(e -> checkAnswer());
    }

    private void startGame() {
        // Reseta o estado do jogo
        score = 0;
        timeLeft = 60;
        scoreLabel.setText("Pontos: " + score);
        timerLabel.setText("Tempo: " + timeLeft);
        answerField.setEnabled(true);
        answerField.requestFocus(); // Foco no campo de texto

        // Configura e inicia o timer
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Tempo: " + timeLeft);
                if (timeLeft <= 0) {
                    endGame();
                }
            }
        });
        gameTimer.start();
        
        // Inicia a primeira pergunta
        nextQuestion();
    }

    private void nextQuestion() {
        currentProblem = problemGenerator.generateProblem();
        questionLabel.setText(currentProblem.getQuestion() + " = ?");
        answerField.setText("");
    }

    private void checkAnswer() {
        try {
            double userAnswer = Double.parseDouble(answerField.getText().trim());
            // Compara com uma pequena tolerância para números de ponto flutuante
            if (Math.abs(userAnswer - currentProblem.getAnswer()) < 0.01) {
                score++;
                scoreLabel.setText("Pontos: " + score);
            }
            nextQuestion();
        } catch (NumberFormatException e) {
            // Ignora se o usuário digitar algo que não é um número
        }
    }

    private void endGame() {
        gameTimer.stop();
        answerField.setEnabled(false);
        questionLabel.setText("Tempo Esgotado!");

        // Pede o nome do jogador
        String playerName = JOptionPane.showInputDialog(this, "Sua pontuação final: " + score + "\nDigite seu nome para o ranking:", "Fim de Jogo", JOptionPane.PLAIN_MESSAGE);

        if (playerName != null && !playerName.trim().isEmpty()) {
            PlayerScore playerScore = new PlayerScore(playerName.trim(), score);
            scoreManager.saveScore(playerScore);
        }

        showRanking();
        // Prepara para um novo jogo
        answerField.setText("");
        questionLabel.setText("Pressione Start para começar");
    }



    private void showRanking() {
        List<PlayerScore> scores = scoreManager.loadScores();
        StringBuilder rankingText = new StringBuilder("<html><h2>Ranking</h2>");

        // Mostra os 10 melhores
        int limit = Math.min(scores.size(), 10);
        for (int i = 0; i < limit; i++) {
            rankingText.append((i + 1) + ". " + scores.get(i).toString() + "<br>");
        }
        rankingText.append("</html>");
        
        JOptionPane.showMessageDialog(this, rankingText.toString(), "Melhores Pontuações", JOptionPane.INFORMATION_MESSAGE);
    }
}