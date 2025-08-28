package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SignupTwo extends JFrame implements ActionListener{
   JLabel pg2,religion,category,income,eg,eg1,occ,pan,adhar,sc,ea;
   JRadioButton y1,n1,y2,n2;
   JButton next;
   JTextField j6,j7;
   JComboBox j1,j2,j3,j4,j5;
   String formno;
   public SignupTwo(String formno){
        setLayout(null);
        this.formno=formno;
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setLocation(350,10);
        setSize(850,800);
           
           pg2= new JLabel("Page 2: Additional Details");
           pg2.setFont(new Font("Raleway",Font.BOLD,38));
           pg2.setBounds(220,30,600,45);
           add(pg2);
           
           religion= new JLabel("Religion:");
           religion.setFont(new Font("Raleway",Font.BOLD,20));
           religion.setBounds(100,140,100,30);
           add(religion);
           
           String reli[]={"Hindu","Muslim","Sikh","Christian"};
           j1=new JComboBox(reli);
           j1.setFont(new Font("Raleway",Font.BOLD,20));
           j1.setBounds(300,140,400,30);
           add(j1);
           
           category= new JLabel("Category:");
           category.setFont(new Font("Raleway",Font.BOLD,20));
           category.setBounds(100,190,200,30);
           add(category);
           
           String cat[]={"General","SC","ST","OBC","Other"};
           j2=new JComboBox(cat);
           j2.setFont(new Font("Raleway",Font.BOLD,20));
           j2.setBounds(300,190,400,30);
           add(j2);
           
           income= new JLabel("Income:");
           income.setFont(new Font("Raleway",Font.BOLD,20));
           income.setBounds(100,240,200,30);
           add(income);
           
           String sal[]={"NULL","<1,50,000","<2,50,000","<5,00,000","Upto 10,00,000"};
           j3=new JComboBox(sal);
           j3.setFont(new Font("Raleway",Font.BOLD,20));
           j3.setBounds(300,240,400,30);
           add(j3);
           
           eg= new JLabel("Education");
           eg.setFont(new Font("Raleway",Font.BOLD,20));
           eg.setBounds(100,290,200,30);
           add(eg);
           
           eg1= new JLabel("Qualification:");
           eg1.setFont(new Font("Raleway",Font.BOLD,20));
           eg1.setBounds(100,310,200,30);
           add(eg1);
           
           String edu[]={"Non-Graduation","Graduate","Post-Graduation","Doctrate","Others"};
           j4=new JComboBox(edu);
           j4.setFont(new Font("Raleway",Font.BOLD,20));
           j4.setBounds(300,300,400,30);
           add(j4);
           
           occ= new JLabel("Occupation:");
           occ.setFont(new Font("Raleway",Font.BOLD,20));
           occ.setBounds(100,360,200,30);
           add(occ);
           
           String occ[]={"Salried","Self-Employed","Buisness","Student","Retired","Others"};
           j5=new JComboBox(occ);
           j5.setFont(new Font("Raleway",Font.BOLD,20));
           j5.setBounds(300,360,400,30);
           add(j5);
           
           pan= new JLabel("PAN Number:");
           pan.setFont(new Font("Raleway",Font.BOLD,20));
           pan.setBounds(100,410,200,30);
           add(pan);
           
           j6=new JTextField();
           j6.setBounds(300,410,400,30);
           j6.setFont(new Font("Raleway",Font.BOLD,18));
           j6.setBackground(Color.WHITE);
           add(j6);
           
           adhar= new JLabel("Aadhar Number:");
           adhar.setFont(new Font("Raleway",Font.BOLD,20));
           adhar.setBounds(100,460,300,30);
           add(adhar);
           
           j7=new JTextField();
           j7.setBounds(300,460,400,30);
           j7.setFont(new Font("Raleway",Font.BOLD,18));
           j7.setBackground(Color.WHITE);
           add(j7);
           
           sc= new JLabel("Senior Citizen:");
           sc.setFont(new Font("Raleway",Font.BOLD,20));
           sc.setBounds(100,510,200,30);
           add(sc);
           
           y2=new JRadioButton("Yes");
           y2.setBackground(Color.WHITE);
           y2.setBounds(300,510,80,40);
           add(y2);
           
           n2=new JRadioButton("No");
           n2.setBackground(Color.WHITE);
           n2.setBounds(400,510,80,40);
           add(n2);
           
           ButtonGroup b2=new ButtonGroup();
           b2.add(y2);
           b2.add(n2);
           
           ea= new JLabel("Existing Account");
           ea.setFont(new Font("Raleway",Font.BOLD,20));
           ea.setBounds(100,570,200,30);
           add(ea);
           
           y1=new JRadioButton("Yes");
           y1.setBounds(300,570,80,40);
           y1.setBackground(Color.WHITE);
           add(y1);
           
           n1=new JRadioButton("No");
           n1.setBounds(400,570,80,40);
           n1.setBackground(Color.WHITE);
           add(n1);
           
           ButtonGroup b1=new ButtonGroup();
           b1.add(y1);
           b1.add(n1);
           
           next=new JButton("Next");
           next.setBounds(400,620,100,40);
           next.setBackground(Color.BLACK);
           next.setForeground(Color.BLACK);
           next.addActionListener(this);
           add(next);
   }
   public void actionPerformed(ActionEvent ae){ 
       String sreligion=(String) j1.getSelectedItem();
       String scategory=(String) j2.getSelectedItem();    
       String ssalary=(String) j3.getSelectedItem();
       String seducation=(String) j4.getSelectedItem();
       String soccupation=(String) j5.getSelectedItem();
       
       String span=j6.getText();
       String sadhaar=j7.getText();
       
       String seniorcitizen=null;
       if(y2.isSelected())
           seniorcitizen="Yes";
       else if(n2.isSelected())
           seniorcitizen="No";
       
       String existingacc=null;
       if(y1.isSelected())
           existingacc="Yes";
       else if(n1.isSelected())
           existingacc="no";
       
       try{
           Conn c=new Conn();
           String query="insert into SignupTwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+ssalary+"','"+seducation+"','"+soccupation+"','"+span+"','"+sadhaar+"','"+seniorcitizen+"','"+existingacc+"')";
           c.s.executeUpdate(query);
           setVisible(false);
          new SignupThree(formno).setVisible(true);
       }catch(Exception e)
       {
           System.out.println(e);
       }
   }
    public static void main(String args[]) {
       new SignupTwo("");       
    }
}
