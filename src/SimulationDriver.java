import concepts.Question;
import concepts.Student;
import concepts.VotingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class SimulationDriver {
   static Random rand = new Random();
    /**
     * "No user input, just a loop, generate a bunch of questions and answers randomly and output them."
     * @param args
     */
    public static void main(String[] args) {
        int amountOfStudents = rand.nextInt((40 - 10) + 1) + 10;
        System.out.println("iVote polling system class size: " + amountOfStudents);
        multi(amountOfStudents);
        System.out.println();
        single(amountOfStudents);
    }

    private static void multi(int amountOfStudents) {
        String question = "What is the meaning of life?";
        List<String> ans = Arrays.asList("A: 42", "B: A Simulation", "C: Love", "D: Chippi Chippi Chappa Chappa");

        VotingService vs = new VotingService();
        vs.config(new Question(question, ans, true));

        // Generate list of students - Size can be 10 to 40
        List<Student> students = new ArrayList<Student>();

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

        Student[] duplicateAnswers = duplicateAnswerStudents(amountOfStudents, students);
        for (int i = 0; i < duplicateAnswers.length; i++) {
            System.out.println(duplicateAnswers[i].getId());
        }

        vs.displayResults();
    }

    private static void single(int amountOfStudents) {
        String question = "Is the Earth flat?";
        List<String> ans = Arrays.asList("True", "False");

        VotingService vs = new VotingService();
        vs.config(new Question(question, ans, false));

        // Generate list of students - Size can be 10 to 40
        List<Student> students = new ArrayList<Student>();

        for(int i = 0; i < amountOfStudents; i++) {
            Student stu = new Student(String.format("STU%02d", i + 1));
            students.add(stu);
            String answer = ans.get(rand.nextInt(ans.size()));
            vs.submitAnswer(stu, answer);
        }

        vs.displayResults();
    }

    private static Student[] duplicateAnswerStudents(int amountOfStudents, List<Student> students) {
        int amntToRedo = rand.nextInt(amountOfStudents);
        Student[] redoStudents = new Student[amntToRedo];
        for(int i = 0; i < amntToRedo; i++) {
            int rngStu = rand.nextInt(students.size());
            redoStudents[i] = students.get(rngStu);
            while(Arrays.asList(redoStudents).contains(students.get(rngStu))) {
                System.out.println(rngStu + " "  );
                rngStu = rand.nextInt(students.size());
                redoStudents[i] = students.get(rngStu);
            }
        }
        return redoStudents;
    }
}