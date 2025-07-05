package com.cbielaszczuk.crm.model;

/**
 * Interface to define status transitions.
 */
public interface TransitionableStatusInterface {
    boolean isValidTransition(TransitionableStatusInterface to);
}
