import java.util.Arrays;
import java.util.Random;

public class MyFirst {

    private static int[] genArray(int length){
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i =0; i < length; i ++){
            array[i] = rnd.nextInt(1000);
        }

        return array;
    }

    private static void sortArray(int[] array){

        int swapNum = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] % 2 == 0){
                for (int j = i; j < array.length; j++){
                    if (array[j] % 2 !=0){
                        swapNum = array[i];
                        array[i] = array[j];
                        array[j] =  swapNum;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = genArray(100);
        System.out.println(Arrays.toString(array));
        //Сортировка массива
        sortArray(array);

        System.out.println(Arrays.toString(array));
    }
}
