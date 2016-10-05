package com.arcao.slf4j.timber;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import de.greenrobot.common.io.IoUtils;
import timber.log.Timber;

/**
 * log to file<br>
 * the log file path maybe like this:<br>
 * <code>/sdcard/your_dir/log.txt</code>
 *
 * @author andych008 <andych008@163.com>
 */
public final class HollowFileTree extends Timber.HollowTree {
  private String dir;

  public HollowFileTree(String dir) {
    super();
    this.dir = dir;
  }

  @Override
  public void v(String message, Object... args) {
    writeMessageSafty(message, null);
  }

  @Override
  public void v(Throwable t, String message, Object... args) {
    writeMessageSafty(message, t);
  }

  @Override
  public void d(String message, Object... args) {
    writeMessageSafty(message, null);
  }

  @Override
  public void d(Throwable t, String message, Object... args) {
    writeMessageSafty(message, t);
  }

  @Override
  public void i(String message, Object... args) {
    writeMessageSafty(message, null);
  }

  @Override
  public void i(Throwable t, String message, Object... args) {
    writeMessageSafty(message, t);
  }

  @Override
  public void w(String message, Object... args) {
    writeMessageSafty(message, null);
  }

  @Override
  public void w(Throwable t, String message, Object... args) {
    writeMessageSafty(message, t);
  }

  @Override
  public void e(String message, Object... args) {
    writeMessageSafty(message, null);
  }

  @Override
  public void e(Throwable t, String message, Object... args) {
    writeMessageSafty(message, t);
  }


  private void writeMessageSafty(String message, Throwable t) {
    try {
      writeMessage(message, t);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeMessage(String message, Throwable t) throws IOException {
    if (message == null || message.length() == 0) {
      if (t != null) {
        message = Log.getStackTraceString(t);
      } else {
        // Swallow message if it's null and there's no throwable.
        return;
      }
    } else if (t != null) {
      message += "\n" + Log.getStackTraceString(t);
    }

    String logDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dir + File.separator;

    if (!TextUtils.isEmpty(logDir)) {
      File threadListFile = new File(logDir);
      if (!threadListFile.exists()) {
        if (!threadListFile.mkdirs()) {
          return;
        }
      }
    }

    File file = new File(logDir + "log.txt");

    if (!file.exists()) {
      if (!file.createNewFile()) {
        return;
      }
    }

    writeUtf8(file, message + "\n");
  }

  private void writeUtf8(File file, CharSequence text) throws IOException {
    Writer writer = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
    IoUtils.writeAllCharsAndClose(writer, text);
  }
}
