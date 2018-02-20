/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author knowter
 */
public class CheckSumGetter {

private static final String REGEXP_CHECKSUM = "var A.*\\((.*)\\)";
private String xmlString;
private ArrayList<Integer> checkSum;

public CheckSumGetter(String xmlString) {
  this.xmlString = xmlString;
  this.checkSum = null;
}

/**
 * Get the value of xmlString
 *
 * @return the value of xmlString
 */
public String getString() {
  return xmlString;
}

/**
 * Set the value of string
 *
 * @param xmlString new value of string
 */
public void setString(String xmlString) {
  this.xmlString = xmlString;
}

public ArrayList<Integer> getCheckSum() {
  if (checkSum != null) {
    return checkSum;
  }

  if (xmlString.isEmpty()) {
    return null;
  }

  Pattern pattern = Pattern.compile(REGEXP_CHECKSUM);
  Matcher matcher = pattern.matcher(xmlString);
  matcher.find();
  String checkSumStr = null;
  try {
    checkSumStr = matcher.group(1);
  } catch (IllegalStateException | IndexOutOfBoundsException ex) {
    return null;
  }

  if (checkSumStr == null) {
    return null;
  }

  String[] arrayCheckSumm = checkSumStr.split(",");

  if (arrayCheckSumm.length != 7) {
    return null;
  }

  checkSum = new ArrayList<>();
  try {
    for (int i = 0; i < 7; i++) {
      checkSum.add(Integer.parseInt(arrayCheckSumm[i].trim()));
    }
  } catch (NumberFormatException e) {
    return null;
  }

  return checkSum;
}

}
