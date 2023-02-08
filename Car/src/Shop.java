import java.util.*;

public class Shop {
    private static List<Vehicle> allCars = new ArrayList<>();

    /** Список автомобилей в порядке возрастания цены **/
    public List<Vehicle> listByPrice() {
        List<Vehicle> list  = new ArrayList<>(allCars);
        Collections.sort(list, Comparator.comparing(Vehicle::getPrice));

        return list;
    }

    /** Список автомобилей в порядке возрастания расхода топлива на 100 км **/
    public List<Vehicle> listByRate(){
        List<Vehicle> list  = new ArrayList<>(allCars);
        Collections.sort(list,Comparator.comparing(Vehicle::getRate));

        return list;
    }
    /** Список автомобилей в порядке возрастания расходов за указанное число лет **/
    public List<Vehicle> listByPriceDecreasing(int years, int distance){
        List<Vehicle> list  = new ArrayList<>(allCars);

        Collections.sort(list, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                double priceO1 = years*o1.getPercent()/100*o1.getPrice() + o1.getRate()/100*distance*51*years;
                double priceO2 = years*o2.getPercent()/100*o2.getPrice() + o2.getRate()/100*distance*51*years;
                return (int)(priceO1 - priceO2);
            }
        });

        return  list;
    }
    public static void main(String[] args) {
        Shop shop = new Shop();

        allCars.add(new Vehicle("Volskwagen","Tiguan",12,15.1,10.2));
        allCars.add(new Vehicle("Lada","Vest",9,23,14));
        allCars.add(new Vehicle("Lada","Vest",15,20,20.3));
        allCars.add(new Vehicle("Volskwagen","Tiguan",5,11,30.5));
        allCars.add(new Vehicle("Lada","Vest",20,9,17.7));

        List<Vehicle> groupList = new ArrayList<>();

        groupList = shop.listByPrice();
        for (Vehicle list:groupList) {
            System.out.println(list.toString());
        }

        System.out.println();

        groupList = shop.listByRate();
        for (Vehicle list:groupList) {
            System.out.println(list.toString());
        }

        System.out.println();

        groupList = shop.listByPriceDecreasing(10,2000);
        for (Vehicle list:groupList) {
            System.out.println(list.toString());
        }
    }
}
