package com.arcao.slf4j.timber;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import timber.log.Timber;
import timber.log.Timber.Tree;

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
 * Each logger name will be used as the tag for Timber if Timber has planted {@link timber.log.Timber.TaggedTree}.
 * If tag contains also class package, it will be removed (same way like in {@link timber.log.Timber.DebugTree}).
 * </p>
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
class TimberLoggerAdapter extends MarkerIgnoringBase {
  private static final long serialVersionUID = -1227274521521287937L;

  private enum LogType {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
  }

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
    log(LogType.TRACE, msg, null);
  }

  @Override
  public void trace(String format, Object arg) {
    formatAndLog(LogType.TRACE, format, arg);
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    formatAndLog(LogType.TRACE, format, arg1, arg2);
  }

  @Override
  public void trace(String format, Object... argArray) {
    formatAndLog(LogType.TRACE, format, argArray);
  }

  @Override
  public void trace(String msg, Throwable t) {
    log(LogType.TRACE, msg, t);
  }

  @Override
  public boolean isDebugEnabled() {
    return true;
  }

  @Override
  public void debug(String msg) {
    log(LogType.DEBUG, msg, null);
  }

  @Override
  public void debug(String format, Object arg) {
    formatAndLog(LogType.DEBUG, format, arg);
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    formatAndLog(LogType.DEBUG, format, arg1, arg2);
  }

  @Override
  public void debug(String format, Object... argArray) {
    formatAndLog(LogType.DEBUG, format, argArray);
  }

  @Override
  public void debug(String msg, Throwable t) {
    log(LogType.DEBUG, msg, t);
  }

  @Override
  public boolean isInfoEnabled() {
    return true;
  }

  @Override
  public void info(String msg) {
    log(LogType.INFO, msg, null);
  }

  @Override
  public void info(String format, Object arg) {
    formatAndLog(LogType.INFO, format, arg);
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    formatAndLog(LogType.INFO, format, arg1, arg2);
  }

  @Override
  public void info(String format, Object... argArray) {
    formatAndLog(LogType.INFO, format, argArray);
  }

  @Override
  public void info(String msg, Throwable t) {
    log(LogType.INFO, msg, t);
  }

  @Override
  public boolean isWarnEnabled() {
    return true;
  }

  @Override
  public void warn(String msg) {
    log(LogType.WARN, msg, null);
  }

  @Override
  public void warn(String format, Object arg) {
    formatAndLog(LogType.WARN, format, arg);
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    formatAndLog(LogType.WARN, format, arg1, arg2);
  }

  @Override
  public void warn(String format, Object... argArray) {
    formatAndLog(LogType.WARN, format, argArray);
  }

  @Override
  public void warn(String msg, Throwable t) {
    log(LogType.WARN, msg, t);
  }

  @Override
  public boolean isErrorEnabled() {
    return true;
  }

  @Override
  public void error(String msg) {
    log(LogType.ERROR, msg, null);
  }

  @Override
  public void error(String format, Object arg) {
    formatAndLog(LogType.ERROR, format, arg);
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    formatAndLog(LogType.ERROR, format, arg1, arg2);
  }

  @Override
  public void error(String format, Object... argArray) {
    formatAndLog(LogType.ERROR, format, argArray);
  }

  @Override
  public void error(String msg, Throwable t) {
    log(LogType.ERROR, msg, t);
  }

  private void formatAndLog(LogType logType, String format, Object... argArray) {
    FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
    log(logType, ft.getMessage(), ft.getThrowable());
  }

  private void log(LogType logType, String message, Throwable throwable) {
    Tree tree = Timber.tag(name);

    switch(logType) {
      case TRACE:
        if (throwable != null) {
          tree.v(throwable, message);
        } else {
          tree.v(message);
        }
        break;

      case DEBUG:
        if (throwable != null) {
          tree.d(throwable, message);
        } else {
          tree.d(message);
        }
        break;

      case INFO:
        if (throwable != null) {
          tree.i(throwable, message);
        } else {
          tree.i(message);
        }
        break;

      case WARN:
        if (throwable != null) {
          tree.w(throwable, message);
        } else {
          tree.w(message);
        }
        break;

      case ERROR:
        if (throwable != null) {
          tree.e(throwable, message);
        } else {
          tree.e(message);
        }
        break;
    }
  }
}