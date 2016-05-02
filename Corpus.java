import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by elnaz on 18.04.16.
 */
public class Corpus {

    public static String[] emotions = {"sad","happy","anger","fear","disgust","surprise"};
    int size;
    private List<Tweet> tweets;

    public Corpus() {
        size = 0;
        tweets = new LinkedList<Tweet>();
    }

    public Corpus(String path) throws IOException{
        this();
        readCorpus(path);
    }

    public void readCorpus(String path) throws IOException{
        File[] files = new File(path).listFiles();
        for (File file : files){
            List<String> content = Files.readAllLines(Paths.get(file.getPath()),StandardCharsets.UTF_8);
            size += file.length();
            for(int i=0; i<content.size(); i++) {
                String[] s = content.get(i).split("\t");
                Tweet t = new Tweet(s[0], s[1], s[2], new Long(s[3]), s[5], s[6], s[7], s[8]);
                tweets.add(t);
            }
        }
    }

    public String toString(){
        String output = "";
        for(Tweet t: tweets){
            output += t.toString() + "\n";
        }
        return output;
    }

    public void evaluate(){
        Result[] results = new Result[6];
        for (int i=0; i<6; i++){
            results[i] = evaluator(emotions[i]);
            //System.out.println(results[i].getTP() + " " + results[i].getFP() + " " + results[i].getFN() + " " + results[i].getTN());
        }

        double[] precisions = new double[6];
        double[] recalls = new double[6];
        double[] fscores = new double[6];
        double sum_precision = 0, sum_recall = 0, sum_f1 = 0;
        int i = 0;
        for (Result res: results){

            double d = (double) res.getTP()/(res.getTP()+res.getFP());
            precisions[i] = res.precision(); recalls[i] = res.recall();
            fscores[i] = 2 * precisions[i] * recalls[i] / (precisions[i] + recalls[i]);
            System.out.println(emotions[i] + "  \tP: " + precisions[i] + "\tR: " + recalls[i] + "\tF1: " + fscores[i]);
            sum_precision += precisions[i]; sum_recall += recalls[i];
            sum_f1 += fscores[i];
            i++;
        }
        //Micro averaging
        double f_score = sum_f1 / 6;
        System.out.println("F1 Micro Averaging: " + f_score);
        //Macro averaging
        double ave_precision = sum_precision / 6;
        double ave_recall = sum_recall / 6;
        f_score = 2*ave_precision*ave_recall/(ave_precision+ave_recall);
        System.out.println("F1 Macro Averaging: " + f_score);

    }


    public Result evaluator(String emotion){
        int TP = 0, FP = 0, FN = 0, TN=0;
        for(Tweet t: tweets){
            Result r = t.evaluator(emotion);
            TP += r.getTP(); TN += r.getTN();
            FP += r.getFP(); FN += r.getFN();
        }
        System.out.println(emotion + "  \tTP: "+ TP + "\tFP: " + FP + "\tFN: " + FN + "\tTN: " + TN);
        return new Result(TP, FP, FN, TN);
    }

    public void classify(){
        Classifier classifier = new Classifier(this);

    }

    public List<Tweet> getTweets() {
        return tweets;
    }

}

