/** @author Sadia Afreen */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
public class FASimulator {
    /** Driver class for Finite Automata Simulation: NFA, DFA and matching */
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 1) {
            System.out.println("Error, usage: java ClassName inputfile");
            System.exit(1);
        }
        /* Taking input files and simulation type from console */
        Scanner selectSimulation = new Scanner(args[0]);
        Scanner testCase = new Scanner(new FileInputStream(args[1]));
        Scanner input = new Scanner(new FileInputStream(args[2]));
        String inputString = input.nextLine();

        /* Initializing local variable for start state, final states, transitions */
        char startState = 0;
        Set<Character> finalStates = new TreeSet<>();
        ArrayList<String> transition = new ArrayList<>();
        Set<Character> states = new HashSet<>();

        /* Processing input files */
        Scanner rowScanner;
        int i = 0;
        while (testCase.hasNextLine()) {
            String s = testCase.nextLine();
            rowScanner = new Scanner(s);
            if (i == 0) {
                while (rowScanner.hasNext()) {
                    Character f = rowScanner.next().charAt(0);
                    finalStates.add(f);
                    states.add(f);
                }
            }
            if (i == 1) {
                char ss =  rowScanner.next().charAt(0);
                startState = ss;
                states.add(ss);
            }
            if (i == 2) {
                while (rowScanner.hasNext()) {
                    char state = rowScanner.next().charAt(0);
                    states.add(state);
                }
            }
            if (i == 3) {
                while (rowScanner.hasNext()) {
                    String t = rowScanner.next();
                    transition.add(t);
                }
            }
            i++;
        }
        /* Declaring runtime calculation variables */
        long startTime, endTime;

        /* Simulation types and action */
        String simulationType = selectSimulation.next();

        if (simulationType.equals("1")) {
            ENFA enfa = new ENFA(startState, finalStates, transition);
            startTime = System.currentTimeMillis();
            enfa.nfaMatch(inputString);
            endTime = System.currentTimeMillis();
//            System.out.println("NFA Runtime: " + (endTime - startTime) + " milliseconds");
        }
        else if (simulationType.equals("2")) {
            DFA dfa = new DFA(startState, finalStates, transition);
            startTime = System.currentTimeMillis();
            if (dfa.dfaMatch(inputString))
                System.out.println("yes");
            else System.out.println("no");
            endTime = System.currentTimeMillis();
//            System.out.println("DFA Runtime: " + (endTime - startTime) + " milliseconds");
        }
//        System.out.println("States [Q]: "+states.size());
//        System.out.println("Transitions [Delta]: "+transition.size());
    }
}