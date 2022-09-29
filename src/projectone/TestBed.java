package projectone;

public class TestBed {
    protected static final int GREATER = 1;
    protected static final int EQUAL = 0;
    protected static final int SMALLER = -1;
    protected static final int INITIALIZECOUNT = 0;

    public int testMemberCompareTo(Member testCandidateOne, Member testCandidateTwo, int testCaseNum, int expectedValue) {
        int outputValue = testCandidateOne.compareTo(testCandidateTwo);
        printTestMemberMsg(testCandidateOne, testCandidateTwo, testCaseNum, expectedValue, outputValue);
        return outputValue == expectedValue ? 1 : 0;
    }

    public int testDateIsValid(Date testCandidate, int testCaseNum, boolean expectedValue) {
        boolean outputValue = testCandidate.isValid();
        printTestMemberMsg(testCandidate, testCaseNum, expectedValue, outputValue);
        return outputValue == expectedValue ? 1 : 0;
    }

    private void printTestMemberMsg(Member testCandidateOne, Member testCandidateTwo, int testCaseNum, int expectedValue, int outputValue) {
        System.out.println("----------------");
        System.out.println("---------------- \ntest case " + testCaseNum + "\n" + "input: " + "\n" + "\t Member1's first name: "
                + testCandidateOne.getFname() + "     last name: " + testCandidateOne.getLname()
                + "\n\t Member2's first name: " + testCandidateTwo.getFname() + "     last name: "
                + testCandidateTwo.getLname() + "\n" + "output: " + outputValue + "\nexpected: "
                + expectedValue + "\nresult: " + (outputValue == expectedValue ? "correct" : "false" + "\n----------------"));
    }

    private void printTestMemberMsg(Date testCandidate, int testCaseNum, boolean expectedValue, boolean outputValue) {
        System.out.println("----------------");
        System.out.println("---------------- \ntest case " + testCaseNum + "\n" + "input: " + "\n" + "\t This date is: "
                + testCandidate.toString() + "\noutput: " + outputValue + "\nexpected: "
                + expectedValue + "\nresult: " + (outputValue == expectedValue ? "correct" : "false" + "\n----------------"));
    }
}
