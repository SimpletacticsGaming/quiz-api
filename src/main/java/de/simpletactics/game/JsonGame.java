package de.simpletactics.game;

public class JsonGame {

    //Variablen für DDIR
    private String question;
    private String answer;
    private int points;
    private int bank;
    private int turn;

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }

    public int getBank() {
        return bank;
    }

    public int getTurn() {
        return turn;
    }
}
