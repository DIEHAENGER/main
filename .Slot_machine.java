// Slot_machine.java
import ch.aplu.ev3.*;
import java.awt.Color;
class Slot_machine  //rip in peperonies 2015-2015 †
{            
	Slot_machine()
	{
		LegoRobot robot = new LegoRobot();
		Motor slot1 = new Motor(MotorPort.A);
		Motor slot2 = new Motor(MotorPort.B);
		Motor slot3 = new Motor(MotorPort.C);
		Motor csmotor = new Motor(MotorPort.D);
		ColorSensor cs = new ColorSensor(SensorPort.S4);
		TouchSensor ts = new TouchSensor(SensorPort.S2);
		TouchSensor stop = new TouchSensor(SensorPort.S3);
		robot.addPart(slot1);
		robot.addPart(slot2);
		robot.addPart(slot3);    
		robot.addPart(csmotor);
		robot.addPart(cs);
		robot.addPart(ts);
		robot.addPart(stop);
		
		int a = 1;
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		while(!robot.isEscapeHit())
		{
			if(robot.isEnterHit())
			{
				slot1.forward();
				slot2.forward();
				slot3.forward();
				slot1.setSpeed(100);
				slot2.setSpeed(100);
				slot3.setSpeed(100);  
			}
			while(a<4)
			{
				if(stop.isPressed())
				{
					if(a==3)
					{
						slot3.stop();
						a=a+1;
					}
					if(a==2)
					{
						slot2.stop();
						a=a+1;
						slot3.setSpeed(100*a);
					}
					if(a==1)
					{
						slot1.stop();
						a=a+1;
						slot2.setSpeed(100*a);
						slot3.setSpeed(100*a);
					}
					
				}
			}
			while(a==4)
			{
				if (!ts.isPressed())
				{
					csmotor.backward();  
				}
				
				csmotor.forward();
				Tools.delay(150);
				csmotor.stop();
				c1 = cs.getColorID();
				csmotor.forward();
				Tools.delay(150);
				csmotor.stop();
				c2 = cs.getColorID();
				csmotor.forward();
				Tools.delay(150);
				csmotor.stop();
				c3 = cs.getColorID();  
				a = a+1;


			}
			while(a==5)  
			{
				if (c1==c2==c3)
				{
					drawString(Jackpot!,10,10);
					playTone(261,25);
					playTone(293,25);
					playTone(329,150);
				}
				else
				{
					if (c1==c2||c1==c3||c2==c3)
					{
						drawString(Geldzurück,10,10);
						
						playTone(293,25);
						
					}
					else
					{
						drawString(Verloren,10,10);
						playTone(261,25);
						
						
					}
				}
				
				
			
				
			}
		}


		robot.exit();
	}
	public static void main(String[] args)
	{
		new Slot_machine();
	}
}

