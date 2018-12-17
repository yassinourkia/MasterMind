import java.util.Random;

public class MasterMindJeu
{

public static String genererSequence()
{
String boules="NBRVbJ";
char[] tmp=boules.toCharArray();
String Resultat="";
int k=0,l=0;
Random alea=new Random();
char sauv='\0';

for(int i=1;i<=4;i++)
{
for(int j=0;j<50;j++);
{k=alea.nextInt(60)/10;
l=alea.nextInt(60)/10;

sauv=tmp[k];
tmp[k]=tmp[l];
tmp[l]=sauv;}


Resultat+=String.valueOf(tmp[k]);

}

return Resultat;}


public static int nb_place_exactes(String Solution,String proposition)
{
int Resultat=0;
for(int i=0;i<proposition.length();i++)
if(proposition.charAt(i)==Solution.charAt(i)) Resultat++;
return Resultat;
}

public static int nb_place_fausses(String Solution,String proposition)
{
String tmp_prop=proposition;
String tmp_sol=Solution;
int Resultat=0;

for(int i=0;i<tmp_sol.length();i++)
{
if( tmp_sol.charAt(i) == tmp_prop.charAt(i) )
{ tmp_prop = remplacerChar(tmp_prop,i);
tmp_sol = remplacerChar(tmp_sol,i); }
}

tmp_prop = supprimerEspaces(tmp_prop);
tmp_sol = supprimerEspaces(tmp_sol);

int index=0;


for(int i=0;i<tmp_prop.length();i++)
{
index=tmp_sol.indexOf(String.valueOf(tmp_prop.charAt(i)) );

if(index!=-1)
{Resultat++;
tmp_sol = supprimerChar(tmp_sol,index);
}
}
return Resultat;
}

public static String supprimerEspaces(String ch)
{String res="";
for(int i=0;i<ch.length();i++)
if(ch.charAt(i) != ' ') res+=ch.charAt(i);
return res;
}

public static String supprimerChar(String ch,int index)
{String res="";
for(int i=0;i<ch.length();i++)
if(i != index) res+=ch.charAt(i);
return res;
}

public static String remplacerChar(String ch,int index)
{String res="";
for(int i=0;i<ch.length();i++)
if(i != index) res+=ch.charAt(i);
else res+=' ';

return res;
}

}
