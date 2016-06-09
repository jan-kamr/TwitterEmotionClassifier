import java.io.IOException;

/**
 * Created by elnaz on 18.04.16.
 */
public class Main {

    public static void main(String[] args) {

        try
        {
            String directory_path_training = "/home/elnaz/Desktop/Courses/Team Lab/nohashtag/train";
            Corpus corpus = new Corpus(directory_path_training);
            Classifier classifier = new Classifier(corpus);
            corpus = null;
            System.gc();
            for(int i = 0 ; i < 6 ; i++){
                System.out.println(i);
                System.out.println(classifier.mcp.perceptrons[i].feature_weights.toString());
                System.out.println("\n\n");
            }

            String directory_path_test = "/home/elnaz/Desktop/Courses/Team Lab/nohashtag/test";
            Corpus test_corpus = new Corpus(directory_path_test);
            classifier.classify(test_corpus);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
