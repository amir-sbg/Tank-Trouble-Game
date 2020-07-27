package game.troubleTankSample.Awards;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PowerBoosterAward extends Award {
    public PowerBoosterAward() {
        super("powerAward");
        int i = (int) Math.random();
        if (i % 2 == 0) {
            this.awardsType = "powerAward2X";
            this.awardsQuantity = 2;
            try {
                this.awardsImage= ImageIO.read(new File("./resources/awards resources/2Xpower.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.awardsType = "powerAward3X";
            this.awardsQuantity = 3;
            try {
                this.awardsImage=ImageIO.read(new File("./resources/awards resources/3Xpower.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doAction(/*Tank tank*/) {
        //todo
        //tank.setPower(tank.getPower()*awardsQuantity);
    }
}
