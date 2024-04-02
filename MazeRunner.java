import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.GradientPaint;


public class MazeRunner extends JPanel implements KeyListener{

    JFrame frame;
    String[][] maze;
    Hero hero;
    int endR,endC;
    boolean in2D = true;
    int numMoves = 0;
    ArrayList<Wall> walls;
    public static void main(String[] args){
        MazeRunner m = new MazeRunner();

    }

    public MazeRunner(){
        frame = new JFrame();
        frame.add(this);

        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setMaze();
        frame.addKeyListener(this);

        frame.setVisible(true);


        
        
    }

    public void keyPressed(KeyEvent e){
        hero.move(e.getKeyCode());
        if(e.getKeyCode() == 32){
            in2D = !in2D;
        }
        if(!in2D)
            set3D();
            
        repaint();
    }

    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }

    public void set3D(){
        walls = new ArrayList<Wall>();

        for(int a=0;a<5;a++){
            leftWall(a);
            leftRect(a);
            rightWall(a);
            rightRect(a);
            ceiling(a);
            floor(a);
        }

        for(int a=0;a<5;a++){
            int r = hero.getR();
            int c = hero.getC();
            int dir = hero.getDir();

            switch(dir){
                case 'N':
                    try{
                        if(maze[r-a][c-1].equals("*"))
                        leftWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'S':
                    try{
                        if(maze[r+a][c+1].equals("*"))
                        leftWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'E':
                    try{
                        if(maze[r-1][c+a].equals("*"))
                        leftWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'W':
                    try{
                        if(maze[r+1][c-a].equals("*"))
                        leftWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;

            }

        }

        for(int a=4;a>=0;a--){
            int r = hero.getR();
            int c = hero.getC();
            int dir = hero.getDir();

            switch(dir){
                case 'N':
                    try{
                        if(maze[r-a][c+1].equals("*"))
                        rightWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'S':
                    try{
                        if(maze[r+a][c-1].equals("*"))
                        rightWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'E':
                    try{
                        if(maze[r+1][c+a].equals("*"))
                        rightWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'W':
                    try{
                        if(maze[r-1][c-a].equals("*"))
                        rightWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;

            }

        }

        for(int a=4;a>=0;a--){
            int r = hero.getR();
            int c = hero.getC();
            int dir = hero.getDir();




            switch(dir){
                case 'N':
                    try{
                        if(maze[r-a][c].equals("*"))
                            frontWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'S':
                    try{
                        if(maze[r+a][c].equals("*"))
                            frontWall(a);
                        if(r+a == 19 && c == 4){
                            endWall(a);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'E':
                    try{
                        if(maze[r][c+a].equals("*"))
                            frontWall(a);
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;
                case 'W':
                    try{
                        if(maze[r][c-a].equals("*")){
                            frontWall(a);
                            
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){

                    }
                    break;

            }

        }

        
            
    }

    public void leftWall(int a){
        int[] x = {200+50*a, 250+50*a, 250+50*a, 200+50*a};
        int[] y = {100+50*a, 150+50*a, 600-50*a, 650-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "leftWall", 50, null));
    }

    public void rightWall(int a){
        int[] x = {750-50*a, 700-50*a, 700-50*a, 750-50*a};
        int[] y = {100+50*a, 150+50*a, 600-50*a, 650-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "rightWall", 50, null));
    
    }


    public void leftRect(int a){
        int[] x = {200+50*a, 250+50*a, 250+50*a, 200+50*a};
        int[] y = {150+50*a, 150+50*a, 600-50*a, 600-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "leftRect", 50, null));
    }

    public void rightRect(int a){
        int[] x = {750-50*a, 700-50*a, 700-50*a, 750-50*a};
        int[] y = {150+50*a, 150+50*a, 600-50*a, 600-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "rightRect", 50, null));
    }

    public void ceiling(int a){
        int[] x = {200+50*a, 250+50*a, 700-50*a, 750-50*a};
        int[] y = {100+50*a, 150+50*a, 150+50*a, 100+50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "ceiling", 50, null));
    }

    public void floor(int a){
        int[] x = {200+50*a, 250+50*a, 700-50*a, 750-50*a};
        int[] y = {650-50*a, 600-50*a, 600-50*a, 650-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "floor", 50, null));
    }

    public void frontWall(int a){
        int[] x = {200+50*a, 750-50*a, 750-50*a, 200+50*a};
        int[] y = {100+50*a, 100+50*a, 650-50*a, 650-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "frontWall", 50, null));
    }

    public void endWall(int a){
        int[] x = {200+50*a, 750-50*a, 750-50*a, 200+50*a};
        int[] y = {100+50*a, 100+50*a, 650-50*a, 650-50*a};
        walls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "endWall", 50, null));
    }

    public class Wall{
        int[] x, y;
        int r, g, b;
        String type;
        int distance;
        Color color;

        public Wall(int[] x, int[] y, int r, int g, int b, String type, int distance, Color color){
            this.x = x;
            this.y = y;
            this.r = r;
            this.g = g;
            this.b = b;
            this.type = type;
            this.distance = distance;
            this.color = color;
        }
        public Polygon getPoly(){
            return new Polygon(x, y, 4);
        }

        public GradientPaint getPaint(){
            int endr = r - 50;
            int endg = g - 50;
            int endb = b - 50;
            if(r<0)
                r = 0;
            if(g<0)
                g = 0;
            if(b<0)
                b = 0;
            if(endr<0)
                endr = 0;
            if(endg<0)
                endg = 0;
            if(endb<0)
                endb = 0;
            Color startColor = new Color(r, g, b);
            Color endColor = new Color(endr, endg, endb);
    
            switch(type){
                case "rightWall":
                case "leftWall":
                    return new GradientPaint(x[0], y[0], startColor, x[1], y[0], endColor);
                case "endWall":
                    return new GradientPaint(x[0], y[0], new Color(125,110,24), x[1], y[0], new Color(255,215,0));
                    
            }
            System.out.println(type);
            return new GradientPaint(x[0], y[0], startColor, x[0], y[2], endColor);
            
        }
    
        public Color getColor(){
            return new Color(r, g, b);
        }

        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2=(Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,1500, 800);

        if(in2D){

            for(int r=0;r<maze.length;r++){
                for(int c=0;c<maze[r].length;c++){
                    if(maze[r][c].equals("*")){
                        g2.setColor(new Color(40, 117, 91));
                        g2.fillRect(c*20 + 50, r*20 + 50, 20, 20);
                    }
                    if(maze[r][c].equals(".")){
                        g2.setColor(Color.RED);
                        g2.fillRect(c*20 + 50, r*20 + 50, 20, 20);
                    }
                }
            }
            
            g2.setColor(new Color(194, 128, 118));
            g2.fillOval(hero.getC()*20 + 50, hero.getR()*20 + 50, 20, 20);

            //display number of moves
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 25));
            g2.drawString("Number of Moves - "+numMoves, 100, 500);

            //display text at end
            int r = hero.getR();
            int c = hero.getC();
            if(r == 19 && c == 4){
                g2.setColor(Color.WHITE);
                g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                g2.drawString("Great job on reaching the end of the maze!", 200, 650);
            }


        }
        else{
            for(Wall wall: walls){
                g2.setPaint(wall.getPaint());
                g2.fillPolygon(wall.getPoly());
                g2.setColor(new Color(204, 170, 165));
                g2.drawPolygon(wall.getPoly());
            }


            //Painting 5 x 5 mini map
            g2.setColor(new Color(40, 117, 91));
            int row = hero.getR();
            int col = hero.getC();
            for(int r=-5;r<=5;r++){
                for(int c=-5;c<=5;c++){
                    try{
                        if(maze[row+r][col+c].equals("*")){
                            g2.fillRect(c*20 + 1000, r*20 +350, 20, 20);
                        }
                    }catch(ArrayIndexOutOfBoundsException ee){
                        g2.fillRect(c*20 + 1000, r*20 +350, 20, 20);
                    }

                
                }
            }

            g2.setColor(Color.WHITE);
            g2.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
            g2.drawString("Mini Map", 900, 240);

            g2.setColor(new Color(194, 128, 118));
            g2.fillOval(1000, 350, 20, 20);


            //display number of moves
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
            g2.drawString("Number of Moves - "+numMoves, 300, 700);

            //display text at end
            int r = hero.getR();
            int c = hero.getC();
            if(r == 19 && c == 4){
                g2.setColor(Color.WHITE);
                g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                g2.drawString("It looks like you were lost for a while.", 300, 400);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                g2.drawString("Glad you were able to finally make it out.", 280, 430);
            }


        }
        

    }

    public void setMaze(){
        File file = new File("Maze.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            maze = new String[20][60];
            int r = 0;
            boolean first = true;
            while((text=reader.readLine()) != null){

                if(first){
                    String[] pieces = text.split(" ");
                    int row = Integer.parseInt(pieces[0]);
                    int col = Integer.parseInt(pieces[1]);
                    hero = new Hero(row, col, pieces[2].charAt(0));
                    endR = Integer.parseInt(pieces[3]);
                    endC = Integer.parseInt(pieces[4]);
                    first = false;

                    
                }
                else{
                    String[] pieces = text.split("");
                    maze[r] = pieces;
                    r++;
                }


                
            }
        }
        catch(IOException e){

        }
        
        for(int r=0;r<maze.length;r++){
            for(int c=0;c<maze[r].length;c++){
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }

    public class Hero{
        int r;
        int c;
        int dir;
        public Hero(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public int getR(){
            return this.r;
        }
        public int getC(){
            return this.c;
        }
        public int getDir(){
            return this.dir;
        }
        public void move(int key){
            switch(key){
                //turn left
                case 37:
                    switch(dir){
                        case 'E':
                            dir = 'N';
                            numMoves++;
                            break;
                        case 'N':
                            dir = 'W';
                            numMoves++;
                            break;
                        case 'W':
                            dir = 'S';
                            numMoves++;
                            break;
                        case 'S':
                            dir = 'E';
                            numMoves++;
                            break;
                    }
                    break;
                //forward
                case 38:
                    switch(dir){
                        case 'E':
                            try{
                                if(!maze[r][c+1].equals("*") && !maze[r][c+1].equals(".")){
                                    maze[r][c] = ".";
                                    c++;
                                    numMoves++;
                                }
                                

                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'N':
                            try{
                                if(!maze[r-1][c].equals("*") && !maze[r-1][c].equals(".")){
                                    maze[r][c] = ".";
                                    r--;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'W':
                            try{
                                if(!maze[r][c-1].equals("*") && !maze[r][c-1].equals(".")){
                                    maze[r][c] = ".";
                                    c--;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'S':
                            try{
                                if(!maze[r+1][c].equals("*") && !maze[r+1][c].equals(".")){
                                    maze[r][c] = ".";
                                    r++;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                    }
                    break;
                //turn right
                case 39:
                    switch(dir){
                        case 'W':
                            dir = 'N';
                            numMoves++;
                            break;
                        case 'N':
                            dir = 'E';
                            numMoves++;
                            break;
                        case 'E':
                            dir = 'S';
                            numMoves++;
                            break;
                        case 'S':
                            dir = 'W';
                            numMoves++;
                            break;
                    }
                    break;
                //backward
                case 40:
                    switch(dir){
                        case 'E':
                            try{
                                if(!maze[r][c-1].equals("*") && !maze[r][c-1].equals(".")){
                                    maze[r][c] = ".";
                                    c--;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'N':
                            try{
                                if(!maze[r+1][c].equals("*") && !maze[r+1][c].equals(".")){
                                    maze[r][c] = ".";
                                    r++;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'W':
                            try{
                                if(!maze[r][c+1].equals("*") && !maze[r][c+1].equals(".")){
                                    maze[r][c] = ".";
                                    c++;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                        case 'S':
                            try{
                                if(!maze[r-1][c].equals("*") && !maze[r-1][c].equals(".")){
                                    maze[r][c] = ".";
                                    r--;
                                    numMoves++;
                                }
                            }
                            catch(ArrayIndexOutOfBoundsException e){

                            }
                            break;
                    }
                    break;
            }
        }
    }

}
