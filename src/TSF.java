import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;



class TSF
{
    JFrame jf = new JFrame ("TSF");

    JPanel panel ;

    JLabel day1,day2,day3,day4,day5,day6,day7,time;
    JLabel timelist[]=new JLabel[30];
    JSeparator sepa = new JSeparator();
    JSeparator vsepa = new JSeparator(SwingConstants.VERTICAL);//HORIZONTAL

    ClassTime o1,o2,o3,o4,o5,o6; //for sundat&tuesday
    ClassTime l1,l2,l3,l4,l5,l6; //for sundat&tuesday
    ClassTime s1,s2; //for sundat&tuesday
    ClassTime m1,m2; //for sundat&tuesday
    ClassTime t1,t2; //for sundat&tuesday
    ClassTime w1,w2; //for sundat&tuesday
    ClassTime th1,th2; //for sundat&tuesday


    ArrayList<ClassTime> Sunday ;
    ArrayList<ClassTime> Monday ;
    ArrayList<ClassTime> SundayConsultingList ;
    ArrayList<ClassTime> MondayConsultingList ;
    ArrayList<ClassTime> TuesdayConsultingList ;
    ArrayList<ClassTime> WednesdayConsultingList ;
    ArrayList<ClassTime> ThursdayConsultingList ;

    JButton SundayClass[] = new JButton[20];
    JButton Mondayclass[] = new JButton[20];
    JButton TuesdayClass[] = new JButton[20];
    JButton WednesdayClass[] = new JButton[20];

    JButton SundayConsulting[] = new JButton[20];
    JButton MondayConsulting[] = new JButton[20];
    JButton TuesdayConsulting[] = new JButton[20];
    JButton WednesdayConsulting[] = new JButton[20];
    JButton ThursdayConsulting[] = new JButton[20];
    JButton status= new JButton();
    JTextArea notice=new JTextArea();
    JScrollPane js=new JScrollPane(notice);
    JLabel notlbl =new JLabel("Notice :");

    public TSF(Teacher teacher)
    {
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension (800, 1200));
        panel.setLayout(null);

        SundayConsultingList = new ArrayList<ClassTime>();
        MondayConsultingList = new ArrayList<ClassTime>();
        TuesdayConsultingList = new ArrayList<ClassTime>();
        WednesdayConsultingList = new ArrayList<ClassTime>();
        ThursdayConsultingList = new ArrayList<ClassTime>();

