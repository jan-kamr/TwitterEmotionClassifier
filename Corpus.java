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
            String[] s = content.get(0).split(",");
            //Tweet(String gold, String hashtag, String date, long id, String username, String lang, String name, String text){
            Tweet t = new Tweet(s[0], s[1], s[2], new Long(s[3]), s[4], s[5], s[6], s[7]);
            tweets.add(t);
        }
    }
}

