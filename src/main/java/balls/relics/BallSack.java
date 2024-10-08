package balls.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnRemoveCardFromMasterDeckRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import balls.BallsInitializer;
import balls.cards.TesticularTorsion;

public class BallSack extends AbstractBallRelic implements OnRemoveCardFromMasterDeckRelic {

    private static final String NAME = BallSack.class.getSimpleName();
    public static final String RELIC_ID = BallsInitializer.makeID(NAME);

    private AbstractRelic commonBall = null;
    private AbstractRelic uncommonBall = null;
    private AbstractRelic rareBall = null;

    private boolean opened = true;
    private boolean obtainNewCurse = false;

    public BallSack() {
        super(RELIC_ID, NAME, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.HEAVY);
    }

    public void onEquip() {
        AbstractBallRelic tmp;
        int idx = 0;

        while (commonBall == null) {
            idx = AbstractDungeon.relicRng.random(BallsInitializer.balls.size() - 1);
            tmp = BallsInitializer.balls.get(idx);
            if (tmp.tier == RelicTier.COMMON)
                commonBall = tmp.makeCopy();
        }

        while (uncommonBall == null) {
            idx = AbstractDungeon.relicRng.random(BallsInitializer.balls.size() - 1);
            tmp = BallsInitializer.balls.get(idx);
            if (tmp.tier == RelicTier.UNCOMMON)
                uncommonBall = tmp.makeCopy();
        }

        while (rareBall == null) {
            idx = AbstractDungeon.relicRng.random(BallsInitializer.balls.size() - 1);
            tmp = BallsInitializer.balls.get(idx);
            if (tmp.tier == RelicTier.RARE)
                rareBall = tmp.makeCopy();
        }

        opened = false;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        TesticularTorsion card = new TesticularTorsion();
        group.addToBottom(card);
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, "I CAST TESTICULAR TORSION");
    }

    public void update() {
        super.update();
        if (!this.opened && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(commonBall));
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(uncommonBall));
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(rareBall));
            AbstractDungeon.combatRewardScreen.positionRewards();
            this.opened = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }

        // need to check this in update() or you get ConcurrentModificationException
        if (this.obtainNewCurse) {
            this.obtainNewCurse = false;
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            AbstractCard curse = AbstractDungeon.returnRandomCurse();
            group.addToBottom(curse);
            AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, "Cured at a cost...");
        }
    }

    @Override
    public void onRemoveCardFromMasterDeck(AbstractCard card) {
        if (card.cardID.equals(TesticularTorsion.CARD_ID)) {
            this.obtainNewCurse = true;
        }
    }
}
