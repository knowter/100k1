/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author knowter
 */
public class GameApp {

public static void main(String[] args) throws IOException {

  try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "10.250.25.59", 3128)) {
    //webClient.setHTMLParserListener(HTMLParserListener.LOG_REPORTER);
    final HtmlPage page = webClient.getPage("http://100-1.ru/");
    String xmlPage = page.asXml();
    //System.out.println("xmlPage: \n"+xmlPage);

    CheckSumGetter checkSumGetter = new CheckSumGetter(xmlPage);
    ArrayList<Integer> checkSum = checkSumGetter.getCheckSum();
    for (int i = 0; i < 7; i++) {
      System.out.println("chekSum " + i + ": " + checkSum.get(i));
    }
  }
}
}
