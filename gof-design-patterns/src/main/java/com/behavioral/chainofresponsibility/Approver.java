package com.behavioral.chainofresponsibility;

public abstract class Approver {

    private Approver next;

    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    public void process(PurchaseRequest request) {
        if (canApprove(request)) {
            System.out.printf("%s approved purchase of $%.2f for %s%n",
                    getClass().getSimpleName(), request.getAmount(), request.getPurpose());
            return;
        }

        if (next != null) {
            next.process(request);
        } else {
            System.out.printf("No approver in the chain can approve $%.2f for %s%n",
                    request.getAmount(), request.getPurpose());
        }
    }

    protected abstract boolean canApprove(PurchaseRequest request);
}
