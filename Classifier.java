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
                //add features to perceptron of gold emotion
                for (String s: t.getTokens()){
                    if(mcp.perceptrons[corpus.getIndex(t.getGold_emotion())].feature_weights.get(s) == null)
                        mcp.perceptrons[corpus.getIndex(t.getGold_emotion())].feature_weights.put(s, (double) 0.0);
                    else {
                        mcp.perceptrons[corpus.getIndex(t.getGold_emotion())].feature_weights.put(s, (double) mcp.perceptrons[corpus.getIndex(t.getGold_emotion())].feature_weights.get(s) + (double) 1.0/Corpus.emotions_no[Corpus.getIndex(t.getGold_emotion())]);
                    }
                }

                //apply classification for the tweet
                String goldLabel = t.getGold_emotion();
                String predictedLabel = mcp.getBestLabel(t);
                t.setPredicted_emotion(predictedLabel);

                //System.out.println(mcp.perceptrons[0].toString());
                if(!predictedLabel.equals(t.getGold_emotion())){
                    mcp.getPerceptronForLabel(predictedLabel).subtract_weight(t);
                    mcp.getPerceptronForLabel(goldLabel).add_weight(t);
                }
            }
        }
    }

    public void classify(Corpus testcorpus){
        System.out.println("-----------------------------------TEST DATA------------------------------------------");
        for (Tweet t:testcorpus.getTweets()) {
            String predictedLabel = mcp.getBestLabel(t);
            t.setPredicted_emotion(predictedLabel);
           // System.out.println(t.getGold_emotion() + " " + t.getPredicted_emotion());
        }
        testcorpus.evaluate();
    }
}
