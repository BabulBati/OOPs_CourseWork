package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Competitor {

	// Competitor details
    private int competitorID;
    private Name name;
    private String level;
    private int age;
    private int[] scores;

    /**
     * Constructs a Competitor object with all required details.
     *
     * @param competitorID unique competitor number
     * @param name         name of the competitor
     * @param level        competition level
     * @param age          age of the competitor
     * @param scores       array of scores achieved by the competitor
     */
    public Competitor(int competitorID, Name name, String level, int age, int[] scores) {
        this.competitorID = competitorID;
        this.name = name;
        this.level = level;
        this.age = age;
        this.scores = scores;
    }

    /**
     * @return competitor ID
     */
    public int getCompetitorID() {
        return competitorID;
    }

    /**
     * @return Name object of the competitor
     */
    public Name getName() {
        return name;
    }

    /**
     * @return competition level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @return age of the competitor
     */
    public int getAge() {
        return age;
    }

    /**
     * @return array of competitor scores
     */
    public int[] getScoreArray() {
        return scores;
    }

    /**
     * Calculates the overall (average) score of the competitor.
     * Missing scores marked as -1 are ignored.
     *
     * @return average score, or 0.0 if no valid scores exist
     */
    public double getOverallScore() {
        int total = 0;
        int count = 0;

        // Sum valid scores only
        for (int s : scores) {
            if (s >= 0) {
                total += s;
                count++;
            }
        }

        return count > 0 ? (double) total / count : 0.0;
    }

    /**
     * Returns full detailed information about the competitor,
     * including name, age, level, individual scores, and overall score.
     *
     * @return formatted string containing full competitor details
     */
    public String getFullDetails() {
        StringBuilder scoreList = new StringBuilder();

        // Convert score array to readable format
        for (int i = 0; i < scores.length; i++) {
            scoreList.append(scores[i] == -1 ? "NA" : scores[i]);
            if (i < scores.length - 1) {
                scoreList.append(", ");
            }
        }

        return "Competitor number " + competitorID +
               ", name " + name +
               ", age " + age + ".\n" +
               name.getFirstName() + " is a " + level +
               " and received these scores: " + scoreList + ".\n" +
               "Overall score: " + getOverallScore();
    }

    /**
     * Returns a short summary of the competitor.
     *
     * @return short competitor details
     */
    public String getShortDetails() {
        return "CN " + competitorID +
               " (" + name.getInitials() + ") has overall score " +
               getOverallScore();
    }

    /**
     * Loads all competitors and their scores from the database.
     *
     * @return list of Competitor objects retrieved from the database
     */
    public static List<Competitor> loadAllFromDB() {

        List<Competitor> list = new ArrayList<>();

        // SQL query to retrieve competitor and score data
        String sql = """
                SELECT c.competitorID, c.firstName, c.lastName, c.age,
                       s.level, s.score1, s.score2, s.score3, s.score4, s.score5
                FROM Competitors c
                LEFT JOIN score s ON c.competitorID = s.competitorID
            """;

        try (
            // Establish database connection
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {

            // Process each record from the result set
            while (rs.next()) {

                // Read scores and handle NULL values
                int[] scores = new int[5];
                for (int i = 1; i <= 5; i++) {
                    int value = rs.getInt("score" + i);
                    scores[i - 1] = rs.wasNull() ? -1 : value;
                }

                // Create Name object from database values
                Name name = new Name(
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );

                // Handle missing level value
                String level = rs.getString("level");
                if (level == null) {
                    level = "-";
                }

                // Create Competitor object and add it to the list
                list.add(new Competitor(
                        rs.getInt("competitorID"),
                        name,
                        level,
                        rs.getInt("age"),
                        scores
                ));
            }

        } catch (Exception e) {
            // Print error details if database access fails
            e.printStackTrace();
        }

        return list;
    }
}
