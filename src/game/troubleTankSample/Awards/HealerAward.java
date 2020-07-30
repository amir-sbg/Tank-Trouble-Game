package game.troubleTankSample.Awards;

import game.troubleTankSample.Tank;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class HealerAward extends Award{

    public HealerAward(){
        super("healAward");
        this.awardsQuantity= (float) 1.1;
        try {
            this.awardsImage= ImageIO.read(new File("./resources/awards resources/healer.png"));
        } catch (IOException e) {
            System.err.println("Image Does Not Exist!");
        }
    }

    @Override
    public void doAction(Tank tank) {

      tank.setHealth((int) (awardsQuantity*tank.getHealth()));
    }

}
