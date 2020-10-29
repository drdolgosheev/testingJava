import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GCDTest {

    // функция, возвращающая n-ое число Фибоначчи
    public static int f(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

    GCD test;

    @BeforeEach
    void setUp() {
        test = new GCD();
    }

    @Test
    public void Basic() {
        assertEquals(1, test.gcd(1, 1));
    }

    @Test
    public void firstIsDCD() {
        assertEquals(2, test.gcd(2, 8));
        assertEquals(2, test.gcd(2, 16));
        assertEquals(2, test.gcd(2, 32));
        assertEquals(3, test.gcd(3, 9));
    }

    @Test
    public void secondIsGCD() {
        assertEquals(2, test.gcd(8, 2));
        assertEquals(2, test.gcd(16, 2));
        assertEquals(2, test.gcd(32, 2));
        assertEquals(3, test.gcd(9, 3));
    }

    @Test
    public void firstLessThanSecond() {
        assertEquals(2, test.gcd(6, 4));
        assertEquals(3, test.gcd(6, 3));
        assertEquals(2, test.gcd(12, 2));

    }

    @Test
    public void secondLessThanFirst() {
        assertEquals(2, test.gcd(4, 6));
    }

    @Test
    public void firstNegative() {
        assertEquals(2, test.gcd(-10, 2));
    }

    @Test
    public void firstNegative_2() {
        assertEquals(5, test.gcd(-10, 5));
    }

    @Test
    public void secondNegative() {
        assertEquals(2, test.gcd(10, -2));
    }

    @Test
    public void secondNegative_2() {
        assertEquals(13, test.gcd(13, -26));
    }

    @Test
    public void bothNegative() {
        assertEquals(2, test.gcd(-10, -2));
    }

    @Test
    public void firstZero() {
        assertEquals(2, test.gcd(0, 2));
    }

    @Test
    public void secondZero() {
        assertEquals(2, test.gcd(2, 0));
    }

    @Test
    public void bothZero() {
        assertEquals(0, test.gcd(0, 0));
    }

    @Test
    public void simpleNumbers() {
        assertEquals(1, test.gcd(13, 17));
    }

    @Test
    public void equalNumbers() {
        assertEquals(13, test.gcd(13, 13));
    }

    ///TODO Расскоментируйте для проверки результатов тестa
    // Некорректные результаты работы
//    @Test
//    public void firstBorderNumber() {
//        // Прибавляем 1 т.к. кастуется некорректно либо Math.pow - работает некорректно, (int)pow(2,31) = 2147483647, а должно быть (int)pow(2,32) = 2147483648
//        assertEquals(2, test.gcd(-((int) Math.pow(2, 31) + 1), 2));
//        System.out.print((int) Math.pow(2, 31)); // Проверяем что выведет на 1 меньше нужного резульата
//    }

    // Некорректные результаты работы
    @Test
    public void bothBorderNumber_2() {
        assertEquals(2, test.gcd(-((int) Math.pow(2, 31)+1), -((int) Math.pow(2, 31)+1)));
    }

    @Test
    public void secondBorderNumber() {
        assertEquals(2, test.gcd(((int) Math.pow(2, 31) - 1), -2));
    }

    @Test
    public void fibNumbers() {
        int f1 = f(45);
        int f2 = f(44);
        long start = System.currentTimeMillis();
        assertEquals(1, test.gcd(f1, f2));
        long finish = System.currentTimeMillis();
        System.out.print("Время выполния для чисел Фибоначчи gcd(f(40),f(30): " + (finish - start) + "ms\n");

    }

    @Test
    public void firstNegativeSecondZero() {
        assertEquals(2, test.gcd(-2, 0));
    }

    @Test
    public void secondNegativeFirstZero() {
        assertEquals(2, test.gcd(0, -2));
    }

    @Test
    public void secondNegativeFirstNegativeSimple() {
        assertEquals(13, test.gcd(-13, -13));
        assertEquals(1, test.gcd(-1, -1));
    }

    @Test
    public void secondPositiveFirstNegativeSimple() {
        assertEquals(13, test.gcd(-13, 13));
        assertEquals(10, test.gcd(-10, 10));
    }

}