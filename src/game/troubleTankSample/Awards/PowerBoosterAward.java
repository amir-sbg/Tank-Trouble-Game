package game.troubleTankSample.Awards;

public class PowerBoosterAward extends Award {
    public PowerBoosterAward() {
        super("powerAward");
        int i = (int) Math.random();
        if (i % 2 == 0) {
            this.awardsType = "powerAward2X";
            this.awardsQuantity = 2;
        } else {
            this.awardsType = "powerAward3X";
            this.awardsQuantity = 3;
        }
    }

    @Override
    public void doAction(/*Tank tank*/) {
        //todo
        //tank.setPower(tank.getPower()*awardsQuantity);
    }
}
