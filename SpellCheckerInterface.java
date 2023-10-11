import java.util.ArrayList;
import java.util.Set;

public interface SpellCheckerInterface {

    //methods
    ArrayList<String> getIncorrectWords(String filename);

    Set<String> getSuggestions(String word);

}