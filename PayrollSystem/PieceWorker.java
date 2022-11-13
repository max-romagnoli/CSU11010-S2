package com.company;

public class PieceWorker extends Employee {

    private double wage;
    private int pieces;

    public PieceWorker(String firstName, String lastName, String socialSecurityNumber, double wage, int pieces) {
        super(firstName, lastName, socialSecurityNumber);
        this.wage = wage;
        this.pieces = pieces;

        if (wage < 0.0)
            throw new IllegalArgumentException(
                    "Wage must be >= 0.0");

        if (pieces < 0)
            throw new IllegalArgumentException(
                    "Wage must be >= 0.0");

    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        if (wage < 0.0)
            throw new IllegalArgumentException(
                    "Wage must be >= 0.0");

        this.wage = wage;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        if (pieces < 0)
            throw new IllegalArgumentException(
                    "Wage must be >= 0.0");
        this.pieces = pieces;
    }

    @Override
    public double earnings() {
        return getWage()*getPieces();
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s%n%s: $%,.2f; %s: %d",
                "piece worker", super.toString(),
                "wage per piece", getWage(),
                "pieces produced", getPieces());
    }

}
