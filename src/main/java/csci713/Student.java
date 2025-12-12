package csci713;
public class Student {
    public String name;  // Should be private
    public int age;
    public double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    // Getters and Setters (some missing deliberately)
    public String getName() { return name; }
    // Missing setter for name

    // Age setter has unnecessary check (bug)
    public void setAge(int age) {
        if (age < 0) {  // Bug: Should handle > 120 as well
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // Extra unused method (code smell)
    public void printStudentInfo() {
        System.out.println(name + " " + age + " " + gpa);
    }
}
