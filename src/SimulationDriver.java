import concepts.Student;
import concepts.Question;
import concepts.VotingService;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Emma Gutierrez
 * CS 3560 - Summer 2024
 * Dr. Yu Sun
 * SimulationDriver.java
 */
public class SimulationDriver {
   static Random rand = new Random();
    /**
     * Main method, calls both main functions.
     */
    public static void main(String[] args) {
        int amountOfStudents = rand.nextInt((40 - 10) + 1) + 10;
        System.out.println("iVote polling system class size: " + amountOfStudents);
        multi(amountOfStudents);
        System.out.println();
        single(amountOfStudents);
    }

    /**
     * Processes the simulation for Multiple  Choice, Multiple  Answer questions. Question is pre-stored
     * alongside correct answer and list of answers. A {@link  VotingService} instance is created
     * for the simulation then configured with the relevant fields. Random class size of 10-40
     * is generated, populated, and then processed for answers randomly. Then an array representing a
     * second batch of randomly selected {@link Student} objects is generated as a subset of Students.
     * It is processed like the first batch of answers and then the display function is called.
     *
     * @param amountOfStudents previously generated class size ranging from 10 to 40.
     */
    private static void multi(int amountOfStudents) {
        String question = "What is the meaning of life?";
        String correctAnswer = "D: Chippi Chippi Chappa Chappa";
        List<String> ans = Arrays.asList("A: 42", "B: A Simulation", "C: Love", "D: Chippi Chippi Chappa Chappa");

        VotingService vs = new VotingService();
        vs.config(new Question(question, ans, true, correctAnswer));

        // Generate list of students - Size can be 10 to 40
        List<Student> students = new ArrayList<Student>();

        // Initial round of answers from students generated here randomly.
        for(int i = 0; i < amountOfStudents; i++) {
            Student stu = new Student(String.format("STU%02d", i + 1));
            students.add(stu);
            int amntOfAnswers = rand.nextInt(3) + 1;
            String answer = "";
            for(int j = 0; j < amntOfAnswers; j++) {
               answer += (ans.get(rand.nextInt(ans.size())) + ",");
            }
            vs.submitAnswer(stu, answer);
        }

        //vs.displayResults(); // Tester print to see that duplicate answers update properly.

        // Randomly determine which students answer a second time and add to list, culling initial attempt.
        Student[] duplicateAnswers = duplicateAnswerStudents(students);
        for(int i = 0; i < duplicateAnswers.length; i++) {
            Student stu = duplicateAnswers[i];
            students.add(stu);
            int amntOfAnswers = rand.nextInt(3) + 1;
            String answer = "";
            for(int j = 0; j < amntOfAnswers; j++) {
                answer += (ans.get(rand.nextInt(ans.size())) + ",");
            }
            vs.submitAnswer(stu, answer);
        }
        vs.displayResults();
    }

    /**
     * Processes the simulation for Single Choice, Single Answer questions. Question is pre-stored
     * alongside correct answer and list of answers. A {@link  VotingService} instance is created
     * for the simulation then configured with the relevant fields. Random class size of 10-40
     * is generated, populated, and then processed for answers randomly. Then an array representing a
     * second batch of randomly selected {@link Student} objects is generated as a subset of Students.
     * It is processed like the first batch of answers and then the display function is called.
     *
     * @param amountOfStudents previously generated class size ranging from 10 to 40.
     */
    private static void single(int amountOfStudents) {
        String correctAnswer = "False";
        String question = "Is the Earth flat?";
        List<String> ans = Arrays.asList("True", "False");

        VotingService vs = new VotingService();
        vs.config(new Question(question, ans, false, correctAnswer));

        // Generate list of students - Size can be 10 to 40
        List<Student> students = new ArrayList<Student>();

        // Initial round of answers from students generated here randomly.
        for(int i = 0; i < amountOfStudents; i++) {
            Student stu = new Student(String.format("STU%02d", i + 1));
            students.add(stu);
            String answer = ans.get(rand.nextInt(ans.size()));
            vs.submitAnswer(stu, answer);
        }

        //vs.displayResults(); // Tester print to see that duplicate answers update properly.

        // Randomly determine which students answer a second time and add to list, culling initial attempt.
        Student[] duplicateAnswers = duplicateAnswerStudents(students);
        for(int i = 0; i < duplicateAnswers.length; i++) {
            Student stu = duplicateAnswers[i];
            students.add(stu);
            String answer = ans.get(rand.nextInt(ans.size()));
            vs.submitAnswer(stu, answer);
        }

        vs.displayResults();
    }

    /**
     * Function that calculates which students in a given list of students to select as part
     * of the simulation for secondary answer submissions to the iVote VotingService.
     *
     * @param students Student list containing all students in the class
     * @return array of students that have been randomly selected to re-submit their answers.
     */
    private static Student[] duplicateAnswerStudents(List<Student> students) {
        int amountToRedo = rand.nextInt(students.size());
        Student[] redoStudents = new Student[amountToRedo];
        for(int i = 0; i < amountToRedo; i++) {
            // Pick a student at random, assign it to array, check it's not in array, repeat.
            int rngStu = rand.nextInt(students.size());
            redoStudents[i] = students.get(rngStu);
            while(Arrays.asList(redoStudents).contains(students.get(rngStu))) {
                rngStu = rand.nextInt(students.size());
            }
            redoStudents[i] = students.get(rngStu);
        }
        return redoStudents;
    }
}