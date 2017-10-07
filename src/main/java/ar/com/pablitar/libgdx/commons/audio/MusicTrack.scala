package ar.com.pablitar.libgdx.commons.audio

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Disposable
import ar.com.pablitar.libgdx.commons.CommonMathUtils


/**
  * Created by pablitar on 17/06/17.
  */
class MusicTrack(val music: Music, positions: Seq[Float] = Seq(0), looping: Boolean = true) extends Disposable {
  music.setLooping(looping)
  def randomPosition(): Float = CommonMathUtils.randomInSeq(positions)
  def play(position: Float = randomPosition()) = {
    try {
      music.play()
      music.setPosition(position)
    }  catch {
      case e: IllegalStateException => Gdx.app.error("Music Track", "Error al comenzar al reproducir la música", e)
    }
  }

  def setVolume(volume: Float) = {
    try {
      music.setVolume(volume)
    } catch {
      case e: IllegalStateException => Gdx.app.error("Music Track", "Error al setear el volumen", e)
    }
  }

  def stop(): Unit = {
    try {
      music.stop()
    } catch {
      case e: IllegalStateException => Gdx.app.error("Music Track", "Error al detener la música", e)
    }
  }

  def dispose(): Unit = {
    try {
      music.dispose()
    } catch {
      case e: IllegalStateException => Gdx.app.error("Music Track", "Error al cerrar el track de música", e)
    }
  }
}