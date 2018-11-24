package game.gamestate;

import game.entities.Player;
import static game.gamestate.GameState.xOffset;
import static game.gamestate.GameState.yOffset;
import game.mapping.Map;
import game.objects.Block;
import game.resources.Images;
import java.awt.Graphics;

public class IceState extends GameState{
    
    private Player player;
    private Map map;
    
    public IceState(GameStateManager gsm){
        super(gsm);
    }

    public void init() {
        player = new Player(30,30);
        map = new Map ("/Maps/map3.map");
        
        xOffset = -350;
        yOffset = -100;
    }
    
    public void tick() {
        player.tick(map.getBlocks());
        
        if(yOffset >= 400){
            heartLeft -= 1;
            if(heartLeft == 0) gsm.states.push(new DeathState(gsm));
            yOffset = -200;
            xOffset = -350;
        } else if (xOffset >= Map.getWidth() * Block.getBlockSize()-(2*Block.blockSize + Block.blockSize/2) - 385){
            gsm.states.push(new OptionState3(gsm));
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(Images.blocks[10], 0,0, 900,600, null);
        map.draw(g);
        player.draw(g);
    }
    
    public void keyPressed(int k) {
        player.keyPressed(k);
    }
    
    public void keyReleased(int k) {
        player.keyReleased(k);
    }
}
