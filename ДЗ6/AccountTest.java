package com.company;

import org.testng.Assert;

import static org.testng.Assert.*;

public class AccountTest {

    private static final int sum = 9999;
    Account target;

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        target = new Account();
        target.balance = sum;
    }

    @org.testng.annotations.Test
    public void testWithdrawBasic() {
        target.withdraw(5000);
        System.out.print(target.balance);
        assertEquals(target.balance,4999);
    }

    @org.testng.annotations.Test
    public void testGetBalanceBasic() {
        assertEquals(target.getBalance(),sum);
    }

    @org.testng.annotations.Test
    public void testGetMaxCreditBasic() {
        assertEquals(target.getMaxCredit(),1000);
    }

    @org.testng.annotations.Test
    public void testIsBlockedBasic() {
        assertEquals(target.isBlocked(),false);
    }

    @org.testng.annotations.Test
    public void testIsBlocked() {
        assertEquals(target.isBlocked(),false);
    }

    @org.testng.annotations.Test
    public void testBlockBasic() {
        target.block();
        assertEquals(target.isBlocked(), true);
    }

    @org.testng.annotations.Test
    public void testUnblockBasic() {
        target.block();
        target.unblock();
        assertEquals(target.isBlocked(),false);
    }

    @org.testng.annotations.Test
    public void testSetMaxCreditBasic() {
        target.block();
        assertEquals(target.setMaxCredit(sum),true);
        assertEquals(target.getMaxCredit(),sum);
    }


    @org.testng.annotations.Test
    public void testNegative() {
        int bound = 1000001;
        target.withdraw(-bound);
        assertEquals(target.balance,9999);
        target.deposit(-bound);
        assertEquals(target.balance,9999);
    }
        @org.testng.annotations.Test
    public void testSecond() {
        assertEquals(target.withdraw(sum),true);
        target.withdraw(1000000);
        assertEquals(target.getBalance(), 0);
    }

    @org.testng.annotations.Test
    public void testSecondFalse() {
        target.balance = - 100000;
        assertEquals(target.withdraw(10000), false);
        assertEquals(target.balance, - 100000);

    }

    @org.testng.annotations.Test
    public void testThird() {
       assertEquals(target.setMaxCredit(10000),false); // Не выполняется
       assertEquals(target.getMaxCredit(),1000); // Не выполняется
    }

    @org.testng.annotations.Test
    public void testFoursUnblocked() {
        assertEquals(target.setMaxCredit(1000002),false); // Не выполняется
        assertEquals(target.getMaxCredit(),1000); // Не выполняется
    }

    @org.testng.annotations.Test
    public void testFoursBlocked() {
        target.block();
        assertEquals(target.setMaxCredit(1000002),false); // Не выполняется
        assertEquals(target.getMaxCredit(),1000); // Не выполняется
    }

    @org.testng.annotations.Test
    public void testFoursBlockedMax() {
        target.block();
        assertEquals(target.setMaxCredit(1000000),true); // Не выполняется
        assertEquals(target.getMaxCredit(),1000000); // Не выполняется
    }

    @org.testng.annotations.Test
    public void testSix() {
        target.balance = -1001;
        target.block();
        assertEquals(target.unblock(),false);
        assertEquals(target.blocked, true);
    }

    @org.testng.annotations.Test
    public void testSevens() {
        int bound = 1000001;
        assertEquals(target.deposit(bound),false);
        assertEquals(target.withdraw(bound), false);
        assertEquals(target.balance, sum);
    }

    @org.testng.annotations.Test
    public void testEight() {
        target.block();
        int bound = 1000001;
        assertEquals(target.deposit(bound),false);
        assertEquals(target.withdraw(bound), false);
        assertEquals(target.balance, sum);
    }

    @org.testng.annotations.Test
    public void testLimit() {
        target.block();
        target.setMaxCredit(1000000);
        assertTrue(target.withdraw(1000000));
        assertEquals(target.unblock(),true);
    }

    @org.testng.annotations.Test
    public void testProWith() {
        target.deposit(1000000);
        target.deposit(1000000);
        assertEquals(target.balance, 1000000*2);
    }
}