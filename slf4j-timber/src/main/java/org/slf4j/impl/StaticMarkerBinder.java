package org.slf4j.impl;

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

/**
 *
 * The binding of {@link org.slf4j.MarkerFactory} class with an actual instance of
 * {@link IMarkerFactory} is performed using information returned by this class.
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
public class StaticMarkerBinder implements MarkerFactoryBinder {

  /**
   * The unique instance of this class.
   */
  public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();

  final IMarkerFactory markerFactory = new BasicMarkerFactory();

  private StaticMarkerBinder() {
  }

  /**
   * Currently this method always returns an instance of
   * {@link BasicMarkerFactory}.
   */
  @Override
  public IMarkerFactory getMarkerFactory() {
    return markerFactory;
  }

  /**
   * Currently, this method returns the class name of
   * {@link BasicMarkerFactory}.
   */
  @Override
  public String getMarkerFactoryClassStr() {
    return markerFactory.getClass().getName();
  }
}
