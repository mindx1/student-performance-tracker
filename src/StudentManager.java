import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {
    private Map<String, Student> map = new LinkedHashMap<>();

    public boolean addStudent(String id, String name) {
        if (id == null || id.isEmpty() || map.containsKey(id)) return false;
        map.put(id, new Student(id, name));
        return true;
    }

    public boolean addScoreToStudent(String id, double score) {
        Student s = map.get(id);
        if (s == null) return false;
        s.addScore(score);
        return true;
    }

    public Collection<Student> allStudents() {
        return map.values();
    }

    public boolean exportCSV(String filename) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("ID,Name,Average,Grade,Scores\n");
            for (Student s : map.values()) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.getId()).append(",");
                sb.append("\"").append(s.getName().replace("\"", "'")).append("\",");
                sb.append(String.format("%.2f", s.getAverage())).append(",");
                sb.append(s.getGrade()).append(",");
                sb.append("\"");
                List<Double> sc = s.getScores();
                for (int i = 0; i < sc.size(); i++) {
                    sb.append(String.format("%.2f", sc.get(i)));
                    if (i < sc.size() - 1) sb.append(";");
                }
                sb.append("\"\n");
                fw.write(sb.toString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Export error: " + e.getMessage());
            return false;
        }
    }
}
