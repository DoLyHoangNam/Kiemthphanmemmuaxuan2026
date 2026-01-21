package analyzer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    // Epsilon nhỏ để tạo ca "sát biên" đúng kiểu BVA
    private static final double EPS = 1e-9;

    // ============================================================
    // BVA tests for countExcellentStudents
    // Spec assumed:
    // - Valid score: 0 <= score <= 10
    // - Excellent: 8 <= score <= 10
    // - Null elements are ignored
    // - Null/empty list => 0
    // ============================================================

    @Test
    public void countExcellentStudents_nullList_returns0() {
        assertEquals(0, analyzer.countExcellentStudents(null));
    }

    @Test
    public void countExcellentStudents_emptyList_returns0() {
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
    }

    @Test
    public void countExcellentStudents_nullElement_isIgnored() {
        assertEquals(0, analyzer.countExcellentStudents(Arrays.asList((Double) null)));
    }

    // ----- BVA around 0 (valid domain boundary) -----

    @Test
    public void countExcellentStudents_boundary0_justBelow0_notCounted() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(0.0 - EPS)));
    }

    @Test
    public void countExcellentStudents_boundary0_at0_notExcellent() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(0.0)));
    }

    @Test
    public void countExcellentStudents_boundary0_justAbove0_notExcellent() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(0.0 + EPS)));
    }

    // ----- BVA around 8 (excellent boundary) -----

    @Test
    public void countExcellentStudents_boundary8_justBelow8_notCounted() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(8.0 - EPS)));
    }

    @Test
    public void countExcellentStudents_boundary8_at8_counted() {
        assertEquals(1, analyzer.countExcellentStudents(List.of(8.0)));
    }

    @Test
    public void countExcellentStudents_boundary8_justAbove8_counted() {
        assertEquals(1, analyzer.countExcellentStudents(List.of(8.0 + EPS)));
    }

    // ----- BVA around 10 (valid + excellent upper boundary) -----

    @Test
    public void countExcellentStudents_boundary10_justBelow10_counted() {
        assertEquals(1, analyzer.countExcellentStudents(List.of(10.0 - EPS)));
    }

    @Test
    public void countExcellentStudents_boundary10_at10_counted() {
        assertEquals(1, analyzer.countExcellentStudents(List.of(10.0)));
    }

    @Test
    public void countExcellentStudents_boundary10_justAbove10_notCounted() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(10.0 + EPS)));
    }

    // ----- Representative invalid values far outside -----

    @Test
public void countExcellentStudents_negativeScore_notCounted() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(-1.0)));
    }

    @Test
    public void countExcellentStudents_over10_notCounted() {
        assertEquals(0, analyzer.countExcellentStudents(List.of(11.0)));
    }

    @Test
    public void countExcellentStudents_mixedData_countsOnlyValidExcellent() {
        // excellent: 9.0, 8.5, 10.0 => 3
        // not excellent but valid: 7.0, 0.0
        // invalid: 11.0, -1.0
        // null: ignored
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, 10.0, 0.0, null);
        assertEquals(3, analyzer.countExcellentStudents(scores));
    }

    // ============================================================
    // BVA tests for calculateValidAverage
    // Spec assumed:
    // - Average of valid scores in [0,10]
    // - Ignore null and invalid
    // - If no valid scores => 0.0
    // ============================================================

    @Test
    public void calculateValidAverage_nullList_returns0() {
        assertEquals(0.0, analyzer.calculateValidAverage(null), 0.0);
    }

    @Test
    public void calculateValidAverage_emptyList_returns0() {
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.0);
    }

    @Test
    public void calculateValidAverage_nullElement_isIgnored_returns0() {
        assertEquals(0.0, analyzer.calculateValidAverage(Arrays.asList((Double) null)), 0.0);
    }

    @Test
    public void calculateValidAverage_allInvalid_returns0() {
        List<Double> scores = Arrays.asList(-1.0, 11.0, 10.0 + EPS, null, 0.0 - EPS);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.0);
    }

    // ----- BVA around 0 -----

    @Test
    public void calculateValidAverage_boundary0_justBelow0_ignored() {
        // only invalid => 0
        assertEquals(0.0, analyzer.calculateValidAverage(List.of(0.0 - EPS)), 0.0);
    }

    @Test
    public void calculateValidAverage_boundary0_at0_average0() {
        assertEquals(0.0, analyzer.calculateValidAverage(List.of(0.0)), 1e-12);
    }

    @Test
    public void calculateValidAverage_boundary0_justAbove0_averageJustAbove0() {
        assertEquals(0.0 + EPS, analyzer.calculateValidAverage(List.of(0.0 + EPS)), 1e-12);
    }

    // ----- BVA around 10 -----

    @Test
    public void calculateValidAverage_boundary10_justBelow10_averageJustBelow10() {
        assertEquals(10.0 - EPS, analyzer.calculateValidAverage(List.of(10.0 - EPS)), 1e-12);
    }

    @Test
    public void calculateValidAverage_boundary10_at10_average10() {
        assertEquals(10.0, analyzer.calculateValidAverage(List.of(10.0)), 1e-12);
    }

    @Test
    public void calculateValidAverage_boundary10_justAbove10_ignored() {
        assertEquals(0.0, analyzer.calculateValidAverage(List.of(10.0 + EPS)), 0.0);
    }

    @Test
    public void calculateValidAverage_allValid_computesAverage() {
        // (0 + 10) / 2 = 5
assertEquals(5.0, analyzer.calculateValidAverage(Arrays.asList(0.0, 10.0)), 1e-12);
    }

    @Test
    public void calculateValidAverage_onlyOneValid_amongInvalid_returnsThatValue() {
        // valid: 5.0 only => avg = 5.0
        List<Double> scores = Arrays.asList(-1.0, 11.0, null, 5.0, 10.0 + EPS);
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 1e-12);
    }

    @Test
    public void calculateValidAverage_mixedValidInvalid_computesOnlyValid() {
        // valid: 9.0, 8.5, 7.0 => avg = 24.5/3 = 8.166666...
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(24.5 / 3.0, analyzer.calculateValidAverage(scores), 1e-12);
    }

    @Test
    public void calculateValidAverage_mixedWithNull_ignoresNullAndInvalid() {
        // valid: 6.0, 10.0 => avg = 8.0
        List<Double> scores = Arrays.asList(null, -2.0, 6.0, 10.0, 12.0);
        assertEquals(8.0, analyzer.calculateValidAverage(scores), 1e-12);
    }

    // ============================================================
    // Optional robustness tests for Double special values (recommended)
    // Only keep these if your spec expects to ignore them as invalid.
    // ============================================================

    @Test
    public void calculateValidAverage_specialValues_areIgnored() {
        List<Double> scores = Arrays.asList(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 5.0);
        // Expect avg = 5.0 if specials are treated invalid/ignored.
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 1e-12);
    }

    @Test
    public void countExcellentStudents_specialValues_notCounted() {
        List<Double> scores = Arrays.asList(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 9.0);
        // Only 9.0 is valid excellent
        assertEquals(1, analyzer.countExcellentStudents(scores));
    }
}