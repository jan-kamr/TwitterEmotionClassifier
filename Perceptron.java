import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Linear classifier - binary Perceptron
 * Created by elnaz on 02.05.16.
 */
public class Perceptron {

    int threshold = 0;
    HashMap<String, Double> feature_weights;

    public Perceptron(){
        feature_weights = new HashMap<>();
    }

    public double sum_weights()
    {
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : feature_weights.entrySet()) {
            Double value = entry.getValue();
            sum += (double) value;
        }
        return sum;
    }

    public void add_weight(Tweet t){
        List<String> features = t.featureExtraction();
        for (String s: features) {
            if(feature_weights.get(s)!=null) feature_weights.put(s, feature_weights.get(s)+1);
            else feature_weights.put(s, 0.0);
        }
    }

    public void subtract_weight(Tweet t){
        List<String> features = t.featureExtraction();
        for (String s: features) {
            if(feature_weights.get(s)!=null) feature_weights.put(s, feature_weights.get(s)-1);
            else feature_weights.put(s, 0.0);
        }
    }

    public boolean classification_decision()
    {
        return sum_weights()>threshold;
    }
}
