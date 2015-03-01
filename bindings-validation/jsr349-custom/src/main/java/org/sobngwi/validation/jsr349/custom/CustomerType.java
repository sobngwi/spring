package org.sobngwi.validation.jsr349.custom;

public enum CustomerType {
    INDIVIDUAL("I"), CORPORATE("C");

    private String code;

    private CustomerType(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}
