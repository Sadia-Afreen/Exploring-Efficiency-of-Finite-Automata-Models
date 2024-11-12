/** @author Sadia Afreen */
import java.util.*;
public class ENFA {
    /** Declaring start state, final states, transitions for NFA */
    Character startState;
    Set<Character> finalState;
    Map<Character, Map<Character, List<Character>>> nfaTransitionList;
    ENFA(Character start, Set<Character> ends, ArrayList<String> nfaTransition) {
        nfaTransitionList = new TreeMap<>();

        /* Assigning starting state and final states values */

        startState = start;
        finalState = ends;

        /* Transitions for NFA */
        for (String e : nfaTransition) {
            char from = e.charAt(0), to = e.charAt(2), c = e.charAt(1);
            if (!nfaTransitionList.containsKey(from))
                nfaTransitionList.put(from, new TreeMap<>());
            if (!nfaTransitionList.get(from).containsKey(c))
                nfaTransitionList.get(from).put(c, new ArrayList<>());
            nfaTransitionList.get(from).get(c).add(to);
        }
    }
    /* NFA matching for input string */
    public void nfaMatch(String s) {
        Set<Character> currStates = new TreeSet<>();
        currStates.add(startState);

        int j = 0;
        int flag = 0;
        while (j < s.length()) {
            int read_input = 0;
            char c = s.charAt(j);
            Set<Character> nextStates = new TreeSet<>();

            /* if only transition is epsilon */
            if (Objects.equals(s, "e")) {
                if (nfaTransitionList.get(startState).containsKey('e'))
                    currStates.addAll(nfaTransitionList.get(startState).get('e'));
                break;
            }
            for (Character state : currStates) {
                if ((!nfaTransitionList.containsKey(state)) && j < s.length()) {
                    flag = 0;
                    read_input = 1;
                    break;
                }
                /* Handling non-epsilon transitions */
                if (nfaTransitionList.get(state).containsKey(c)) {
                    nextStates.addAll(nfaTransitionList.get(state).get(c));
                    read_input = 1;
                }
                /* Handling epsilon transitions */
                else if (nfaTransitionList.get(state).containsKey('e')) {
                    nextStates.addAll(nfaTransitionList.get(state).get('e'));
                }
            }

            if (nextStates.isEmpty()) flag = 0;
            currStates = nextStates;
            if (read_input == 1) j++;
        }
        /* Handling epsilon transition for the last state */
        List<Character> currStatesList = new ArrayList<>(currStates);
        if (currStatesList.size() > 0) {
            Character lastState = currStatesList.get(currStatesList.size() - 1);
            if (nfaTransitionList.containsKey(lastState) && nfaTransitionList.get(lastState).containsKey('e'))
                currStates.addAll(nfaTransitionList.get(lastState).get('e'));
        }
        /* Searching if final state exists in the traversed states by the input string */
        for (Character state : currStates) {
            if (finalState.contains(state)) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) System.out.println("no");
        if (flag == 1) System.out.println("yes");
    }
}
