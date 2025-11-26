package turing;

public class T2Simulator{

    public int runOnFormattedTape(Tape formattedTape,
                                  StepwiseTuringMachine A,
                                  StepwiseTuringMachine B){

        StringBuilder wBuilder = new StringBuilder();
        int i = 3;
        while(i < formattedTape.length() &&
                formattedTape.getCell(i) != '#'){
            wBuilder.append(formattedTape.getCell(i));
            i++;
        }
        String w = wBuilder.toString();

        A.initialize(w);
        B.initialize(w);

        while(true){
            if(!A.isHalted()){
                A.step();
            }
            if(A.isHalted()){
                return 0;
            }

            if(!B.isHalted()){
                B.step();
            }
            if(B.isHalted()){
                return 1;
            }
        }
    }
}
