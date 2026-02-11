package main;

/**
 * Represents a quiz question with multiple options.
 */
public class Question {

    private int id;
    private String questionText, optionA, optionB, optionC, optionD, correctOption;

    /**
     * Constructs a Question object.
     */
    public Question(int id, String questionText, String optionA, String optionB,
                    String optionC, String optionD, String correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }
}
