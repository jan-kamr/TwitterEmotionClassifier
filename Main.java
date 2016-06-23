import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by elnaz on 18.04.16.
 */
public class Main {

    public static void main(String[] args) {

        try
        {
            String directory_path_training = "C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/Corpus_nohashtags/toprocess/training";
            //String directory_path_training = "C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/corpus_without_negations/toprocess/training";

            //Corpus corpus = new Corpus(directory_path_training);
            Corpus_negationLess corpus = new Corpus_negationLess(directory_path_training);

            Classifier classifier = new Classifier(corpus);
            corpus = null;
            System.gc();
            for(int i = 0 ; i < 6 ; i++){
                /**PrintWriter writer = new PrintWriter("C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/tryoutput", "UTF-8");
                 writer.println("The first line");
                 writer.println("The second line");
                 writer.close();**/
            /**List<String> lines = Arrays.asList("The first line", "The second line");
             Path file = Paths.get("the-file-name.txt");
             Files.write(file, lines, Charset.forName("UTF-8"));**/

                /**PrintWriter writer = new PrintWriter("C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/negationless_corpus_mcp_output/negationless_perceptron_weights.txt", "UTF-8");
                 writer.println(0);
                 writer.println(classifier.mcp.perceptrons[0].feature_weights.toString());
                 writer.println(1);
                 writer.println(classifier.mcp.perceptrons[1].feature_weights.toString());
                 writer.println(2);
                 writer.println(classifier.mcp.perceptrons[2].feature_weights.toString());
                 writer.println(3);
                 writer.println(classifier.mcp.perceptrons[3].feature_weights.toString());
                 writer.println(4);
                 writer.println(classifier.mcp.perceptrons[4].feature_weights.toString());
                 writer.println(5);
                 writer.println(classifier.mcp.perceptrons[5].feature_weights.toString());
                 writer.close();**/

                /**PrintWriter writer = new PrintWriter("C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/tryoutput/negationless_perceptron" + Integer.toString(i) + "_weights.txt", "UTF-8");
                writer.println(i);
                writer.println(classifier.mcp.perceptrons[i].feature_weights.toString());**/



                //System.out.println(i);
                //System.out.println(classifier.mcp.perceptrons[i].feature_weights.toString());
                //System.out.println("\n\n");
            }
            //C:/Users/Jean/IdeaProjects/TwitterEmotionClassifier-master_090616/src

            //Serializer s = new Serializer(classifier.mcp, "C:/Users/Jean/IdeaProjects/TwitterEmotionClassifier-master_090616/src/negationless_mcp.ser");
            //s.save_model();
            /**Serializer s = new Serializer(classifier.mcp, "C:/Users/Jean/IdeaProjects/TwitterEmotionClassifier-master_090616/src/normal_mcp.ser");
            s.save_model();**/

            String directory_path_test = "C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/Corpus_nohashtags/toprocess/test";
            //String directory_path_test = "C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/corpus_without_negations/toprocess/test";

            //Corpus test_corpus = new Corpus(directory_path_test);
            Corpus_negationLess test_corpus = new Corpus_negationLess(directory_path_test);
            classifier.classify(test_corpus);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
