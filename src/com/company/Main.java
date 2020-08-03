package com.company;
import javafx.util.Pair;

import java.io.File;
import java.io.FileReader;
import java.util.*;


public class Main {

    static final String PREFIX = "The changing word was:";
    static final String FILE_PATH = "resources\\input.txt";
    static final int PREAMBLE_LENGTH = 20;


    public static void main(String[] args) {

        // input strings
        ArrayList<String> input;

        // save input items not to process the same item again
        ArrayList<String> inputHistory = new ArrayList<>();

        // all possible sub-strings of an input item without one word
        // Pair is: <Integer: input string number, String: the relevant changed word (missing word)>
        HashMap<String, ArrayList<Pair<Integer,String>>> matches = new HashMap<>();
        StringBuilder sb;
        String partial = "";
        String txtOnly = "";


        input = getFileInput(FILE_PATH);


        for (int inputCounter=0; inputCounter<input.size(); inputCounter++) {
            txtOnly = input.get(inputCounter).substring(PREAMBLE_LENGTH);
            if (inputHistory.contains(txtOnly)) {
                continue;
            }
            inputHistory.add(txtOnly);
            String[] words = txtOnly.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                sb = new StringBuilder();

                for (int j = 0; j < words.length; j++) {
                    if (i != j) {
                        sb.append(words[j] + " ");
                    }
                }
                partial = sb.toString().trim();

                if (!matches.containsKey(partial)) {
                    matches.put(partial, new ArrayList<>());
                }
                matches.get(partial).add(new Pair(inputCounter,words[i]));
            }
        }

        for (Map.Entry<String, ArrayList<Pair<Integer,String>>> entry : matches.entrySet()) {
            if (entry.getValue().size() > 1) {
                sb = new StringBuilder();
                String output = "";
//                System.out.println("Related strings:");
//                System.out.println("================");
                for (int i=0; i<entry.getValue().size(); i++){
                    System.out.println(input.get(entry.getValue().get(i).getKey()));
                }
                System.out.print(PREFIX + " " );
                for (Pair pair : entry.getValue()) {
                    sb.append(pair.getValue() + ", ");
                }
                output = sb.subSequence(0, sb.length()-2).toString();
                System.out.println(output + "\n");
            }
        }
    }

    // import file strings into array
    public static ArrayList<String> getFileInput(String filename) {
        ArrayList<String> result = new ArrayList<>();

        try {
            File inputFile = new File(filename);
            Scanner s = new Scanner(new FileReader(inputFile));

            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
