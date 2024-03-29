package balls.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import balls.BallsInitializer;

public class GolfBall extends AbstractBallRelic {

    private static final String NAME = GolfBall.class.getSimpleName();
    public static final String RELIC_ID = BallsInitializer.makeID(NAME);

    private static final int HOLE_IN_ONE = 25;
    private static final int EAGLE = 20;
    private static final int BIRDIE = 15;
    private static final int PAR = 10;
    private static final int BOGEY = 5;

    public GolfBall() {
        super(RELIC_ID, NAME, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onVictory() {
        int turnNumber = GameActionManager.turn;
        int golfScore = turnNumber - AbstractDungeon.actNum;
        int goldToGain = 0;
        if (turnNumber == 1) {
            goldToGain = HOLE_IN_ONE;
        } else if (golfScore == -2) {
            goldToGain = EAGLE;
        } else if (golfScore == -1) {
            goldToGain = BIRDIE;
        } else if (golfScore == 0) {
            goldToGain = PAR;
        } else if (golfScore == 1) {
            goldToGain = BOGEY;
        }
        if (goldToGain > 0) {
            this.flash();
            AbstractDungeon.getCurrRoom().addGoldToRewards(goldToGain);
        }
    }
}
