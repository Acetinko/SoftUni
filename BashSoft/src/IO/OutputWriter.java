package IO;

import java.util.List;

/*
    Basic write to console functionality
*/
public class OutputWriter {
    /*
    Method for writing a message to the console
     */
    public static void writeMessage(String message){
        System.out.print(message);
    }

    /*
    Method to writing message to a new line
     */
    public static void writeMessageOnNewLine(String message){
        System.out.println(message);
    }

    /*
    Method to write new empty line
     */
    public static void writeEmptyLine(){
        System.out.println();
    }

    /*
    Method for writing messages for errors, exception and etc.
     */
    public static void displayException(String message) {
        System.out.print(message);
    }

    public static void printStudent(String name, List<Integer> marks){
        String output = String.format("%s - %s", name, marks.toString());
        OutputWriter.writeMessageOnNewLine(output);
    }
}