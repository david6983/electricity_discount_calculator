package com.testing.sse3305;

import org.junit.jupiter.api.*;

import java.io.*;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        private void clearOutput() {
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
                boolean isNonResidential,
                String sector
        ) {
            String nonResidentialType = "";
            String residential = "Residential ";
            String sectorDisplayed = sector == null ? "" : " ".concat(sector);

            if (isNonResidential) {
                nonResidentialType = "Enter Type of Sector(Hotel/Travel/Commercial/Industrial): ";
                residential = "Non-Residential";
            }

            return "Enter 0 to stop; 1 to continue Enter Type of Customer (Residential/Non-Residential): " +
                    "Enter electricity consumption (kWh): " + nonResidentialType + "\n" +
                    "-------------- UNDERSTAND YOUR ELECTRICTIY BIL -------------- \n" +
                    "Tariff: " + residential  + sectorDisplayed + "\n" +
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
                    false,
                    null
            ), getOutput());


            clearOutput();

            provideInput("1\nResidential\n200\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    43.6,
                    0,
                    43.6,
                    21.8,
                    0,
                    21.8,
                    21.8,
                    false,
                    null
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC2] user_type = Residential AND 200 kWh < consumption <= 300 kWh")
        public void main_integration_testCase2() {
            provideInput("1\nResidential\n201\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    43.818,
                    0,
                    43.818,
                    21.909,
                    0,
                    21.909,
                    21.909,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n299\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    76.666,
                    0,
                    76.666,
                    46.5995,
                    0,
                    46.5995,
                    30.0665,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n300\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    77,
                    0,
                    77,
                    46.85,
                    0,
                    46.85,
                    30.15,
                    false,
                    null
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC3] user_type = Residential AND 300 kWh < consumption <= 600 kWh")
        public void main_integration_testCase3() {
            provideInput("1\nResidential\n301\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    77.334,
                    0,
                    77.334,
                    47.10005,
                    0,
                    47.10005,
                    30.2335,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n599\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    231.284,
                    0,
                    231.284,
                    177.9914,
                    0,
                    177.9914,
                    53.2926,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n600\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    231.8,
                    0,
                    231.8,
                    178.43,
                    0,
                    178.43,
                    53.37,
                    false,
                    null
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC4] user_type = Residential AND 600 kWh < consumption <= 900 kWh")
        public void main_integration_testCase4() {
            provideInput("1\nResidential\n601\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    232.316,
                    0.03096,
                    232.34696,
                    178.8686,
                    0.026316,
                    178.894916,
                    53.452044,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n899\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    395.054,
                    9.79524,
                    404.84924,
                    338.41892,
                    9.5993352,
                    348.0182552,
                    56.8309848,
                    false,
                    null
            ), getOutput());

            clearOutput();

            provideInput("1\nResidential\n900\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    395.6,
                    9.828,
                    405.428,
                    338.954,
                    9.63144,
                    348.58544,
                    56.84256,
                    false,
                    null
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC5] user_type = Residential AND consumption > 900 kWh")
        public void main_integration_testCase5() {
            provideInput("1\nResidential\n901\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    396.146,
                    9.86076,
                    406.00676,
                    339.48908,
                    9.6635448,
                    349.1526248,
                    56.8541352,
                    false,
                    null
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC6] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase6() {
            provideInput("1\nNon-Residential\n601\nHotel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    291.109,
                    3.55854,
                    294.66754,
                    247.44265,
                    4.140759,
                    251.583409,
                    43.084131,
                    true,
                    "Hotel"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC7] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase7() {
            provideInput("1\nNon-Residential\n601\nTravel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    291.109,
                    3.55854,
                    294.66754,
                    247.44265,
                    4.140759,
                    251.583409,
                    43.084131,
                    true,
                    "Travel"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC8] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase8() {
            provideInput("1\nNon-Residential\n599\nTravel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.091,
                    0,
                    290.091,
                    246.57735,
                    0,
                    246.57735,
                    43.51365,
                    true,
                    "Travel"
            ), getOutput());

            clearOutput();

            provideInput("1\nNon-Residential\n600\nTravel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.6,
                    0,
                    290.6,
                    247.01,
                    0,
                    247.01,
                    43.59,
                    true,
                    "Travel"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC9] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Hotel or Travel")
        public void main_integration_testCase9() {
            provideInput("1\nNon-Residential\n599\nHotel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.091,
                    0,
                    290.091,
                    246.57735,
                    0,
                    246.57735,
                    43.51365,
                    true,
                    "Hotel"
            ), getOutput());

            clearOutput();

            provideInput("1\nNon-Residential\n600\nHotel\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.6,
                    0,
                    290.6,
                    247.01,
                    0,
                    247.01,
                    43.59,
                    true,
                    "Hotel"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC10] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase10() {
            provideInput("1\nNon-Residential\n601\nCommercial\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    291.109,
                    3.55854,
                    294.66754,
                    285.28682,
                    6.4114092,
                    291.6982292,
                    2.9693108,
                    true,
                    "Commercial"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC11] user_type = Non-Residential AND consumption > 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase11() {
            provideInput("1\nNon-Residential\n601\nIndustrial\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    291.109,
                    3.55854,
                    294.66754,
                    285.28682,
                    6.4114092,
                    291.6982292,
                    2.9693108,
                    true,
                    "Industrial"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC12] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase12() {
            provideInput("1\nNon-Residential\n600\nCommercial\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.6,
                    0,
                    290.6,
                    284.788,
                    0,
                    284.788,
                    5.812,
                    true,
                    "Commercial"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC13] user_type = Non-Residential AND consumption <= 600 kWh AND sector_type = Commercial or Industrial")
        public void main_integration_testCase13() {
            provideInput("1\nNon-Residential\n600\nIndustrial\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(generateOutputTestingTemplateResidential(
                    290.6,
                    0,
                    290.6,
                    284.788,
                    0,
                    284.788,
                    5.812,
                    true,
                    "Industrial"
            ), getOutput());
        }

        @Test
        @DisplayName("[Integration Test - Main][TC14] user_type = Non-Residential AND sector_type is wrong")
        public void main_integration_testCase14() {
            String output = "Enter 0 to stop; 1 to continue Enter Type of Customer " +
                    "(Residential/Non-Residential): Enter electricity consumption (kWh): " +
                    "Enter Type of Sector(Hotel/Travel/Commercial/Industrial): Wrong Sector Type!!!\n" +
                    "Enter 0 to stop; 1 to continue \n" +
                    "You chose to STOP!!!\n";

            provideInput("1\nNon-Residential\n600\nLorem\n0\n");

            ElectricityDiscountCalc.main(null);

            assertEquals(output, getOutput());
        }

        @Test
        public void classShouldInstantiateMainClass() {
            assertTrue((new ElectricityDiscountCalc()) instanceof ElectricityDiscountCalc);
        }
    }
}
