import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Logout {
    public void logout() throws IOException {
        // stack is used
        Stack s = new Stack<>();  // creating stack  object as stack class is predefined here
        FileReader gFileReader = new FileReader("qotes"); // reading quotation file
        BufferedReader c = new BufferedReader(gFileReader);  // reads line by line
        String line;  // stores file line in variable line
        while((line=c.readLine())!=null) {
            s.push(line); // addding line by line in satck
        }
        Random rand = new Random();

        // using random class to get random quotation from qotes file
        String string = (String) s.get(rand.nextInt(s.size()));
        // by random method we are getting randomly one number from 0 to size of stack so u will get random number and using .get method you will get that index's value from stack
        System.out.println(ConsoleColors.BLACK_BOLD+"----------------------------------------------------------------------------------------------------"+"\u001B[0m");
        System.out.println(ConsoleColors.ORANGE_BACKGROUND+ConsoleColors.BLACK_BOLD+string);
        System.out.println("\u001B[0m");
c.close();
    }
}
