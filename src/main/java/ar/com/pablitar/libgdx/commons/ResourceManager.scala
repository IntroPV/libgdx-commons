package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureRegion
import scala.collection.mutable.AnyRefMap
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture.TextureFilter

trait ResourceManager {
  def texture(path: String) = {
    val tex = new Texture(Gdx.files.internal(path))
    tex.setFilter(TextureFilter.Linear, TextureFilter.Linear)
    tex
  }

  def textureRegion(path: String) = new TextureRegion(texture(path))

  private val regionCache = AnyRefMap.empty[String, TextureRegion]
  private val spriteCache = AnyRefMap.empty[String, Sprite]
  
  def atlas: TextureAtlas

  def atlasRegion(name: String) = {
    regionCache.getOrElseUpdate(name, atlas.findRegion(name))
  }

  implicit def atlasSprite(name: String): Sprite = {
    new Sprite(spriteCache.getOrElseUpdate(name, atlas.createSprite(name)))
  }

  implicit def stringToFileHandle(path: String) = {
    Gdx.files.internal(path)
  }

  def particleEffect(path: String) = {
    val p = new ParticleEffect()
    p.load(path, atlas)
    p
  }

  def musicFromFile(musicFile: String): Music = {
    val music = Gdx.audio.newMusic(musicFile)
    music.setLooping(true)
    music
  }
  
  def sprites(name: String) = {
    val sps = atlas.createSprites(name)
    sps.toArray().foreach(_.setOriginCenter())
    sps
  }
}