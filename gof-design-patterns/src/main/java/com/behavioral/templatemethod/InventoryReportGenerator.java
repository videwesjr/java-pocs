package com.behavioral.templatemethod;

public final class InventoryReportGenerator extends ReportGenerator {

    private int itemsInStock;

    @Override
    protected String reportTitle() {
        return "Inventory Report";
    }

    @Override
    protected void collectData() {
        System.out.println("Collecting stock counts from the warehouse system...");
        itemsInStock = 342;
    }

    @Override
    protected void analyzeData() {
        System.out.println("Analyzed stock levels across all warehouses: " + itemsInStock + " items in stock");
    }

    @Override
    protected boolean includeSummary() {
        return false;
    }
}
