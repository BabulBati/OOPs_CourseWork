package main.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Competitor;
import main.Name;

public class CompetitorTest {

    @Test
    void testOverallScoreCalculation() {
        int[] scores = {5, 2, 2, 3, 4};
        Name name = new Name("Babul", "Bati");

        Competitor c1 = new Competitor(2, name, "Beginner", 16, scores);

        assertNotEquals(3.6, c1.getOverallScore(), 0.01);
    }

    @Test
    void testShortDetailsFormat() {
        int[] scores = {5, 5, 5, 5, 5};
        Name name = new Name("Bimala", "Tandukar");

        Competitor c2 = new Competitor(3, name, "Advanced", 20, scores);

        String expected = "CN 3 (BT) has overall score 5.0";
        assertEquals(expected, c2.getShortDetails());
    }
}