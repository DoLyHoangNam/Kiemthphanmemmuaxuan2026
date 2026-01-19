package analyzer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class StudentAnalyzerTest {
    StudentAnalyzer analyzer = new StudentAnalyzer();

    @Test
    public void testCriteria() {
        // 1. EP & BVA: Test vùng tương đương và giá trị biên
        // Biên 7.99 (không giỏi) và 8.0 (giỏi)
        assertEquals(1, analyzer.countExcellent(Arrays.asList(7.99, 8.0)));
        
        // 2. DT: Test bảng quyết định (Trường hợp Null/Lỗi)
        assertEquals(0, analyzer.countExcellent(null)); // Quy tắc danh sách null
        assertEquals(0, analyzer.countExcellent(Arrays.asList(-1.0, 11.0))); // Quy tắc điểm ngoài vùng
    }
}