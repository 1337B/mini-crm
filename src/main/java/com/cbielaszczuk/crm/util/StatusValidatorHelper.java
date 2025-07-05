package com.cbielaszczuk.crm.util;

import com.cbielaszczuk.crm.model.TransitionableStatusInterface;

/**
 * Utility class to validate transitions between status enums implementing TransitionableStatusInterface.
 */
public class StatusValidatorHelper {

    /**
     * Validates if a status transition is allowed.
     *
     * @param from current status
     * @param to   desired target status
     * @return true if transition is valid, false otherwise
     */
    public static boolean isValid(TransitionableStatusInterface from, TransitionableStatusInterface to) {
        if (from == null || to == null) return false;
        if (!from.getClass().equals(to.getClass())) return false;

        return switch (from.toString()) {
            case "NOT_STARTED" -> to.toString().equals("IN_PROGRESS") || to.toString().equals("CANCELLED");
            case "IN_PROGRESS" -> to.toString().equals("ON_HOLD") || to.toString().equals("FINISHED") || to.toString().equals("CANCELLED");
            case "ON_HOLD"     -> to.toString().equals("IN_PROGRESS") || to.toString().equals("CANCELLED");
            case "FINISHED", "CANCELLED" -> false;
            default -> false;
        };
    }
}
