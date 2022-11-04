package com.alevel.java.report.ubike.command;

import com.alevel.java.report.ubike.exceptions.UbikeIngestException;

public interface Command<T> {

    T execute() throws UbikeIngestException;

}
