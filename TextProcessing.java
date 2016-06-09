import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by elnaz on 25.04.16.
 */
public class TextProcessing {

    public  String[] stopWordsArray = {"the","a","an","of","I", "and", "or"};

    public TextProcessing() {
    }

    public String toLowerCase(String text) //Keeps Acronyms?? I LOVE IT
    {
        //text = text.replaceAll("(\\p{Upper})(\\.)(\\p{Upper})", "$1$3"); //removes dots in acronyms
        text = text.toLowerCase();
        return text;
    }

    public String handlePuncuation(String text)
	/* The metacharacters supported by regex are: <([{\^-=$!|]})?*+.> */
    {
        text = text.replaceAll("http(.*)?", "");
        text = text.replaceAll("\\[newline\\]", "");
        text = text.replaceAll("(\"|\\?|\\!|\\(|\\)|\\-|\\+|\\{|\\}|\\|\\||\\[|\\]|\\.|,|:|;|'|`|/|%|~|\\*|\\<|\\>|\\=)", "");
        return text;
    }

    public String normalize(String text)
    {
        return this.handlePuncuation(this.toLowerCase(text));
    }

    public List<String> tokenize(String text)
    {
        String[] t = text.split("( )+"); //Even if more spaces are inserted between words
        return Arrays.asList(t);
    }

    public Set<String> remove_stopWords(String[] tokens){
        Set<String> stopWordsSet = new HashSet<String>(Arrays.asList(stopWordsArray));
        Set<String> no_stopWord = new HashSet<String>(Arrays.asList(tokens));
        for(String word : tokens)
        {
            if(!stopWordsSet.contains(word))
            {
                no_stopWord.add(word);
            }
        }
        return no_stopWord;
    }

}
