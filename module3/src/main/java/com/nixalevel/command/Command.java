package com.nixalevel.command;

import com.nixalevel.exceptions.LSException;

public interface Command<T> {

    T execute() throws LSException;

}
