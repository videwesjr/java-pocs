package com.behavioral.chainofresponsibility;

public class Director extends Approver {

    @Override
    protected boolean canApprove(PurchaseRequest request) {
        return request.getAmount() <= 10_000;
    }
}
