package org.lsz.autocomplete.core;

/**
 * Created by lsz on 2014/11/15.
 */
public class Item {
    String uid;
    int score;
    String term;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
