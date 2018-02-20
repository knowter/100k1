/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import ru.knowter.games.game100k1.letters.ThreeLetters;

/**
 *
 * @author knowter
 */
public class GameApp {

public static void main(String[] args) throws IOException {

  try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52)) {
    //webClient.setHTMLParserListener(HTMLParserListener.LOG_REPORTER);
    final HtmlPage page = webClient.getPage("http://100-1.ru/");
    String xmlPage = page.asXml();
    //System.out.println("xmlPage: \n"+xmlPage);
    ThreeLetters threeLetters = new ThreeLetters();
    
    CheckSumGetter checkSumGetter = new CheckSumGetter(xmlPage);
    ArrayList<Integer> checkSums = checkSumGetter.getCheckSum();
    for (int i = 0; i < 7; i++) {
      Integer checkSum = checkSums.get(i);
      System.out.println("chekSum " + i + ": " + checkSum);
      threeLetters.getLettersByCheckSum(checkSum);
    }
  }
}
}
