package com.behavioral.chainofresponsibility;

public class VicePresident extends Approver {

    @Override
    protected boolean canApprove(PurchaseRequest request) {
        return request.getAmount() <= 50_000;
    }
}
