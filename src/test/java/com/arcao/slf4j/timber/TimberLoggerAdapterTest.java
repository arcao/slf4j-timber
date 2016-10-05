package com.arcao.slf4j.timber;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowLog.LogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timber.log.Timber;
import android.util.Log;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class TimberLoggerAdapterTest {
  private static final Logger logger = LoggerFactory.getLogger(TimberLoggerAdapterTest.class);

  @Before @After public void setUpAndTearDown() {
    Timber.uprootAll();
  }

  @Test public void debugTest() {
    Timber.plant(new Timber.DebugTree());
    // Timber.plant(new HollowFileTree("slf4j-test"));

    logger.debug("Hello, world!");

    List<LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(1);
    LogItem log = logs.get(0);
    assertThat(log.type).isEqualTo(Log.DEBUG);
    assertThat(log.tag).isEqualTo("TimberLoggerAdapterTest");
    assertThat(log.msg).isEqualTo("Hello, world!");
    assertThat(log.throwable).isNull();
  }
}
