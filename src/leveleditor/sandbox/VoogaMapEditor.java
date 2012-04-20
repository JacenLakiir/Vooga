package leveleditor.sandbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;                   

public class VoogaMapEditor extends JFrame implements ActionListener,ChangeListener{   
    private static final long serialVersionUID = 1L;
    private JMenu[] jMenu = {                           //菜单项  
           new JMenu("File"),  
    };  
    private JMenuItem[] jFileItem = {                   //子菜单项  
	    new JMenuItem("Open"),  
    };  
      private JMenuBar myMenuBar = new JMenuBar(); 
      JFileChooser myFileChooser = new JFileChooser();   
      JSlider jSliderX = new JSlider(JSlider.HORIZONTAL,
10,70,60);                                                   
//创建两个JSlider控件  
      JSlider jSliderY = new JSlider(JSlider.VERTICAL,10,70,60);  
      JLabel jLabel_rows = new JLabel("地图行数:");       //文本  
      JTextField jTextField_rows = new JTextField("10");  //输入框     
      JLabel jLabel_cols = new JLabel("地图列数:");       //文本  
      JTextField jTextField_cols = new JTextField("10");  //输入框  
      JButton jButton = new JButton("确定");                //确定按钮  
      JSpinner jSpinnerX = new JSpinner();            
//创建两个JSpinner控件  
      JSpinner jSpinnerY = new JSpinner();  
      JScrollPane jsp;                                //滚动窗口  
      //SplitPanel sp;  
      public VoogaMapEditor(){                             //构造器  
          for(JMenuItem item : jFileItem){             
//将子菜单添加到文件菜单下  
              jMenu[0].add(item);  
              item.addActionListener(this);  
          }  
          for(JMenu temp: jMenu){                     //将菜单项添加到菜单栏  
              myMenuBar.add(temp);  
          }  
          this.setJMenuBar(myMenuBar);       
                                                  //初始化垂直分割拖拉条  
          jSliderY.setBounds(560,10,40,100);          //设置位置和大小  
          jSliderY.setMinorTickSpacing(2);  
          jSliderY.setMajorTickSpacing(20);  
          jSliderY.setPaintTicks(true);  
          jSliderY.setPaintLabels(true);  
          this.add(jSliderY);                         //添加到窗口中  
          jSliderY.setValue(30);                      //设置初始值  
          jSliderY.addChangeListener(this);               //添加监听  
                                                  //初始化水平分割拖拉条  
          jSliderX.setBounds(10,410,100,40);          //设置位置和大小  
          jSliderX.setMinorTickSpacing(2);  
          jSliderX.setMajorTickSpacing(20);  
          jSliderX.setPaintTicks(true);  
          jSliderX.setPaintLabels(true);  
          this.add(jSliderX);                         //添加到窗口中  
          jSliderX.setValue(30);                      //设置初始值  
          jSliderX.addChangeListener(this);               //添加监听  
          jSpinnerX.setBounds(120, 410, 50, 20);          //设置位置和大小  
          this.add(jSpinnerX);                            //添加到窗口中  
          jSpinnerX.setValue(30);                     //设置初始值  
          jSpinnerX.addChangeListener(this);          //添加监听  
          jSpinnerY.setBounds(560,120,40,20);         //设置大小和位置  
          this.add(jSpinnerY);                            //添加到窗口中  
          jSpinnerY.setValue(30);                     //设置初始值  
          jSpinnerY.addChangeListener(this);          //添加监听  
                                                  //输入自定义地图行数的  
                                                    //文本框  
          jLabel_rows.setBounds(190,410,60,20);           //设置大小和位置  
          this.add(jLabel_rows);                      //添加到窗口中  
          jTextField_rows.setBounds(245,410,60,20);       //设置大小和位置  
          this.add(jTextField_rows);                      //添加到窗口中  
                                                  //输入自定义地图列数的  
                                                    //文本框  
          jLabel_cols.setBounds(320,410,60,20);           //设置大小和位置  
          this.add(jLabel_cols);                      //添加到窗口中  
          jTextField_cols.setBounds(375,410,60,20);       //设置大小和位置  
          this.add(jTextField_cols);                      //添加到窗口中  
          jButton.setBounds(450,410,60,20);               //确定按钮  
          this.add(jButton);                          //添加按钮到窗口  
          jButton.addActionListener(this);                //为按钮添加监听  
          this.setTitle("地图设计器 V0.1");                    //设置标题  
          this.setLayout(null);                       //设置窗口的布局  
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //关闭按钮  
          this.setBounds(100, 100, 640, 500);         //设置窗口的大小和位置  
          this.setVisible(true);                      //设置窗口的可见性  
      }  
      public void actionPerformed(ActionEvent e) {        //实现接口中的方法  
          //该处省略了方法的实现，将在之后的某处给出  
      }  
      public void stateChanged(ChangeEvent e) {           //实现接口中的方法  
          //该处省略了方法的实现，将在之后的某处给出  
      }  

      }  