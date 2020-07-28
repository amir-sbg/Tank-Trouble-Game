package game.troubleTankSample.Awards;

import game.troubleTankSample.Tank;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShieldAward extends Award{
    private  int lastHealty;
    public ShieldAward(){
        super("shieldAward");
        this.awardsQuantity=15;
        try {
            this.awardsImage= ImageIO.read(new File("./resources/awards resources/shield.png"));
        } catch (IOException e) {
            System.err.println("Image Does Not Exist!");
        }
    }

    @Override
    public void doAction(Tank tank) {
        MakeTimeLap makeTimeLap=new MakeTimeLap(tank);
        ExecutorService executorService = Executors.newCachedThreadPool();
lastHealty=tank.getHealth();
       tank.setShield(true);
        executorService.execute(makeTimeLap);
    }
    class MakeTimeLap implements Runnable{
        private Tank tank;
        MakeTimeLap(Tank tank){
            this.tank=tank;
        }
        @Override
        public void run() {
            try {
                Thread.sleep((int)awardsQuantity *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tank.setShield(false);
            tank.setHealth(lastHealty);

        }
    }
}
