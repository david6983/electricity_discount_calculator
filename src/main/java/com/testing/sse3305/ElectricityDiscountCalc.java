package com.testing.sse3305;

import java.util.Scanner;
import java.text.DecimalFormat;

public class ElectricityDiscountCalc {

    private static Scanner reader;

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        reader = new Scanner(System.in);
        System.out.print("Enter 0 to stop; 1 to continue ");
        int ans = reader.nextInt();

        while (ans == 1) {
            System.out.print("Enter Type of Customer (Residential/Non-Residential): ");
            String user_type = reader.next();
            if (user_type.equals("Residential") || user_type.equals("Non-Residential")) {
                String sector_type = "";

                System.out.print("Enter electricity consumption (kWh): ");
                int consump = reader.nextInt();

                double amount = 0.00;
                double tax = 0.00;
                double amountDisc = 0.00;
                double taxDisc = 0.00;
                int print_status = 0;

                if (user_type.equals("Residential")) {
                    amount = calcNoDiscBilR(consump);
                    tax = calcTax(consump, amount, 0);
                    amountDisc = calcDiscBilR(consump);
                    taxDisc = calcTax(consump, amountDisc, 1);
                } else {
                    System.out.print("Enter Type of Sector(Hotel/Travel/Commercial/Industrial): ");
                    sector_type = reader.next();

                    if (sector_type.equals("Hotel") || sector_type.equals("Travel")) {
                        amount = calcNoDiscBilNR(consump);
                        tax = calcTax(consump, amount, 0);
                        amountDisc = calcDiscBilNR1(consump);
                        taxDisc = calcTax(consump, amountDisc, 1);
                    } else if (sector_type.equals("Commercial") || sector_type.equals("Industrial")) {
                        amount = calcNoDiscBilNR(consump);
                        tax = calcTax(consump, amount, 0);
                        amountDisc = calcDiscBilNR2(consump);
                        taxDisc = calcTax(consump, amountDisc, 1);
                    } else {
                        System.out.println("Wrong Sector Type!!!");
                        print_status = 1;
                    }
                }
                if (print_status == 0) {
                    System.out.println("");
                    System.out.println("-------------- UNDERSTAND YOUR ELECTRICTIY BIL -------------- ");
                    System.out.println("Tariff: " + user_type + " " + sector_type);
                    System.out.println("");
                    System.out.println("Total Electricity Bil without Tax: " + df.format(amount));
                    System.out.println("Total Electricity Tax: " + df.format(tax));
                    System.out.println("Total Electricity Bil with Tax: " + df.format(amount + tax));
                    System.out.println("");
                    System.out.println("****************************************************************");
                    System.out.println("");
                    System.out.println("Total Discounted Electricity Bil without Tax: " + df.format(amountDisc));
                    System.out.println("Total Discounted Electricity Tax: " + df.format(taxDisc));
                    System.out.println("Total Discounted Electricity with Tax: " + df.format(amountDisc + taxDisc));
                    System.out.println("");
                    System.out.println("*****************************************************************");
                    System.out.println("");
                    System.out.println("Total Saving during COVID-19: " + df.format((amount + tax) - (amountDisc + taxDisc)));
                    System.out.println("");
                }
                System.out.println("Enter 0 to stop; 1 to continue ");
                ans = reader.nextInt();

            }
        }

