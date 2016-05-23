import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by elnaz on 18.04.16.
 */
public class Main {

    public static void main(String[] args) {

        try
        {
            String directory_path = "/home/elnaz/Desktop/Courses/Computational Linguistics Team Lab NLP/Twitter";
            Corpus corpus = new Corpus(directory_path);
            //Clissifier.classify(corpus);
            corpus.classify();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
