package oddmrog.copter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Observable;
import java.util.Observer;

import oddmrog.copter.game.mvc.Model;
import oddmrog.copter.game.mvc.View;
import oddmrog.copter.game.sprites.Copter;
import oddmrog.copter.game.states.PlayState;

//public class CopterGame extends ApplicationAdapter {
//	private SpriteBatch sb;
//	private PlayState playstate;
//	public static final int WIDTH = 640;
//	public static final int HEIGHT = 480;
//	private Music music;
//
//
//	@Override
//	public void create () {
//		sb = new SpriteBatch();
//		playstate = PlayState.getInstance();
//		music = Gdx.audio.newMusic(Gdx.files.internal("roflcopter.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.1f);
//		music.play();
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(255, 0, 254, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		playstate.update(Gdx.graphics.getDeltaTime());
//		playstate.render(sb);
//	}
//
//	@Override
//	public void dispose () {
//		sb.dispose();
//	}
//}