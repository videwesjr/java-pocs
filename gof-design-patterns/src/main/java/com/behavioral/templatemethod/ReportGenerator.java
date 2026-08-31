package com.behavioral.templatemethod;

public abstract class ReportGenerator {

    public final void generateReport() {
        System.out.println("=== " + reportTitle() + " ===");
        collectData();
        analyzeData();
        if (includeSummary()) {
            printSummary();
        }
        System.out.println("Report saved as " + reportTitle().toLowerCase().replace(" ", "_") + ".pdf");
    }

    protected abstract String reportTitle();

    protected abstract void collectData();

    protected abstract void analyzeData();

    protected boolean includeSummary() {
        return true;
    }

    protected void printSummary() {
        System.out.println("Summary: nothing noteworthy to report");
    }
}
