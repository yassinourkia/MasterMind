import java.*;
import java.lang.*;
import java.io.FileOutputStream;
import java.io.*;

class SEssai {
   public int nNbNoirs;
   public int nNbBlancs;
   public int[] couleurs=new int[50];
}

/**
 * 
 * @author ourkia yassin
 *
 */
class mastermind
{
  public int rd(int nb_couleurs)
  {
  int result=(int)(Math.random()*10);
    if(result<nb_couleurs&&result>=0)
    {
    return result;
    }
  return rd(nb_couleurs);
  }
    
  public int random(int nb_couleurs)
  {
  int i=new mastermind().rd(nb_couleurs);
  return i;
  }
  
  public int evaluation(int []pEssai,int []pCombinaison,int nb_pions,int nb_couleurs)
  {
    int[][] couleur=new int[50][2];
    int c;
    int nResultat;
    for(c=0;c<nb_couleurs;c++)
    {
    couleur[c][0]=couleur[c][1]=0;
    }
    for(c=0;c<nb_pions;c++)
    {
    couleur[pEssai[c]][0]++;
    couleur[pCombinaison[c]][1]++;
    }
    nResultat=0;
    for(c=0;c<nb_couleurs;c++)
    {
     nResultat+=couleur[c][(couleur[c][0]>couleur[c][1] ? 1 : 0)];
    }
    for(c=0;c<nb_pions;c++)
    {
     nResultat+=(pEssai[c]==pCombinaison[c] ? 1 : 0)*100;
    }
    return nResultat-nResultat/100;
  }
  
  public int recherche(int []couleurs,int profondeur,int[][]essais,int nNbEssais,int []pCombinaison,int nb_pions,int nb_couleurs)
  {
    int essai;
    int nNbBlancs,nNbNoirs;
    int c;
	
	if(profondeur==nb_pions)
	{
		for(essai=0;essai<nNbEssais;essai++)
		{
		nNbNoirs=new mastermind().evaluation(couleurs,essais[essai],nb_pions,nb_couleurs);
		nNbBlancs=nNbNoirs%100;
		nNbNoirs/=100;
   
			if(!(nNbBlancs==evaluation(essais[essai],pCombinaison,nb_pions,nb_couleurs)%100 &&  nNbNoirs==evaluation(essais[essai],pCombinaison,nb_pions,nb_couleurs)/100)) 
			{
			break;
			}
		}
	return(essai<nNbEssais ? 0 : 1);
	}
	
	for(c=0;c<nb_couleurs;c++)
	{
	couleurs[profondeur]=c;
		if(new mastermind().recherche(couleurs,profondeur+1,essais,nNbEssais,pCombinaison,nb_pions,nb_couleurs)==1) 
		{
		return 1;	
		}
    }
    return 0;
  }
  
  public String convertn2c(int number)
  {
  String str="";
  switch (number) {
            case 0:  str="Rouge"; break;
            case 1:  str="Vert"; break;
            case 2:  str="Jaune"; break;
            case 3:  str="Orange"; break;
            case 4:  str="Bleu"; break;
            case 5:  str="Violet"; break;
            
            default: System.out.println("Invalid color");break;
        }
return str;
  }
  
  public int[][] solve(int colors,int pieces,int []solution)
  {
  int [][]result=new int[10][4];
    try
    {
    
		int bFini=0;
		int nNbEssais=0;
		int i;
		SEssai pCour=new SEssai();
		int [][]pEssais=new int[100][pieces];
		
		for(i=0;i<pieces;i++)
		{
		pEssais[0][i]=0;
        }        
         
		 while(bFini==0)
		{
        
		pCour.couleurs=pEssais[nNbEssais];
		nNbEssais++;
	    new mastermind().recherche(pCour.couleurs,0,pEssais,nNbEssais-1,solution,pieces,colors);
		
		pCour.nNbNoirs=new mastermind().evaluation(pCour.couleurs,solution,pieces,colors);
		pCour.nNbBlancs=pCour.nNbNoirs%100;
		pCour.nNbNoirs/=100;
		
		for(int c=0;c<pieces;c++)
		{
	
		result[nNbEssais][c]=pCour.couleurs[c];
		}
		
		bFini=(pCour.nNbNoirs==pieces ? 1 : 0);
		
		}
		  
		 System.out.print("Mastermind resolu en "+ nNbEssais+" coups\n\n");
			
		  



          //}
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
	return result;
  }
  

}