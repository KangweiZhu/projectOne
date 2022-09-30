package projectone;

/**
 * The TestBed is for test uses. It implements the functionality of testing the Member class's compareTo() method and
 * testing the Date's class isValid() method.
 * The TestBed class and the TestBed object should only appear at the Date class's main method and Member class's main method.
 *
 * @author Kangwei Zhu Michael ISRAEL
 */
public class TestBed {
    protected static final int GREATER = 1;
    protected static final int EQUAL = 0;
    protected static final int SMALLER = -1;
    protected static final int INITIALIZECOUNT = 0;

    /**
     * This method is for testing the Member class's compareTo() method.
     * It will compare the expected output with the output that the current compareTo method will produce.
     *
     * @param testCandidateOne The first member object.
     * @param testCandidateTwo The second member object.
     * @param testCaseNum      The number of current test case.
     * @param expectedValue    The result what you expect firstMember.compareTo(secondMember) will return.
     * @return Return 1 if the expected output is the same as what compareTo() method returns. Return 0 otherwise.
     */
    public int testMemberCompareTo(Member testCandidateOne, Member testCandidateTwo, int testCaseNum, int expectedValue) {
        int outputValue = testCandidateOne.compareTo(testCandidateTwo);
        printTestMemberMsg(testCandidateOne, testCandidateTwo, testCaseNum, expectedValue, outputValue);
        return outputValue == expectedValue ? 1 : 0;
    }

    /**
     * This method is for testing the Date class's isValid() method.
     * It will compare the expected value of isValid() that should return with the value isValid() is actually returning.
     *
     * @param testCandidate The Date object that are going to be tested
     * @param testCaseNum   The number of current test case
     * @param expectedValue The value what you expect Date.isValid() will produce.
     * @return if the expected output is the same as what isValid() method  returns, then return 1. Return 0 otherwise.
     */
    public int testDateIsValid(Date testCandidate, int testCaseNum, boolean expectedValue) {
        boolean outputValue = testCandidate.isValid();
        printTestMemberMsg(testCandidate, testCaseNum, expectedValue, outputValue);
        return outputValue == expectedValue ? 1 : 0;
    }

    /**
     * This method is for printing the test details of compareTo() method on command line.
     *
     * @param testCandidateOne The first member object.
     * @param testCandidateTwo The second member object.
     * @param testCaseNum      The number of current test case.
     * @param expectedValue    The result what you expect firstMember.compareTo(secondMember) will return.
     * @param outputValue      The output when Member class's compareTo() method is called.
     */
    private void printTestMemberMsg(Member testCandidateOne, Member testCandidateTwo, int testCaseNum, int expectedValue, int outputValue) {
        System.out.println("----------------");
        System.out.println("---------------- \ntest case " + testCaseNum + "\n" + "input: " + "\n" + "\t Member1's first name: "
                + testCandidateOne.getFname() + "     last name: " + testCandidateOne.getLname()
                + "\n\t Member2's first name: " + testCandidateTwo.getFname() + "     last name: "
                + testCandidateTwo.getLname() + "\n" + "output: " + outputValue + "\nexpected: "
                + expectedValue + "\nresult: " + (outputValue == expectedValue ? "correct" : "false" + "\n----------------"));
    }

    /**
     * This method is for printing the test details of compareTo() method on command line.
     *
     * @param testCandidate The Date object that are going to be tested.
     * @param testCaseNum   The number of current test case.
     * @param expectedValue The value what you expect Date.isValid() will produce.
     * @param outputValue   The value that isValid() method actually returns.
     */
    private void printTestMemberMsg(Date testCandidate, int testCaseNum, boolean expectedValue, boolean outputValue) {
        System.out.println("----------------");
        System.out.println("---------------- \ntest case " + testCaseNum + "\n" + "input: " + "\n" + "\t This date is: "
                + testCandidate.toString() + "\noutput: " + outputValue + "\nexpected: "
                + expectedValue + "\nresult: " + (outputValue == expectedValue ? "correct" : "false" + "\n----------------"));
    }
}
