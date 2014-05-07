package com.arcao.slf4j.timber;

import org.slf4j.helpers.MarkerIgnoringBase;

import timber.log.Timber;

/**
 * <p>A simple implementation that delegates all log requests to the Timber
 * logging facilities. Note that this logger does not support {@link org.slf4j.Marker}.
 * Methods taking marker data as parameter simply invoke the eponymous method
 * without the Marker argument, discarding any marker data in the process.</p>
 *
 * <p>The logging levels specified for SLF4J can be almost directly mapped to
 * the logging method that exist in Timber. The following table
 * shows the mapping implemented by this logger.</p>
 *
 * <table border="1">
 * <tr><th><b>SLF4J<b></th><th><b>Timber</b></th></tr>
 * <tr><td>TRACE</td><td>Timber.v(...)</td></tr>
 * <tr><td>DEBUG</td><td>Timber.d(...)</td></tr>
 * <tr><td>INFO</td><td>Timber.i(...)</td></tr>
 * <tr><td>WARN</td><td>Timber.w(...)</td></tr>
 * <tr><td>ERROR</td><td>Timber.e(...)</td></tr>
 * </table>
 *
 * <p>Use loggers as usual:
 * <ul>
 *     <li>
 *         Declare a logger<br/>
 *         <code>private static final Logger logger = LoggerFactory.getLogger(MyClass.class);</code>
 *     </li>
 *     <li>
 *         Invoke logging methods, e.g.,<br/>
 *         <code>logger.debug("Some log message. Details: {}", someObject);</code><br/>
 *         <code>logger.debug("Some log message with varargs. Details: {}, {}, {}", someObject1, someObject2, someObject3);</code>
 *     </li>
 * </ul>
 * </p>
 *
 * <p>Logger instances created using the LoggerFactory are named either according to the name
 * or the fully qualified class name of the class given as a parameter.
 *
 * Each logger name will be used as the log message tag on the Android platform.
 * However, tag names cannot be longer than 23 characters so if logger name exceeds this limit then
 * it will be truncated by the LoggerFactory. The following examples illustrate this.
 * <table border="1">
 * <tr><th><b>Original Name<b></th><th><b>Truncated Name</b></th></tr>
 * <tr><td>org.example.myproject.mypackage.MyClass</td><td>o*.e*.m*.m*.MyClass</td></tr>
 * <tr><td>o.e.myproject.mypackage.MyClass</td><td>o.e.m*.m*.MyClass</td></tr>
 * <tr><td>org.example.ThisNameIsWayTooLongAndWillBeTruncated</td><td>*LongAndWillBeTruncated</td></tr>
 * <tr><td>ThisNameIsWayTooLongAndWillBeTruncated</td><td>*LongAndWillBeTruncated</td></tr>
 * </table>
 * </p>
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
class TimberLoggerAdapter extends MarkerIgnoringBase {
  private static final long serialVersionUID = -1227274521521287937L;


  /**
   * Package access allows only {@link AndroidLoggerFactory} to instantiate
   * SimpleLogger instances.
   */
  TimberLoggerAdapter(String tag) {
    this.name = tag;
  }

  @Override
  public boolean isTraceEnabled() {
    return true;
  }

  @Override
  public void trace(String msg) {
    Timber.tag(name).v(msg);
  }

  @Override
  public void trace(String format, Object arg) {
    Timber.tag(name).v(format, arg);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    Timber.tag(name).v(format, arg1, arg2);
  }

  @Override
  public void trace(String format, Object... argArray) {
    Timber.tag(name).v(format, argArray);
  }

  @Override
  public void trace(String msg, Throwable t) {
    Timber.tag(name).v(t, msg);
  }

  @Override
  public boolean isDebugEnabled() {
    return true;
  }

  @Override
  public void debug(String msg) {
    Timber.tag(name).d(msg);
  }

  @Override
  public void debug(String format, Object arg) {
    Timber.tag(name).d(format, arg);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    Timber.tag(name).d(format, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... argArray) {
    Timber.tag(name).d(format, argArray);
  }

  @Override
  public void debug(String msg, Throwable t) {
    Timber.tag(name).d(t, msg);
  }

  @Override
  public boolean isInfoEnabled() {
    return true;
  }

  @Override
  public void info(String msg) {
    Timber.tag(name).i(msg);
  }

  @Override
  public void info(String format, Object arg) {
    Timber.tag(name).i(format, arg);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    Timber.tag(name).i(format, arg1, arg2);
  }

  @Override
  public void info(String format, Object... argArray) {
    Timber.tag(name).i(format, argArray);
  }

  @Override
  public void info(String msg, Throwable t) {
    Timber.tag(name).i(t, msg);
  }

  @Override
  public boolean isWarnEnabled() {
    return true;
  }

  @Override
  public void warn(String msg) {
    Timber.tag(name).w(msg);
  }

  @Override
  public void warn(String format, Object arg) {
    Timber.tag(name).w(format, arg);
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    Timber.tag(name).w(format, arg1, arg2);
  }

  @Override
  public void warn(String format, Object... argArray) {
    Timber.tag(name).w(format, argArray);
  }

  @Override
  public void warn(String msg, Throwable t) {
    Timber.tag(name).w(t, msg);
  }

  @Override
  public boolean isErrorEnabled() {
    return true;
  }

  @Override
  public void error(String msg) {
    Timber.tag(name).e(msg);
  }

  @Override
  public void error(String format, Object arg) {
    Timber.tag(name).e(format, arg);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    Timber.tag(name).e(format, arg1, arg2);
  }

  @Override
  public void error(String format, Object... argArray) {
    Timber.tag(name).e(format, argArray);
  }

  @Override
  public void error(String msg, Throwable t) {
    Timber.tag(name).e(t, msg);
  }
}