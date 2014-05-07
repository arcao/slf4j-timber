package com.arcao.slf4j.timber;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * TimberLoggerFactory is an implementation of {@link ILoggerFactory} returning
 * the appropriately named {@link TimberLoggerFactory} instance.
 *
 * @author Martin Sloup <arcao@arcao.com>
 */
public class TimberLoggerFactory implements ILoggerFactory {
  private static final Pattern ANONYMOUS_CLASS = Pattern.compile("\\$\\d+$");

  private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<String, Logger>();

  /**
   * Return an appropriate {@link TimberLoggerAdapter} instance by name.
   */
  @Override
  public Logger getLogger(String name) {
    String tag = createTag(name);
    Logger logger = loggerMap.get(tag);
    if (logger == null) {
      Logger newInstance = new TimberLoggerAdapter(tag);
      Logger oldInstance = loggerMap.putIfAbsent(tag, newInstance);
      logger = oldInstance == null ? newInstance : oldInstance;
    }
    return logger;
  }

  private static String createTag(String name) {
    String tag = name;

    Matcher m = ANONYMOUS_CLASS.matcher(tag);
    if (m.find()) {
      tag = m.replaceAll("");
    }
    return tag.substring(tag.lastIndexOf('.') + 1);
  }
}