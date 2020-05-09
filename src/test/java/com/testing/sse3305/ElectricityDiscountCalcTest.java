package com.testing.sse3305;

import org.junit.jupiter.api.*;

import java.io.*;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Verify the Electricity discount calculator class")
public class ElectricityDiscountCalcTest {
    private DecimalFormat df2;
    
    @BeforeEach
    public void beforeEach() {
        df2 = new DecimalFormat("#.##");
    }
    
    /* #### calcTax #####*/

    @Test
    @DisplayName("[calcTax][TC1] consumption < 601")
    public void calcTax_testCase1() {
        assertEquals(0, ElectricityDiscountCalc.calcTax(600, 100, 1));
    }

    @Test
    @DisplayName("[calcTax][TC2] consumption >= 601 AND disc == 0")
    public void calcTax_testCase2() {
        assertEquals(df2.format(-7.908), df2.format(ElectricityDiscountCalc.calcTax(601, 100, 0)));
    }

    @Test
    @DisplayName("[calcTax][TC3] consumption >= 601 AND disc != 0")
    public void calcTax_testCase3() {
        assertEquals(df2.format(-4.7058), df2.format(ElectricityDiscountCalc.calcTax(601, 100, 1)));
    }

    /* #### calcDisBilNR1 #####*/

    @Test
    @DisplayName("[calcDisBilNR1][TC1] consumption < 201")
    public void calcDisBilNR1_testCase1() {
        assertEquals(df2.format(73.95), df2.format(ElectricityDiscountCalc.calcDiscBilNR1(200)));
    }

    @Test
    @DisplayName("[calcDisBilNR1][TC2] consumption >= 201")
    public void calcDisBilNR1_testCase2() {
        assertEquals(df2.format(74.31975), df2.format(ElectricityDiscountCalc.calcDiscBilNR1(201)));
        assertEquals(df2.format(74.8153), df2.format(ElectricityDiscountCalc.calcDiscBilNR1(202)));
    }

    /* #### calcDisBilNR2 #####*/

    @Test
    @DisplayName("[calcDisBilNR2][TC1] consumption < 201")
    public void calcDisBilNR2_testCase1() {
        assertEquals(df2.format(85.26), df2.format(ElectricityDiscountCalc.calcDiscBilNR2(200)));
    }

    @Test
    @DisplayName("[calcDisBilNR2][TC2] consumption >= 201")
    public void calcDisBilNR2_testCase2() {
        assertEquals(df2.format(85.6863), df2.format(ElectricityDiscountCalc.calcDiscBilNR2(201)));
        assertEquals(df2.format(86.25764), df2.format(ElectricityDiscountCalc.calcDiscBilNR2(202)));
    }

    /* #### calcNoDisBilNR #####*/

    @Test
    @DisplayName("[calcNoDisBilNR][TC1] consumption < 201")
    public void calcNoDisBilNR_testCase1() {
        assertEquals(df2.format(87), df2.format(ElectricityDiscountCalc.calcNoDiscBilNR(200)));
    }

    @Test
    @DisplayName("[calcNoDisBilNR][TC2] consumption >= 201")
    public void calcNoDisBilNR_testCase2() {
        assertEquals(df2.format(87.435), df2.format(ElectricityDiscountCalc.calcNoDiscBilNR(201)));
        assertEquals(df2.format(88.018), df2.format(ElectricityDiscountCalc.calcNoDiscBilNR(202)));
    }

    /* #### calcDisBilR #####*/

    @Test
    @DisplayName("[calcDisBilR][TC1] consumption > 201")
    public void calcDisBilR_testCase1() {
        assertEquals(df2.format(22.301), df2.format(ElectricityDiscountCalc.calcDiscBilR(202)));
    }

    @Test
    @DisplayName("[calcDisBilR][TC2] consumption > 301")
    public void calcDisBilR_testCase2() {
        assertEquals(df2.format(47.7272), df2.format(ElectricityDiscountCalc.calcDiscBilR(302)));
    }

    @Test
    @DisplayName("[calcDisBilR][TC3] consumption > 601")
    public void calcDisBilR_testCase3() {
        assertEquals(df2.format(179.50016), df2.format(ElectricityDiscountCalc.calcDiscBilR(602)));
    }

    @Test
    @DisplayName("[calcDisBilR][TC4] consumption > 901")
    public void calcDisBilR_testCase4() {
        assertEquals(df2.format(340.07316), df2.format(ElectricityDiscountCalc.calcDiscBilR(902)));
    }

    @Test
    @DisplayName("[calcDisBilR][TC5] consumption <= 200")
    public void calcDisBilR_testCase5() {
        assertEquals(df2.format(21.691), df2.format(ElectricityDiscountCalc.calcDiscBilR(199)));
        assertEquals(df2.format(21.8), df2.format(ElectricityDiscountCalc.calcDiscBilR(200)));
        assertEquals(df2.format(21.909), df2.format(ElectricityDiscountCalc.calcDiscBilR(201)));
    }

