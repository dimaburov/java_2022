import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    private static void printFigureData(String nameClass, double side)
            throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        List<String> nameMethod = new ArrayList<>();
        List<Object> valueMethod = new ArrayList<>();
        Class<? extends Figure> figureClass = (Class<? extends Figure>) Class.forName(nameClass);
        Constructor<? extends Figure>[] publicConstructors = (Constructor<? extends Figure>[]) figureClass.getConstructors();

        List<Figure> figures = new ArrayList<>();

        figures.add(publicConstructors[0].newInstance(side));

        nameMethod = Stream.of(figureClass.getDeclaredMethods())
                .filter(x->x.getParameterCount()==0 && x.getReturnType().getTypeName()=="double" && Modifier.isPublic(x.getModifiers()))
                .map(x->x.getName())
                .toList();

        System.out.println(nameClass);
        valueMethod = Stream.of(figureClass.getDeclaredMethods())
                .filter(x->x.getParameterCount()==0 && x.getReturnType().getTypeName()=="double" && Modifier.isPublic(x.getModifiers()))
                .map(x-> {
            try {
                return x.invoke(figures.get(0));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        for (int i=0;i<nameMethod.size();i++){
            System.out.println(nameMethod.get(i) + ": " + valueMethod.get(i));
        }
        System.out.println();
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Main function = new Main();

        function.printFigureData("Circle",10);
        function.printFigureData("Triangle",5);
        function.printFigureData("Square",3);
    }
}
