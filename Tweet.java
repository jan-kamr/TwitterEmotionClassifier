import java.lang.String;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by elnaz on 18.04.16.
 */
public class Tweet {

    private long tweet_id;
    private String text;
    private String date;
    private String username;
    private String language;
    private String name;
    private String gold_emotion;
    private String predicted_emotion;
    private String hashtag;
    private List<String> tokens;

    public Tweet(){
    }

    public Tweet(String gold, String hashtag, long id, String text){

        TextProcessing tp = new TextProcessing();
        setTokens(tp.tokenize(tp.normalize(text)));
        setText(text);

        setTweet_id(id);
        setHashtag(hashtag);
        setGold_emotion(gold);
        //setPredicted_emotion("sad");
    }

    public Tweet(String gold, String hashtag, String date, long id, String username, String lang, String name, String text){
        this(gold, hashtag, id, text);
        setDate(date);
        setUsername(username);
        setLanguage(lang);
        setName(name);
    }

    public boolean taggedCorrectly(){
        return getGold_emotion().equals(getPredicted_emotion());
    }

    public Result evaluator(String emotion){
        int TP = 0, FP = 0, FN = 0, TN=0;
        if(this.taggedCorrectly() && this.getGold_emotion().equals(emotion)) TP++;
        else if(!this.taggedCorrectly() && this.getGold_emotion().equals(emotion)) FN++;
        else if(!this.taggedCorrectly() && this.getPredicted_emotion().equals(emotion)) FP++;
        else TN++;
        Result r = new Result(TP, FP, FN, TN);
        return r;
    }

    public List<String> featureExtraction(){
        List<String> features = new LinkedList<>();
        for(String token: tokens){
            if(!token.equals(getHashtag())) //all hashtags
                features.add("W="+token);
        }
        features.add("USERNAME="+getUsername());
        //features.add("DATE="+getDate());
        //features.add("LANG="+getLanguage());
        //features.add("NAME="+getName());
        return features;
    }

    public String toString(){
        return getText() + " " + taggedCorrectly();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(long tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGold_emotion() {
        return gold_emotion;
    }

    public void setGold_emotion(String gold_emotion) {
        this.gold_emotion = gold_emotion;
    }

    public String getPredicted_emotion() {
        return predicted_emotion;
    }

    public void setPredicted_emotion(String predicted_emotion) {
        this.predicted_emotion = predicted_emotion;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
