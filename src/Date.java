import java.util.Calendar;
import java.util.StringTokenizer;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int FIRSTDAYOFMONTH = 0;
    public static final int ZERO = 0;
    public static final int MONTHSINYEAR = 12;
    public static final int BIGMONTHS = 31;
    public static final int MEDIUMMONTHS = 30;
    public static final int FEBLEAPYEAR = 29;
    public static final int LITTLEMONTHS = 28;
    public static final int VALIDAGE = 18;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    private static final int January = 1;
    private static final int February = 2;
    private static final int March = 3;
    private static final int April = 4;
    private static final int May = 5;
    private static final int June = 6;
    private static final int July = 7;
    private static final int August = 8;
    private static final int September = 9;
    private static final int October = 10;
    private static final int November = 11;
    private static final int December = 12;
    private static final int ISPROBABLYDEAD = 1900;

    public Date() {
        Calendar todaysDate = Calendar.getInstance();
        this.day = todaysDate.get(Calendar.DATE);
        this.month = todaysDate.get(Calendar.MONTH);
        this.year = todaysDate.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    public Date(String date) {
//        Date newDate = new Date();
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    } //take “mm/dd/yyyy” and create a Date object

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(Date date) {
        if (this.year > date.year) {
            return 1;
        } else if (this.year < date.year) {
            return -1;
        } else {
            if (this.month > date.month) {
                return 1;
            } else if (this.month < date.month) {
                return -1;
            } else {
                if (this.day > date.day) {
                    return 1;
                } else if (this.day < date.day) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public boolean isLeapYear() {
        if (year % QUADRENNIAL == ZERO) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDob() {
        Date currentDate = new Date();
//        System.out.println(year+", "+currentDate.year);
//        System.out.println(month+", "+currentDate.month);
//        System.out.println(day+", "+currentDate.day);
        if (!this.isValid()) {
            System.out.println("DOB " + this.toString() + ": invalid calendar date!");
            return false;
        }
//        under 18 or today or future date ERROR TEST
        if (((year < (currentDate.year - VALIDAGE)) || (year == (currentDate.year - VALIDAGE) &&
                month < currentDate.month) || (year == (currentDate.year - VALIDAGE) &&
                month == currentDate.month) && day <= currentDate.day)) {
            return true;
        } else if ((year == currentDate.year &&
                month == currentDate.month && day == currentDate.day) ||
                (year == currentDate.year &&
                        month >= currentDate.month && day >= currentDate.day) ||
                (year > currentDate.year)) {
            System.out.println("DOB " + this.toString() + ": cannot be today or a future date!");
        } else {
            System.out.println("DOB " + this.toString() + ": must be 18 or older to join!");
        }
        return false;
    }

    public boolean isValidExpiration() {
        Date currentDate = new Date();
        if (!this.isValid()) {
            System.out.println("Expiration date " + this.toString() + ": invalid calendar date!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isValid() {
        Date currentDate = new Date();
        if (year > ISPROBABLYDEAD && month <= MONTHSINYEAR && month > 0) {
            if (month == January || month == March || month == May || month == July || month == August || month == December || month == October) {
                return (day > ZERO && day <= BIGMONTHS);
            } else if (month == April || month == June || month == September || month == November) {
                return (day > ZERO && day <= MEDIUMMONTHS);
            } else if (month == February) {
                if (isLeapYear()) {
                    return (day > ZERO && day <= FEBLEAPYEAR);
                } else {
                    return (day > ZERO && day <= LITTLEMONTHS);
                }
            }
        } else {
            return false;
        }
        return false;
    } //check if a date is a valid calendar date

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }


    public static void main(String[] args) {
        TestBed testBed = new TestBed();
        int passTestCases = TestBed.INITIALIZECOUNT;
        boolean expectedValue;
        int testCaseNumber = TestBed.INITIALIZECOUNT;
        Date testBedCandidate;

        //test case 1
        testBedCandidate = new Date("11/21/800");
        expectedValue = false;
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 2
        testBedCandidate = new Date("2/29/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 3
        testBedCandidate = new Date("13/29/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 4
        testBedCandidate = new Date("3/32/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 5
        testBedCandidate = new Date("4/31/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 6
        testBedCandidate = new Date("2/31/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 7
        testBedCandidate = new Date("3/-27/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 8
        testBedCandidate = new Date("11/21/1905");
        expectedValue = true;
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 9
        testBedCandidate = new Date("2/27/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 10
        testBedCandidate = new Date("1/29/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 11
        testBedCandidate = new Date("3/31/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 12
        testBedCandidate = new Date("4/30/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 13
        testBedCandidate = new Date("2/27/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);

        //test case 14
        testBedCandidate = new Date("3/27/2018");
        passTestCases += testBed.testDateIsValid(testBedCandidate,testCaseNumber,expectedValue);
        System.out.println("total test cases: 14, test cases passed: " +  passTestCases);
    }
}
