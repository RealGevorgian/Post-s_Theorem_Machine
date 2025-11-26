package turing;

import java.util.ArrayList;
import java.util.List;

public class Tape{

    public static final char BLANK = '_';

    private final List<Character> cells = new ArrayList<>();
    private int head = 0;

    public Tape(){
        cells.add(BLANK);
        head = 0;
    }

    public Tape(String content){
        if(content == null || content.isEmpty()){
            cells.add(BLANK);
        } else{
            for(char c : content.toCharArray()){
                cells.add(c);
            }
        }
        head = 0;
    }

    public char read(){
        ensureIndex(head);
        return cells.get(head);
    }

    public void write(char c){
        ensureIndex(head);
        cells.set(head, c);
    }

    public void moveLeft(){
        if (head > 0) {
            head--;
        }
    }

    public void moveRight(){
        head++;
        ensureIndex(head);
    }

    public int getHead(){
        return head;
    }

    public void setHead(int position){
        this.head = Math.max(0, position);
        ensureIndex(this.head);
    }

    private void ensureIndex(int i){
        while (cells.size() <= i) {
            cells.add(BLANK);
        }
    }

    public int length(){
        return cells.size();
    }

    public char getCell(int index){
        ensureIndex(index);
        return cells.get(index);
    }

    public void setCell(int index, char c){
        ensureIndex(index);
        cells.set(index, c);
    }

    public void clearAndWriteSingle(char c){
        cells.clear();
        cells.add(c);
        head = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cells.size(); i++){
            if(i == head){
                sb.append('[');
            }
            sb.append(cells.get(i));
            if(i == head){
                sb.append(']');
            }
        }
        return sb.toString();
    }
}
