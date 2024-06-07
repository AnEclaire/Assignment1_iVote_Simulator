package concepts;
import java.util.List;

public class Question {
    private boolean isMultiple;
    private String questionText;
    private List<String> answers;

    public Question(String questionText, List<String> answers, boolean isMultiple) {
        this.answers = answers;
        this.isMultiple = isMultiple;
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public boolean isMultiple() {
        return isMultiple;
    }
}
