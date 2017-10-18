import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentRepository {
    /* boolean variable to check if data is already initialized */
    public static boolean isDataInitialized = false;
    /* Map that will contains courses with students with list of their grades */
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    public static void initializeData(){
        if(isDataInitialized) {
            System.out.println(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }
        /* Initialize the Map */
        studentsByCourse = new HashMap<>();
        readData();
    }

    private static void readData(){
        /* Method to fill the Map from console */
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("")){
            String[] tokens = input.split("\\s+");
            String course = tokens[0];
            String student = tokens[1];
            Integer mark = Integer.parseInt(tokens[2]);

            if(!studentsByCourse.containsKey(course)){
                studentsByCourse.put(course, new HashMap<>());
            }
            if(!studentsByCourse.get(course).containsKey(student)){
                studentsByCourse.get(course).put(student, new ArrayList<>());
            }
            studentsByCourse.get(course).get(student).add(mark);

            input = scanner.nextLine();

            isDataInitialized = true;

        }
        // OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private static boolean isQueryForCoursePossible(String courseName){
        if(!isDataInitialized){
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if(!studentsByCourse.containsKey(courseName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
        }

        return true;
    }

    private static boolean isQueryForStudentPossible(String courseName, String studentName){
        if(!isQueryForCoursePossible(courseName)){
            return false;
        }

        if(!studentsByCourse.get(courseName).containsKey(studentName)){
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }
        return true;
    }

    /* Get student and print its marks */
    public static void getStudentMarksInCourse(String course, String student){
        if(!isQueryForStudentPossible(course, student)){
            return;
        }

        /* Collect grades of the student in variable */
        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    public static void getStudentsByCourse(String course){
        if(!isQueryForCoursePossible(course)){
            return;
        }

        OutputWriter.writeMessageOnNewLine(course +":");
        for(Map.Entry<String, ArrayList<Integer>> student : studentsByCourse.get(course).entrySet()){
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }
}
