package adapter;/*
 * ============================================================
 *                  ADAPTER PATTERN
 * ============================================================
 *
 * Definition:
 * -----------
 * Adapter Pattern is a structural design pattern
 * that converts one interface into another compatible
 * interface so that incompatible classes can work together.
 *
 *
 * Simple Meaning:
 * ---------------
 * Sometimes two systems have incompatible structures.
 *
 * Example:
 *
 * Our application expects:
 *      adapter.Student interface
 *
 * But an external/legacy system provides:
 *      adapter.SchoolStudent class
 *
 * Since adapter.SchoolStudent does not implement adapter.Student,
 * we cannot use it directly.
 *
 *
 * Adapter Pattern solves this problem by creating
 * an adapter class that converts one structure
 * into another compatible structure.
 *
 *
 * Real-world Analogy:
 * -------------------
 * Mobile Charger Adapter:
 *
 * - Mobile charger expects one socket type
 * - Wall socket provides another type
 * - Adapter converts them to compatible formats
 *
 *
 * Advantages:
 * -----------
 * 1. Allows incompatible systems to work together
 * 2. Promotes code reusability
 * 3. Avoids modifying existing legacy code
 * 4. Helps integrate third-party systems
 * 5. Keeps client code clean
 *
 *
 * Real-world Software Usage:
 * --------------------------
 * - Third-party API integration
 * - Legacy system integration
 * - DTO conversion
 * - strategy.Payment gateway integration
 * - builder.Response normalization between microservices
 *
 *
 * IMPORTANT INTERVIEW LINE:
 * -------------------------
 * "Adapter Pattern converts one interface into another
 * compatible interface so that incompatible classes
 * can work together."
 *
 * ============================================================
 */



import java.util.ArrayList;
import java.util.List;



/*
 * ============================================================
 *                  TARGET INTERFACE
 * ============================================================
 *
 * This is the interface expected by our system.
 *
 * All student types should eventually behave
 * like this interface.
 */
interface Student {

    String getName();

    String getAge();
}



/*
 * ============================================================
 *                  ADAPTEE CLASS
 * ============================================================
 *
 * Existing/legacy/external class.
 *
 * Problem:
 * --------
 * This class does NOT implement adapter.Student interface.
 *
 * Therefore it is incompatible with our system.
 */
class SchoolStudent {

    private final String firstName;
    private final String lastName;
    private final String age;


    public SchoolStudent(
            String firstName,
            String lastName,
            String age
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    public String getFirstName() {

        return firstName;
    }


    public String getLastName() {

        return lastName;
    }


    public String getAge() {

        return age;
    }
}



/*
 * ============================================================
 *                  ADAPTER CLASS
 * ============================================================
 *
 * Adapter converts adapter.SchoolStudent
 * into adapter.Student-compatible structure.
 *
 * This allows adapter.SchoolStudent objects
 * to work with our system.
 */
class SchoolStudentAdapter implements Student {

    /*
     * Wrapped adaptee object.
     */
    private final SchoolStudent student;


    /*
     * Constructor receives incompatible object.
     */
    public SchoolStudentAdapter(
            SchoolStudent student
    ) {

        this.student = student;
    }


    /*
     * Converting adapter.SchoolStudent data
     * into adapter.Student-compatible format.
     */
    @Override
    public String getName() {

        return student.getFirstName()
                + " "
                + student.getLastName();
    }


    @Override
    public String getAge() {

        return student.getAge();
    }
}



/*
 * ============================================================
 *              NORMAL COMPATIBLE CLASS
 * ============================================================
 *
 * This class already implements adapter.Student interface.
 *
 * Therefore it works directly with the system.
 */
class CollegeStudent implements Student {

    private final String name;
    private final String age;


    public CollegeStudent(
            String name,
            String age
    ) {

        this.name = name;
        this.age = age;
    }


    @Override
    public String getName() {

        return this.name;
    }


    @Override
    public String getAge() {

        return this.age;
    }
}



/*
 * ============================================================
 *                  CLIENT/SERVICE CLASS
 * ============================================================
 *
 * This service expects all objects
 * to behave like adapter.Student interface.
 *
 * Thanks to Adapter Pattern,
 * both adapter.CollegeStudent and adapter.SchoolStudent
 * can now work together.
 */
class StudentService {

    public List<Student> getAllStudents() {

        /*
         * Compatible objects
         */
        Student collegeStudent1 =
                new CollegeStudent(
                        "Mohit",
                        "21"
                );

        Student collegeStudent2 =
                new CollegeStudent(
                        "Rohit",
                        "22"
                );


        /*
         * Incompatible legacy objects
         */
        SchoolStudent schoolStudent1 =
                new SchoolStudent(
                        "Virat",
                        "Kohli",
                        "18"
                );

        SchoolStudent schoolStudent2 =
                new SchoolStudent(
                        "MS",
                        "Dhoni",
                        "19"
                );


        /*
         * Final unified student list
         */
        List<Student> allStudents =
                new ArrayList<>();


        /*
         * Directly adding compatible objects
         */
        allStudents.add(collegeStudent1);

        allStudents.add(collegeStudent2);


        /*
         * Adding incompatible objects
         * through adapter.
         */
        allStudents.add(
                new SchoolStudentAdapter(
                        schoolStudent1
                )
        );

        allStudents.add(
                new SchoolStudentAdapter(
                        schoolStudent2
                )
        );


        return allStudents;
    }
}



/*
 * ============================================================
 *                      CLIENT CLASS
 * ============================================================
 *
 * Demonstrates Adapter Pattern usage.
 */
public class StudentAdapterPattern {

    public static void main(String[] args) {

        StudentService service =
                new StudentService();


        /*
         * Both compatible and adapted objects
         * are treated uniformly as adapter.Student.
         */
        List<Student> students =
                service.getAllStudents();


        /*
         * Printing all students
         */
        for (Student student : students) {

            System.out.println(
                    "Name : "
                            + student.getName()
                            + ", Age : "
                            + student.getAge()
            );
        }
    }
}