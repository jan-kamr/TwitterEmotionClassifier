import java.util.List;

/**
 * Created by elnaz on 09.05.16.
 */
public class MultiClassPerceptron {

    Perceptron[] perceptrons = new Perceptron[Corpus.no_of_emotions()];

    public MultiClassPerceptron(){
        for(int i  = 0 ; i < Corpus.no_of_emotions(); i++){
            perceptrons[i] = new Perceptron();
        }
    }

    public int getBestIndex(Tweet t){
        double max = -Double.MAX_VALUE;
        int index = -1;
        for(int i  = 0 ; i < Corpus.no_of_emotions(); i++){
            if(Double.compare(perceptrons[i].sum_weights() , max)>0) {
                max = perceptrons[i].sum_weights();
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
