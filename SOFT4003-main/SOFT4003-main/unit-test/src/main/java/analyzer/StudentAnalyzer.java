package analyzer;
import java.util.List;

public class StudentAnalyzer {
    // Tiêu chí EP/BVA: Kiểm tra điểm hợp lệ và đạt giỏi
    public int countExcellent(List<Double> scores) {
        if (scores == null) return 0;
        int count = 0;
        for (Double s : scores) {
            // Logic: Điểm [0-10]. Giỏi >= 8.0
            if (s != null && s >= 0 && s <= 10 && s >= 8.0) count++;
        }
        return count;
    }
}