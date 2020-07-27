package game.troubleTankSample.Awards;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShieldAward extends Award{
    public ShieldAward(){
        super("shieldAward");
        this.awardsQuantity=15;
        try {
            this.awardsImage= ImageIO.read(new File("./resources/awards resources/shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doAction(/*Tank tank*/) {
        MakeTimeLap makeTimeLap=new MakeTimeLap();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //todo
        //tank.turnShieldON();
        executorService.execute(makeTimeLap);
    }
    class MakeTimeLap implements Runnable{

        @Override
        public void run(/*Tank tank*/) {
            try {
                Thread.sleep((int)awardsQuantity *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //todo
            //tank.turnShieldOFF();

        }
    }
}
