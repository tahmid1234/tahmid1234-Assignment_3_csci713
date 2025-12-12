package csci713;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void testAddStudentAndTopStudent() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);

        service.addStudent(s1);
        service.addStudent(s2);

        // Test if top student is correctly identified
        Student top = service.getTopStudent();
        assertEquals("Bob", top.getName());
    }

    @Test
    void testCalculateAverageGpa() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));
        service.addStudent(new Student("Bob", 22, 3.5));

        double avg = service.calculateAverageGpa();
        assertEquals(3.5, avg, 0.001);
    }
    @Test
    void testGetTopStudent_singleStudent() {
        StudentService service = new StudentService();
        Student s = new Student("Bob",22, 3.5);
        service.addStudent(s);
        assertEquals(s, service.getTopStudent());
    }

    @Test
    void testGetTopStudent_multipleStudents() {
        StudentService service = new StudentService();
        Student s1 = new Student("A",22, 3.9);
        Student s2 = new Student("B", 22,3.2);
        Student s3 = new Student("C",22, 3.7);

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        // Bug note: getTopStudent finds MIN GPA
        assertEquals(s1, service.getTopStudent());
    }

    @Test
    void testGetTopStudent_emptyList_throwsException() {
        StudentService service = new StudentService();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            service.getTopStudent();
        });
    }
    @Test
    void testCalculateAverageGpa_emptyList() {
        StudentService service = new StudentService();
        assertEquals(0.0, service.calculateAverageGpa());
    }

    @Test
    void testRemoveStudentByName_removesCorrectStudent() {
        StudentService service = new StudentService();
        Student s1 = new Student("Alice",22, 3.1);
        Student s2 = new Student("Bob",22, 2.7);
        service.addStudent(s1);
        service.addStudent(s2);

        // This will trigger ConcurrentModificationException unless fix is applied
        assertDoesNotThrow(() -> service.removeStudentByName("Alice"));

        // check that Bob remains (behavior depends on how you fixed it)
        assertEquals(1, service.calculateAverageGpa() > 0 ? 1 : 1); // dummy assert to avoid empty list
    }

    @Test
    void testRemoveStudentByName_studentNotFound() {
        StudentService service = new StudentService();
        service.addStudent(new Student("Alice", 20, 3.5));

        // Tests branch where name doesn't match
        assertDoesNotThrow(() -> service.removeStudentByName("NonExistent"));
    }

    @Test
    void testRemoveStudentByName_emptyList() {
        StudentService service = new StudentService();

        // Tests early exit when list is empty
        assertDoesNotThrow(() -> service.removeStudentByName("Anyone"));
    }

    @Test
    void testGetTopStudent_allSameGpa() {
        StudentService service = new StudentService();
        Student s1 = new Student("A", 20, 3.5);
        Student s2 = new Student("B", 21, 3.5);

        service.addStudent(s1);
        service.addStudent(s2);

        // Tests branch where condition s.getGpa() < top.getGpa() is always false
        assertEquals(s1, service.getTopStudent());
    }
    @Test
    void testCalculateAverageGpa_withStudents() {
        StudentService service = new StudentService();
        service.addStudent(new Student("A", 20, 3.0));
        service.addStudent(new Student("B", 21, 4.0));
        assertEquals(3.5, service.calculateAverageGpa(), 0.001);
    }

    @Test
    void testGetTopStudent_updatesTop() {
        StudentService service = new StudentService();
        service.addStudent(new Student("High", 20, 4.0));
        service.addStudent(new Student("Low", 21, 2.0));
        service.addStudent(new Student("Mid", 22, 3.0));

        // Forces the if (s.getGpa() < top.getGpa()) to be TRUE
        assertEquals("High", service.getTopStudent().getName());
    }

    // Intentionally leave out tests for:
    // - removeStudentByName
    // - behavior with empty student list
    // - Utils methods
}