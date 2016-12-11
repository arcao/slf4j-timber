package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import com.arcao.slf4j.timber.TimberLoggerFactory;

/**
 * The binding of {@link org.slf4j.LoggerFactory} class with an actual instance of
 * {@link ILoggerFactory} is performed using information returned by this class.
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {
  public static String REQUESTED_API_VERSION = "1.7.7"; // SUPPRESS CHECKSTYLE slf4j

  /**
   * The unique instance of this class.
   */
  private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

  /**
   * Return the singleton of this class.
   *
   * @return the StaticLoggerBinder singleton
   */
  public static StaticLoggerBinder getSingleton() {
    return SINGLETON;
  }

  /**
   * The ILoggerFactory instance returned by the {@link #getLoggerFactory} method
   * should always be the same object
   */
  private final ILoggerFactory loggerFactory;

  private StaticLoggerBinder() {
    loggerFactory = new TimberLoggerFactory();
  }

  @Override
  public ILoggerFactory getLoggerFactory() {
    return loggerFactory;
  }

  @Override
  public String getLoggerFactoryClassStr() {
    return loggerFactory.getClass().getName();
  }
}
