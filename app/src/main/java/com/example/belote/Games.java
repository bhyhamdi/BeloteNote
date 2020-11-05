package com.example.belote;

public class Games {
    private int id ;
    private String score1 ;
    private String score2 ;

    public Games(int id, String score1, String score2) {
        this.id = id;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Games(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }
}
