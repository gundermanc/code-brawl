package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.Main.Troop;
import java.util.ArrayList;


public class Player1 extends Player{

        private double dist(double x1, double y1, double x2, double y2){
            return Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) );
        }
        
        private void run(Troop currentTroop, ArrayList<Troop> e)
        {
            double min = currentTroop.getRadius();
            try{
            Troop mint = e.get(0);
            for (Troop t: e)
            {
                if (dist(currentTroop.getX(),currentTroop.getY(),t.getX(),t.getY())<min) 
                {min = dist(currentTroop.getX(),currentTroop.getY(),t.getX(),t.getY());mint = t;}
            }
            currentTroop.walk(currentTroop.getX()-mint.getX(),currentTroop.getY()-mint.getY());
            }
            catch (Exception ex)
            {
            	return;
            }
        }
        
        private void fight(Troop currentTroop, ArrayList<Troop> e)
        {
            try
            {
        	int min = Troop.MAX_HEALTH+1;
            Troop mint = e.get(0);
            for (Troop t: e)
            {
                if (t.getHealth()<min) {min = t.getHealth();mint = t;}
            }
            currentTroop.shoot(mint);
            }
            catch(Exception ex)
            {
            	return;
            }
        }
        public void tick(Troop troop,EnvironmentInfo env)
        {
            int x = (int) troop.getX();
            int y = (int) troop.getY();
            int tEH = 0;
            int tFH = 0;
            int borderX = (int)env.getWidth();
            int borderY = (int)env.getHeight();
            ArrayList<Troop> visible = troop.look();
            ArrayList<Troop> enemy = new ArrayList();
            ArrayList<Troop> friend = new ArrayList();
            if (visible.size()==0) {troop.walk(-(x-borderX/2),-(y-borderY/2));return;}
            for (Troop t : visible)
            {
                if (t.getPlayerID()!=troop.getPlayerID())
                {
                    enemy.add(t);
                    tEH += t.getHealth();
                }
                else
                {
                    friend.add(t);
                    tFH += t.getHealth();
                }
            }
           if ((tEH-tFH)<(enemy.size()-friend.size())) run(troop,enemy); else fight(troop,enemy);
        }
        
}
