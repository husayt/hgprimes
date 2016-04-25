/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox.base.validation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class InvalidEntityException extends RuntimeException {
    private static final long serialVersionUID = -8762073181655035705L;

    private final ImmutableList<String> errors;


    public InvalidEntityException(String message, Iterable<String> errors) {
        super(message);
        this.errors = ImmutableList.copyOf(errors);
    }

    public InvalidEntityException(String error) {
        this("The request entity had the following errors:", Lists.newArrayList(error));
    }

    public ImmutableList<String> getErrors() {
        return this.errors;
    }
}

