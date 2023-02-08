import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите целое положительное число: ");
        int number=0;
        try{
            number = sc.nextInt();

            if (number < 0) throw new Exception();
            if (check_simple_number(number)) System.out.printf("Число %d простое",number);
            else System.out.printf("Число  %d  не простое",number);
        }catch (Exception ex) {
            System.out.println("Введено не целое положительное число");
        }

    }

    private static boolean check_simple_number(int number){

        if (number<2) return false;

        for (int i = 2;i<number;i++){
            if (number%i==0) return false;
        }

        return true;
    }

}
