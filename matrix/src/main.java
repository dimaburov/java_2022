import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    private static List<List<Matrix>> listSubset = new ArrayList<>();
    private static List<List<Matrix>> listNewSubset = new ArrayList<>();
    private static List<Integer> resultMultiplication = new ArrayList<>();
    private static List<Integer> resultLocalMultiplication = new ArrayList<>();

    private static int optimalMultiplication(int[] p){
        setListSubSetMatrix(p);
        //Последнее перемножение всех матриц по очереди
        resultMultiplication.add(valueMultiplication(listSubset.get(0)));
        while (true){
            for (List<Matrix> list:listSubset) {
                subList(list);
            }
            resultMultiplication.add(getSubsetMultiplication(listNewSubset)+getTotalResultLocalMultiplication());
            resultLocalMultiplication = new ArrayList<>();
            listSubset = listNewSubset;
            listNewSubset = new ArrayList<>();
            if (listSubset.stream().filter(x->x.size()>1).count()==0) break;
        }
        //Последнее перемножение всех матриц по очереди
        resultMultiplication.add(getSubsetMultiplication(listSubset));

        return resultMultiplication.stream().min(Integer::compare).get();
    }

    //Преобразовывание списка размеров матриц
    private static void setListSubSetMatrix(int[] p){
        List<Matrix> list = new ArrayList<>();
        for (int i=0;i<p.length-1;i++){
            list.add(new Matrix(p[i],p[i+1]));
        }
        listSubset.add(list);
    }
    private static int getTotalResultLocalMultiplication(){
        return resultLocalMultiplication.stream().reduce(0,Integer::sum);
    }
    //По подспискам вычисляет крайние перемножения
    private static int getSubsetMultiplication(List<List<Matrix>> listNewSubset){
        List<Matrix> list1;
        List<Matrix> list2;
        int result = 0;

        if (listNewSubset.size() != 1){
            for (int i=0;i<listNewSubset.size()-1;i++){
                list1 = listNewSubset.get(i);
                list2 = listNewSubset.get(i+1);

                result+= list1.get(0).getLength()*list1.get(list1.size()-1).getWidth()*list2.get(list2.size()-1).getWidth();
            }
        }
        return  result;
    }
    //Разделяем список на два по оптимального кол-ву операций
    private static void subList(List<Matrix> list){
        List<Matrix> subList1;
        List<Matrix> subList2;
        int valueMulti = 0;
        int resultValueMulti =Integer.MAX_VALUE;
        int iSub = 1;

        subList1 = list.subList(0,1);
        subList2 = list.subList(1,list.size());

        for (int i=1;i<list.size();i++){
            subList1 = list.subList(0,i);
            subList2 = list.subList(i,list.size());
            valueMulti = valueMultiplication(subList1)+valueMultiplication(subList2); //list.get(0).getLength()*list.get(i).getLength()*list.get(list.size()-1).getWidth();
            if (valueMulti < resultValueMulti) {
                iSub = i;
                resultValueMulti = valueMulti;
            }
        }
        subList1 = list.subList(0,iSub);
        subList2 = list.subList(iSub,list.size());

        if (list.size() == 1) {
            listNewSubset.add(subList1);
            resultValueMulti = 0;
        }
        else {
            listNewSubset.add(subList1);
            listNewSubset.add(subList2);
        }
        resultLocalMultiplication.add(resultValueMulti);
    }

    //Получаем кол-во операций для передаваемого массива
    private static int valueMultiplication(List<Matrix> list){
        int resultMultiplication = 0;
        for (int i=0;i<list.size()-1;i++){
            resultMultiplication += list.get(i).getLength()*list.get(i).getWidth()*list.get(i+1).getWidth();
        }
        return resultMultiplication;
    }

    public static void main(String[] args) {
        //Список размерности матриц
        int minMultiplication;
        int[] p = new int[]{3,12,6,10,9,13};
        minMultiplication = optimalMultiplication(p);
        System.out.println(minMultiplication);
    }
}
