package oddmrog.copter.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import oddmrog.copter.game.CopterGame;
import oddmrog.copter.game.mvc.Model;
import oddmrog.copter.game.sprites.Copter;

/**
 * Created by oddmrog on 23.01.18.
 */

public final class PlayState {

    private static final PlayState INSTANCE = new PlayState();
    private BitmapFont font;
    private Array<Copter> copters;

    private PlayState(){
        copters = new Array<Copter>();
        copters.add(new Copter("n1"), new Copter("n2"), new Copter("n3"));
        font = new BitmapFont();
    }

    public static PlayState getInstance(){
        return INSTANCE;
    }

    public void update(float dt){
        handleInput();
        for(Copter copter : copters){
            copter.update(dt);
            for (int i = 0; i < copters.size; i++ ){
                if(copter != copters.get(i))
                    overlapping(copter, copters.get(i));
            }
        }

    }


    public void overlapping(Copter c1, Copter c2){
        if(c1.getBounds().overlaps(c2.getBounds())){
            c1.setVelocity(-c1.getVelocity().x, -c1.getVelocity().y, 0);
            c2.setVelocity(-c2.getVelocity().x, -c2.getVelocity().y, 0);
            c1.bounce();
            c2.bounce();
        }


    }

    public void handleInput(){
        if(Gdx.input.justTouched()){
            for(Copter copter : copters)
                copter.turn();
        }
    }

    public void render(SpriteBatch sb){

//        sb.begin();
//        font.draw(sb, copters.get(0).getPosition().toString(), 25, Model.HEIGHT - 25);
//        for(Copter copter : copters) {
//            sb.draw(copter.getTextureRegion().getTexture(), copter.getPosition().x, copter.getPosition().y,
//                    copter.getTextureRegion().getRegionWidth(), copter.getTextureRegion().getRegionHeight(), 0, 0,
//                    copter.getTextureRegion().getRegionWidth(), copter.getTextureRegion().getRegionHeight(), !copter.isFlipped(), false);
//        }
//        sb.end();
    }
}
