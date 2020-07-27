package game.troubleTankSample.Awards;

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
            e.printStackTrace();
        }
    }

    @Override
    public void doAction() {

       MakeTimeLap makeTimeLap=new MakeTimeLap();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //todo
        //tank.turnLaserON();
        executorService.execute(makeTimeLap);

    }

    class MakeTimeLap implements Runnable{

        @Override
        public void run(/*Tank tank*/) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //todo
            //tank.turnLaserOFF();

        }
    }
}
