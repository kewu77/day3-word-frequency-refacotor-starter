import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String ERROR_MESSAGE = "Calculate Error";
    public static final String SPACE = " ";
    public static final String SPECIAL_WORD = "";

    public String WordFrequency(String sentence) {


        try {
            if(sentence.equals(SPECIAL_WORD))
                return SPECIAL_WORD;
            //split the input string with 1 to n pieces of spaces
            List<WordFrequency> frequencies = getInitialWordFrequencies(sentence);

            //get the map for the next step of sizing the same word
            frequencies = getFrequencies(frequencies);

            return joinResult(frequencies);

        } catch (Exception e) {
            return ERROR_MESSAGE;
        }

    }

    private String joinResult(List<WordFrequency> frequencies) {
        return frequencies.stream().
                sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount()).
                map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount()).
                collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getFrequencies(List<WordFrequency> frequencies) {
        Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(frequencies);
        return wordToWordFrequency.entrySet().stream().map((entry) -> new WordFrequency(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());

    }

    private List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(REGEX);
        return Arrays.stream(words).
                map(word -> new WordFrequency(word, 1)).
                collect(Collectors.toList());
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}
