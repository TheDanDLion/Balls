package balls.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import balls.BallsInitializer;

public class RiceBall extends AbstractBallRelic {

    private static final String NAME = RiceBall.class.getSimpleName();
    public static final String RELIC_ID = BallsInitializer.makeID(NAME);

    public RiceBall() {
        super(RELIC_ID, NAME, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(12, true);
    }
}
