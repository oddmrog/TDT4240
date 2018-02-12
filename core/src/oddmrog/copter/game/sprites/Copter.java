package oddmrog.copter.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import oddmrog.copter.game.CopterGame;
import oddmrog.copter.game.mvc.Model;

/**
 * Created by oddmrog on 23.01.18.
 */

public class Copter {

    private Vector3 position;
    private Vector3 velocity;
    private boolean isFlipped;
    private Animation copterAnimation;
    private Array<Texture> textureArray;
    private Random rand;
    private Rectangle bounds;
    private String name;

    public Copter(String name){
        textureArray = new Array<Texture>();
        textureArray.add(new Texture("heli1.png"));
        textureArray.add(new Texture("heli2.png"));
        textureArray.add(new Texture("heli3.png"));
        textureArray.add(new Texture("heli4.png"));
        rand = new Random();
        this.name = name;

        int maxX = Model.WIDTH - 162;
        int maxY = Model.HEIGHT - 65;
        int x = rand.nextInt(maxX);
        int y = rand.nextInt(maxY);
        bounds = new Rectangle(x, y, textureArray.get(0).getWidth(), textureArray.get(0).getHeight());

        position = new Vector3(x, y, 0);
        velocity = new Vector3(rand.nextInt(7500) - 2500 , rand.nextInt(2000)-1000, 0);
        isFlipped = false;
        copterAnimation = new Animation(textureArray, textureArray.size, 0.4f);
    }

    public void update(float dt){
        copterAnimation.update(dt);
        velocity.scl(dt);
        isWallCollision();
        position.add(velocity.x * dt, velocity.y * dt,0);
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public void isWallCollision(){
        if((bounds.x + bounds.width) > Model.WIDTH || bounds.x < 0){
            setVelocity(-velocity.x, velocity.y, 0);
            bounce();
        }

        if(bounds.y > (Model.HEIGHT - bounds.height)|| bounds.y < 0){
            setVelocity(velocity.x, -velocity.y, 0);
        }

    }

    public void setVelocity(float x,float y,float z){
        velocity.set(x, y, z);
    }

    public void bounce(){
        if(velocity.x < 0)
            isFlipped = true;
        else
            isFlipped = false;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }


    public Texture getTexture(){
        return copterAnimation.getFrame();
    }

    public Vector3 getPosition(){
        return position;
    }

    public boolean isFlipped(){
        return isFlipped;
    }

    public void turn(){
        velocity.rotate(Vector3.Z, 45);
        System.out.println("height of texture:" + bounds.height);
        System.out.println("width of texture:" + bounds.width);
    }


}
