package main;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Controls application flow and menu-based interaction.
 */
public class Manager {

    private CompetitorList competitorList;
    private Scanner scanner;

    /**
     * Initializes competitor list and input scanner.
     */
    public Manager() {
        competitorList = new CompetitorList();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Displays menu and processes user choices.
     */
    public void start() {
        int choice = 0;

        do {
            System.out.println("\n Competitor Management \n");
            System.out.println("1. Generate Full Report");
            System.out.println("2. Display Top Performer");
            System.out.println("3. Generate Statistics");
            System.out.println("4. Search Competitor by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {
	            case 1:
	                fullReport();
	                break;
	            case 2:
	                topPerformer();
	                break;
	            case 3:
	                statistics();
	                break;
	            case 4:
	                searchID();
	                break;
	            case 5:
	                System.out.println("See You Again!");
	                break;
	            default:
	                System.out.println("Invalid choice!");
	        }

        } while (choice != 5);
    }
    
    private void fullReport() {
        System.out.println("\n Full Report \n");

        System.out.printf("%-5s %-20s %-12s %-20s %-8s\n",
                "ID", "Name", "Level", "Scores", "Overall","\n");


        for (Competitor c : competitorList.getAll()) {
            int[] scores = c.getScoreArray();
            String[] displayScores = new String[scores.length];
            for (int i = 0; i < scores.length; i++) {
                displayScores[i] = scores[i] == -1 ? "NA" : String.valueOf(scores[i]);
            }

            System.out.printf("%-5d %-20s %-12s %-20s %-8.2f\n",
                    c.getCompetitorID(),
                    c.getName(),
                    c.getLevel() == null ? "-" : c.getLevel(),
                    Arrays.toString(displayScores),
                    c.getOverallScore());
        }

    }

    private void topPerformer() {
        Competitor top = competitorList.getTopPerformer();
        if (top == null) {
            System.out.println("No competitors found.");
            return;
        }
        System.out.println("\n Top Performer \n");
        System.out.println(top.getFullDetails());
    }
    
    private void statistics() {
        int[] freq = competitorList.getScoreFrequency();
        System.out.println("\n Statistics \n");
        System.out.println("\nTotal competitors: " + competitorList.size());
        System.out.println("Score Frequency:");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Score " + i + ": " + freq[i]);
        }
    }

    private void searchID() {
        System.out.println("\n Competitor ID \n");
        System.out.print("Enter Competitor ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Competitor best = null;
        for (Competitor c : competitorList.getAll()) {
            if (c.getCompetitorID() == id) {
                if (best == null || c.getOverallScore() > best.getOverallScore()) {
                    best = c;
                }
            }
        }

        if (best == null) {
            System.out.println("Competitor not found.");
        } else {
            System.out.println(best.getShortDetails());
        }
    }

}