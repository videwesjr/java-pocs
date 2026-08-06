package com.behavioral.chainofresponsibility;

public class Manager extends Approver {

    @Override
    protected boolean canApprove(PurchaseRequest request) {
        return request.getAmount() <= 1_000;
    }
}
