import java.io.Serializable;
import java.util.HashMap;

/**
 * Linear classifier - binary Perceptron
 * Created by elnaz on 02.05.16.
 */
//public class Perceptron {
public class Perceptron implements Serializable {

    int threshold = 0;
    HashMap<String, Double> feature_weights;

    public Perceptron(){
        feature_weights = new HashMap<>();
    }

    public double sum_weights(Tweet t)
    {
        double sum = 0.0;
        if (t.getTokens()!=null) {
            for (String s : t.getTokens()) {
                if (feature_weights.get(s) != null) sum += (double) feature_weights.get(s);
            }
        }
        return sum;
    }

    public void add_weight(Tweet t){
        for (String s: t.getTokens()) {
            //System.out.println(s);
            if(feature_weights.get(s)!=null) feature_weights.put(s, (double)feature_weights.get(s)+1.0);
            else feature_weights.put(s, (double)0.0);
        }
    }

    public void subtract_weight(Tweet t){
        for (String s: t.getTokens()) {
            if(feature_weights.get(s)!=null) feature_weights.put(s, (double)feature_weights.get(s)-1.0);
            else feature_weights.put(s, (double)0.0);
        }
    }

    public boolean classification_decision(Tweet t)
    {
        return sum_weights(t)>threshold;
    }
}
