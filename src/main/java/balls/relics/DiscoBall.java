package balls.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnSkipCardRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import balls.BallsInitializer;

public class DiscoBall extends AbstractBallRelic implements OnSkipCardRelic {

    private static final String NAME = DiscoBall.class.getSimpleName();
    public static final String RELIC_ID = BallsInitializer.makeID(NAME);

    public boolean canSkip;

    public DiscoBall() { // TODO: broken
        super(RELIC_ID, NAME, AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.CLINK);
        this.canSkip = false;
    }

    @Override
    public void onSkipCard(RewardItem item) {
        this.canSkip = true;
        this.flash();
        AbstractDungeon.getCurrRoom().rewards.remove(item);
    }

    @Override
    public void onSkipSingingBowl(RewardItem item) {}
}
