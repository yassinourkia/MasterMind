import javax.swing.*;
import java.applet.*;

/**
 * 
 * @author ourkia yassin
 * Main to class to launch the application
 *
 */
public class MasterMindFenetre extends JFrame
{
static
{
try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
catch(Exception e) {}
}
 MasterMindFenetre()
 {
 super("MASTERMIND - WeedoGift");
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setResizable(false);
 setSize(420,550);
 setLocationRelativeTo(null);
 try
 {
 Applet MMA = new MasterMindApplet();
 MMA.init();
 getContentPane().add(MMA);

 }
 catch(Exception p)
 {
 JOptionPane.showMessageDialog(this,p.toString());
 }
 setVisible(true);
 }

 public static void main(String x[])
 {
 new MasterMindFenetre();
 }

 } 