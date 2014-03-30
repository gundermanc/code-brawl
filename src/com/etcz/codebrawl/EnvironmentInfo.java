package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;

public class EnvironmentInfo
{
    private final double WIDTH = 1000.0;
    private final double HEIGHT = 600.0;
    //private Wall[] wall;

    public PowerUp[] powerUps;

    protected EnvironmentInfo(int numPlayers)
    {
        powerUps = new PowerUp[numPlayers*2];
        for (int i = 0; i<powerUps.length; i++)
        {
            powerUps[i] = new PowerUp();
        }
    }
    
    public PowerUp[] getPowerUpInfo()
    {
        return powerUps;
    }
    
    public double getWidth()
    {
        return this.WIDTH;
    }
    
    public double getHeight()
    {
        return this.HEIGHT;
    }
    ///////////////////////////////////////////
    public class PowerUp
    {
        private int x;
        private int y;
        private int power;
        private boolean picked = false;
        private PowerUp()
        {
            power = ((int)(Math.random()*4));
            x = (int) (Math.random() * getWidth());
            y = (int) (Math.random() * getHeight());
        }
        
        public int getPower()
        {
            return power;
        }
        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }
        protected void pick()
        {
            picked = true;
        }
        public boolean isPicked()
        {
            return picked;
        }
    }
    
}
