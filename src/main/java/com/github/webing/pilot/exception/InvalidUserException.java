package com.github.webing.pilot.exception;

import java.io.IOException;

/**
 * Created by KD4 on 16. 2. 25.
 */
public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("invalid User Id or User Status. can't find User.");
    }

    public InvalidUserException(String msg) {
        super(msg);
    }

    public InvalidUserException(String s, Throwable e) {
        super(s,e);
    }
}
