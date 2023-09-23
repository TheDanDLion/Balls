package balls.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import balls.BallsInitializer;

public class TennisBall extends AbstractBallRelic implements ClickableRelic {

    private static final String NAME = TennisBall.class.getSimpleName();;
    public static final String RELIC_ID = BallsInitializer.makeID(NAME);

    public TennisBall() {
        super(RELIC_ID, NAME, RelicTier.COMMON, LandingSound.FLAT);
        this.grayscale = false;
        this.counter = 0;
    }

    @Override
    public void onRightClick() {
        if (!this.grayscale) {
            flash();
            usedUp();
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.counter));
        }
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        this.counter++;
    }
}
