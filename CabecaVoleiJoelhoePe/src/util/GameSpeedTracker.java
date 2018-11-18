package util;

public class GameSpeedTracker
{
	static public double NANOS_IN_ONE_SECOND = 1e9;
	protected int ticksPerSecond;
	protected long previousNanotime;
	protected int countedTicks;
	protected int totalTicks;

	public void start()
	{
		previousNanotime = System.nanoTime();
		countedTicks = 0;
		ticksPerSecond = 0;
		totalTicks = 0;
	}

	public int countTick()
	{
		countedTicks++;
		totalTicks++;
		update();
		return totalTicks;
	}

	protected void update()
	{
		if (System.nanoTime() - previousNanotime > NANOS_IN_ONE_SECOND)
		{
			ticksPerSecond = countedTicks;
			countedTicks = 0;
			previousNanotime = System.nanoTime();
		}
	}

	public int getTPS()
	{
		return ticksPerSecond;
	}

	public int getTotalTicks()
	{
		return totalTicks;
	}
}
