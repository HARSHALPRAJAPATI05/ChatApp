package chatting.application;

import java.awt.*; // Fior color
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import java.net.*;

public class Server implements ActionListener{
    
    static JFrame f = new JFrame();
//  Text Feild ne globally declare krva thi constructor ni andr and bar use kri skie 
    JTextField text;
    JPanel a1;
//  Msg send krie tyare right side show thay ena mate BOX Layout no use krie
    static Box vertical = Box.createVerticalBox(); //ek ni niche ek msg aave ena mate
    static DataOutputStream dout;
    
    Server(){
        
        f.setLayout(null);
        
//  Green aadi patti mate
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
//  Panel ne frame upr set karva mate
        f.add(p1);  
        
//  This is image for BACK button
//  File mathi direct image leva mate ClassLoader no use kryo 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
//  Image ne nani krine set krva mate [ This i2 is a scaled image ]
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
//  Scaled image ne direct show nai thay etle i3 ma nakhyu
        ImageIcon i3 = new ImageIcon(i2);
//  Image ne frame pr set krva mate lable no use kryo pchi add function thi add karyu
        JLabel back = new JLabel(i3 );
        back.setBounds(5,20,25,25); 
//  Frame upr add thase image. Hve panel upr add krva  mate
        p1.add(back);
        
//  BACK button pr click krie to frame close thavi joie 
        back.addMouseListener(new MouseAdapter (){
            @Override
            public void mouseClicked(MouseEvent e){
                f.setVisible(false);
                    // OR
                System.exit(0);
            }
        });
        
//  Profile photo mate
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/6.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6 );
        profile.setBounds(40,10,50,50); 
        p1.add(profile);
        
//  Video call image mate
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30 , Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9 );
        video.setBounds(300,20,30,30); 
        p1.add(video);
        
//  Call image mate
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30 , Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12 );
        phone.setBounds(360,20,35,30); 
        p1.add(phone);
        
//  Call image mate
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15 );
        morevert.setBounds(420,20,10,25); 
        p1.add(morevert);
        
        JLabel name = new JLabel("Girl");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
//  Font ni size moti krva mate
//  Font family, font type, font size
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
        p1.add(status);
        
//  Text area creat krva mate 
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);
        
//  Text lkhva mate
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
        f.add(text);
        
        JButton b = new JButton("Send");
        b.setBounds(320,655,123,40);
        b.setBackground(new Color(7,94,84));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
//  Button pr click krva thi msg show thay ena mate
        b.addActionListener(this);
        f.add(b);
        
//  Output ma frame ni size mate dimension aapya        
        f.setSize(450,700); 
//  Frame kya khulse(Run krya pchi) ena mate Location aapyu
        f.setLocation(70,20); 
//  upr close vadi white patti close krva mate
        f.setUndecorated(true);
//  Frame no color change krva mate
        f.getContentPane().setBackground(Color.RED);  
//  Output ma visible thay ena mate true
        f.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        try{
         
//  Text Feild ni andr lkhelu get kri skie in output
//  GetText e String ne return kre che.Ena mate je value aave ene string ma leva mate      
        String out = text.getText();
      
//  Label ne Panel ma nakhsu pchi prine thse 
        JPanel p2 = formatLabel(out);
        
//  TextArea ni andr lkhelu lava mate
        a1.setLayout(new BorderLayout());  // BorderLayout thi element ne top,buttom,
                                           // left,right,center ma set kri skie
        JPanel right = new JPanel(new BorderLayout());
//  Righr side lava mate
/*      String ma output nthi letu ena mate label creat krsu ane label ma out,
                ni value nakhsu pchi Panel creat krsu ane Panel ne show krsu */ 
        right.add(p2, BorderLayout.LINE_END); // jya line end thay tya Hello(msg) print thay

//  Right side je aavse ene vertical box ma nkhva mate
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));//2 msgs vache ni space mate 15

//  Upr nu bdhu a1(TextArea) ma jse etle ema add krva mate
        a1.add(vertical, BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
// Ek var msg type thaya pchi keyboard mathi erase thai jay ena mate
        text.setText("");
        
//  msg send krie etle show thava mate frame reload thavi joie ena mate niche function che
//  Three type of function for frame reloading
        f.repaint();
        f.invalidate();
        f.validate();
        
        }catch(IOException e){
        System.out.println(e);
        }
    }   
    
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
//  Panel nu layout eva mate...kya btavanu che.End ma btavanu che.Ena mate no code
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
// Width aapvathi fix space 6utse green patti ma
        JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 18));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true); // Msg upr green color lava mate
//  Pedding krva mate LinrBorer thi thase
//          Left,Upr,Botom thi space 15 jose and Right thi 50
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
//  Time lava mate Calendar class no objects bnavo pdse  
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM::HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime())); //Live time leva mate SetText function no use thay che
        panel.add(time);
        
        return panel;
    }
    
    public static void main(String [] args){
        new Server();
        
        try{
            ServerSocket skt = new ServerSocket(5656);

//  Server ne infinte chalava mate and bdha msgs ne accept karava mate
            while(true){
                
//  Ahiya thi bdha msgs access thay 6. Etle store krva mate Socket lidhu                
                Socket s = skt.accept(); 
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                
                while(true){
//  msgs ne infinite receive and send krva mate
//          1.Receive krva mate = ReadUTF. Je String ne return kre che
//          2.Send krva mate = SendUTF
                    String msg = din.readUTF(); // Client side thi aayelo message msg ma store thase
//  Aayela message ne Frame pr Disply krva mate
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);// Right side Client no msg show thay
                    //Ek ni nioche ek shoe krva mate
                    vertical.add(left);
                    f.validate();
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