        System.out.println("You chose to STOP!!!");
    }

    // To calculate residential no discount bill
    public static double calcNoDiscBilR(int consumption) {
        double bil_amount = 0.00;
        double first200kwh = 0.00;
        double next100kwh = 0.00;
        double next300kwh_1 = 0.00;
        double next300kwh_2 = 0.00;
        double nextbalancekwh = 0.00;

        if (consumption > 201) {
            if (consumption > 301) {
                if (consumption > 601) {
                    if (consumption > 901) {
                        first200kwh = (200 * 21.80) / 100;
                        next100kwh = (100 * 33.40) / 100;
                        next300kwh_1 = (300 * 51.60) / 100;
                        next300kwh_2 = (300 * 54.60) / 100;
                        consumption = consumption - 900;
                        nextbalancekwh = (consumption * 57.10) / 100;
                        bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2 + nextbalancekwh;
                    } else {
                        first200kwh = (200 * 21.80) / 100;
                        next100kwh = (100 * 33.40) / 100;
                        next300kwh_1 = (300 * 51.60) / 100;
                        consumption = consumption - 600;
                        next300kwh_2 = (consumption * 54.60) / 100;
                        bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2;
                    }
                } else {
                    first200kwh = (200 * 21.80) / 100;
                    next100kwh = (100 * 33.40) / 100;
                    consumption = consumption - 300;
                    next300kwh_1 = (consumption * 51.60) / 100;
                    bil_amount = first200kwh + next100kwh + next300kwh_1;
                }
            } else {
                first200kwh = (200 * 21.80) / 100;
                consumption = consumption - 200;
                next100kwh = (consumption * 33.40) / 100;
                bil_amount = first200kwh + next100kwh;
            }
        } else {
            bil_amount = (consumption * 21.80) / 100;
            consumption = consumption - 200;
        }
        return bil_amount;
    }

    // To calculate residential discounted bill
    public static double calcDiscBilR(int consumption) {
        double bil_amount = 0.00;
        double first200kwh = 0.00;
        double next100kwh = 0.00;
        double next300kwh_1 = 0.00;
        double next300kwh_2 = 0.00;
        double nextbalancekwh = 0.00;

        if (consumption > 201) {
            if (consumption > 301) {
                if (consumption > 601) {
                    if (consumption > 901) {
                        first200kwh = 0.5 * ((200 * 21.80) / 100);
                        next100kwh = 0.75 * ((100 * 33.40) / 100);
                        next300kwh_1 = 0.85 * ((300 * 51.60) / 100);
                        next300kwh_2 = 0.98 * ((300 * 54.60) / 100);
                        consumption = consumption - 900;
                        nextbalancekwh = 0.98 * ((consumption * 57.10) / 100);
                        bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2 + nextbalancekwh;
                    } else {
                        first200kwh = 0.5 * ((200 * 21.80) / 100);
                        next100kwh = 0.75 * ((100 * 33.40) / 100);
                        next300kwh_1 = 0.85 * ((300 * 51.60) / 100);
                        consumption = consumption - 600;
                        next300kwh_2 = 0.98 * ((consumption * 54.60) / 100);
                        bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2;
                    }
                } else {
                    first200kwh = 0.5 * ((200 * 21.80) / 100);
                    next100kwh = 0.75 * ((100 * 33.40) / 100);
                    consumption = consumption - 300;
                    next300kwh_1 = 0.85 * ((consumption * 51.60) / 100);
                    bil_amount = first200kwh + next100kwh + next300kwh_1;
                }
            } else {
                first200kwh = 0.5 * ((200 * 21.80) / 100);
                consumption = consumption - 200;
                next100kwh = 0.75 * ((consumption * 33.40) / 100);
                bil_amount = first200kwh + next100kwh;
            }
        } else {
            bil_amount = 0.5 * ((consumption * 21.80) / 100);
        }
        return bil_amount;
    }

    // To calculate non-residential no discount bill
    public static double calcNoDiscBilNR(int consumption) {
        double bil_amount = 0.00;
        double first200kwh = 0.00;
        double nextbalancekwh = 0.00;

        if (consumption > 201) {
            first200kwh = 200 * 0.435;
            consumption = consumption - 200;
            nextbalancekwh = consumption * 0.509;
            bil_amount = first200kwh + nextbalancekwh;

        } else {
            bil_amount = (consumption * 0.435);
        }
        return bil_amount;
    }

    // To calculate non-residential discounted bill for most affected sectors i.e.
    // Hotel and Travel
    public static double calcDiscBilNR1(int consumption) {
        double bil_amount = 0.00;
        double first200kwh = 0.00;
        double nextbalancekwh = 0.00;

        if (consumption > 201) {
            first200kwh = 200 * 0.435;
            consumption = consumption - 200;
            nextbalancekwh = consumption * 0.509;
            bil_amount = 0.85 * (first200kwh + nextbalancekwh);

        } else {
            bil_amount = 0.85 * (consumption * 0.435);
        }
        return bil_amount;
    }

    // To calculate non-residential discounted bill for less affected sectors i.e.
    // Commercial and Industrial
    public static double calcDiscBilNR2(int consumption) {
        double bil_amount = 0.00;
        double first200kwh = 0.00;
        double nextbalancekwh = 0.00;

        if (consumption > 201) {
            first200kwh = 200 * 0.435;
            consumption = consumption - 200;
            nextbalancekwh = consumption * 0.509;
            bil_amount = 0.98 * (first200kwh + nextbalancekwh);

        } else {
            bil_amount = 0.98 * (consumption * 0.435);
        }
        return bil_amount;
    }

    // To calculate 6% on bill amount. The tax is charged for consumption more than
    // 600kWh
    public static double calcTax(int consumption, double bil_amount, int disc) {
        if (consumption < 601) {
            return 0;
        } else {
            if (disc == 0) {
                // Tax for normal bill
                return ((bil_amount - 231.80) * 0.06);
            } else {
                // Tax for discounted bill
                return ((bil_amount - 178.43) * 0.06);
            }
        }
    }

}

