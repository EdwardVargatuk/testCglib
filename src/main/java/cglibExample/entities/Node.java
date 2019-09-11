package cglibExample.entities;

import java.util.Objects;

/**
 * 08.09.2019 20:50
 *
 * @author Edward
 */
public class Node {

    private int number;
    private String name;
    private boolean transactionPassed;

    public Node(int number, String name, boolean transactionPassed) {
        this.number = number;
        this.name = name;
        this.transactionPassed = transactionPassed;
    }

    public Node(String name, boolean transactionPassed) {
        this.name = name;
        this.transactionPassed = transactionPassed;
    }

    public Node(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isTransactionPassed() {
        return transactionPassed;
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", transactionPassed=" + transactionPassed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getNumber() == node.getNumber() &&
                isTransactionPassed() == node.isTransactionPassed() &&
                getName().equals(node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName(), isTransactionPassed());
    }
}

