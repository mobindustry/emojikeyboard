package net.mobindustry.emojilib.emoji;

public class DpCalculator {

    public final float density;

    public DpCalculator(float density) {
        this.density = density;
    }

    public int dp(float value) {
        return (int) Math.ceil(density * value);
    }
}
