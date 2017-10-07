package ar.com.pablitar.libgdx.commons.persistence

import com.badlogic.gdx.utils.async.{AsyncExecutor, AsyncTask}
import com.badlogic.gdx.{Gdx, Preferences}

object UserData extends Preferences {

  private val prefsExecutor = new AsyncExecutor(1)
  val prefs = Gdx.app.getPreferences("userData")
  var autoFlush = true
  var flushPending = false

  override def putString(key: String, value: String): Preferences = {
    prefs.putString(key, value)
    flushIfConfigured()
  }

  def flushIfConfigured(): Preferences = {
    if(autoFlush) flush()
    this
  }

  override def get(): java.util.Map[String, _] = prefs.get()

  override def put(vals: java.util.Map[String, _]): Preferences = prefs.put(vals)

  override def clear(): Unit = {
    prefs.clear()
    flushIfConfigured()
  }

  override def getFloat(key: String): Float = prefs.getFloat(key)

  override def getFloat(key: String, defValue: Float): Float = prefs.getFloat(key, defValue)

  override def getLong(key: String): Long = prefs.getLong(key)

  override def getLong(key: String, defValue: Long): Long = prefs.getLong(key, defValue)

  override def flush(): Unit = {
    prefsExecutor.submit(new AsyncTask[Unit] {
      override def call(): Unit = prefs.flush()
    })
  }

  override def putFloat(key: String, value: Float): Preferences = {
    prefs.putFloat(key, value)
    flushIfConfigured()
  }

  override def remove(key: String): Unit = {
    prefs.remove(key)
    flushIfConfigured()
  }

  override def putBoolean(key: String, value: Boolean): Preferences = {
    prefs.putBoolean(key, value)
    flushIfConfigured()
  }

  override def getBoolean(key: String): Boolean = prefs.getBoolean(key)

  override def getBoolean(key: String, defValue: Boolean): Boolean = prefs.getBoolean(key, defValue)

  override def putInteger(key: String, value: Int): Preferences = {
    prefs.putInteger(key, value)
    flushIfConfigured()
  }

  def flushSync(): Preferences = {
    prefs.flush()
    this
  }

  override def contains(key: String): Boolean = prefs.contains(key)

  override def getInteger(key: String): Int = prefs.getInteger(key)

  override def getInteger(key: String, defValue: Int): Int = prefs.getInteger(key, defValue)

  override def putLong(key: String, value: Long): Preferences = {
    prefs.putLong(key, value)
    flushIfConfigured()
  }

  override def getString(key: String): String = prefs.getString(key)

  override def getString(key: String, defValue: String): String = prefs.getString(key, defValue)
}
