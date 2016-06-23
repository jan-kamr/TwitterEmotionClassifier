import java.io.Serializable;

/**
 * Created by elnaz on 09.05.16.
 */
//public class MultiClassPerceptron {
public class MultiClassPerceptron implements Serializable {

    Perceptron[] perceptrons;

    public MultiClassPerceptron(){
        perceptrons = new Perceptron[Corpus.no_of_emotions()];
        for(int i  = 0 ; i < Corpus.no_of_emotions(); i++){
            perceptrons[i] = new Perceptron();
        }
    }

    public int getBestIndex(Tweet t){
        double max = -99999999999999999999.0;
        int index = -1;
        for(int i  = 0 ; i < Corpus.no_of_emotions(); i++){
            double sum = perceptrons[i].sum_weights(t);
            //if(sum>max) {//Double.compare(sum , max)>0
            if(Double.compare(sum , max)>0){
                max = sum;
                index  = i;
            }
        }
        return index;
    }

    public String getBestLabel(Tweet t) { return Corpus.emotions[getBestIndex(t)]; }

    public Perceptron getBestPerceptron(Tweet t){ return perceptrons[getBestIndex(t)]; }

    public Perceptron getPerceptronForLabel(String label) { return perceptrons[Corpus.getIndex(label)]; }

    public void setPerceptrons(Perceptron[] perceptrons) {
        this.perceptrons = perceptrons;
    }


}
