package game.troubleTankSample.Awards;

import game.troubleTankSample.Tank;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LaserAward extends Award {
    public LaserAward(){
        super("guardAward");
        this.awardsQuantity=3;
        try {
            this.awardsImage= ImageIO.read(new File("./resources/awards resources/laser.png"));
        } catch (IOException e) {
            System.err.println("Image Does Not Exist!");
        }
    }

    @Override
    public void doAction(Tank tank) {

       MakeTimeLap makeTimeLap=new MakeTimeLap(tank);
        ExecutorService executorService = Executors.newCachedThreadPool();

        tank.setLaser(true);
        executorService.execute(makeTimeLap);

    }

    class MakeTimeLap implements Runnable{
        private Tank tank;
        public MakeTimeLap(Tank tank){
            this.tank=tank;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tank.setLaser(true);

        }
    }
}
