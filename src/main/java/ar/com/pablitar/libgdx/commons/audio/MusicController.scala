package ar.com.pablitar.libgdx.commons.audio

import ar.com.pablitar.libgdx.commons.extensions.AsyncExecutorExtensions._
import com.badlogic.gdx.utils.async.AsyncExecutor

trait MusicControllerMode {
  def update(delta: Float, m: MusicController)

  def updateVolume(controller: MusicController): Unit
}

object Normal extends MusicControllerMode {
  def update(delta: Float, m: MusicController): Unit = {
    //NADA
  }

  def updateVolume(controller: MusicController): Unit = {
    controller.maximizeCurrentMusicVolume
  }
}

class FadeToNext(duration: Float) extends MusicControllerMode {
  var elapsed: Float = 0

  override def update(delta: Float, m: MusicController): Unit = {
    elapsed += delta

    if (elapsed >= duration) {
      m.switchToNext()
    } else {
      updateVolume(m)
    }
  }

  def updateVolume(controller: MusicController): Unit = {
    controller.setFadeVolumesAccordingTo(elapsed / duration)
  }
}

/**
  * Created by pablitar on 14/04/17.
  */
class MusicController {

  implicit val asyncExecutor = new AsyncExecutor(1)

  var currentMusic = Option.empty[MusicTrack]
  var nextMusic = Option.empty[MusicTrack]

  var musicVolume = 1.0f

  var mode: MusicControllerMode = Normal

  def playNow(m: () => MusicTrack): Unit = {
    async {
      val music: MusicTrack = m()
      clearCurrentMusic
      currentMusic = Some(music)
      currentMusic.foreach(x => {
        x.play()
        x.setVolume(volume(1f))
      })
    }
  }

  def clearCurrentMusic: Unit = {
    currentMusic.foreach { it =>
      doClearMusic(it)
    }
  }

  def doClearMusic(aMusic: MusicTrack) = {
    async {
      aMusic.stop()
      aMusic.dispose()
    }
  }

  def setMusicVolume(newVolume: Float) = {
    musicVolume = newVolume
    mode.updateVolume(this)
  }

  def fadeOutAndPlay(musicProvider: () => MusicTrack, fadeDuration: Float = 0.5f): Unit = {
    //No hace falta sincronizar demasiado porque el executor tiene un solo thread. Si eso cambiara quizás habría que sincronizar.
    async {
      val next: MusicTrack = musicProvider()
      next.play()
      next.setVolume(0f)

      if (nextMusic.isDefined) {
        switchToNext()
      }
      nextMusic = Some(next)

      this.mode = new FadeToNext(fadeDuration)
    }
  }

  def update(delta: Float): Unit = {
    this.mode.update(delta, this)
  }

  def switchToNext() = {
    clearCurrentMusic
    currentMusic = nextMusic
    nextMusic = None
    maximizeCurrentMusicVolume
    this.mode = Normal
  }

  def maximizeCurrentMusicVolume: Unit = {
    async {
      currentMusic.foreach(_.setVolume(volume(1f)))
    }
  }

  def setFadeVolumesAccordingTo(alpha: Float): Unit = {
    async {
      this.currentMusic.foreach(_.setVolume(volume(1 - alpha)))
      this.nextMusic.foreach(_.setVolume(volume(alpha)))
    }
  }

  def volume(alpha: Float): Float = {
    musicVolume * alpha
  }
}
