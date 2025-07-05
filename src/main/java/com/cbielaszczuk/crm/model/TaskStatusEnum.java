package com.cbielaszczuk.crm.model;

import com.cbielaszczuk.crm.util.StatusValidatorHelper;

public enum TaskStatusEnum implements TransitionableStatusInterface {
    NOT_STARTED,
    IN_PROGRESS,
    ON_HOLD,
    FINISHED,
    CANCELLED;

    @Override
    public boolean isValidTransition(TransitionableStatusInterface to) {
        return StatusValidatorHelper.isValid(this, to);
    }
}
