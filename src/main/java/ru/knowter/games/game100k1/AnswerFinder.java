/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.knowter.games.game100k1.letters.Letter;

/**
 *
 * @author knowter
 */
public class AnswerFinder {

private ArrayList<Letter> threeLetters;
private static final String REGEXP_RIGHT_ANSWER = "Совeршeнно вeрно.*(\\\".*\\\")";

public AnswerFinder(ArrayList<Letter> threeLetters) {
  this.threeLetters = threeLetters;
}

public String find(HtmlPage page) {
  String result = "";

  for (int i = 0; i < threeLetters.size(); i++) {
    page.getFormByName("sendans").getInputByName("user_answer").setValueAttribute(threeLetters.get(i).getLetter());
    System.out.println("checking letters: "+threeLetters.get(i).getLetter());
    Page click;
    try {
      click = page.getFormByName("sendans").getInputByValue("Отправить").click();
    } catch (IOException ex) {
      continue;
    }

    Pattern pattern = Pattern.compile(REGEXP_RIGHT_ANSWER);
    Matcher matcher = pattern.matcher(click.getWebResponse().getContentAsString());
    matcher.find();
    try {
      result = matcher.group(1);
      break;
    } catch (IllegalStateException | IndexOutOfBoundsException ex) {
      continue;
    }
  }
  return result;
}

public ArrayList<Letter> getThreeLetters() {
  return threeLetters;
}

public void setThreeLetters(ArrayList<Letter> threeLetters) {
  this.threeLetters = threeLetters;
}

}
