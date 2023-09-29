package ru.neoflex.dtos;

public enum Source {
    MAIL("mail"),
    MOBILE("mobile"),
    BANK("bank"),
    GOSUSLUGI("gosuslugi");
    private final String value;

    Source(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
