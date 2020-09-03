import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) throws IOException {
        //需要从命令行读入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstWordList = br.readLine();
        String secondWordList = br.readLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        String regex="^([a-zA-Z]+[,])*[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match1 = pattern.matcher(firstWordList);
        Matcher match2 = pattern.matcher(secondWordList);
        boolean a = match1.matches();
        boolean b = match2.matches();
        if (!a || !b){
            throw new RuntimeException("input not valid");
        }

        List<String> first = Arrays.asList(firstWordList.split(","));
        List<String> firstLower = first.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> firstList = firstLower.stream().distinct().collect(Collectors.toList());
        List<String> second = Arrays.asList(secondWordList.split(","));
        List<String> secondLower = second.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> secondList = secondLower.stream().distinct().collect(Collectors.toList());
        List<String> same = new ArrayList<String>();

        Stream.iterate(0,i -> i+1).limit(firstList.size()).forEach(i -> {
            Stream.iterate(0,j -> j+1).limit(secondList.size()).forEach(j -> {
                    if (firstList.get(i).equals(secondList.get(j))){
                        same.add(firstList.get(i));
                    }
            });
        });
        Collections.sort(same);
        Stream.iterate(0,i -> i+1).limit(same.size()).forEach(i ->{
            same.set(i,same.get(i).replace(""," ").trim());
        });
        return same;
//        Stream.iterate(0,i -> i+1).limit(same.size()).forEach(i -> {
//            Iterator<String> firstIt = firstList.iterator();
//            Iterator<String> secondIt = secondList.iterator();
//            while(firstIt.hasNext()){
//                String x = firstIt.next();
//                if(x.equals(same.get(i))){
//                    firstIt.remove();
//                }
//            }
//            while(secondIt.hasNext()){
//                String x = secondIt.next();
//                if(x.equals(same.get(i))){
//                    secondIt.remove();
//                }
//            }
//        });
//        newList.addAll(firstList);
//        newList.addAll(secondList);
//        Collections.sort(newList);
    }
}
