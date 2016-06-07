import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by elnaz on 25.04.16.
 */
public class Classifier {

    private int max_iter;
    MultiClassPerceptron mcp;

    public Classifier() {
        max_iter = 10;
        mcp = new MultiClassPerceptron();
    }

    public Classifier(Corpus corpus) {
        this();
        train(corpus);
    }

    public void train(Corpus corpus){
        for (int i = 0 ; i < max_iter; i++){
            System.out.println("-----------------------------------ITERATION NO#" + (i+1) + "------------------------------------------");
            for (Tweet t:corpus.getTweets()) {
                System.out.println(t.getGold_emotion() + " " + t.getPredicted_emotion());
                String predictedLabel = mcp.getBestLabel(t);
                //                                                                                                                                                                                                                          System.out.println(t.getGold_emotion() + " " + predictedLabel);
                t.setPredicted_emotion(predictedLabel);
                String goldLabel = t.getGold_emotion();
                //System.out.println(predictedLabel + " " + goldLabel);
                if(!predictedLabel.equals(t.getGold_emotion())){
                    mcp.getPerceptronForLabel(predictedLabel).subtract_weight(t);
                    mcp.getPerceptronForLabel(goldLabel).add_weight(t);
                }
            }
            //corpus.evaluate();
            break;
        }
    }

    public void classify(Corpus testcorpus){
        System.out.println("-----------------------------------TEST DATA------------------------------------------");
        for (Tweet t:testcorpus.getTweets()) {
            String predictedLabel = mcp.getBestLabel(t);
            t.setPredicted_emotion(predictedLabel);
            //System.out.println(predictedLabel + " " + t.getGold_emotion());
        }
        testcorpus.evaluate();
    }
}