        notlbl.setBounds(10,5,100,20);
        panel.add(notlbl);
        notlbl.setForeground(Color.BLUE);
        notice.setEditable(false);
        try
        {
            String query1 ="select NOTICE from tec_notice where TEC_ID ='"+teacher.id+"' ORDER by ID desc";
            ResultSet result = SqlCon.getResult(query1);
            while(result.next())
            {
                String curNotice = result.getString("NOTICE");
                notice.setText(curNotice);
                break;
            }
        }
        catch(Exception e)
        {
            System.out.println("problem in notice");
        }
        notlbl.setFont(new Font("Courier New", Font.BOLD,20));
        js.setBounds(10,35,350,50);
        panel.add(js);
        status.setBounds(710,30,150,40);
        panel.add(status);
        try
        {
            String query2 ="select status_type.STATUS , tec_status.STATUS from status_type,tec_status WHERE tec_status.TEC_ID='"+teacher.id+"' and status_type.S_VALUE=tec_status.STATUS";
            ResultSet result2 = SqlCon.getResult(query2);
            while(result2.next())
            {
                String curStatus = result2.getString(1);
                int x = Integer.parseInt(result2.getString(2));
                if(x==0)
                {
                    status.setText(curStatus);
                    status.setBackground(Color.GREEN);
                }
                if(x==1)
                {
                    status.setText(curStatus);
                    status.setBackground(Color.RED);
                }
                if(x==2)
                {
                    status.setText(curStatus);
                    status.setBackground(Color.YELLOW);
                }
                if(x==3)
                {
                    status.setText(curStatus);
                    status.setBackground(Color.GRAY);
                }
                if(x==4)
                {
                    status.setText(curStatus);
                    status.setBackground(Color.CYAN);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("problem in status");
        }

        //for sunday consulting
        try
        {
            String query3="select consulting.CONS_START ,consulting.CONS_END from consulting WHERE consulting.TEC_ID='"+teacher.id+"' and consulting.SAMI_ID=(select max(SAMI_ID) from consulting) and consulting.DAY='Sunday'";
            ResultSet result3 = SqlCon.getResult(query3);
            while(result3.next())
            {
                String start = result3.getString(1);
                String end = result3.getString(2);
                SundayConsultingList.add(new ClassTime("Consulting",teacher.room,start,end));
            }
        }
        catch(Exception ex2)
        {
            System.out.println("problem in Consulting");
        }

        int Y = 0,SundayConsultingCounter=0,hight=0;
        Iterator itr3 = SundayConsultingList.iterator();

        while(itr3.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime suncon = (ClassTime)itr3.next();
            SundayConsulting[SundayConsultingCounter] = new JButton("<html>"+suncon.getSubjectName()+"<br/>Room NO:"+suncon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(suncon.StartTime);
            double Ftime = Double.parseDouble(suncon.FinishTime);

            //Time setting
            if((Stime == Math.round(Stime)))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
                double h = (Ftime-Stime);
                hight = (int)(h*80.00);
            }
            if((Stime != Math.round(Stime)))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
                double h = (Ftime-Stime);
                if(h==.30)
                {
                    hight = 20+(int)(h*80.00);
                }
                else hight = (int)(h*80.00);
            }
            SundayConsulting[SundayConsultingCounter].setBounds(220,Y+80,80,hight);
            SundayConsulting[SundayConsultingCounter].setOpaque(true);
            SundayConsulting[SundayConsultingCounter].setBackground(color);
            SundayConsulting[SundayConsultingCounter].setForeground(Color.WHITE);
            SundayConsulting[SundayConsultingCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(SundayConsulting[SundayConsultingCounter]);
            SundayConsultingCounter++;

        }

        //for monday consulting

        try
        {
            String query3="select consulting.CONS_START ,consulting.CONS_END from consulting WHERE consulting.TEC_ID='"+teacher.id+"' and consulting.SAMI_ID=(select max(SAMI_ID) from consulting) and consulting.DAY='Monday'";
            ResultSet result3 = SqlCon.getResult(query3);
            while(result3.next())
            {
                String start = result3.getString(1);
                String end = result3.getString(2);
                MondayConsultingList.add(new ClassTime("Consulting",teacher.room,start,end));
            }
        }
        catch(Exception ex2)
        {
            System.out.println("problem in Consulting");
        }

        int MondayConsultingCounter=0;
        Y = 0;
        hight=0;
        Iterator itr4 = MondayConsultingList.iterator();

        while(itr4.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime moncon = (ClassTime)itr4.next();
            MondayConsulting[MondayConsultingCounter] = new JButton("<html>"+moncon.getSubjectName()+"<br/>Room NO:"+moncon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(moncon.StartTime);
            double Ftime = Double.parseDouble(moncon.FinishTime);

            //Time setting
            if((Stime == Math.round(Stime)))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
                double h = (Ftime-Stime);
                hight = (int)(h*80.00);
            }
            if((Stime != Math.round(Stime)))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
                double h = (Ftime-Stime);
                if(h==.30)
                {
                    hight = 20+(int)(h*80.00);
                }
                else hight = (int)(h*80.00);
            }
            MondayConsulting[MondayConsultingCounter].setBounds(320,Y+80,80,hight);
            MondayConsulting[MondayConsultingCounter].setOpaque(true);
            MondayConsulting[MondayConsultingCounter].setBackground(color);
            MondayConsulting[MondayConsultingCounter].setForeground(Color.WHITE);
            MondayConsulting[MondayConsultingCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(MondayConsulting[MondayConsultingCounter]);
            MondayConsultingCounter++;

        }

        //for tuesday consulting
        try
        {
            String query3="select consulting.CONS_START ,consulting.CONS_END from consulting WHERE consulting.TEC_ID='"+teacher.id+"' and consulting.SAMI_ID=(select max(SAMI_ID) from consulting) and consulting.DAY='Tuesday'";
            ResultSet result3 = SqlCon.getResult(query3);
            while(result3.next())
            {
                String start = result3.getString(1);
                String end = result3.getString(2);
                TuesdayConsultingList.add(new ClassTime("Consulting",teacher.room,start,end));
            }
        }
        catch(Exception ex2)
        {
            System.out.println("problem in Consulting");
        }
        int TuesdayConsultingCounter=0;
        Y = 0;
        hight=0;
        Iterator itr5 = TuesdayConsultingList.iterator();

        while(itr5.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime tucon = (ClassTime)itr5.next();
            TuesdayConsulting[TuesdayConsultingCounter] = new JButton("<html>"+tucon.getSubjectName()+"<br/>Room NO:"+tucon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(tucon.StartTime);
            double Ftime = Double.parseDouble(tucon.FinishTime);

            //Time setting
            if((Stime == Math.round(Stime)))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
                double h = (Ftime-Stime);
                hight = 20+(int)(h*80.00);
            }
            if((Stime != Math.round(Stime)))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
                double h = (Ftime-Stime);
                if(h==.30)
                {
                    hight = 20+(int)(h*80.00);
                }
                else hight = (int)(h*80.00);
            }
            TuesdayConsulting[TuesdayConsultingCounter].setBounds(420,Y+80,80,hight);
            TuesdayConsulting[TuesdayConsultingCounter].setOpaque(true);
            TuesdayConsulting[TuesdayConsultingCounter].setBackground(color);
            TuesdayConsulting[TuesdayConsultingCounter].setForeground(Color.WHITE);
            TuesdayConsulting[TuesdayConsultingCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(TuesdayConsulting[TuesdayConsultingCounter]);
            TuesdayConsultingCounter++;

        }


        //for wednesday consulting
        try
        {
            String query3="select consulting.CONS_START ,consulting.CONS_END from consulting WHERE consulting.TEC_ID='"+teacher.id+"' and consulting.SAMI_ID=(select max(SAMI_ID) from consulting) and consulting.DAY='Wednesday'";
            ResultSet result3 = SqlCon.getResult(query3);
            while(result3.next())
            {
                String start = result3.getString(1);
                String end = result3.getString(2);
                WednesdayConsultingList.add(new ClassTime("Consulting",teacher.room,start,end));
            }
        }
        catch(Exception ex2)
        {
            System.out.println("problem in Consulting");
        }
        int WednesdayConsultingCounter=0;
        Y = 0;
        hight=0;
        Iterator itr6 = WednesdayConsultingList.iterator();

        while(itr6.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime wecon = (ClassTime)itr6.next();
            WednesdayConsulting[WednesdayConsultingCounter] = new JButton("<html>"+wecon.getSubjectName()+"<br/>Room NO:"+wecon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(wecon.StartTime);
            double Ftime = Double.parseDouble(wecon.FinishTime);

            //Time setting
            if((Stime == Math.round(Stime)))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
                double h = (Ftime-Stime);
                hight = (int)(h*80.00);
            }
            if((Stime != Math.round(Stime)))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
                double h = (Ftime-Stime);
                if(h==.30)
                {
                    hight = 20+(int)(h*80.00);
                }
                else hight = (int)(h*80.00);
            }
            WednesdayConsulting[WednesdayConsultingCounter].setBounds(520,Y+80,80,hight);
            WednesdayConsulting[WednesdayConsultingCounter].setOpaque(true);
            WednesdayConsulting[WednesdayConsultingCounter].setBackground(color);
            WednesdayConsulting[WednesdayConsultingCounter].setForeground(Color.WHITE);
            WednesdayConsulting[WednesdayConsultingCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(WednesdayConsulting[WednesdayConsultingCounter]);
            WednesdayConsultingCounter++;

        }


        //for Thursday consulting
        try
        {
            String query3="select consulting.CONS_START ,consulting.CONS_END from consulting WHERE consulting.TEC_ID='"+teacher.id+"' and consulting.SAMI_ID=(select max(SAMI_ID) from consulting) and consulting.DAY='Thursday'";
            ResultSet result3 = SqlCon.getResult(query3);
            while(result3.next())
            {
                String start = result3.getString(1);
                String end = result3.getString(2);
                ThursdayConsultingList.add(new ClassTime("Consulting",teacher.room,start,end));
            }
        }
        catch(Exception ex2)
        {
            System.out.println("problem in Consulting");
        }
        int ThursdayConsultingCounter=0;
        Y = 0;
        hight=0;
        Iterator itr7 = ThursdayConsultingList.iterator();

        while(itr7.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime thucon = (ClassTime)itr7.next();
            ThursdayConsulting[ThursdayConsultingCounter] = new JButton("<html>"+thucon.getSubjectName()+"<br/>Room NO:"+thucon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(thucon.StartTime);
            double Ftime = Double.parseDouble(thucon.FinishTime);

            //Time setting
            if((Stime == Math.round(Stime)))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
                double h = (Ftime-Stime);
                hight = (int)(h*80.00);
            }
            if((Stime != Math.round(Stime)))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
                double h = (Ftime-Stime);
                if(h==.30)
                {
                    hight = 20+(int)(h*80.00);
                }
                else hight = (int)(h*80.00);
            }
            ThursdayConsulting[ThursdayConsultingCounter].setBounds(620,Y+80,80,hight);
            ThursdayConsulting[ThursdayConsultingCounter].setOpaque(true);
            ThursdayConsulting[ThursdayConsultingCounter].setBackground(color);
            ThursdayConsulting[ThursdayConsultingCounter].setForeground(Color.WHITE);
            ThursdayConsulting[ThursdayConsultingCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(ThursdayConsulting[ThursdayConsultingCounter]);
            ThursdayConsultingCounter++;

        }


        // for sunday and tuesday
        Sunday = new ArrayList<ClassTime>();
        try
        {
            String query4 ="SELECT section.COR_NAME,section.ROOM_NO,cor_by_sami.START_AT,cor_by_sami.END_TIME from section,cor_by_sami where cor_by_sami.TEC_ID='"+teacher.id+"' and cor_by_sami.DAY1='Sunday' and section.ID=cor_by_sami.SEC_ID and cor_by_sami.SAMI_ID=(select max(SAMI_ID) from cor_by_sami)";
            ResultSet result4 = SqlCon.getResult(query4);
            while(result4.next())
            {
                String name=result4.getString(1);
                String room=result4.getString(2);
                String start=result4.getString(3);
                String end = result4.getString(4);
                Sunday.add(new ClassTime("Default",name,room,start,end));
            }
        }
        catch(Exception e)
        {
            System.out.println("Problem in DB");
        }

        Iterator itr = Sunday.iterator();
        Y = 0;
        hight=0;
        int SundayTuesdayClassCounter=0;
        while(itr.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime sun = (ClassTime)itr.next();
            //SundayClass[SundayClassCounter] = new JLabel("<html>"+sun.getSubjectName()+"<br>Room :"+sun.getRoomNo()+"</html>",SwingConstants.CENTER);
            SundayClass[SundayTuesdayClassCounter] = new JButton("<html>"+sun.getSubjectName()+"<br/>Room NO:"+sun.getRoomNo()+"</html>");
            TuesdayClass[SundayTuesdayClassCounter] = new JButton("<html>"+sun.getSubjectName()+"<br/>Room NO:"+sun.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(sun.StartTime);
            double Ftime = Double.parseDouble(sun.FinishTime);
            String ClassType = "";

            if(Math.round(Ftime-Stime)==3)
            {
                ClassType = "Lab";
            }

            if(Math.round(Ftime-Stime)==2)
            {
                ClassType = "CS_Theory";
            }

            if((Ftime-Stime)==1.5)
            {
                ClassType = "Theory";
            }


            //Time setting
            if(((Stime == Math.round(Stime))))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
            }
            if(((Stime != Math.round(Stime))) )
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
            }



            //Button Setting
            if(ClassType == "CS_Theory")
            {
                SundayClass[SundayTuesdayClassCounter].setBounds(220,Y+80,80,160);
                TuesdayClass[SundayTuesdayClassCounter].setBounds(420,Y+80,80,160);
            }
            else if(ClassType == "Theory")
            {
                SundayClass[SundayTuesdayClassCounter].setBounds(220,Y+80,80,130);
                TuesdayClass[SundayTuesdayClassCounter].setBounds(420,Y+80,80,130);
            }
            else if (ClassType == "Lab")
            {
                SundayClass[SundayTuesdayClassCounter].setBounds(220,Y+80,80,240);
                TuesdayClass[SundayTuesdayClassCounter].setBounds(420,Y+80,80,240);
            }

            SundayClass[SundayTuesdayClassCounter].setOpaque(true);
            SundayClass[SundayTuesdayClassCounter].setBackground(color);
            SundayClass[SundayTuesdayClassCounter].setForeground(Color.WHITE);
            SundayClass[SundayTuesdayClassCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(SundayClass[SundayTuesdayClassCounter]);
            TuesdayClass[SundayTuesdayClassCounter].setOpaque(true);
            TuesdayClass[SundayTuesdayClassCounter].setBackground(color);
            TuesdayClass[SundayTuesdayClassCounter].setForeground(Color.WHITE);
            TuesdayClass[SundayTuesdayClassCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(TuesdayClass[SundayTuesdayClassCounter]);
            SundayTuesdayClassCounter++;

        }

        // for monday and wednesday

        Monday = new ArrayList<ClassTime>();
        try
        {
            String query4 ="SELECT section.COR_NAME,section.ROOM_NO,cor_by_sami.START_AT,cor_by_sami.END_TIME from section,cor_by_sami where cor_by_sami.TEC_ID='"+teacher.id+"' and cor_by_sami.DAY1='Monday' and section.ID=cor_by_sami.SEC_ID and cor_by_sami.SAMI_ID=(select max(SAMI_ID) from cor_by_sami)";
            ResultSet result4 = SqlCon.getResult(query4);
            while(result4.next())
            {
                String name=result4.getString(1);
                String room=result4.getString(2);
                String start=result4.getString(3);
                String end = result4.getString(4);
                Monday.add(new ClassTime("Default",name,room,start,end));
            }
        }
        catch(Exception e)
        {
            System.out.println("Problem in DB");
        }
        Iterator itr2 = Monday.iterator();
        Y = 0;
        hight=0;
        int MondayWednesdayClassCounter=0;
        while(itr2.hasNext())
        {
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            Color color = new Color(red, green, blue);

            ClassTime mon = (ClassTime)itr2.next();
            //SundayClass[SundayClassCounter] = new JLabel("<html>"+sun.getSubjectName()+"<br>Room :"+sun.getRoomNo()+"</html>",SwingConstants.CENTER);
            Mondayclass[MondayWednesdayClassCounter] = new JButton("<html>"+mon.getSubjectName()+"<br/>Room NO:"+mon.getRoomNo()+"</html>");
            WednesdayClass[MondayWednesdayClassCounter] = new JButton("<html>"+mon.getSubjectName()+"<br/>Room NO:"+mon.getRoomNo()+"</html>");

            double Stime = Double.parseDouble(mon.StartTime);
            double Ftime = Double.parseDouble(mon.FinishTime);

            String ClassType = "";

            if(Math.round(Ftime-Stime)==3)
            {
                ClassType = "Lab";
            }

            if(Math.round(Ftime-Stime)==2)
            {
                ClassType = "CS_Theory";
            }

            if((Ftime-Stime)==1.5)
            {
                ClassType = "Theory";
            }

            //Time setting
            if(((Stime == Math.round(Stime))))
            {
                double t = (1.00+(Stime-8.00));
                Y = (int)(t*80.00);
            }
            if(((Stime != Math.round(Stime))))
            {
                double t = 1.00+(Stime-8.00);
                Y = 20+(int)(t*80.00);
            }



            //Button Setting
            if(ClassType == "CS_Theory")
            {
                Mondayclass[MondayWednesdayClassCounter].setBounds(320,Y+80,80,160);
                WednesdayClass[MondayWednesdayClassCounter].setBounds(520,Y+80,80,160);
            }
            else if(ClassType == "Theory")
            {
                Mondayclass[MondayWednesdayClassCounter].setBounds(320,Y+80,80,130);
                WednesdayClass[MondayWednesdayClassCounter].setBounds(520,Y+80,80,130);
            }
            else if (ClassType == "Lab")
            {
                Mondayclass[MondayWednesdayClassCounter].setBounds(320,Y+80,80,240);
                WednesdayClass[MondayWednesdayClassCounter].setBounds(520,Y+80,80,240);
            }


            Mondayclass[MondayWednesdayClassCounter].setOpaque(true);
            Mondayclass[MondayWednesdayClassCounter].setBackground(color);
            Mondayclass[MondayWednesdayClassCounter].setForeground(Color.WHITE);
            Mondayclass[MondayWednesdayClassCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(Mondayclass[MondayWednesdayClassCounter]);
            WednesdayClass[MondayWednesdayClassCounter].setOpaque(true);
            WednesdayClass[MondayWednesdayClassCounter].setBackground(color);
            WednesdayClass[MondayWednesdayClassCounter].setForeground(Color.WHITE);
            WednesdayClass[MondayWednesdayClassCounter].setFont(new Font("Courier New", Font.PLAIN,15));
            panel.add(WednesdayClass[MondayWednesdayClassCounter]);
            MondayWednesdayClassCounter++;

        }






        day1= new JLabel("    Saturday");
        day1.setBounds(120,110,80,20);
        day1.setBackground(Color.darkGray);
        day1.setForeground(Color.green);
        day1.setOpaque(true);
        panel.add(day1);
        panel.add(vsepa);

        day2= new JLabel("      Sunday");
        day2.setBounds(220,110,80,20);
        day2.setBackground(Color.darkGray);
        day2.setForeground(Color.green);
        day2.setOpaque(true);
        panel.add(day2);
        panel.add(vsepa);

        day3= new JLabel("      Monday");
        day3.setBounds(320,110,80,20);
        day3.setBackground(Color.darkGray);
        day3.setForeground(Color.green);
        day3.setOpaque(true);
        panel.add(day3);
        panel.add(vsepa);

        day4= new JLabel("     Tuesday");
        day4.setBounds(420,110,80,20);
        day4.setBackground(Color.darkGray);
        day4.setForeground(Color.green);
        day4.setOpaque(true);
        panel.add(day4);
        panel.add(vsepa);

        day5= new JLabel("  Wednesday");
        day5.setBounds(520,110,80,20);
        day5.setBackground(Color.darkGray);
        day5.setForeground(Color.green);
        day5.setOpaque(true);
        panel.add(day5);
        panel.add(vsepa);

        day6= new JLabel("    Thursday");
        day6.setBounds(620,110,80,20);
        day6.setBackground(Color.darkGray);
        day6.setForeground(Color.green);
        day6.setOpaque(true);
        panel.add(day6);
        panel.add(vsepa);

        day7= new JLabel("        Friday");
        day7.setBounds(720,110,80,20);
        day7.setBackground(Color.darkGray);
        day7.setForeground(Color.green);
        day7.setOpaque(true);
        panel.add(day7);
        panel.add(vsepa);

        time =new JLabel(" Time");
        time.setFont(new Font("Courier New", Font.ITALIC,30));
        time.setForeground(Color.RED);
        int y=120;
        int a = 8, b = 30;
        boolean fraction = true;
        time.setBounds(0,110,100,20);
        panel.add(time);


        String convertedTime="";
        for(int i=0; i<30; i++)
        {
            y+=40;


            if(fraction==true)
            {
                String setTime = a+":00";

                try
                {
                    String _24HourTime = setTime;
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(_24HourTime);
                    convertedTime = _12HourSDF.format(_24HourDt);
                }
                catch (Exception e) {}

                timelist[i]= new JLabel("  "+convertedTime);
                fraction = false;
            }
            else
            {
                String setTime = a+":"+b;

                try
                {
                    String _24HourTime = setTime;
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(_24HourTime);
                    convertedTime = _12HourSDF.format(_24HourDt);
                }
                catch (Exception e) {}

                timelist[i]= new JLabel("  "+convertedTime);
                a++;
                fraction = true;

            }

            timelist[i].setBounds(0,y,100,20);
            timelist[i].setFont(new Font("Courier New", Font.BOLD,15));
            panel.add(timelist[i]);
            panel.add(sepa);

        }



        //Now we try scrollpane on a panel
        JScrollPane scrollpane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jf.add(scrollpane);
        //jf.add(panel);
        jf.setSize(900,1200);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setMinimumSize(new Dimension(900, 900));
        jf.setLocation(600,0);
        //jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
