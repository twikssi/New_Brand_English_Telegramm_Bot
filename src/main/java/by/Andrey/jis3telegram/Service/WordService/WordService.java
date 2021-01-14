package by.Andrey.jis3telegram.Service.WordService;

import by.Andrey.jis3telegram.bean.Word;
import by.Andrey.jis3telegram.data.service.ValidateService;
import by.Andrey.jis3telegram.enums.PartsOfSpeech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordService {

    public static Word createNewWord(String name, PartsOfSpeech partsOfSpeech, String transcription, String meaning, String example, boolean isDaily){
        return Word.builder()
                .name(name)
                .partsOfSpeech(partsOfSpeech)
                .transcription(transcription)
                .meaning(meaning)
                .example(example)
                .isDaily(isDaily).build();
    }

    public static Word createNewWordFromNoFormatStringList(List<String> listNoFormatFieldsOfWord){
        List<String> listFormatFieldsOfWord = ValidateService.getListFieldsOfWord(listNoFormatFieldsOfWord);
        return Word.builder()
                .name(listFormatFieldsOfWord.get(0))
                .partsOfSpeech(PartsOfSpeech.valueOf(listFormatFieldsOfWord.get(1)))
                .transcription(listFormatFieldsOfWord.get(2))
                .meaning(listFormatFieldsOfWord.get(3))
                .example(listFormatFieldsOfWord.get(4))
                .isDaily(false).build();
    }

    public static boolean validateWord(Word word){
        if (word.getPartsOfSpeech() == PartsOfSpeech.NO_PART_OF_SPEECH &&
                word.getTranscription().equals("[]") &&
                word.getMeaning().equals("")){
            return false;
        } else {
            return true;
        }
    }

    public static List<Word> getListOnlyWords(List<Word> words){
        List<Word> onlyWords = words.stream().filter(a -> a.getPartsOfSpeech() != PartsOfSpeech.PHRASAL_VERB)
                .collect(Collectors.toList());
        return onlyWords;
    }

    public static List<Word> getListOnlyPhrasalVerbs(List<Word> words){
        List<Word> onlyWords = words.stream().filter(a -> a.getPartsOfSpeech() == PartsOfSpeech.PHRASAL_VERB)
                .collect(Collectors.toList());
        return onlyWords;
    }

    public static Word getRandomWord (List<Word> words){
        List<Word> wordNoImmutable = new ArrayList<>();
        wordNoImmutable.addAll(words);
        Collections.shuffle(wordNoImmutable);
        return wordNoImmutable.get(0);
    }

    public static List<Word> getListWordsFromListString(List<String> wordsString){
        List<Word> wordsList = new ArrayList<>();
        for (String line: wordsString){
            String[] lineArray = line.split("=");
            wordsList.add(Word.builder()
                    .name(lineArray[0])
                    .partsOfSpeech(PartsOfSpeech.valueOf(lineArray[1].toUpperCase()))
                    .transcription(lineArray[2])
                    .meaning(lineArray[3])
                    .example(lineArray[4])
                    .numberOfRepetitions(Long.valueOf(lineArray[5]))
                    .isDaily(Boolean.valueOf(lineArray[6]))
                    .build());
        }
        return wordsList;
    }

    public static void printList(List<Word> words){
       words.stream().forEach(a -> System.out.println(a.getAmazingView()));
    }

    public static void increaseNumberOfRepetitions(List<Word> words, Word wordToIncrease){
        for (Word objWord: words){
            if (objWord.equals(wordToIncrease)){
                objWord.setNumberOfRepetitions(objWord.getNumberOfRepetitions() + 1);
            }
        };
    }

    public static boolean isWordExistInList(List<Word> words, String wanted){
        boolean isExiste = false;
        for(Word word: words){
            if (word.getName().equals(wanted.toLowerCase().trim())){
                isExiste = true;
            }
        }
        return isExiste;
    }

    public static Word searchWordWithName(List<Word> words, String wanted){
        return words.stream()
                .filter(a->a.getName().equals(wanted.toLowerCase().trim()))
                .collect(Collectors.toList()).get(0);
    }
}
