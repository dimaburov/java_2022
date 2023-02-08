import javax.swing.text.html.parser.Parser;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitString {

    public static void main(String[] args) {
        Map<Character,Long> result = countStops(Set.of('1','2','3','4') ,List.of("all", "b 12", "c 2 d 3", "3---3","44").stream());
        System.out.println(result.get('1'));
        System.out.println(result.get('2'));
        System.out.println(result.get('3'));
        System.out.println(result.get('4'));
    }


    public static Map<Character,Long> countStops(Set<Character> stops,
                                                     Stream<String> words){
        return words.filter(x -> x.length() > 3)
                     .flatMap((str)->(str).chars().mapToObj(x->(char)x))
                     .filter(x->stops.contains(x))
                     .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

}
