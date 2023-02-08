import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class testThread {
    private static class Check5Second implements Runnable {

        Set<Thread> threadSet;
        List<MyProcess> listHorses;

        public Check5Second(Set<Thread> threadSetNew,List<MyProcess> listHorsesNew) { threadSet = threadSetNew;listHorses = listHorsesNew;}

        public void run() {
            int sec = 5;
            boolean checkWin = false;
            while (true){

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }

                //Проверяем, есть ли победитель
                for (Thread horseThread : threadSet){
                    if (!horseThread.isAlive() && listHorses.get(Integer.parseInt(horseThread.getName().split("-")[1])).getTimeEnd() !=0){
                      checkWin = true;
                      break;
                    }
                }

                if (checkWin){
                    long checkTime = 1000000000;
                    int countDead = 0;
                    List<MyProcess> winHorse = new ArrayList<>();
                    //Идём по всем лошадям и ищем победителей
                    for (MyProcess horse:listHorses){
                        if (horse.getTimeEnd()!=0 && horse.getTimeEnd()==checkTime){
                            winHorse.add(horse);
                        }
                        if (horse.getTimeEnd()!=0 && horse.getTimeEnd()<checkTime){
                            winHorse = new ArrayList<>();
                            checkTime =  horse.getTimeEnd();
                            winHorse.add(horse);
                        }
                        //Проверка на то, что не одна лошадь не дошла до финиша
                        if (horse.getTimeEnd()==0) countDead++;
                    }
                    if (countDead == listHorses.size()) System.out.println("Нет победителей среди лошадей");
                    else {
                        for (MyProcess win: winHorse){
                            System.out.println("Winners at "+ (double)win.getTimeEnd()/1000+" seconds: Horse "+win.getNumber());
                        }
                    }
                    Thread.currentThread().interrupt();
                    break;
                }

                System.out.printf("-------------%d------------\n",sec);
                sec +=5;
                for (Thread horseThread : threadSet){
                    if (horseThread.isAlive() ){
                        System.out.println("Horse "+ horseThread.getName().split("-")[1] + " at " +(int)listHorses.get(Integer.parseInt(horseThread.getName().split("-")[1])).getDistance() + " meters");
                    }
                }
                System.out.println("------------------------------\n");
            }
        }
    }
    private static class MyProcess implements Runnable {
        final int number;
        double speed;
        long timeEnd=0;
        double distance=0;


        public MyProcess(int n,int s) { number = n;speed = s;}

        public synchronized double getDistance() {
            return distance;
        }
        public synchronized int getNumber() {
            return number;
        }

        public synchronized long getTimeEnd() {
            return timeEnd;
        }


        public void run() {
            Random rnd = new Random();
            int random = 0;
            long time = System.currentTimeMillis();
           while(true){
               try {
                   sleep(1000);
               } catch (InterruptedException e) {break;}
               //Случайно меняем скорость
               random = rnd.nextInt(2)+1;
               //Уменьшаем скорость
               if (random == 1){
                   if (speed <= 0.1) Thread.currentThread().interrupt();
                   else speed -=0.1;
               }
               //Увеличиваем скорость
               else{
                   if (speed <= 20.0) speed += 0.1;
               }
               //Замеряем сколько пробежала лошадь
               distance += speed;
               //Конец забега
               if (distance>=1000.0){
                   timeEnd = System.currentTimeMillis() - time;
                   Thread.currentThread().interrupt();
               }
           }
        }
    }
    public static void main(String []args) throws InterruptedException {
        Set<Thread> threadSet = new HashSet<>();
        List<MyProcess> listHorses = new ArrayList<>();

        MyProcess horse;
        for (int i=0;i<10;i++){
            horse= new MyProcess(i,10);
            threadSet.add(new Thread(horse));
            listHorses.add(horse);
        }

        for (Thread horseThread : threadSet){
            horseThread.start();
        }
        Check5Second check5Second = new Check5Second(threadSet,listHorses);
        new Thread(check5Second).start();
    }
}
