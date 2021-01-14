package by.Andrey.jis3telegram.Service.WordService;

import by.Andrey.jis3telegram.bean.Word;
import by.Andrey.jis3telegram.data.dataFromWebSite.DataFromWebSite;
import by.Andrey.jis3telegram.data.service.DataService;
import by.Andrey.jis3telegram.enums.PartsOfSpeech;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.Andrey.jis3telegram.Service.WordService.WordService.*;
import static org.junit.Assert.*;

public class WordServiceTest {
    List<Word> words;

    @Before
    public void setup(){
        words = List.of(
                WordService.createNewWord("get", PartsOfSpeech.VERB, "[get]", "получать", "I get info", false),
                WordService.createNewWord("get over", PartsOfSpeech.PHRASAL_VERB, "[get over]", "справлять с проблемой", "I get over my legs pain", false),
                WordService.createNewWord("slide", PartsOfSpeech.VERB, "[slaɪd]", "скользить", " When I was little I used to like sliding on the polished floor in my socks. ", false),
                WordService.createNewWord("innocent", PartsOfSpeech.ADJECTIVE, "[ˈɪn.ə.sənt]", "невиновный", "He firmly believes that she is innocent of the crime.", false),
                WordService.createNewWord("stay up", PartsOfSpeech.PHRASAL_VERB, "[stay up]", "не ложиться спать", " I stayed up to watch the Olympics on television. ", false),
                WordService.createNewWord("humble", PartsOfSpeech.ADJECTIVE, "[ˈhʌm.bl]", "not proud or not believing that you are important", "He's very humble about his success.", false)
        );
    }

    @Test
    public void createNewWord() {
       printList(words);
    }

    @Test
    public void getListOnlyWords() {
        printList(WordService.getListOnlyWords(words));

        Word expected = WordService.getListOnlyWords(words).get(0);
        Word actual = WordService.createNewWord("get", PartsOfSpeech.VERB, "[get]", "получать", "I get info", false);
        assertEquals(expected, actual);
    }

    @Test
    public void getListOnlyPhrasalVerbs() {
        printList(WordService.getListOnlyPhrasalVerbs(words));

        Word expected = WordService.getListOnlyPhrasalVerbs(words).get(0);
        Word actual = WordService.createNewWord("get over", PartsOfSpeech.PHRASAL_VERB, "[get over]", "справлять с проблемой", "I get over my legs pain", false);
        assertEquals(expected, actual);
    }

    @Test
    public void getRandomWord() {
        System.out.println(WordService.getRandomWord(WordService.getListOnlyWords(words)).getAmazingView());
    }

    @Test
    public void getListWordsFromListString() throws IOException {
        List<String> listString = DataService.getListStringWordsFromFile("wordsnewbrandcopytest.txt");
        List<Word> result = WordService.getListWordsFromListString(listString);
                result.forEach(System.out::println);

        String expected = "Word(name=stand out, partsOfSpeech=PHRASAL_VERB, transcription=[], meaning=to be very noticeable:, example=The black lettering really stands out on that orange background., numberOfRepetitions=0, isDaily=false)";
        String actual = result.get(4).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void increaseNumberOfRepetitions(){
       Word word = WordService.createNewWord("get", PartsOfSpeech.VERB, "[get]", "получать", "I get info", false);
       List<Word> wordsNoIm = new ArrayList<>(words);
       wordsNoIm.forEach(a-> System.out.println(a.viewToWriteFile()));
       WordService.increaseNumberOfRepetitions(wordsNoIm, word);
       wordsNoIm.forEach(a-> System.out.println(a.viewToWriteFile()));

       long expected = 1;
       long actual = wordsNoIm.get(0).getNumberOfRepetitions();
       assertEquals(expected,actual);
    }


    @Test
    public void isWordExistInList() {
        String word1 = "   stay uP";

        boolean expected = true;
        boolean actual = WordService.isWordExistInList(words, word1);
        assertEquals(expected,actual);

    }

    @Test
    public void searchWordWithName() {
        String word1 = "   stay uP";
        Word word = WordService.createNewWord("stay up", PartsOfSpeech.PHRASAL_VERB, "[stay up]", "не ложиться спать", " I stayed up to watch the Olympics on television. ", false);
        System.out.println(WordService.searchWordWithName(words, word1));

        Word expected = word;
        Word actual = WordService.searchWordWithName(words, word1);
        assertEquals(expected,actual);

    }

//    @Test
//    public void createNewWordFromNoFormatStringList() {
//        DataFromWebSite fromWebSite = new DataFromWebSite("hit");
//        Word word = WordService.createNewWordFromNoFormatStringList(fromWebSite.getListNoFormatFieldOfWord());
//        System.out.println(word.getAmazingView());
//    }
}