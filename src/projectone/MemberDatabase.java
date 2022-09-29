package projectone;

public class MemberDatabase {
    public static final int NOT_FOUND = -1;
    private static final int INCREMENT = 4;
    private Member[] mlist;
    private int size;

    public MemberDatabase() {
        mlist = new Member[INCREMENT];
        size = 0;
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (mlist[i].equals(member)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public int contains(Member member) {
        return find(member);
    }

    private void grow() {
        Member[] temp = new Member[mlist.length + INCREMENT];
        for (int i = 0; i < mlist.length; i++) {
            temp[i] = mlist[i];
        }
        mlist = temp;
    }

    public Member returnMember(int index) {
        return mlist[index];
    }

    public int getSize() {
        return size;
    }

    public boolean add(Member member) {
        if (find(member) != NOT_FOUND) {
            return false;
        }
        mlist[size] = member;
        size++;
        if (size == mlist.length) {
            grow();
        }
        return true;
    }

    public boolean remove(Member member) {
        int index = find(member);
        if (index == NOT_FOUND) {
            return false;
        } else {
            for (int i = index; (i + 1) < size; i++) {
                mlist[i] = mlist[i + 1];
            }
            size--;
        }
        return true;
    }

    public void print() {
        if (size == 0) {
            System.out.println("projectone.Member database is empty!");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(mlist[i].toString());
            }
            System.out.println("-end of list-");
            System.out.println();
        }
    }

    public void printSchedule() {
        for (int i = 0; i < size; i++) {
            System.out.print("\t\t");
            System.out.println(mlist[i].toString());
        }
    }

    public void printByCounty() {
        if (size == 0) {
            print();
        } else {
            System.out.println();
            System.out.println("-list of members sorted by county and zipcode-");
            for (int i = 0; i < size; i++) {
                Member currMember = mlist[i];
                int j = i - 1;
                while (j >= 0 && (mlist[j].getLocation()).compareLocation(currMember) > 0) { //move smaller to the left
                    mlist[j + 1] = mlist[j];
                    j--;
                }
                mlist[j + 1] = currMember;
            }
            print();
        }
    }

    public void printByExpirationDate() {
        if (size == 0) {
            print();
        } else {
            System.out.println();
            System.out.println("-list of members sorted by membership expiration date-");
            for (int i = 0; i < size; i++) {
                Member currMember = mlist[i];
                int j = i - 1;
                while (j >= 0 && mlist[j].getExpire().getYear() >= currMember.getExpire().getYear()) { //move smaller to the left
                    if (mlist[j].getExpire().getYear() == currMember.getExpire().getYear()) {
                        if (mlist[j].getExpire().getMonth() == currMember.getExpire().getMonth()) {
                            if (mlist[j].getExpire().getDay() == currMember.getExpire().getDay()) {
                                break;
                            } else if (mlist[j].getExpire().getDay() > currMember.getExpire().getDay()) {
                                mlist[j + 1] = mlist[j];
                                j--;
                            } else {
                                break;
                            }
                        } else if (mlist[j].getExpire().getMonth() > currMember.getExpire().getMonth()) {
                            mlist[j + 1] = mlist[j];
                            j--;
                        } else {
                            break;
                        }
                    } else {
                        mlist[j + 1] = mlist[j];
                        j--;
                    }
                }
                mlist[j + 1] = currMember;
            }
            print();
        }
    }

    public void printByName() {
        if (size == 0) {
            print();
        } else {
            System.out.println();
            System.out.println("-list of members sorted by name, and last name-");
            for (int i = 0; i < size; i++) {
                Member currMember = mlist[i];
                int j = i - 1;
                while (j >= 0 && mlist[j].compareTo(currMember) > 0) { //move smaller to the left
                    mlist[j + 1] = mlist[j];
                    j--;
                }
                mlist[j + 1] = currMember;
            }
            print();
        }
    }

}