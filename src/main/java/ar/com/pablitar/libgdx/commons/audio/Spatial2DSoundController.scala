package ar.com.pablitar.libgdx.commons.audio

import com.badlogic.gdx.audio.Sound
import ar.com.pablitar.libgdx.commons.CommonMathUtils

class Spatial2DSoundController {
  
  var volume = 1.0f
  
  def play(sound: Sound) = {
    sound.play(volume)
  }
  
  def playAny(sounds: Seq[Sound]) = {
    play(CommonMathUtils.randomInSeq(sounds))
  }
}