package de.simpletactics.questions;

import java.util.Stack;

public class QuestionStack {

    private Stack<Question> questions = new Stack<>();

    public void add(Question q) throws QuestionException{
        this.questions.add(q);
    }

    public Question pop(){
        if (!this.questions.empty()){
            return this.questions.pop();
        }
        return null;
    }

    public boolean isEmpty(){
        return this.questions.isEmpty();
    }

    public int stackSize(){
        return this.questions.size();
    }

}


