/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1.letters;

import java.util.ArrayList;

/**
 *
 * @author knowter
 */
public class ThreeLetters {

private ArrayList<Letter> letters;
private ArrayList<Letter> threeLetters;


public ThreeLetters() {
  letters = new ArrayList<>();
  initLetters(letters);
  initThreeLetters(threeLetters);
}

private void initLetters(ArrayList<Letter> pLetters) {
  int i = 1040;
  pLetters.add(new Letter(i++, "А"));
  pLetters.add(new Letter(i++, "Б"));
  pLetters.add(new Letter(i++, "В"));
  pLetters.add(new Letter(i++, "Г"));
  pLetters.add(new Letter(i++, "Д"));
  pLetters.add(new Letter(i++, "Е"));
  pLetters.add(new Letter(i++, "Ж"));
  pLetters.add(new Letter(i++, "З"));
  pLetters.add(new Letter(i++, "И"));
  pLetters.add(new Letter(i++, "Й"));
  pLetters.add(new Letter(i++, "К"));
  pLetters.add(new Letter(i++, "Л"));
  pLetters.add(new Letter(i++, "М"));
  pLetters.add(new Letter(i++, "Н"));
  pLetters.add(new Letter(i++, "О"));
  pLetters.add(new Letter(i++, "П"));
  pLetters.add(new Letter(i++, "Р"));
  pLetters.add(new Letter(i++, "С"));
  pLetters.add(new Letter(i++, "Т"));
  pLetters.add(new Letter(i++, "У"));
  pLetters.add(new Letter(i++, "Ф"));
  pLetters.add(new Letter(i++, "Х"));
  pLetters.add(new Letter(i++, "Ц"));
  pLetters.add(new Letter(i++, "Ч"));
  pLetters.add(new Letter(i++, "Ш"));
  pLetters.add(new Letter(i++, "Щ"));
  pLetters.add(new Letter(i++, "Ъ"));
  pLetters.add(new Letter(i++, "Ы"));
  pLetters.add(new Letter(i++, "Ь"));
  pLetters.add(new Letter(i++, "Э"));
  pLetters.add(new Letter(i++, "Ю"));
  pLetters.add(new Letter(i++, "Я"));
}

private void initThreeLetters(ArrayList<Letter> ThreeLetters) {
  threeLetters = new ArrayList<>();
  for (int i = 0; i < 32; i++) {
    for (int j = 0; j < 32; j++) {
      for (int k = 0; k < 32; k++) {
        threeLetters.add(new Letter(getCode(i, j, k), getLetter(i, j, k)));
      }
    }
  }
}

private Integer getCode(int i, int j, int k) {
  return letters.get(i).getCode() + letters.get(j).getCode() + letters.get(k).getCode()+1;
}

private String getLetter(int i, int j, int k) {
  return letters.get(i).getLetter() + letters.get(j).getLetter() + letters.get(k).getLetter();
}

public ArrayList<Letter> getLettersByCheckSum(int pCheckSum){
  ArrayList<Letter> arrayList = new ArrayList<>();
  for (int i=0; i<threeLetters.size(); i++){
    Letter letter = threeLetters.get(i);
    if (letter.getCode() == pCheckSum){
      arrayList.add(letter);
      ///System.out.println("-------"+letter);
    }
  }
  return arrayList;
}
}
