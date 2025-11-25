import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private List<Double> scores;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void addScore(double s) {
        if (s < 0) return;
        scores.add(s);
    }

    public List<Double> getScores() { return Collections.unmodifiableList(scores); }

    public double getAverage() {
        if (scores.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double s : scores) sum += s;
        return sum / scores.size();
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 50) return "D";
        return "F";
    }
}
