import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TTT1 extends JFrame implements ItemListener, ActionListener{
int i,j,ii,jj,k,x,y,yesnull,count; 
int a[][]={{0,1,2,3,1},{0,1,4,7,1},{0,1,5,9,1},{0,2,5,8,1},{0,3,5,7,1},{0,3,6,9,1},{0,4,5,6,1},{0,7,8,9,1} };
int a1[][]={{0,1,2,3,1},{0,1,4,7,1},{0,1,5,9,1},{0,2,5,8,1},{0,3,5,7,1},{0,3,6,9,1},{0,4,5,6,1},{0,7,8,9,1} };

boolean state,type,set;

Icon ic1,ic2,icon;
Checkbox c1,c2;
JLabel l1,l2;
JButton b[]=new JButton[9];
JButton reset;
JButton clue;

public void showButton(){

x=10; y=10;j=0;
for(i=0;i<=8;i++,x+=100,j++){
   b[i]=new JButton();
   if(j==3){
       j=0; y+=100; x=10;
    }
   b[i].setBounds(x,y,100,100);
   add(b[i]);
   b[i].addActionListener(this);
}//eof for

reset=new JButton("RESET");
reset.setBounds(70,350,100,50);
add(reset);
reset.addActionListener(this);

}

public void check(int num1){
for(ii=0;ii<=7;ii++){
   for(jj=1;jj<=3;jj++){
        if(a[ii][jj]==num1){ a[ii][4]=1;  }

   }

 }
}  

public void complogic(int num){  
 set=true;
 for(i=0;i<=7;i++){  
   for(j=1;j<=3;j++){  
      if(a[i][j]==num){  
        a[i][0]=1;a[i][4]=0;
      }  
    }  
  }  
   int count=0;
   for(i=0;i<=7;i++){                                             
       count=0;  
       for(j=1;j<=3;j++){ 
           if(b[(a[i][j]-1)].getIcon()==ic1)break;
           else{   
           if(b[(a[i][j]-1)].getIcon()!=null && b[(a[i][j]-1)].getIcon()==ic2){  
             count++;  
               }                        
            else{ yesnull=a[i][j]; }  
         }
        }                                 
      if(count==2){                     
         b[yesnull-1].setIcon(ic2);   
         System.out.println("comp");
         this.check(yesnull); set=false;break;  
         }  
   }     
   if(set)
   {
     for(i=0;i<=7;i++){ 
         count=0;
         for(j=1;j<=3;j++){ 
           if(b[(a[i][j]-1)].getIcon()==ic2)break;
           else{   
           if(b[(a[i][j]-1)].getIcon()!=null && b[(a[i][j]-1)].getIcon()==ic1){  
             count++;  
               }                        
            else{ yesnull=a[i][j]; }  
         }
        }        
         if(count==2){                     
         b[yesnull-1].setIcon(ic2);   
          System.out.println("player");
         this.check(yesnull); set=false;break;  
         
         }                                                                                                            
   }
   }
   if(set)   
   {
       for(int i=0;i<=7;i++)
       {
          if(a[i][0]==0){
          for(int j=1;j<=3;j++)
          {
               if(b[(a[i][j]-1)].getIcon()==null){
                b[(a[i][j]-1)].setIcon(ic2);
                System.out.println("comp2");
                this.check(a[i][j]);
                set=false;
                break;
                }
          }
           if(!set)break;
          }
       }
   }                                     
  
}//eof complogic  




TTT1(){  
super("tic tac toe by Vasanthi");  
  
CheckboxGroup cbg=new CheckboxGroup();  
c1=new Checkbox("vs computer",cbg,false);  
c2=new Checkbox("vs friend",cbg,false);  
c1.setBounds(120,80,100,40);  
c2.setBounds(120,150,100,40);  
add(c1); add(c2);  
c1.addItemListener(this);  
c2.addItemListener(this);  
  
  
state=true;type=true;set=true;  
ic1=new ImageIcon("ic1.jpg");  
ic2=new ImageIcon("ic2.jpg");  
setLayout(null);  
setSize(350,450);  
setVisible(true);  
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
}//eof constructor  


public void itemStateChanged(ItemEvent e){  
    if(c1.getState())  
    {   
       type=false;  
    }  
    else if(c2.getState())  
    { 
       type=true;  
    }  
    remove(c1);remove(c2);  
    repaint(0,0,330,450);  
    showButton();  
}//eof itemstate  

public void actionPerformed(ActionEvent e){
	if(type==true)//logicfriend
	{
	   if(e.getSource()==reset){
	       for(i=0;i<=8;i++){
		  b[i].setIcon(null);
	       }  
            for(int i=0;i<=7;i++)
            {
                 for(int j=1;j<=3;j++)
                  a[i][j]=a1[i][j];
            }
	   }
	   else{ 
	      for(i=0;i<=8;i++){
		 if(e.getSource()==b[i]){
		    if(b[i].getIcon()==null){
		      if(state==true){ icon=ic2;         
		       state=false;} 
		      else{ icon=ic1; state=true; }
		    b[i].setIcon(icon);
		    }
		 } 
	       }
	   }
	}//eof logicfriend
	else if(type==false){      //computerlogic                               
	      if(e.getSource()==reset){
		  for(i=0;i<=8;i++){
		    b[i].setIcon(null);
		  }
                   for(int i=0;i<=7;i++)
		    {
		         for(int j=1;j<=3;j++)
		          a[i][j]=a1[i][j];
		    }
		  }
		else{  
		    for(i=0;i<=8;i++){
		       if(e.getSource()==b[i]){
		          if(b[i].getIcon()==null){
                              b[i].setIcon(ic1);
		              if(b[4].getIcon()==null){
				 b[4].setIcon(ic2);
				 this.check(5);
			      }
		              else{
		                this.complogic(i);
		              }
		           }
		         }
		     }
		}
	}//eof computerlogic

	for(i=0;i<=7;i++){
	  
	  Icon icon1=b[(a[i][1]-1)].getIcon();
	  Icon icon2=b[(a[i][2]-1)].getIcon();
	  Icon icon3=b[(a[i][3]-1)].getIcon();
	     if((icon1==icon2)&&(icon2==icon3)&&(icon1!=null)){
                    if(icon1==ic1){
		    JOptionPane.showMessageDialog(TTT1.this,"!!!YOU won!!! click reset");			 
		    break;
		    }
		    else if(icon1==ic2){ 
		       JOptionPane.showMessageDialog(TTT1.this,"!!!AWK (COMPUTER) won!!! click reset");
		        break;			 
		 }
           
	       }
	 }

}//eof actionperformed


public static void main(String []args){
TTT1 t = new TTT1();
}//eof main
}//eof class





