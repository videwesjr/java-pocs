package com.behavioral.templatemethod;

public final class SalesReportGenerator extends ReportGenerator {

    private int unitsSold;
    private int revenueCents;

    @Override
    protected String reportTitle() {
        return "Sales Report";
    }

    @Override
    protected void collectData() {
        System.out.println("Collecting sales data from the orders database...");
        unitsSold = 1280;
        revenueCents = 45_900_00;
    }

    @Override
    protected void analyzeData() {
        System.out.printf("Analyzed %d units sold totaling $%.2f%n", unitsSold, revenueCents / 100.0);
    }

    @Override
    protected void printSummary() {
        System.out.printf("Summary: average sale value was $%.2f%n", revenueCents / 100.0 / unitsSold);
    }
}
