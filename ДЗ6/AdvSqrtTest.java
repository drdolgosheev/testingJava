import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

public class AdvSqrtTest {

    AdvSqrt target;
    Double min = 1*Math.pow(10,-45);
    Double midle = 3*Math.pow(10,-45);
    Double max = 1.1754942*Math.pow(10,-38);

    @DataProvider(name = "positive_date")
    public Object[][] PositiveData() {
        return new Object[][] {
                {1.0},
                {2.0},
                {3.0},
                {4.0},
                {5.0},
                {6.0},
                {7.0},
                {8.0},
                {9.0},
                {10.0},
                {11.0},
                {12.0},
                {13.0},
                {14.0},
                {15.0},
                {16.0},
                {17.0},
                {18.0},
                {19.0},
                {20.0},
                {21.0},
                {22.0},
                {23.0},
                {24.0},
                {25.0},
                {26.0},
                {27.0},
                {28.0}
        };
    }

    @DataProvider(name = "negative_date")
    public Object[][] NegativeData() {
        return new Object[][] {
                {Double.longBitsToDouble(-0x12)},
                {Double.longBitsToDouble(-0x111)},
                {-1.0},
                {-2.0},
                {-3.0},
                {-4.0},
                {-5.0},
                {-6.0},
                {-7.0},
                {-8.0},
                {-9.0},
                {-10.0},
                {-11.0},
                {-12.0},
                {-13.0},
                {-14.0},
                {-15.0},
                {-16.0},
                {-17.0},
                {-18.0},
                {-19.0},
                {-20.0},
                {-21.0},
                {-22.0},
                {-23.0},
                {-24.0},
                {-25.0},
                {-26.0},
                {-27.0},
                {-28.0}
        };
    }


    @DataProvider(name = "denorm_date")
    public Object[][] DenormData() {
        return new Object[][] {
                {min}, // min denormal
                {midle}, // min + 1
                {max}, // max denormal
        };
    }

    @DataProvider(name = "negative_denorm_date")
    public Object[][] NegativeDenormData() {
        return new Object[][] {
                {-min}, // min denormal
                {-midle}, // min + 1
                {-max}, // max denormal
        };
    }

    @DataProvider(name = "fractional_date")
    public Object[][] PositiveFractionalData() {
        return new Object[][] {
                {Double.longBitsToDouble(0x0)},
                {Double.longBitsToDouble(0x01)},
                {Double.longBitsToDouble(0x11)},
                {0.0},
                {0.111111},
                {0.123445},
                {0.002},
                {0.0234},
                {0.02111},
                {0.52111},
                {0.022222},
                {1.1},
                {2.2},
                {3.3},
                {4.4},
                {5.5},
                {6.6},
                {7.7},
                {8.8},
                {9.9},
        };
    }

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        target = new AdvSqrt();
    }

    @org.testng.annotations.Test
    public void testSqrtNaN() {
            assertEquals(Double.NaN, target.sqrt(Double.NaN));
    }

    @org.testng.annotations.Test
    public void testSqrtMinusZero() {
        assertEquals(Double.longBitsToDouble(0x0), target.sqrt(Double.longBitsToDouble(0x0)));
    }

    @org.testng.annotations.Test
    public void testZero() {
        assertEquals(0, target.sqrt(0));
    }

    @org.testng.annotations.Test
    public void testSqrtPositiveInf() {
        assertEquals(Double.POSITIVE_INFINITY, target.sqrt(Double.POSITIVE_INFINITY));
    }

    @org.testng.annotations.Test
    public void testSqrtNegativeInf() {
        assertEquals(Double.NaN, target.sqrt(Double.NEGATIVE_INFINITY));
    }

    // Работает не для всех данных
    @org.testng.annotations.Test(dataProvider = "positive_date")
    public void testSqrtBasic(Double data) {
        assertEquals(Math.sqrt(data), target.sqrt(data));
    }

    @org.testng.annotations.Test(dataProvider = "negative_date")
    public void testSqrtNegative(Double data) {
        assertEquals(Double.NaN, target.sqrt(data));
    }

    // Работает не для всех данных
    @org.testng.annotations.Test(dataProvider = "fractional_date")
    public void testSqrtFractional(Double data) {
        assertEquals(Math.sqrt(data), target.sqrt(data));
    }

    // Работает не для всех данных
    @org.testng.annotations.Test(dataProvider = "denorm_date")
    public void testSqrtDenormal(Double data) {
        assertEquals(Math.sqrt(data), target.sqrt(data));
    }

    @org.testng.annotations.Test(dataProvider = "negative_denorm_date")
    public void testSqrtDenormalNegative(Double data) {
        assertEquals(Math.sqrt(data), target.sqrt(data));
    }
}