import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";

    public String WordFrequency(String sentence) {
        if (sentence.split(REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(REGEX);
                List<WordFrequency> frequencies = Arrays.stream(words).
                        map(word -> new WordFrequency(word,1)).
                        collect(Collectors.toList());
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(frequencies);
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencies.add(wordFrequency);
                }
                frequencies = wordFrequencies;
                frequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency wordFrequency : frequencies) {
                    String wordDetail = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(wordDetail);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            } else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return map;
    }


}
