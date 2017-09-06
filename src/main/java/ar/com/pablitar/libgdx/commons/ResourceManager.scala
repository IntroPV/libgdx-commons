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
import scala.collection.mutable.Seq
import com.badlogic.gdx.graphics.g2d.BitmapFont
import scala.collection.mutable.ArrayBuffer
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

trait ResourceManager extends Disposable {
  val managedFonts = ArrayBuffer.empty[BitmapFont]
  val managedTextures = ArrayBuffer.empty[Texture]

  def texture(path: String, filter: TextureFilter = TextureFilter.Linear) = {
    val tex = new Texture(Gdx.files.internal(path))
    tex.setFilter(filter, filter)
    managedTextures += tex
    tex
  }

  def textureRegion(path: String) = new TextureRegion(texture(path))
  def sprite(path: String) = new Sprite(texture(path))
  def sprite(tex: Texture, position: Vector2, width: Int, height: Int) = {
      val s = new Sprite(tex, position.x.toInt, position.y.toInt, height, width)
      s.setOriginCenter()
      s
  }

  private val regionCache = AnyRefMap.empty[String, TextureRegion]
  private val spriteCache = AnyRefMap.empty[String, Sprite]

  def atlasOption: Option[TextureAtlas] = None

  def atlas = atlasOption.get

  def atlasRegion(name: String) = {
    regionCache.getOrElseUpdate(name, atlas.findRegion(name))
  }

  implicit def atlasSprite(name: String): Sprite = {
    new Sprite(spriteCache.getOrElseUpdate(name, atlas.createSprite(name)))
  }

  implicit def stringToFileHandle(path: String) = {
    Gdx.files.internal(path)
  }

  def spriteTable(tex: Texture, position: Vector2, width: Int, height: Int, columns: Int, rows: Int = 1, spacingX: Int = 1, spacingY: Int = 1) = {
    for (column <- (0 until columns); row <- (0 until rows)) yield {
      val offset = new Vector2(column * (width + spacingX), row * (height + spacingY))
      sprite(tex, position + offset, height, width)
    }
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

  def managedFont(path: String) = {
    val font = new BitmapFont(path)
    font.getData.markupEnabled = true
    managedFonts += font
    font
  }

  def dispose() = {
    managedFonts.foreach(_.dispose())
    managedTextures.foreach(_.dispose())

    atlasOption.foreach(_.dispose())
  }
}