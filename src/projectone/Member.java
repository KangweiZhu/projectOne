package projectone;

/**
 * This class is for the creation and data manipulation of Member object.
 * A Member object could be viewed as a real person in the world. He should have first name, last name, date of birth.
 * And if this person is registered to a fitness class, he also should have expiration date and the location of the
 * fitness class he takes.
 *
 * @author Michael ISRAEL, Kangwei Zhu
 */
public class Member implements Comparable<Member> {
    private final int SMALLER = -1;
    private final int EQUAL = 0;
    private final int GREATER = 1;
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
     * This is a constructor method for member class that takes any parameters
     * This method should be used when creating a member object without any information of it.
     */
    public Member() {
    }

    /**
     * This is a constructor method of member class that takes five parameters.
     * This method is supposed to use when creating a member object with full information
     * set up all the information of this member.
     *
     * @param fname    The first name of this member object.
     * @param lname    The last name of this member object.
     * @param dob      The date of birth of this member object.
     * @param expire   This member object's fitness class expiration date.
     * @param location This member object's fitness gym's location.
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     * This is a constructor method of member class that takes three parameters.
     * This method should be used when a member object is created for check into database or register a class.
     *
     * @param fname The first name of this member.
     * @param lname The last name of this member.
     * @param dob   This member's date of birth.
     */
    public Member(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * This is a constructor method of member class takes two parameters.
     * This method should only be used in testbed main.
     *
     * @param fname The first name of this member.
     * @param lname The last name of this member.
     */
    public Member(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * The method is used when getting the first name of a Member object.
     *
     * @return First name of a Member object.
     */
    public String getFname() {
        return fname;
    }

    /**
     * This method is used when setting the first name of a Member object.
     *
     * @param fname The first name of a Member object.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * This method is used when getting the last name of a Member object.
     *
     * @return Last name of a Member object.
     */
    public String getLname() {
        return lname;
    }

    /**
     * This method is used when setting the last name of a Member object.
     *
     * @param lname The Last name of a Member object.
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * This method is used when getting the date of birth of a Member object.
     *
     * @return The Date type date of birth.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * This method is used when setting the date of birth of a Member object.
     *
     * @param dob Date of birth of a Member object.
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * This method is used when getting the expiration date of a Member object.
     *
     * @return The Date type expiration date.
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * This method is used when setting the expiration date of a Member object.
     *
     * @param expire The expiration date.
     */
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    /**
     * This method is used when getting the fitness class location of a Member object.
     *
     * @return The location of this Member object
     */
    public Location getLocation() {
        return location;
    }

    /**
     * This method is used when setting the fitness class location of a Member object
     *
     * @param location The location of this Member object
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * This method overrides the original Member toString() method.
     * Now it shows all the information of a Member object
     *
     * @return Full information include first name, last name, date of birth, zipcode, and county of a Member object.
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + ", " + this.dob.toString() + ", projectone.Member expires "
                + this.expire.toString() + ", projectone.Location: " + this.location + ", "
                + this.location.getZipCode() + ", " + this.location.getCounty();
    }

    /**
     * This equal method checks whether two members are same.
     * This method will check if the input object is an instance of Member, if it is, then compared the member with this
     * input member, and check whether they are the same. At here two members that have the same first name, last name,
     * and date of birth are considered as equal.
     *
     * @param obj input object
     * @return true if they are same, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member objMember) {
            boolean isFNameSame = this.fname.equalsIgnoreCase(objMember.fname);
            boolean isLNameSame = this.lname.equalsIgnoreCase(objMember.lname);
            boolean isDobSame = this.getDob().getDay() == objMember.getDob().getDay() &&
                    this.getDob().getMonth() == objMember.getDob().getMonth() &&
                    this.getDob().getYear() == objMember.getDob().getYear();
            return isFNameSame && isLNameSame && isDobSame;
        }
        int a = "aaa".compareTo("aa");
        return false;
    }

    /**
     * This method compares the two member's names' value(ignore case difference). It is used when sorting an array of
     * Member object by name.
     * This method calls java.lang.String compareTo() method when comparing the members' names. String's compareTo
     * method is comparing two strings lexicographically.The comparison is based on the Unicode value of each character
     * in the strings.
     *
     * @param member the object to be compared.
     * @return 1 if first member greater than second, 0 if two member are equal, -1 if first member smaller than second/
     */
    @Override
    public int compareTo(Member member) {
        String first = (this.lname + this.fname).toLowerCase();
        String second = (member.lname + member.fname).toLowerCase();
        if (first.compareTo(second) > 0) {
            return GREATER;
        } else if (first.compareTo(second) == 0) {
            return EQUAL;
        } else {
            return SMALLER;
        }
    }

    /**
     * The testBed main method for testing Member class's compareTo method.
     * It will create a testBed object, and use the testMemberCompareTo() to test if the compareTo method will give the
     * correct output
     *
     * @param args arguments that pass to the testbed main. At here should be no arguments.
     */
    public static void main(String[] args) {
        TestBed testBed = new TestBed();
        int passTestCases = TestBed.INITIALIZECOUNT;//count the value of cases that pass the test,initialize it to zero
        int expectedValue;
        int testCaseNumber = TestBed.INITIALIZECOUNT;//record the value of test cases, initialize it to zero.
        Member testCandidateOne;
        Member testCandidateTwo;

        //test case 1
        testCandidateOne = new Member("Mike", "Lee");
        testCandidateTwo = new Member("Mike", "Lee");
        expectedValue = TestBed.EQUAL;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        testCaseNumber++;

        //test case 2
        testCandidateOne = new Member("Mike", "Lee");
        testCandidateTwo = new Member("Bike", "Lee");
        expectedValue = TestBed.GREATER;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        testCaseNumber++;

        //test case 3
        testCandidateOne = new Member("Bike", "Lee");
        testCandidateTwo = new Member("Mike", "Lee");
        expectedValue = TestBed.SMALLER;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        testCaseNumber++;

        //test case 4
        testCandidateOne = new Member("Mike", "Lee");
        testCandidateTwo = new Member("Mikeee", "Lee");
        expectedValue = TestBed.SMALLER;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        testCaseNumber++;

        //test case 5
        testCandidateOne = new Member("Mikeee", "Lee");
        testCandidateTwo = new Member("Mike", "Lee");
        expectedValue = TestBed.GREATER;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        testCaseNumber++;

        //test case 6
        testCandidateOne = new Member("MIKE", "LEE");
        testCandidateTwo = new Member("mike", "lee");
        expectedValue = TestBed.EQUAL;
        passTestCases += testBed.testMemberCompareTo(testCandidateOne, testCandidateTwo, testCaseNumber, expectedValue);
        System.out.println("total test cases: 6, test cases passed: " + passTestCases);
    }
}
