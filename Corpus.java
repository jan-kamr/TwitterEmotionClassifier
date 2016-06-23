import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by elnaz on 18.04.16.
 */
public class Corpus {

    public static String[] emotions = {"happy", "sad","surprise","anger","fear","disgust"};
    /**public static String[] negationalTerms = {"no", "not", "none", "no one", "nobody", "nothing", "neither", "nowhere",
                    "never", "doesn't", "isn't", "wasn't", "shouldn't", "wouldn't", "couldn't", "won't", "can't", "don't", "n't"};**/
    public static int[] emotions_no = {0,0,0,0,0,0};
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
                if(!s[6].equals("en")) continue;
                Tweet t = null;
                if(s.length>8) {
                    //for (int j = 0; j < negationalTerms.length; j++) {
                    //if (s[8].toLowerCase().contains(negationalTerms[j].toLowerCase())) {
                    //if (s[8].toLowerCase().contains(negationalTerms[j].toLowerCase())&&!s[8].toLowerCase().contains("con't".toLowerCase())) {
                            //System.out.println(s[8]);
                            //Tweet(String gold, String HASHTAG REMOVED, String date, long id, SOMETHING REMOVED, String username, String lang, String name, String text)
                            t = new Tweet(s[0], s[2], new Long(s[3]), s[5], s[6], s[7], s[8]);
                            //System.out.println("USERNAME: " + s[5]);
                            /**System.out.println("WHAT: " + s[7]);
                             try {
                             Thread.sleep(500);
                             } catch (InterruptedException e) {
                             e.printStackTrace();
                             }**/
                            //if(!t.getGold_emotion().equals("happy"))
                            tweets.add(t);
                            emotions_no[this.getIndex(t.getGold_emotion())]++;
                            //System.out.println(t.getTokens().toString());


                }
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
        Result[] results = new Result[emotions.length];
        for (int i=0; i<emotions.length; i++){
            results[i] = evaluator(emotions[i]);
        }

        double[] precisions = new double[emotions.length];
        double[] recalls = new double[emotions.length];
        double[] fscores = new double[emotions.length];
        double sum_precision = 0, sum_recall = 0, sum_f1 = 0;
        int i = 0;
        for (Result res: results){
            precisions[i] = res.precision(); recalls[i] = res.recall();
            if (Double.isNaN(precisions[i])) precisions[i] = 0;
            if (Double.isNaN(recalls[i])) recalls[i] = 0;
            fscores[i] = 2 * precisions[i] * recalls[i] / (precisions[i] + recalls[i]);
            if (Double.isNaN(fscores[i])) fscores[i] = 0;
            System.out.println(emotions[i] + "  \tP: " + precisions[i] + "\tR: " + recalls[i] + "\tF1: " + fscores[i]);
            sum_precision += precisions[i]; sum_recall += recalls[i];
            sum_f1 += fscores[i];
            i++;
        }
        //Micro averaging
        double f_score = sum_f1 / emotions.length;
        System.out.println("F1 Micro Averaging: " + f_score);
        //Macro averaging
        double ave_precision = sum_precision / emotions.length;
        double ave_recall = sum_recall / emotions.length;
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

    public List<Tweet> getTweets() {
        return tweets;
    }

    public static int no_of_emotions() { return emotions.length; }

    public static int getIndex(String emotion){
        for (int i = 0; i < emotions.length; i++) {
            if(emotions[i].equals(emotion))
                return i;
        }
        return -1;
    }
}

