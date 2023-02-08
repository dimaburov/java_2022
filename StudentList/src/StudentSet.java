import java.util.ArrayList;
import java.util.List;

public class StudentSet {

    private List<Student> listStudent = new ArrayList<>();

    /* Добавляет в множество нового студента с пустым набором оценок */
    public Student addStudent(String firstName, String name, String group){
        Student newStudent = new Student(firstName, name, group);
        if (listStudent.contains(newStudent)) throw new IllegalArgumentException("Студен уже записан в список");
        listStudent.add(newStudent);
        return newStudent;
    }
    /* Находит в множестве студента по имени, фамилии и группе */
    public Student findStudent(String firstName, String name, String group){
        Student searchStudent = new Student(firstName,name,group);
        if (listStudent.indexOf(searchStudent) == -1) throw new IllegalArgumentException("Введён не существующий студент");
        return listStudent.get(listStudent.indexOf(searchStudent));
    }

    /* Выдаёт список всех студентов в множестве */
    public List<Student> listStudent(){return listStudent;}

    /* Добавляет или заменяет оценку заданному студенту по заданному предмету */
    public void addMark(Student s, String subject, int mark){
        if (mark > 5 || mark < 2) throw new IllegalArgumentException("Некорректный ввод оценки");
        if (listStudent.indexOf(s) == -1) throw new IllegalArgumentException("Введён не существующий студент");
        listStudent.get(listStudent.indexOf(s)).setMarksStudent(mark,subject);
    }

    /* Удаляет у заданного студента оценку по заданному предмету */
    public void removeMark(Student s, String subject){
        if (listStudent.indexOf(s) == -1) throw new IllegalArgumentException("Введён не существующий студент");
        listStudent.get(listStudent.indexOf(s)).removeMarkStudent(subject);
    }

    /* Вывод на консоль студентов, у которых все оценки - пятёрки */
    public void printSet5(){
        for (Student student : listStudent) {
            if (!(student.getMarks().containsValue(4) || student.getMarks().containsValue(3) || student.getMarks().containsValue(2) || student.getMarks().isEmpty())){
                System.out.println(student.toString());
            }
        }
    }

    /* Вывод на консоль студентов, у которых все оценки - четыре и нет оценок ниже */
    public void printSet4(){
        for (Student student : listStudent) {
            if (!(student.getMarks().containsValue(3) || student.getMarks().containsValue(2)||student.getMarks().containsValue(5) || student.getMarks().isEmpty())){
                System.out.println(student.toString());
            }
        }
    }

    /* Вывод на консоль студентов, у которых есть тройки и нет двоек*/
    public void printSet3(){
        for (Student student : listStudent) {
            if (!(student.getMarks().containsValue(5) || student.getMarks().containsValue(4)||student.getMarks().containsValue(2) || student.getMarks().isEmpty())){
                System.out.println(student.toString());
            }
        }
    }

    /* Вывод на консоль студентов, у которых среди оценок есть двойик*/
    public void printSet2(){
        for (Student student : listStudent) {
            if (!(student.getMarks().containsValue(5) || student.getMarks().containsValue(4)||student.getMarks().containsValue(3) || student.getMarks().isEmpty())){
                System.out.println(student.toString());
            }
        }
    }

    /* Вывод на консоль студентов, у которых нет оценок*/
    public void printSet0(){
        for (Student student : listStudent) {
            if (student.getMarks().isEmpty()){
                System.out.println(student.toString());
            }
        }
    }

    /* Выводит на консолько студентов */
    public void printAllSets(){
        for (Student student : listStudent) {
            System.out.println(student.toString());
        }
    }

}
