package by.Andrey.jis3telegram.data.service;

import by.Andrey.jis3telegram.Service.WordService.WordService;
import by.Andrey.jis3telegram.bean.Word;
import by.Andrey.jis3telegram.data.dataFromWebSite.DataFromWebSite;
import by.Andrey.jis3telegram.enums.PartsOfSpeech;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DataServiceTest {
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
    public void copyFiles() {
        DataService.copyFiles("wordsCopy.txt", "wordsTest.txt");
    }


    @Test
    public void writeListWordsToFile() {
        words = List.of(
                WordService.createNewWord("get", PartsOfSpeech.VERB, "[get]", "получать", "I get info", false),
                WordService.createNewWord("get over", PartsOfSpeech.PHRASAL_VERB, "[get over]", "справлять с проблемой", "I get over my legs pain", false),
                WordService.createNewWord("slide", PartsOfSpeech.VERB, "[slaɪd]", "скользить", " When I was little I used to like sliding on the polished floor in my socks. ", false),
                WordService.createNewWord("innocent", PartsOfSpeech.ADJECTIVE, "[ˈɪn.ə.sənt]", "невиновный", "He firmly believes that she is innocent of the crime.", false),
                WordService.createNewWord("stay up", PartsOfSpeech.PHRASAL_VERB, "[stay up]", "не ложиться спать", " I stayed up to watch the Olympics on television. ", false),
                WordService.createNewWord("humble", PartsOfSpeech.ADJECTIVE, "[ˈhʌm.bl]", "not proud or not believing that you are important", "He's very humble about his success.", false)
        );
        try {
            DataService.writeListWordsToFile("wordsTest.txt", words , false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rewriteFieldNumberOfRepetitionToFile() throws IOException {
        Word word = WordService.createNewWord("get", PartsOfSpeech.VERB, "[get]", "получать", "I get info", false);
        word.setNumberOfRepetitions(2);
        DataService.rewriteFieldNumberOfRepetitionToFile("wordsTest.txt", "wordsCopyTest.txt", word);
    }

//    @org.junit.Test
//    public void deleteWordFromFile() throws IOException {
//        List<String> listString = DataService.getListStringWordsFromFile("wordsTest.txt");
//        List<Word> listWords = WordService.getListWordsFromListString(listString);
//        Word word = WordService.searchWordWithName(listWords,"pull out");
//
//        DataService.deleteWordFromFile(listWords,"wordsTest.txt", "wordsCopyTest.txt", word );
//    }

//    @org.junit.Test
//    public void writeNewWordToFile() throws IOException {
//        DataFromWebSite fromWebSite = new DataFromWebSite("pull");
//        Word word = WordService.createNewWordFromNoFormatStringList(fromWebSite.getListNoFormatFieldOfWord());
//        DataService.writeNewWordToFile("wordsTest.txt", "wordsCopyTest.txt", word);
//    }
}