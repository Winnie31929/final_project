package project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.sound.Sounds;
import javafx.scene.effect.ColorAdjust;

public class project extends Application{
	
	@Override
	
	public void start(Stage primaryStage) {

		//create a pane
		Pane pane=new Pane();

		//判斷死亡聲音不要重複播放
		Flag.dead_sound = false;

		//右下角是否播放背景音樂
		sound.playloop(Sounds.BGM);
		Rectangle music=new Rectangle(570, 470, 25, 25);
		Image volume = new Image("/project/resources/image/volume.png");
		ImagePattern iv_volume = new ImagePattern(volume);
		Image mute = new Image("/project/resources/image/mute.png");
		ImagePattern iv_mute = new ImagePattern(mute);
		music.setFill(iv_volume);
		music.setOnMouseClicked(e->{
			if(music.getFill()==iv_volume)
			{
				sound.stoploop(Sounds.BGM);
				music.setFill(iv_mute);
				music.setX(565);
			}
			else
			{
				sound.playloop(Sounds.BGM);
				music.setFill(iv_volume);
				music.setX(570);
			}

		});

		//背景顏色切換
		ColorAdjust colorAdjust=new ColorAdjust();

		//背景
		Image background = new Image("/project/resources/image/blueback.jpg");
		ImageView iv_background = new ImageView(background);
		iv_background.setFitWidth(400);
		iv_background.setFitHeight(500);
		pane.getChildren().add(iv_background);
		iv_background.setEffect(colorAdjust);
		
		//顏色庫
		Color red = Color.MINTCREAM;
		Color orange = Color.ORANGE;
		Color yellow = Color.YELLOW;
		Color green = Color.GREEN;
		Color blue = Color.BLUE;
		Color ingigo = Color.INDIGO;
		Color purple = Color.PURPLE;
		Color pink = Color.PINK;
		Color gold = Color.GOLD;
		Color silver = Color.SILVER;
		Color skyblue = Color.SKYBLUE; 

        //天花板尖刺和尖刺板子
		Image Ceiling = new Image("/project/resources/image/Ceiling.png");
		Image Nails = new Image("/project/resources/image/Nails.png");
		ImageView iv_ceiling = new ImageView(Ceiling);
		ImagePattern ip_nails = new ImagePattern(Nails);
		pane.getChildren().add(iv_ceiling);

		//松鼠角色(有些在底下全域)
		Rectangle r_squirrel = new Rectangle(); 
		r_squirrel.setFill(role.ip_squirrel_stop);
		r_squirrel.setWidth(40);
		r_squirrel.setHeight(40);
		r_squirrel.setLayoutX(90);
		r_squirrel.setLayoutY(2000d/11-30);//一開始在第四快板子上
		
		
		//遊戲資訊視窗(包括標題，層數和重新開始按鈕，設定每11塊板子一層)
		Rectangle r_info=new Rectangle(400,0,200,500);
		r_info.setFill(Color.ALICEBLUE);
		Rectangle r_button=new Rectangle(460,442,80,30);//重新開始紐
		r_button.setStroke(Color.GREEN);
		r_button.setFill(Color.WHITE);
		Rectangle r_start=new Rectangle(460,410,80,30);//開始紐
		r_start.setStroke(Color.BLUE);
		r_start.setFill(Color.WHITE);
		Text title=new Text(420,50,"松鼠下樓梯");


		Text rule=new Text(430,90,"       遊戲說明\n\n頭上尖刺步步逼近\n操縱松鼠往下逃吧\n\nA鍵->角色往左移動\nD鍵->角色往右移動"
				+ "\n\n小心下面來的尖刺\n碰到尖刺及墜落地\n底即遊戲結束\n\n你能下到第幾層呢?");
		rule.setFont(Font.font(15));
		Text t_layer=new Text(460,348,"現在層數: 1");
		t_layer.setFont(Font.font(15));
		Text t_toplayer=new Text(460,378,"最高層數: 1");
		Text t_start=new Text(485, 428,"開始");
		Text t_start2=new Text(485, 428,"開始");
		Text t_pause=new Text(485, 428,"暫停");
		Text t_restart=new Text(468,460,"重新開始");

		//字體
		//Cubic_11_1.010_R.ttf
		Font font = Font.loadFont("file:src/project/resources/fonts/Cubic_11_1.010_R.ttf", 15);
		Font font2 = Font.loadFont("file:src/project/resources/fonts/Cubic_11_1.010_R.ttf", 30);
		title.setFont(font2);
		title.setFill(Color.MEDIUMBLUE);
		title.setStroke(Color.GOLD);
		rule.setFont(font);
		t_layer.setFont(font);
		t_start.setFont(font);
		t_start2.setFont(font);
		t_pause.setFont(font);
		t_restart.setFont(font);
		t_layer.setFont(font);
		t_toplayer.setFont(font);

		pane.getChildren().addAll(r_info,r_start,title,t_layer,t_start,rule,t_toplayer, music);

		//讓層數資料更新，隨層數切換顏色
		Timeline changeinfo=new Timeline(new
				KeyFrame(Duration.millis(100),e->{
					switch((Layer.layer/11)%11)
					{
						case 0:
							double hue0 = map( red.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue0); 
							iv_background.setEffect(colorAdjust);
							break;
						case 1:
							double hue1 = map( orange.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue1); 
							iv_background.setEffect(colorAdjust);
							break;
						case 2:
							double hue2 = map( yellow.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue2); 
							iv_background.setEffect(colorAdjust);
							break;
						case 3:
							double hue3 = map( green.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue3); 
							iv_background.setEffect(colorAdjust);
							break;
						case 4:
							double hue4 = map( blue.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue4); 
							iv_background.setEffect(colorAdjust);
							break;
						case 5:
							double hue5 = map( ingigo.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue5); 
							iv_background.setEffect(colorAdjust);
							break;
						case 6:
							double hue6 = map( purple.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue6); 
							iv_background.setEffect(colorAdjust);
							break;
						case 7:
							double hue7 = map( pink.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue7); 
							iv_background.setEffect(colorAdjust);
							break;
						case 8:
							double hue8 = map( gold.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue8); 
							iv_background.setEffect(colorAdjust);
							break;
						case 9:
							double hue9 = map( silver.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue9); 
							iv_background.setEffect(colorAdjust);
							break;
						case 10:
							double hue10 = map( skyblue.getHue(), 0, 360, -1, 1);
							colorAdjust.setHue(hue10); 
							iv_background.setEffect(colorAdjust);
							break;
					}
					t_layer.setText("現在層數: "+((Layer.layer/11)+1));
					t_toplayer.setText("最高層數: "+((Layer.toplayer/11)+1));
				}));
		changeinfo.setCycleCount(Timeline.INDEFINITE);
		changeinfo.play();
		
		//create n rectangles
		int n=10;
		Rectangle [] rs=new Rectangle[n+1];
		Rectangle start=new Rectangle(80,500d/11,60,10);
		Image Wood = new Image("/project/resources/image/wood.jpg");
		ImagePattern ip_normal = new ImagePattern(Wood);
		start.setFill(ip_normal);
		rs[0]=start;
		pane.getChildren().add(start);
		for(int i=1;i<n;i++) {
			Rectangle r=new Rectangle((int)(Math.random()*340),500d/11*(i+1),60,10);
			if(i==3) {//一開始松鼠在的固定板子
				r.setX(80);
			}
			r.setFill(ip_normal);
			rs[i]=r;
			pane.getChildren().add(r);
		}
        Rectangle danger=new Rectangle((int)Math.random()*340,500,60,10);
		danger.setFill(ip_nails);
		rs[10] = danger;
		pane.getChildren().add(danger);

		/*在3,4,5層數會把指定板子變尖刺*/
		EventHandler <ActionEvent> thirdhandler= (e ->{
			if(Layer.layer>=44 && rs[3].getY()==500) {
				rs[3].setFill(ip_nails);
			}
			else if(Layer.layer>=33 && rs[7].getY()==500) {
				rs[7].setFill(ip_nails);
			}
			else if(Layer.layer>=22 && rs[1].getY()==500) {
				rs[1].setFill(ip_nails);
        	}
		});
		
		Timeline difficulties=new Timeline(new KeyFrame(Duration.millis(1),thirdhandler));
		difficulties.setCycleCount(Timeline.INDEFINITE);
		difficulties.play();
		Flag.first_start=true;

		t_start.setOnMouseClicked(e->{//按到start開始遊戲
			registerKeyboardEventHandler(r_squirrel);//上下左右改成函數
			r_squirrel.requestFocus();
			pane.getChildren().remove(t_start);
			if(Flag.first_start) {
				for(int i=0;i<n+1;i++) {
					moveRectangle(rs[i],r_squirrel);
				}
				pane.getChildren().addAll(r_button,t_restart,t_pause);
			}
			else {
				pane.getChildren().add(t_pause);
			}
			Flag.first_start=false;
			Flag.pause=false;
			
		});
		
		
		t_pause.setOnMouseClicked(e->{
			pane.getChildren().remove(t_pause);
			pane.getChildren().add(t_start);
			Flag.pause=true;
		});
		
		t_restart.setOnMouseClicked(e->{//按到restart重新開始
			Flag.dead_sound = false;
			if(r_start.getParent()==null) {//讓遊戲進行中或暫停時能按restart
				pane.getChildren().add(r_start);
				pane.getChildren().add(t_pause);
			}
			else {
				if(t_start.getParent()==pane) {
					pane.getChildren().remove(t_start);
					pane.getChildren().add(t_pause);
				}
			}
			
			r_squirrel.setLayoutX(90);			
			r_squirrel.setLayoutY(2000d/11-30);

			for(int i=0;i<n+1;i++) {
				rs[i].setY(500d/11*(i+1));
				if(i==10) {
					rs[i].setFill(ip_nails);
				}
				else {
					rs[i].setFill(ip_normal);
				}
				if(i==3) {
					rs[i].setX(80);
				}
				else {
					rs[i].setX((int)(Math.random()*340));
				}
			}

			Layer.layer=0;//重設layer為0
			registerKeyboardEventHandler(r_squirrel);
			r_squirrel.requestFocus();
			r_squirrel.setFill(role.ip_squirrel_stop);
			Flag.pause=false;
			Flag.stop=false;
		});
		
		//add to pane
		pane.getChildren().add(r_squirrel);
	
		//create a handler 讓松鼠碰到板子不會掉下去
		EventHandler <ActionEvent> eventhandler= (e ->{
			
			Flag.touchGround=false;
			int index=0;
			if(!Flag.pause) {
				for(int i=0;i<n+1;i++) {
				//松鼠碰到地板
					if((r_squirrel.getLayoutX()>=rs[i].getX()-20 && r_squirrel.getLayoutX()<=rs[i].getX()+rs[i].getWidth()-20)
						&& r_squirrel.getLayoutY()>=rs[i].getY()-30 && r_squirrel.getLayoutY()<=rs[i].getY()-30+1) {
						Flag.touchGround=true;
						index=i;
					}
				}
				
				if(r_squirrel.getLayoutY()>=490 || r_squirrel.getLayoutY()<=10 ) {//掉到最下面或碰到頂部失敗
					if(r_squirrel.getFill()==role.ip_squirrel_stop||r_squirrel.getFill()==role.ip_squirrel_run) r_squirrel.setFill(role.ip_dead);
					else r_squirrel.setFill(role.ip_dead2);
					if(Flag.dead_sound==false)
					{
						sound.play(Sounds.UHHH);
						Flag.dead_sound=true;
					}
					Flag.stop=true;
					pane.getChildren().remove(t_pause);
					pane.getChildren().remove(r_start);
				}
				else if(Flag.touchGround) {//碰到尖刺松鼠不見
					if(rs[10].getFill().equals(ip_nails) && index==10 ) {
						if(r_squirrel.getFill()==role.ip_squirrel_stop||r_squirrel.getFill()==role.ip_squirrel_run) r_squirrel.setFill(role.ip_dead);
						else r_squirrel.setFill(role.ip_dead2);
						if(Flag.dead_sound==false)
						{
							sound.play(Sounds.UHHH);
							Flag.dead_sound=true;
						}
						Flag.stop=true;
						pane.getChildren().remove(t_pause);
						pane.getChildren().remove(r_start);
					}
					else if(rs[1].getFill().equals(ip_nails) && index==1 ) {
						if(r_squirrel.getFill()==role.ip_squirrel_stop||r_squirrel.getFill()==role.ip_squirrel_run) r_squirrel.setFill(role.ip_dead);
						else r_squirrel.setFill(role.ip_dead2);
						if(Flag.dead_sound==false)
						{
							sound.play(Sounds.UHHH);
							Flag.dead_sound=true;
						}
						Flag.stop=true;
						pane.getChildren().remove(t_pause);
						pane.getChildren().remove(r_start);
					}
					else if(rs[7].getFill().equals(ip_nails) && index==7 ) {
						if(r_squirrel.getFill()==role.ip_squirrel_stop||r_squirrel.getFill()==role.ip_squirrel_run) r_squirrel.setFill(role.ip_dead);
						else r_squirrel.setFill(role.ip_dead2);
						if(Flag.dead_sound==false)
						{
							sound.play(Sounds.UHHH);
							Flag.dead_sound=true;
						}						
						Flag.stop=true;
						pane.getChildren().remove(t_pause);
						pane.getChildren().remove(r_start);
					}
					else if(rs[3].getFill().equals(ip_nails) && index==3 ) {
						if(r_squirrel.getFill()==role.ip_squirrel_stop||r_squirrel.getFill()==role.ip_squirrel_run) r_squirrel.setFill(role.ip_dead);
						else r_squirrel.setFill(role.ip_dead2);
						if(Flag.dead_sound==false)
						{
							sound.play(Sounds.UHHH);
							Flag.dead_sound=true;
						}						
						Flag.stop=true;
						pane.getChildren().remove(t_pause);
						pane.getChildren().remove(r_start);
		        	}
					
				}
				else if(!Flag.touchGround ) {
					r_squirrel.setLayoutY(r_squirrel.getLayoutY()+0.2);
				}
				else {
					r_squirrel.setLayoutY(rs[index].getY() - 30);
				}
			
			}
		});
		
		
		//Time line
		Timeline animation=new Timeline(new
				KeyFrame(Duration.millis(1),eventhandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		//add pane to scene and stage
		Scene scene=new Scene(pane,400+200,500);

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/project/resources/image/squirrel.png"));
		primaryStage.setTitle("松鼠下樓梯");
		primaryStage.setResizable(false);
		primaryStage.show();
		r_squirrel.requestFocus();
		
		
	}
	
	//控制上下左右放進函式
	public void registerKeyboardEventHandler(Rectangle c) {

	    c.setOnKeyPressed(e -> {
	    	if((!Flag.pause) && (!Flag.stop)) {
		        switch (e.getCode()) {
		            case D:
						c.setFill(role.ip_squirrel_run2);
		                // Make sure the circle doesn't go beyond the right side of the screen
		                if (c.getLayoutX() <= 355) {
		                    c.setLayoutX(c.getLayoutX() + 10);
		                }
		                break;
		            case A:
						c.setFill(role.ip_squirrel_run);
		                // Make sure the circle doesn't go beyond the left side of the screen
		                if (c.getLayoutX() >= 10) {
		                    c.setLayoutX(c.getLayoutX() - 10);
		                }
		                break;
		            default:
		                break;
		        	}
		     }   
		   });
	
			
			c.setOnKeyReleased(e -> {
					if(e.getCode()==KeyCode.A) c.setFill(role.ip_squirrel_stop);
					else if(e.getCode()==KeyCode.D) c.setFill(role.ip_squirrel_stop2);
			});
	    
	    Timeline animation=new Timeline(new
				KeyFrame(Duration.millis(100)));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	
	//讓板子往上動的函式
	public static void moveRectangle(Rectangle r,Rectangle c) {
		
		EventHandler <ActionEvent> handler= (e ->{
			boolean moveWithRectangle=false;
			if (c.getLayoutX()>=r.getX()-20 && c.getLayoutX()<=r.getX()+r.getWidth()-20
				&& c.getLayoutY()>=r.getY()-30 && c.getLayoutY()<=r.getY()-30+1) {
				moveWithRectangle = true;
	        }
	        
			if(c.getLayoutY()>=490 || c.getLayoutY()<=10 || Flag.pause || Flag.stop) {
				//do nothing 圓掉到最下面或碰到頂部而停止
			}else if (moveWithRectangle) {
	        	r.setY(r.getY() - 1);
	        	c.setLayoutY(c.getLayoutY()-1);
	        } else if (r.getY() >= 0) {
	        	r.setY(r.getY() - 1);
	        } else {
	        	
	            r.setX((int) (Math.random() * 340));
	            r.setY(500);
	            moveWithRectangle = false;
				if(Layer.toplayer == Layer.layer) Layer.toplayer++;
	            Layer.layer++;
	        }
			
		});
		
		Timeline animation=new Timeline(new
				KeyFrame(Duration.millis(20),handler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		speedup(animation);
	}

	//板子隨時間加速
	public static void speedup(Timeline timeline) {
		double origin_time=timeline.getRate();
		EventHandler <ActionEvent> speedhandler= (e->{
			if(Layer.layer==0) {//重新開始後回到原速度
				timeline.setRate(origin_time);
			}
			timeline.setRate(timeline.getRate()+0.001);
		});
		
		Timeline speedUP=new Timeline(new
				KeyFrame(Duration.millis(10),speedhandler));
		speedUP.setCycleCount(Timeline.INDEFINITE);
		speedUP.play();
	}

	//顏色效果
	public static double map(double value, double start, double stop, double targetStart, double targetStop) {
        return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
   }

	public static void main(String[] args) {
		launch(args);
	}
}

//類似全域變數，計算層數
class Layer{
	static int layer=0;
	static int toplayer=0;
}

//同上，全域的角色資訊
class role{
	static Image Squirrel = new Image("/project/resources/image/squirrel.png");
	static Image Squirrel2 = new Image("/project/resources/image/squirrel2.png");
	static Image Squirrel_run = new Image("/project/resources/image/squirrel.gif");
	static Image Squirrel_run2 = new Image("/project/resources/image/squirrel2.gif");
	static Image dead = new Image("/project/resources/image/dead.png");
	static Image dead2 = new Image("/project/resources/image/dead2.png");
	static ImagePattern ip_squirrel_stop = new ImagePattern(Squirrel);
	static ImagePattern ip_squirrel_stop2 = new ImagePattern(Squirrel2);
	static ImagePattern ip_squirrel_run = new ImagePattern(Squirrel_run);
	static ImagePattern ip_squirrel_run2 = new ImagePattern(Squirrel_run2);
	static ImagePattern ip_dead = new ImagePattern(dead);
	static ImagePattern ip_dead2 = new ImagePattern(dead2);
}

//其他全域變數
class Flag{
	static boolean touchGround = false;
	static boolean stop=false;
	static boolean pause;
	static boolean first_start;
	static boolean dead_sound;
}