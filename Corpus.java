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
    List<Tweet> tweets;

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

    public double evaluate(){
        double f_score=0;

        Result[] results = new Result[6];
        for (int i=0; i<6; i++){
            results[i] = evaluator(emotions[i]);
        }

        double sum_precision = 0, sum_recall = 0;
        for (Result res: results){
            sum_precision += (res.getTP()/(res.getTP()+res.getFP()));
            sum_recall += (res.getTP()/(res.getTP()+res.getFN()));
        }
        double ave_precision = sum_precision / 6;
        double ave_recall = sum_recall / 6;

        //Macro averaging
        f_score = 2*ave_precision*ave_recall/(ave_precision+ave_recall);
        return f_score;
    }


    public Result evaluator(String emotion){
        int TP = 0, FP = 0, FN = 0, TN=0;
        for(Tweet t: tweets){
            if(t.taggedCorrectly()) TP++;
            else {
                if(t.getGold_emotion().equals(emotion)) FN++;
                else if(t.getPredicted_emotion().equals(emotion)) FP++;
                else TN++;
            }
        }
        return new Result(TP, FP, FN, TN);
    }

    public class Result{
        private int TP, FP, FN, TN;

        public Result(){}

        public Result(int TP, int FP, int FN, int TN) {
            this.TP = TP;
            this.FP = FP;
            this.FN = FN;
            this.TN = TN;
        }

        public int getTP() {
            return TP;
        }

        public void setTP(int TP) {
            this.TP = TP;
        }

        public int getFP() {
            return FP;
        }

        public void setFP(int FP) {
            this.FP = FP;
        }

        public int getFN() {
            return FN;
        }

        public void setFN(int FN) {
            this.FN = FN;
        }

        public int getTN() {
            return TN;
        }

        public void setTN(int TN) {
            this.TN = TN;
        }
    }
}

