package game.troubleTankSample.Awards;

public class HealerAward extends Award{

    public HealerAward(){
        super("healAward");
        this.awardsQuantity= (float) 1.1;
    }

    @Override
    public void doAction(/*Tank tank*/) {
        //TODO
      //  tank.setHeal(awardsQuantity*tank.getHeal());
    }

}
