/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.StringSeqHelper;
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
    System.out.println("checking letters: " + threeLetters.get(i).getLetter());
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

public String find() {
  String result = "";

  for (int i = 0; i < threeLetters.size(); i++) {
    //System.out.println("checking letters: " + threeLetters.get(i).getLetter());

    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://100-1.ru/index.asp");
// Request parameters and other properties.
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("main", "today"));
    params.add(new BasicNameValuePair("user_answer", threeLetters.get(i).getLetter()));

    try {
      httpPost.setEntity(new UrlEncodedFormEntity(params, "windows-1251"));
    } catch (UnsupportedEncodingException e) {
      // writing error to Log
      e.printStackTrace();
    }
    /*
 * Execute the HTTP Request
     */
    String content = "";
    try {
      HttpResponse response = httpClient.execute(httpPost);
      HttpEntity respEntity = response.getEntity();

      if (respEntity != null) {
        // EntityUtils to get the response content
        content = EntityUtils.toString(respEntity, Charset.forName("windows-1251"));
       // System.out.println(content);
      }
    } catch (ClientProtocolException e) {
      // writing exception to log
      e.printStackTrace();
    } catch (IOException e) {
      // writing exception to log
      e.printStackTrace();
    }

    Pattern pattern = Pattern.compile(REGEXP_RIGHT_ANSWER);
    Matcher matcher = pattern.matcher(content);
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
