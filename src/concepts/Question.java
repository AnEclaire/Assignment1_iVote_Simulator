package concepts;
import java.util.List;

/**
 * Emma Gutierrez
 * CS 3560 - Summer 2024
 * Dr. Yu Sun
 * Question.java
 */
public class Question {
    private boolean isMultiple;
    private String questionType;
    private String questionText;
    private String correctAnswer;
    private List<String> answers;

    public Question(String questionText, List<String> answers, boolean isMultiple, String correctAnswer) {
        this.answers = answers;
        this.isMultiple = isMultiple;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.questionType = isMultiple() ? "Multiple Choice" : "Single Choice";
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswers() {
        return correctAnswer;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public String getQuestionType() {
        return questionType;
    }
}