    /* #### calcNoDisBilR #####*/

    @Test
    @DisplayName("[calcNoDisBilR][TC1] consumption > 201")
    public void calcNoDisBilR_testCase1() {
        assertEquals(df2.format(44.268), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(202)));
    }

    @Test
    @DisplayName("[calcNoDisBilR][TC2] consumption > 301")
    public void calcNoDisBilR_testCase2() {
        assertEquals(df2.format(78.032), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(302)));
    }

    @Test
    @DisplayName("[calcNoDisBilR][TC3] consumption > 601")
    public void calcNoDisBilR_testCase3() {
        assertEquals(df2.format(232.892), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(602)));
    }

    @Test
    @DisplayName("[calcNoDisBilR][TC4] consumption > 901")
    public void calcNoDisBilR_testCase4() {
        assertEquals(df2.format(396.742), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(902)));
    }

    @Test
    @DisplayName("[calcNoDisBilR][TC5] consumption <= 200")
    public void calcNoDisBilR_testCase5() {
        assertEquals(df2.format(43.382), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(199)));
        assertEquals(df2.format(43.6), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(200)));
        assertEquals(df2.format(43.818), df2.format(ElectricityDiscountCalc.calcNoDiscBilR(201)));
    }

    /* #### Main function : Integration test #####*/

    @Nested
    @DisplayName("Main function : Integration test (IT)")
    class ElectricityDiscountCalcIT {
        private DecimalFormat df;

        private final InputStream systemIn = System.in;
        private final PrintStream systemOut = System.out;

        private ByteArrayInputStream testIn;
        private ByteArrayOutputStream testOut;

        @BeforeEach
        public void setUpOutput() {
            df = new DecimalFormat("#,###,##0.00");
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        private void provideInput(String data) {
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @AfterEach
        public void restoreSystemInputOutput() {
            System.setIn(systemIn);
            System.setOut(systemOut);
        }

        private String generateOutputTestingTemplateResidential(
                double amount,
                double tax,
                double amountWithTax,
                double amountDisc,
                double taxDisc,
                double amountDiscWithTaxDisc,
                double totalSaving,
                boolean isNonResidential
        ) {
            String nonResidentialType = "";

            if (isNonResidential) {
                nonResidentialType = "Enter Type of Sector(Hotel/Travel/Commercial/Industrial): ";
            }

            return "Enter 0 to stop; 1 to continue Enter Type of Customer (Residential/Non-Residential): " +
                    nonResidentialType + "Enter electricity consumption (kWh): \n" +
                    "-------------- UNDERSTAND YOUR ELECTRICTIY BIL -------------- \n" +
                    "Tariff: Residential \n" +
                    "\n" +
                    "Total Electricity Bil without Tax: " + df.format(amount) + "\n" +
                    "Total Electricity Tax: " + df.format(tax) + "\n" +
                    "Total Electricity Bil with Tax: " + df.format(amountWithTax) + "\n" +
                    "\n" +
                    "****************************************************************\n" +
                    "\n" +
                    "Total Discounted Electricity Bil without Tax: " + df.format(amountDisc) + "\n" +
                    "Total Discounted Electricity Tax: " + df.format(taxDisc) + "\n" +
                    "Total Discounted Electricity with Tax: " + df.format(amountDiscWithTaxDisc) + "\n" +
                    "\n" +
                    "*****************************************************************\n" +
                    "\n" +
                    "Total Saving during COVID-19: " + df.format(totalSaving) + "\n" +
                    "\n" +
                    "Enter 0 to stop; 1 to continue \n" +
                    "You chose to STOP!!!\n";
        }

        @Test
        @DisplayName("[Integration Test - Main][TC1] user_type = Residential AND 0 kWh <= consumption <= 200 kWh")
        public void main_integration_testCase1() {
            provideInput("1\nResidential\n199\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    43.382,
                    0,
                    43.382,
                    21.691,
                    0,
                    21.691,
                    21.691,
                    false
            ), getOutput());
            /*
            provideInput("1\nResidential\n200\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    43.382,
                    0,
                    43.382,
                    21.691,
                    0,
                    21.691,
                    21.691,
                    false
            ), getOutput());*/
        }

        @Test
        @DisplayName("[Integration Test - Main][TC2] user_type = Residential AND 200 kWh < consumption <= 300 kWh")
        public void main_integration_testCase2() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC3] user_type = Residential AND 300 kWh < consumption <= 600 kWh")
        public void main_integration_testCase3() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC4] user_type = Residential AND 600 kWh < consumption <= 900 kWh")
        public void main_integration_testCase4() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC5] user_type = Residential AND consumption > 900 kWh")
        public void main_integration_testCase5() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC6] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase6() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC7] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase7() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC8] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase8() {

        }

        @Test
        @DisplayName("[Integration Test - Main][TC9] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase9() {

        }
    }
}
