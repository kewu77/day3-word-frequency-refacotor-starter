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

                return frequencies.stream().
                        sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount()).
                        map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount()).
                        collect(Collectors.joining(LINE_BREAK));

            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
