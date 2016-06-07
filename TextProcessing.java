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
        text = text.replaceAll("(\\p{Upper})(\\.)(\\p{Upper})", "$1$3"); //removes dots in acronyms
        text = text.toLowerCase();
        //System.out.println(text + " ");
        return text;
    }

    public String handlePuncuation(String text)
	/* The metacharacters supported by regex are: <([{\^-=$!|]})?*+.> */
    {

        //text = text.replace("n't", " not");			//changes n't to not
        text = text.replace("'ll", " will");		//changes 'll to will
        text = text.replace("'ve", " have");		//changes 've to have
        text = text.replace("s' ", "s "); 			//plural possessive
        text = text.replace("'s ", " "); 			//??? is/has/possessive-->METADATA
        text = text.replace("\"", "");				//removes "

        text = text.replaceAll("(\\.|\\!|\\=|~|\\-|_|){2,}", "$1");	//repetition of one symbol
        text = text.replaceAll("([a-z])\1{3,}", "\1");				//repetition of one letter?????????
        text = text.replaceAll("(\\-|\\+)(\\D)", " $1 $2 ");			//handling + and - before
        text = text.replaceAll("(\\w)(\\-|\\+)", " $1 $2 ");			//handling + and - after
        text = text.replaceAll("(\\d),(\\d)", "$1$2"); 					//removes commas in numbers
        text = text.replaceAll("(\\D)(\\.)|(\\.)(\\D)", "$1 $2");

        //treat these as a separate token
        text = text.replaceAll("(\\?|\\!|\\(|\\)|\\|\\||\\[|\\]|,|:|;|'|`|/|%|~|\\*|\\<|\\>|\\=)", " $1 ");
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
