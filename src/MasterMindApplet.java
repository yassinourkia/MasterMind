    import java.applet.*;
    import java.awt.*;
    import java.awt.event.*;
	import javax.swing.*;
    /**
     * 
     * @author ourkia yassin
     * Master Mind applet to display all components
     *
     */
    public class MasterMindApplet extends Applet implements MouseListener,ActionListener
    {
    Button btn_Quitter,btn_Recommencer,btn_Resoudre;
    int niveau_courant = 1;
    Color[] boules;
    Color boule_choisie=null;
    
    // utiliser pour les clicks de souris
    Point[] centres_cercles_a_jouer;
    Point[] centres_cercles_a_choisir;
    
    boolean cestGagne,cestPerdu,cestFini,cestGagneA;
    int nb_p_exactes[],nb_p_fausses[];
    String solution;
    String proposition;
    boolean ligne_pleine;
    static { 
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
		catch(Exception e) {}
	}
    public void init()
    {
    setSize(500,500);
    setBackground(new Color(195,200,233));
    setLayout(null);
    
    btn_Recommencer=new Button("Recommencer");
    //btn_Recommencer.setBackground(Color.pink);
    btn_Recommencer.setBounds(250,420,130,20);
    btn_Recommencer.addActionListener(this);
    
    btn_Quitter=new Button("Quitter");
    //btn_Quitter.setBackground(Color.orange);
    btn_Quitter.setBounds(250,445,130,20);
    btn_Quitter.addActionListener(this);
    
    btn_Resoudre=new Button("Resoudre");
    //btn_Quitter.setBackground(Color.orange);
    btn_Resoudre.setBounds(250,470,130,20);
    btn_Resoudre.addActionListener(this);
    


    add(btn_Quitter);
    add(btn_Recommencer);
    add(btn_Resoudre);
    addMouseListener(this);
    init_jeux();
    }
    
    public void init_jeux()
    {
    boules = new Color[41];
    for(int i=1;i<=40;i++)
    boules[i]=Color.gray;
    
    centres_cercles_a_jouer = new Point[41];
    int k=1;
    //for(int i=1;i<=10;i++)
    int l=10;
    while(l>=1)
    {
    for(int j=1;j<=4;j++)
    {centres_cercles_a_jouer[k]=new Point(30+45*(j-1),30+45*(l-1));k++;}
    l--;
    }
    
    centres_cercles_a_choisir = new Point[7];
    k=1;
    for(int i=1;i<=2;i++)
    for(int j=1;j<=3;j++)
    {centres_cercles_a_choisir[k]=new Point(250+45*(j-1),335+45*(i-1));k++;}
    
    solution = MasterMindJeu.genererSequence();
	//solution="BRVb";
	

    cestGagne=false;
	cestGagneA=false;
    cestPerdu=false;
    cestFini=false;
    nb_p_exactes=new int[11];
    nb_p_fausses=new int[11];
    proposition="";
    niveau_courant =1;
    }
    
    public void actionPerformed(ActionEvent ae)
    {if(ae.getActionCommand().equals("Quitter"))
    System.exit(0);
    else if(ae.getActionCommand().equals("Recommencer"))
    {init_jeux();
    repaint();}
    else if(ae.getActionCommand().equals("Resoudre"))
    {
	
	System.out.println(solution);
	int []sol=new int[4];
	
	sol[0]=convertstr2n(solution.substring(0,1));
	System.out.println(solution.substring(0,1)+" "+convertstr2n(solution.substring(0,1)));
	sol[1]=convertstr2n(solution.substring(1,2));
	System.out.println(convertstr2n(solution.substring(1,2)));
	sol[2]=convertstr2n(solution.substring(2,3));
	sol[3]=convertstr2n(solution.substring(3,4));
	
	mastermind mrg=new mastermind();
	int [][]alpha=new int[10][4];
	alpha=mrg.solve(6,4,sol);
	
    for(int l=0;l<10;l++)
    {
	repaint();
    
    ligne_pleine = true;

    if(ligne_pleine)
    {
    proposition="";
    for(int j=0;j<4;j++)
    {
	
	if(niveau_courant==1)
	{
	if(alpha[niveau_courant][j]== 0)
	{
	proposition+="N";
	boules[(4*l)+j+1]=Color.black;
	
	}
}	
	if(niveau_courant>1)
	{
	
	if(alpha[niveau_courant][j]== 0)
	{
	proposition+="N";
	boules[(4*l)+j+1]=Color.black;
	
	}
	
	if(alpha[niveau_courant][j]== 1)
	{
	proposition+="B";
	boules[(4*l)+j+1]=Color.white;
	}
    else if(alpha[niveau_courant][j]== 2)
	{
	proposition+="R";
	boules[(4*l)+j+1]=Color.red;
    }
	else if(alpha[niveau_courant][j]== 3)
	{
	proposition+="V";
	boules[(4*l)+j+1]=Color.green;
    }
	else if(alpha[niveau_courant][j]== 4)
	{
	proposition+="b";
	boules[(4*l)+j+1]=Color.blue;
    }
	else if(alpha[niveau_courant][j]== 5)
	{
	proposition+="J";
	boules[(4*l)+j+1]=Color.yellow;
	}
	}
	}
	
	if(proposition.length()==4 && !proposition.equals(""))
	{
    nb_p_exactes[niveau_courant]=MasterMindJeu.nb_place_exactes(solution,proposition);
    nb_p_fausses[niveau_courant]=MasterMindJeu.nb_place_fausses(solution,proposition);
	
    if(nb_p_exactes[niveau_courant]==4)
    {
	
	cestGagne=true;
      cestGagneA=true;
      cestFini=true;
	  
	  break;
    }
    
    else if(niveau_courant==10)
    {
    cestPerdu=true;
    cestFini=true;
    }

    niveau_courant++;
    }
    }
   
    repaint();
    }
    }
    }
    
	public int convertstr2n(String color)
  {
  int number=0;
  if(color.equals("N"))  number=0;
  else if(color.equals("B"))  number=1;
  else if(color.equals("R"))  number=2;
  else if(color.equals("V"))  number=3;
  else if(color.equals("b"))  number=4;
  else if(color.equals("J") ) number=5;
            
        //}
return number;
  }
    public void mouseClicked(MouseEvent me)
    {
    if(cestFini) return;
    
    int x=me.getX(),y=me.getY();
    // tester si le curseur est dans le grand cadre
    if(x > 5 && x < 190 && y > 5 && y < 500)
    {int num =calculer_numero_boule(x,y);
    if( num != 0)
    {
    if(boules[num]!=Color.gray)
    {boules[num]=Color.gray;repaint();}
    
    else if(boule_choisie != null)
    {
    boules[num]=boule_choisie;
    //boule_choisie=null;
    

    
    ligne_pleine = true;
    
    for(int i=((niveau_courant - 1)*4)+1;i<=(niveau_courant*4);i++)
    if(boules[i]== Color.gray) {ligne_pleine=false;break;}
    
    
    
    
    
    if(ligne_pleine)
    {proposition="";
    for(int i=((niveau_courant - 1)*4)+1;i<=(niveau_courant*4);i++)
    {if(boules[i]== Color.black) proposition+="N";
    else if(boules[i]== Color.white) proposition+="B";
    else if(boules[i]== Color.red) proposition+="R";
    else if(boules[i]== Color.green) proposition+="V";
    else if(boules[i]== Color.blue) proposition+="b";
    else if(boules[i]== Color.yellow) proposition+="J";}
    
    nb_p_exactes[niveau_courant]=MasterMindJeu.nb_place_exactes(solution,proposition);
    nb_p_fausses[niveau_courant]=MasterMindJeu.nb_place_fausses(solution,proposition);
    
    if(nb_p_exactes[niveau_courant]==4)
    {cestGagne=true;cestFini=true;}
    
    else if(niveau_courant==10)
    {cestPerdu=true;
    cestFini=true;}
    
    niveau_courant++;
    
    }
    repaint();
    }
    
    }
    }
    
    // tester si le curseur est dans le petit cadre
    else if(x > 225 && x < 400 && y > 310 && y < 405)
    {
    Color boule_clicke=Determiner_couleur(x,y);
    if(boule_clicke!= null)
    boule_choisie = boule_clicke;
    
    repaint();
    
    }
    
    
    }
    
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}
    
    
    public int calculer_numero_boule(int x,int y)
    {
    // num > ((niveau_courant - 1) 4)) && num <= (niveau_courant 4)
    for(int i=((niveau_courant - 1)*4)+1;i<=(niveau_courant*4);i++)
    {
    if(Math.sqrt(Math.pow(centres_cercles_a_jouer[i].getX()- ((double) x),2) +
    Math.pow(centres_cercles_a_jouer[i].getY()- ((double) y),2) ) <= 20)
    return i;
    }
    return 0;
    
    }
    
    public Color Determiner_couleur(int x,int y)
    {
    int i=0;
    for(i=1;i<=6;i++)
    if(Math.sqrt(Math.pow(centres_cercles_a_choisir[i].getX()- ((double) x),2) +
    Math.pow(centres_cercles_a_choisir[i].getY()- ((double) y),2) ) <= 20)
    break;
    
    if(i==1) return Color.black;
    else if(i==2) return Color.white;
    else if(i==3) return Color.red;
    else if(i==4) return Color.green;
    else if(i==5) return Color.blue;
    else if(i==6) return Color.yellow;
    else return null;
    
    }
    
    
    public void paint(Graphics g)
    {
    dessinerInterface(g);
    // 290 270
    
    
    }
    
    public void dessinerInterface(Graphics g)
    {
    // 2 grands cadres
    g.drawRect(5,5,185,495);
    g.drawRect(220,5,185,500);
    
   
    g.drawRect(225,310,175,95);
    g.drawRect(225,410,175,85);
    // encore
    g.drawRect(365,315,30,85);
    
    if(boule_choisie!=null)
    {
    g.setColor(boule_choisie);
    g.fillRect(365,315,30,85);
    }
    
    // boule noire
    g.setColor(Color.black);
    g.fillOval(230,315,40,40);
    // boule blanche
    g.setColor(Color.white);
    g.fillOval(275,315,40,40);
    // boule rouge
    g.setColor(Color.red);
    g.fillOval(320,315,40,40);
    
    //boule verte
    g.setColor(Color.green);
    g.fillOval(230,360,40,40);
    // boule bleue
    g.setColor(Color.blue);
    g.fillOval(275,360,40,40);
    // boule jaune
    g.setColor(Color.yellow);
    g.fillOval(320,360,40,40);

    // boules vides
    g.setColor(Color.gray);
    for(int i=1;i<=40;i++)
    {g.setColor(boules[i]);
    g.fillOval((int) centres_cercles_a_jouer[i].getX()-20,
    (int) centres_cercles_a_jouer[i].getY()-20,40,40);}
    
    
    g.setColor(Color.black);
    
    g.drawLine(5,460,190,460);
   
    
    for(int i=0;i<solution.length();i++)
    {
    if(solution.charAt(i)=='N') g.setColor(Color.black) ;
    else if(solution.charAt(i)=='B') g.setColor(Color.white) ;
    else if(solution.charAt(i)=='R') g.setColor(Color.red) ;
    else if(solution.charAt(i)=='V') g.setColor(Color.green) ;
    else if(solution.charAt(i)=='b') g.setColor(Color.blue) ;
    else if(solution.charAt(i)=='J') g.setColor(Color.yellow) ;
    
    g.fillOval(10+i*45,460,40,40);
    
    }
    
	//Damier masquant
    for(int i=0,j=0;j<8;j++)
    {
    for(i=0;i<37;i++)

    {
    if(g.getColor()==Color.black)
    g.setColor(Color.white);
    else g.setColor(Color.black);
    g.fillRect(5+i*5,460+j*5,5,5);
    }
    
    }
    
    // les numeros de niveau
    g.setColor(Color.black);
    g.setFont(new Font("Arial",Font.BOLD,18));
    int i=1;
    for(i=1;i<10;i++)
    g.drawString(String.valueOf(i),200,440-(i-1)*45);
    
    g.drawString(String.valueOf(i),195,440-(i-1)*45);
    
    // Dessiner l'evaluation;
    String message="";
    for(int j=1;j<niveau_courant;j++)
    {message=String.valueOf(j +" : T = "+nb_p_exactes[j]+" V = "+nb_p_fausses[j]);
    g.setFont(new Font("Arial",Font.BOLD,18));
    g.drawString(message,230,30+20*(j-1));
    g.setFont(new Font("Arial",Font.BOLD,9));
    message=String.valueOf("T:"+nb_p_exactes[j]+"V:"+nb_p_fausses[j]);
    g.drawString(message,192,460-45*(j-1));}
    
    
    g.setFont(new Font("Arial",Font.BOLD,18));



    if(cestGagne)
    {
	g.setColor(Color.gray);
    g.fillRect(5,458,186,45);

	for(int k=0;k<solution.length();k++)
    {
    if(solution.charAt(k)=='N') g.setColor(Color.black) ;
    else if(solution.charAt(k)=='B') g.setColor(Color.white) ;
    else if(solution.charAt(k)=='R') g.setColor(Color.red) ;
    else if(solution.charAt(k)=='V') g.setColor(Color.green) ;
    else if(solution.charAt(k)=='b') g.setColor(Color.blue) ;
    else if(solution.charAt(k)=='J') g.setColor(Color.yellow) ;
    
    g.fillOval(10+k*45,460,40,40);
    
    }
    g.setColor(Color.black);
    g.drawString("Bravo c'est gagn�!",225,230);
	if(cestGagneA)
	{
	    
	
	
	message=String.valueOf(niveau_courant +" : T = "+nb_p_exactes[niveau_courant]+" V = "+nb_p_fausses[niveau_courant]);
    g.drawString(message,230,30+20*(niveau_courant-1));
    g.setFont(new Font("Arial",Font.BOLD,9));
    message=String.valueOf("T:"+nb_p_exactes[niveau_courant]+"V:"+nb_p_fausses[niveau_courant]);
    g.drawString(message,192,460-45*(niveau_courant-1));
	}

    }
    if(cestPerdu)
    {
    g.setColor(Color.black);
    g.drawString("Perdu, c'etait :",225,230);
    
    for(i=0;i<solution.length();i++)
    {
    if(solution.charAt(i)=='N') g.setColor(Color.black) ;
    else if(solution.charAt(i)=='B') g.setColor(Color.white) ;
    else if(solution.charAt(i)=='R') g.setColor(Color.red) ;
    else if(solution.charAt(i)=='V') g.setColor(Color.green) ;
    else if(solution.charAt(i)=='b') g.setColor(Color.blue) ;
    else if(solution.charAt(i)=='J') g.setColor(Color.yellow) ;
    
    g.fillOval(225+i*25,240,20,20);
    
    }
    
    
    }
    
    }
    
    
    
    }

