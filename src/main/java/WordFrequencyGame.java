import java.util.*;

public class WordFrequencyGame {
    public String WordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");
                List<WordFrequency> frequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    frequencies.add(wordFrequency);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(frequencies);
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencies.add(wordFrequency);
                }
                frequencies = wordFrequencies;
                frequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : frequencies) {
                    String wordDetail = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(wordDetail);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
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
