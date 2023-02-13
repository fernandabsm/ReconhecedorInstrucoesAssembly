package controller;

import repository.Instructions;
import repository.Registers;

import java.util.Arrays;
import java.util.List;

public class Validate {

    private static final List<Character> integers = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public boolean validateExpression(String expression) {
        if (expression.contains("memory")) {
            return validateMemoryExpression(expression);
        }
        String[] parts = expression.split(" ");

        if (parts.length != 2) return false;

        if (Instructions.storage.contains(parts[0])) {
            return validateStorageExpression(parts[1]);
        } else if (Instructions.arithmetic.contains(parts[0])) {
            return validateArithmeticExpression(parts[1]);
        } else {
            return false;
        }
    }

    private boolean validateArithmeticExpression(String values) {
        String[] parts = values.split(",");
        if (parts.length != 3) return false;
        return Arrays.stream(parts).allMatch(this::validateRegisters);
    }

    private boolean validateStorageExpression(String values) {
        String[] parts = values.split(",");
        if (parts.length != 2) return false;
        if (!(Registers.temporaries.contains(parts[0]) || Registers.savedValues.contains(parts[0]))) return false;
        String[] secondPart = parts[1].split("\\(");
        if (secondPart.length != 2 || !isInteger(secondPart[0]) || secondPart[1].length() != 4) return false;
        String[] finalPart = secondPart[1].split("\\)");
        if (finalPart.length != 1) return false;
        return validateRegisters(finalPart[0]);
    }

    private boolean validateMemoryExpression(String expression) {
        return expression.length() == 9 && expression.charAt(6) == '[' && integers.contains(expression.charAt(7)) && expression.charAt(8) == ']';
    }

    private boolean validateRegisters(String register) {
        return Registers.temporaries.contains(register) || Registers.savedValues.contains(register);
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
