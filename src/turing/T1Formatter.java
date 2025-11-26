package turing;

public class T1Formatter{

    public Tape formatInput(String w){
        if(w == null || w.isEmpty()){
            w = String.valueOf(Tape.BLANK);
        }

        char firstSymbol = w.charAt(0);

        StringBuilder sb = new StringBuilder();
        sb.append('$');
        sb.append(firstSymbol);
        sb.append('#');
        sb.append(w);
        sb.append('#');
        sb.append(w);
        sb.append('#');
        Tape tape = new Tape(sb.toString());

        tape.setHead(3);

        return tape;
    }
}
