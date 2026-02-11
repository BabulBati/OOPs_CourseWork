package main;

import java.util.List;

/**
 * Maintains a list of competitors and provides analysis operations.
 */
public class CompetitorList {

    private List<Competitor> competitors;

    /**
     * Loads all competitors from the database.
     */
    public CompetitorList() {
        competitors = Competitor.loadAllFromDB();
    }

    /**
     * @return list of all competitors
     */
    public List<Competitor> getAll() {
        return competitors;
    }

    /**
     * Finds the competitor with the highest overall score.
     *
     * @return top-performing competitor
     */
    public Competitor getTopPerformer() {
        if (competitors.isEmpty()) return null;

        Competitor top = competitors.get(0);
        for (Competitor c : competitors) {
            if (c.getOverallScore() > top.getOverallScore()) {
                top = c;
            }
        }
        return top;
    }

    /**
     * Calculates frequency of scores from 1 to 5.
     *
     * @return array of score frequencies
     */
    public int[] getScoreFrequency() {
        int[] frequency = new int[6];

        for (Competitor c : competitors) {
            for (int s : c.getScoreArray()) {
                if (s >= 1 && s <= 5) {
                    frequency[s]++;
                }
            }
        }
        return frequency;
    }

    /**
     * @return total number of competitors
     */
    public int size() {
        return competitors.size();
    }
}
