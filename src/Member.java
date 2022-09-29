public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member() {
    }

    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    public Member(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public Member(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + ", " + this.dob.toString() + ", Member expires "
                + this.expire.toString() + ", Location: " + this.location + ", "
                + this.location.getZipCode() + ", " + this.location.getCounty();
    }

    @Override
    public boolean equals(Object obj) {
        //returns true if the two first names, last names and dates of birth are equal.
        if (obj instanceof Member objMember) {
            boolean isFNameSame = this.fname.equalsIgnoreCase(objMember.fname);
            boolean isLNameSame = this.lname.equalsIgnoreCase(objMember.lname);
            boolean isDobSame = this.getDob().getDay() == objMember.getDob().getDay() &&
                    this.getDob().getMonth() == objMember.getDob().getMonth() &&
                    this.getDob().getYear() == objMember.getDob().getYear();
//            System.out.println(isFNameSame+", "+isLNameSame+", "+isDobSame);
            return isFNameSame && isLNameSame && isDobSame;
        }
        return false;
//        if (obj instanceof Member objMember) {
//            String first = this.fname + this.lname;
//            String second = objMember.fname + objMember.lname;
//            int i = 0;
//            if(first.length() != second.length()){
//                return false;
//            }
//            while (i < first.length()) {
//                if(first.charAt(i) != second.charAt(i)){
//                    return false;
//                }
//                i++;
//            }
//            return this.getDob().getDay() == objMember.getDob().getDay() &&
//                    this.getDob().getMonth() == objMember.getDob().getMonth() &&
//                    this.getDob().getYear() == objMember.getDob().getYear();
//        }
//        return false;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int compareTo(Member member) {
        String first = (this.lname + this.fname).toLowerCase();
        String second = (member.lname + member.fname).toLowerCase();
        if (first.compareTo(second) > 0) {
            return 1;
        } else if (first.compareTo(second) == 0) {
            return 0;
        } else {
            return -1;
        }
//        System.out.println("first: "+first+", Second: "+second);
//        int maxLength;
//        boolean maxFirst = false;
//        if(first.length() < second.length()){
//            maxLength = first.length();
//            maxFirst = true;
//        }else {
//            maxLength = second.length();
//        }
//        System.out.println("MAxlength: "+maxLength);
//        for(int i = 0; i < maxLength; i++){
//            if(first.charAt(i) > second.charAt(i)){
//                return 1;
//            } else if (first.charAt(i) < second.charAt(i)) {
//                return -1;
//            }
//        }
//        if(first.length() > second.length()){
//            return 1;
//        } else if (first.length() < second.length()) {
//            return -1;
//        }else {
//            return 0;
//        }
    }

    public static void main(String[] args) {
        TestBed testBed = new TestBed();
        int passTestCases = TestBed.INITIALIZECOUNT;
        int expectedValue;
        int testCaseNumber = TestBed.INITIALIZECOUNT;
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
