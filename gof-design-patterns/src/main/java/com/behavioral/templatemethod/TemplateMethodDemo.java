package com.behavioral.templatemethod;

public final class TemplateMethodDemo {

    private TemplateMethodDemo() {
    }

    public static void run() {
        System.out.println("=== Template Method ===");

        ReportGenerator salesReport = new SalesReportGenerator();
        salesReport.generateReport();

        System.out.println();

        ReportGenerator inventoryReport = new InventoryReportGenerator();
        inventoryReport.generateReport();
    }
}
