package projectone;

/**
 * FitnessClass is the class that defines a fitness class the member can check in. In this project, we just assume there
 * are only.
 *
 * @author Michael Israel, Kangwei Zhu
 */
public class FitnessClass {
    private String instructorName;
    private String fitnessClassName;
    private Time classTime;
    private final int NOTFOUND = -1;
    private final MemberDatabase studentsList = new MemberDatabase();
    private MemberDatabase memberDb;
    private FitnessClass[] fitnessClasses;

    /**
     * Construct a FitnessClass object.
     */
    public FitnessClass() {
    }

    /**
     * Initialize a newly created FitnessClass object.
     *
     * @param fitnessClassName The name of fitness class.
     * @param instructorName   The name of instructor.
     * @param classTime        The time of this fitness class.
     * @param memberDb         The main memberDB that stores every fitness class's membership.
     * @param fitnessClasses   The array that store all the FitnessClass object in this project.
     */
    public FitnessClass(String fitnessClassName, String instructorName, Time classTime, MemberDatabase memberDb, FitnessClass[] fitnessClasses) {
        this.fitnessClassName = fitnessClassName;
        this.instructorName = instructorName.toUpperCase();
        this.classTime = classTime;
        this.memberDb = memberDb;
        this.fitnessClasses = fitnessClasses;
    }

    /**
     * Get the student database for this fitness class.
     *
     * @return The student database of this fitness class.
     */
    public MemberDatabase getStudentsList() {
        return studentsList;
    }

    /**
     * Get the name of this fitness class instructor.
     *
     * @return The name of this fitness class instructor.
     */
    public String getInstructor() {
        return instructorName;
    }

    /**
     * Set the name of this fitness class instructor.
     *
     * @param instructorName The name of this fitness class instructor.
     */
    public void setInstructor(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * Get the name of this fitness class.
     *
     * @return The name of this fitness class.
     */
    public String getFitnessClassName() {
        return fitnessClassName;
    }

    /**
     * Set the name of this fitness class.
     *
     * @param fitnessClassName The name of this fitness class.
     */
    public void setFitnessClassName(String fitnessClassName) {
        this.fitnessClassName = fitnessClassName;
    }

    /**
     * Get the time when this fitness class begins.
     *
     * @return The time when this fitness class begins.
     */
    public Time getTime() {
        return classTime;
    }

    /**
     * Set the time when this fitness class begins.
     *
     * @param classTime The time when this fitness class begins.
     */
    public void setTime(Time classTime) {
        this.classTime = classTime;
    }

    /**
     * Print the detail of the fitness class.
     * Notice that it would print the fitness class name, instructor name, time that class begins and all the members in
     * this fitness class(if exists).
     */
    public void printSchedule() {
        System.out.println(fitnessClassName + " - " + instructorName + " " + classTime.getDateTime());
        if (studentsList.getSize() != 0) {
            System.out.println("\t ** participants **");
        }
        studentsList.printSchedule();
    }

    /**
     * Check whether a member is checked in this fitness class or not.
     *
     * @param member A specific Member object.
     * @return True if it is checked in, otherwise false.
     */
    public boolean isRegistered(Member member) {
        return studentsList.contains(member) != NOTFOUND;
    }

    /**
     * Check whether a member's membership has expired or not.
     *
     * @param member A specific Member object.
     * @return True if it is expired, false otherwise.
     */
    public boolean isExpired(Member member) {
        return member.getExpire().compareTo(new Date()) < 0;
    }

    /**
     * Check if the course that a member want to check in has time conflict with the other courses that this member
     * choose.
     *
     * @param className      The name of the class that this member want to check in.
     * @param fitnessClasses The array of FitnessClass object that store all fitnessclasses' information in this project.
     * @param member         A specific Member object
     * @return True if there is a time confict, false if there is no time conflict.
     */
    private boolean isTimeConflict(String className, FitnessClass[] fitnessClasses, Member member) {
        int index = 0;
        String[] times = new String[fitnessClasses.length];
        String time = " ";
        for (int i = 0; i < fitnessClasses.length; i++) {
            if ((fitnessClasses[i].getFitnessClassName()).equalsIgnoreCase(className)) {
                time = fitnessClasses[i].getTime().getDateTime();
            }
        }
        for (int i = 0; i < fitnessClasses.length; i++) {
            if ((fitnessClasses[i].getFitnessClassName()).equalsIgnoreCase(className)) {
                continue;
            }
            if (fitnessClasses[i].getStudentsList().contains(member) != NOTFOUND) {
                if (time.equalsIgnoreCase(fitnessClasses[i].getTime().getDateTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check whether a fitness class exist.
     *
     * @param className The name of the fitness class that you want check
     * @return True if the class exist. False if not exist.
     */
    private boolean isClassExist(String className) {
        for (int i = 0; i < fitnessClasses.length; i++) {
            if (className.equalsIgnoreCase(fitnessClasses[i].getFitnessClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Make a Member object check in this fitness class, and print the commandline messages.
     *
     * @param member   A specific Member object.
     * @param className The name of the class that this member wants to check in.
     * @param fitnessClasses The array of all fitness classes that are available in this project.
     * @param memberDb The main member database that stores all the members in all fitness classes.
     */
    public void checkIn(Member member, String className, FitnessClass[] fitnessClasses, MemberDatabase memberDb) {
        if (!isExpired(member)) {
            if (!isRegistered(member)) {
                if (member.getDob().isValidDob()) {
                    if (!isTimeConflict(className, fitnessClasses, member)) {
                        System.out.println(member.getFname() + " " + member.getLname() + " checked in " + className);
                        studentsList.add(member);
                    } else {
                        System.out.println(className + " time conflict -- " +
                                member.getFname() + " " + member.getLname() + " has already checked in " + className);
                    }
                }
            } else {
                System.out.println(member.getFname() + " " + member.getLname() + " has already checked in " + className);
            }
        } else {
            System.out.println(member.getFname() + " " + member.getLname() + " " + member.getDob().toString() + " membership has expired.");
        }
    }

    /**
     * Make a member drop from this fitness class.
     * @param member A specific Member object.
     * @param memberDb The main member database that stores all the members in all fitness classes.
     */
    public void drop(Member member, MemberDatabase memberDb) {
        if (member.getDob().isValidDob()) {
            if (studentsList.contains(member) != NOTFOUND) {
                studentsList.remove(member);
                System.out.println(member.getFname() + " " + member.getLname() + " dropped " + fitnessClassName);
            } else {
                System.out.println(member.getFname() + " " + member.getLname() + " is not a participant in " + fitnessClassName);
            }
        }
    }
}