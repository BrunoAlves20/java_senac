import java.io.Serializable;

public class PlayerScore implements Serializable, Comparable<PlayerScore> {
    private static final long serialVersionUID = 1L; // Padrão para serialização
    private String name;
    private int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // Usado para ordenar o ranking do maior para o menor
    @Override
    public int compareTo(PlayerScore other) {
        return Integer.compare(other.getScore(), this.score);
    }

    @Override
    public String toString() {
        return name + ": " + score + " pontos";
    }
}