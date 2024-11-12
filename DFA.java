/** @author Sadia Afreen */
import java.util.*;
public class DFA{
    /** Declaring start state, final states, transitions for DFA */
    Character start;
    Set<Character> ends;
    Map<Character, Map<Character,Character>> dfaTransitionList;
    DFA(Character startState, Set<Character> finalStates, ArrayList<String> DfaTransitions) {
        dfaTransitionList = new TreeMap<>();

        /* Assigning starting state and final states values */

        start = startState;
        ends =  finalStates;

        /* Transitions for DFA */

        for (String e : DfaTransitions) {
            char from = e.charAt(0), to = e.charAt(2), c = e.charAt(1);
            if (!dfaTransitionList.containsKey(from))
                dfaTransitionList.put(from, new TreeMap<>());
            dfaTransitionList.get(from).put(c, to);
        }
    }
    /* DFA matching for input string */
    public boolean dfaMatch(String s) {
        Character curr = start;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!dfaTransitionList.get(curr).containsKey(c)) {
                System.out.println("Invalid input for DFA. No transition from "+curr+" for "+c);
                return false;
            }
            curr = dfaTransitionList.get(curr).get(c);
        }
        return ends.contains(curr);
    }
}
