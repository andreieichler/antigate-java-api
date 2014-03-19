package ru.fourqube.antigate.commands;

import ru.fourqube.antigate.exceptions.AntigateException;

/**
 * @author Konstantin Pavlov
 * @since Mar 18, 2014
 */
public interface Command<T> {
    T execute() throws AntigateException;
}
