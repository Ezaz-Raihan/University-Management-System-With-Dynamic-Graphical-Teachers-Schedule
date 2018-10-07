import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

class TeacherPanel
{
    JMenuBar bar=new JMenuBar();
    JTextArea notice = new JTextArea();
    JLabel l1 = new JLabel("Send Notice:");
    JRadioButton status [] = new JRadioButton[5];
    ButtonGroup bg = new ButtonGroup();
    JTextField lastStatus=new JTextField();
    JButton send=new JButton("Send");
    JButton delCons=new JButton("Delete Consulting");

    public TeacherPanel(Teacher teacher)
    {
        JFrame frame=new JFrame("Welcome "+teacher.name);

        try
        {
            String quary ="select NOTICE from tec_notice where TEC_ID = '"+teacher.id+"'";
            ResultSet rs = SqlCon.getResult(quary);
            while(rs.next())
            {
                String lastNotice = rs.getString(1);
                notice.setText(lastNotice);
            }
        }
        catch(Exception e)
        {
            System.out.println("Notice Problem");

        }
        try
        {
            String last=null;
            String quary ="select STATUS from status_type where S_VALUE = (select STATUS from tec_status where TEC_ID ='"+teacher.id+"')";
            ResultSet rs = SqlCon.getResult(quary);
            while(rs.next())
            {
                last= rs.getString(1);
            }
            if(last != null)
            {
                lastStatus.setText(last);
                lastStatus.setEditable(false);
            }
        }
        catch(Exception e)
        {
            System.out.println("Status Update Problem");

        }


        JMenu menu =new JMenu("Menu");
        bar.add(menu);

        JMenuItem editInfo = new JMenuItem("Update Information");
        menu.add(editInfo);
        editInfo.addActionListener(e ->
        {
            frame.setVisible(false);
            new EditTeacher(teacher);
        } );

        JMenuItem editPass = new JMenuItem("Change Password");
        menu.add(editPass);
        editPass.addActionListener(e ->
        {
            frame.setVisible(false);
            new ChangePassword(teacher);
        } );

        frame.setJMenuBar(bar);


        l1.setBounds(10,10,100,20);
        frame.add(l1);


        notice.setEditable(false);
        notice.setLineWrap(true);
        JScrollPane sp = new JScrollPane(notice);
        sp.setBounds(150,10,250,100);
        frame.add(sp);

        JLabel l2 = new JLabel("Today :");
        l2.setBounds(420,10,50,20);
        frame.add(l2);

        JTextField nowdate=new JTextField();
        nowdate.setBounds(420,40,100,20);
        frame.add(nowdate);

        JLabel l4 = new JLabel("Status :");
        l4.setBounds(420,70,50,20);
        frame.add(l4);


        lastStatus.setBounds(420,100,100,20);
        frame.add(lastStatus);

        LocalDate date = LocalDate.now();
        String today = date.toString();
        nowdate.setText(today);
        nowdate.setEditable(false);


        send.setBounds(170,130,70,30);
        frame.add(send);
        send.addActionListener(e ->
        {
            try{
                Calendar cal =Calendar.getInstance();
                SimpleDateFormat sdf =new SimpleDateFormat("HH:mm a");
                String cur_time = sdf.format(cal.getTime());

                String sendDate = nowdate.getText();
                String curNotice = notice.getText();
                if(curNotice.length()==0)
                {
                    JOptionPane.showMessageDialog(send,"Please! Write Something");
                }
                else{
                    SqlCon.sendNotice(teacher.id,sendDate,cur_time,curNotice);
                    notice.setEditable(false);
                    send.setEnabled(false);
                }
            }
            catch(Exception ex)
            {
                System.out.println("Problem to Time");
            }
        } );

        JButton edit=new JButton("Edit");
        edit.setBounds(280,130,70,30);
        frame.add(edit);
        edit.addActionListener(e ->
        {
            send.setEnabled(true);
            notice.setEditable(true);
        } );

        JLabel l3 = new JLabel("Send Current Status: ");
        l3.setBounds(10,170,150,20);
        frame.add(l3);

        status [0]= new JRadioButton("Available");
        status [0].setBounds(180,170,80,20);
        frame.add(status [0]);
        bg.add(status [0]);

        status [1] = new JRadioButton("Not Available");
        status [1].setBounds(280,170,110,20);
        frame.add(status [1]);
        bg.add(status [1]);

        status [2]= new JRadioButton("Busy");
        status [2].setBounds(385,170,80,20);
        frame.add(status [2]);
        bg.add(status [2]);

        status [3]= new JRadioButton("Meeting");
        status [3].setBounds(180,210,80,20);
        frame.add(status [3]);
        bg.add(status [3]);

        status [4]= new JRadioButton("Break");
        status [4].setBounds(265,210,80,20);
        frame.add(status [4]);
        bg.add(status [4]);

        JButton set = new JButton("Set");
        set.setBounds(250,240,80,30);
        frame.add(set);
        set.addActionListener( e->
        {
            try{
                String quary ="select STATUS from tec_status where TEC_ID = '"+teacher.id+"'";
                ResultSet rs = SqlCon.getResult(quary);
                String cur_date = today;
                String lastStat =null;
                while(rs.next())
                {
                    lastStat= rs.getString(1);
                }
                if(lastStat == null)
                {
                    for(int i=0; i<5; i++)
                    {
                        if(status[i].isSelected()==true)
                        {
                            SqlCon.setStatus(teacher.id,cur_date,i);
                            String select = status[i].getText();
                            lastStatus.setText(select);
                            lastStatus.setEditable(false);
                        }
                    }
                }
                else{
                    for(int i=0; i<5; i++)
                    {
                        if(status[i].isSelected()==true)
                        {
                            SqlCon.editStatus(teacher.id,cur_date,i);
                            String select = status[i].getText();
                            lastStatus.setText(select);
                            lastStatus.setEditable(false);
                        }
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("Pronlem in status DB");
            }
        });

        JButton cs=new JButton("show TSF");
        cs.setBounds(200,280,170,40);
        frame.add(cs);
        cs.addActionListener(e ->
        {
            //frame.setVisible(false);
            new TSF(teacher);
        });



        JButton st=new JButton("Set Consulting");
        st.setBounds(120,330,150,40);
        frame.add(st);
        st.addActionListener(e ->
        {
            //frame.setVisible(false);
            new CreateConsulting(teacher);
        });

        JButton dn=new JButton("Delete Notice");
        dn.setBounds(320,330,150,40);
        frame.add(dn);
        dn.addActionListener(e ->
        {
            //frame.setVisible(false);
            new ShowNotice(teacher,1);
        });

        JButton show=new JButton("Show Info.");
        show.setBounds(120,390,150,40);
        frame.add(show);
        show.addActionListener(e ->
        {
            //frame.setVisible(false);
            new ShowTeacher(teacher,1);
        });


        delCons.setBounds(320,390,150,40);
        frame.add(delCons);
        delCons.addActionListener(e ->
        {
            //frame.setVisible(false);
            new ShowCons(teacher);
        });

        JButton logout=new JButton("Log Out");
        logout.setBounds(200,440,170,50);
        frame.add(logout);
        logout.addActionListener(e ->
        {
            frame.setVisible(false);
            new Login();
        });

        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}