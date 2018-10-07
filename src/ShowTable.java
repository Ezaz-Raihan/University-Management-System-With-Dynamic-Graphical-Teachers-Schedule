import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

class ShowTable
{
    JFrame frame = new JFrame();
    SqlCon connection = new SqlCon();
    JTable jt=new JTable();
    JScrollPane sp=new JScrollPane(jt);
    JPanel jp = new JPanel();
    DefaultTableModel model = new DefaultTableModel();

    public ShowTable(String quary,String TName)
    {
        frame.setLocation(500,10);
        jp.add(sp);
        sp.setBounds(0,0,600,500);
        frame.setContentPane(jp);
        //frame.add(sp);

        try
        {
            ResultSet rs = SqlCon.getResult(quary);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            Vector <String> column =new Vector<>(c);
            for(int i=1; i<=c; i++)
            {
                String x =rsmd.getColumnName(i);
                column.add(x);
            }
            model.setColumnIdentifiers(column);
            Vector <String> row ;
            while(rs.next())
            {
                row =new Vector<>(c);
                for(int i=1; i<=c; i++)
                {
                    row.add(rs.getString(i));
                }
                model.addRow(row);
            }
            jt.setModel(model);
        }
        catch(Exception ex)
        {
            System.out.println("opp Erroe");
        }

        JButton delete =new JButton("Delete");
        delete.setBounds(100,520,20,20);
        frame.add(delete);
        delete.addActionListener(e ->
        {
            try
            {
                int rowno = jt.getSelectedRow();
                String select = model.getValueAt(rowno,0).toString();
                String deleteQuary = "delete from "+TName+" where ID ='"+select+"'";
                SqlCon.st.execute(deleteQuary);
                JOptionPane.showMessageDialog(null,"Data Deleted");
                frame.setVisible(false);
                //new AdminPanel();
            }
            catch(Exception ex)
            {
                String wrong="Please select A Row First";
                JOptionPane.showMessageDialog(null,wrong);
            }
        });

        JButton cancel =new JButton("Back");
        cancel.setBounds(200,800,20,20);
        frame.add(cancel);
        cancel.addActionListener(e ->
        {
            frame.setVisible(false);
            //new AdminPanel();
        });



        frame.setSize(500,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}