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
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

class ShowNotice
{
    JButton delete=new JButton("Delete");
    JTable jtable ;
    JFrame frame=new JFrame("Notices... ");
    public ShowNotice(Teacher teacher,int panel)
    {
        frame.setLocation(600,10);
        if(panel == 2)
        {
            delete.setEnabled(false);
        }
        try
        {
            int rowSize=0;
            int rcount=0,ccount=0;
            String query1 = "Select count(ID) from tec_notice where TEC_ID ='"+teacher.id+"'";
            ResultSet result1 = SqlCon.getResult(query1);
            while(result1.next())
            {
                rowSize=result1.getInt(1);
            }
            String [][] data = new String[rowSize][3];
            String query2 = "select DATE,TIME,NOTICE from tec_notice where TEC_ID ='"+teacher.id+"' order by ID desc";
            ResultSet result2 = SqlCon.getResult(query2);
            while(result2.next())
            {
                data[rcount][ccount] = result2.getString(1);
                ccount++;
                data[rcount][ccount] = result2.getString(2);
                ccount++;
                data[rcount][ccount] = result2.getString(3);
                ccount=0;
                rcount++;
            }
            String columnName[] = {"DATE","TIME","NOTICE"};
            jtable = new JTable(data,columnName);


            JScrollPane sp = new JScrollPane(jtable);
            sp.setBounds(0,0,600,500);
            frame.add(sp);


        }
        catch(SQLException se)
        {
            System.out.println("Problem in Consulting DB");
        }

        delete.setBounds(200,500,80,30);
        frame.add(delete);
        delete.addActionListener(e ->
        {
            try{
                int rowno = jtable.getSelectedRow();
                String selectdate = jtable.getValueAt(rowno,0).toString();
                String selecttime = jtable.getValueAt(rowno,1).toString();
                String query ="Delete from tec_notice where DATE = '"+selectdate+"' and TIME = '"+selecttime+"'";
                SqlCon.st.execute(query);
                frame.setVisible(false);
                //new TeacherPanel(teacher);
            }
            catch(SQLException ex)
            {
                System.out.println("Problem In Consulting DB");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(delete,"Plese Selecct a Row First");
            }
        });
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}