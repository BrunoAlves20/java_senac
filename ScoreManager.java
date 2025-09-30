import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private static final String SCORES_FILE = "ranking.dat";

    // Salva a nova pontuação na lista e ressalva o arquivo inteiro
    public void saveScore(PlayerScore score) {
        // 1. Carrega as pontuações que já existem
        List<PlayerScore> scores = loadScores();
        
        // 2. Adiciona a nova pontuação à lista
        scores.add(score);

        // 3. Sobrescreve o arquivo antigo com a lista atualizada
        //    O 'false' no FileOutputStream garante que o arquivo seja recriado.
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORES_FILE, false))) {
            for (PlayerScore s : scores) {
                oos.writeObject(s);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pontuação: " + e.getMessage());
        }
    }

    // Carrega todas as pontuações do arquivo (nenhuma mudança necessária aqui)
    public List<PlayerScore> loadScores() {
        List<PlayerScore> scores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORES_FILE))) {
            while (true) {
                try {
                    PlayerScore score = (PlayerScore) ois.readObject();
                    scores.add(score);
                } catch (EOFException e) {
                    // Fim do arquivo alcançado, o que é normal.
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo ainda não existe, retorna lista vazia. É a primeira vez jogando.
            return scores;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar pontuações: " + e.getMessage());
        }

        // Ordena a lista (usando o método compareTo da classe PlayerScore)
        Collections.sort(scores);
        return scores;
    }
}