import java.util.*;

public class Student {
    private final String firstName;
    private final String name;
    private  final String group;
    private final Map<String,Integer> marks = new HashMap<>();

    public Student(String firstName, String name, String group) {
        this.firstName = firstName;
        this.name = name;
        this.group = group;
    }

    public Map<String, Integer> getMarks() {return marks;}

    /* Заполнение оценок в журнале у студента*/
    public void setMarksStudent(int valueEstimation, String nameSubject){
        marks.put(nameSubject,valueEstimation);
    }

    /* Удаление оценки по заданному предмету у студента */
    public void removeMarkStudent(String nameSubject){
        if (!marks.containsKey(nameSubject)) throw new IllegalArgumentException("Данного предмета нет в журнале");
        marks.remove(nameSubject);
    }

    @Override
    public String toString() {
        return "firstName= " + firstName + '\'' +
                ", name= " + name + '\'' +
                ", group= " + group + '\'' +
                ", marks= " + marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(name, student.name) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, name, group);
    }


}

