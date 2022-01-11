public class Positions{
	private boolean ball;
	private boolean player;
	private boolean error;
	private int number;
	private int spot;
	private String icon;
	//public static final String ANSI_BOLD = "\u001b[1m";
	public static final String ANSI_PURPLE = "\033[1;35m";
	public static final String ANSI_BLACK = "\033[1;30m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\033[1;34m";  //\u001B[34m
	public static final String ANSI_RED = "\033[1;31m"; //\u001B[31m
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";


	public Positions(int number, boolean player){
		System.out.print(ANSI_RESET);
		this.error = false;
		this.player = player;
		this.number = number;
		this.spot = 1;
		this.ball = false;
		//this.icon = "🙍‍♂️";

		if(player){
			if(number <= 10){
				this.icon = ANSI_BLUE + "0" + (number-1) + ANSI_WHITE;
			}else{
				this.icon = ANSI_BLUE + "" + (number -1) + ANSI_WHITE;
			}
		}else{
			this.icon = ANSI_RED + "X" + ANSI_WHITE;
		}
	}

	public void setError(int num){
		if(num == number){
			this.icon = ANSI_BLACK + "X" + ANSI_WHITE;
			this.error = true;
		}else{
			this.icon = ANSI_RED + "X" + ANSI_WHITE;
			this.error = false;
		}
	}

	public boolean getError(){
		return error;
	}

	public int getNumber(){
		return number;
	}

	public int getSpot(){
		return spot;
	}

	public boolean getBall(){
		return ball;
	}

	public String getIcon(){
		return ANSI_BLUE + this.icon + ANSI_WHITE;
	}

	public void setSpot(int spot){
		this.spot = spot;
	}

	public void setBall(boolean ball){
		this.ball = ball;
		if(ball){
			this.icon = "⚽️";
		}else{
			if(number <= 10){
				this.icon = "0" + (number-1);
			}else{
				this.icon = "" + (number -1);
			}
		}
	}

	public void setIcon(){
		this.icon = icon;
	}
}