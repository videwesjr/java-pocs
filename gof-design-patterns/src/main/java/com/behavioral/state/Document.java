package com.behavioral.state;

public final class Document {

    private DocumentState state = new DraftState();
    private boolean admin;

    public void setState(DocumentState state) {
        this.state = state;
        System.out.println("Document is now in state: " + state.name());
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void publish() {
        state.publish(this);
    }

    public String currentState() {
        return state.name();
    }
}
