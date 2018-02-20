/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.knowter.games.game100k1.letters;

/**
 *
 * @author knowter
 */
public class Letter {

private Integer code;

private String letter;

public Letter(Integer code, String letter) {
  this.code = code;
  this.letter = letter;
}

/**
 * Get the value of letter
 *
 * @return the value of letter
 */
public String getLetter() {
  return letter;
}

/**
 * Set the value of letter
 *
 * @param letter new value of letter
 */
public void setLetter(String letter) {
  this.letter = letter;
}

/**
 * Get the value of code
 *
 * @return the value of code
 */
public Integer getCode() {
  return code;
}

/**
 * Set the value of code
 *
 * @param code new value of code
 */
public void setCode(Integer code) {
  this.code = code;
}

  @Override
  public String toString() {
    return getLetter()+"("+getCode()+")";
  }



}
