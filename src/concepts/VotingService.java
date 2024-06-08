package concepts;
import java.util.Map;
import java.util.HashMap;

/**
 * Emma Gutierrez
 * CS 3560 - Summer 2024
 * Dr. Yu Sun
 * VotingService.java
 */
public class VotingService {
    private Question question;
    private Map<String, String> submissions;

    /**
     * Default constructor, initializes empty submissions HashMap.
     */
    public VotingService() {
        submissions = new HashMap<>();
    }

    /**
     * Assigns internal question object to passed in question object.
     *
     * @param question passed in {@link Question} object
     */
    public void config(Question question) {
        this.question = question;
    }

    /**
     * Stores passed in student into map keyed to student ID. Value is student answer.
     *
     * @param student passed in {@link Student} object.
     * @param answer passed in string representation of a student's answer.
     */
    public void submitAnswer(Student student, String answer) {
        submissions.put(student.getId(), answer);
    }

    /**
     * Prints to terminal when called. Stores all results in a HashMap that is then accessed for
     * basic calculations, tracking, and output.
     */
    public void displayResults() {
        if(question == null) {
            System.out.println("No question has been set up!");
            return;
        }

        Map<String, Integer> resultsCounter = new HashMap<>();
        // Logic differs for question type as MultipleChoice can have multiple entries that must be counted.
        for(String answer : submissions.values()) {
            if(question.isMultiple()) {
                String[] answers = answer.split(",");
                for(String a: answers) {
                    resultsCounter.put(a.trim(), resultsCounter.getOrDefault(a.trim(), 0) + 1);
                }
            } else {
                resultsCounter.put(answer, resultsCounter.getOrDefault(answer, 0) + 1);
            }
        }

        int answerCounter = 0;

        System.out.println("Aggregate results for the following " + question.getQuestionType() + " question: " + question.getQuestionText());
        for(String ans : question.getAnswers()) {
            int specificAnswerCount = resultsCounter.getOrDefault(ans, 0);
            System.out.println(ans + ": " + specificAnswerCount);
            answerCounter += specificAnswerCount;
        }
        System.out.println("Correct Answer: " + question.getCorrectAnswers());
        System.out.println("Amount of correct responses: " + resultsCounter.get(question.getCorrectAnswers()) + "/" + answerCounter);
    }
}
