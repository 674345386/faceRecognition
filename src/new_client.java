import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

import org.json.JSONObject;

import java.lang.reflect.Member;

public class new_client {

    private JFrame frame;
    //
    private JLabel cameraView;
    private JLabel picView;
    private Camera_picture camera_picture;
    private Face_dect_info fInfo;
    private String save_pic_path;
    private DrawRec drawRec;
    private String jsonresult;
    private JTable table;
    private JTable table_1;
    private int i=1;

    public void doMyjob() throws Exception {
        this.camera_picture = new Camera_picture();
        this.fInfo = new Face_dect_info();
        this.picView = camera_picture.jLabel_2;
        this.cameraView = camera_picture.jLabel;
        // this.save_pic_path=camera_picture.savedImageFile;
//        this.save_pic_path = "H:\\xiang"+i+".jpg";
        this.save_pic_path = "H:\\xiang"+i+".jpg";
        this.drawRec = new DrawRec();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws Exception {
        // new_client window = new new_client();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new_client window = new new_client();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     *
     * @throws Exception
     */
    public new_client() throws Exception {
        doMyjob();
        initialize();
        // camera_picture.open_camera();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("照片人脸识别");
        frame.setBounds(100, 100, 1410, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblPicview = cameraView;
        lblPicview.setBorder(new LineBorder(Color.gray, 1));
        lblPicview.setBounds(105, 20, 640, 480);

        frame.getContentPane().add(lblPicview);

        JLabel lblNewLabel = picView;
        lblNewLabel.setBounds(750, 20, 640, 480);
        lblNewLabel.setBorder(new LineBorder(Color.gray, 1));
        frame.getContentPane().add(lblNewLabel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        menuBar.setBounds(0, 0, 105, 20);
        frame.getContentPane().add(menuBar);

        JMenu mnNewMenu = new JMenu("文件File");
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu("New menu");
        mnNewMenu.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("New menu");
        mnNewMenu.add(mnNewMenu_2);

        JMenu mnNewMenu_3 = new JMenu("New menu");
        mnNewMenu.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
        mnNewMenu.add(mntmNewMenuItem);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setBounds(0, 500, 1410, 210);
        frame.getContentPane().add(tabbedPane);

        final JTextArea textArea_1 = new JTextArea();
        textArea_1.setText("信息一");
        tabbedPane.addTab("New tab", null, textArea_1, null);

        //

        final JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        tabbedPane.addTab("New 3", null, panel, null);
        panel.setLayout(null);
        //
        final JPanel panel_2 = new JPanel();
        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        tabbedPane.addTab("识别信息", null, panel_2, null);
        panel_2.setLayout(new CardLayout(0, 0));

        final JPanel panel_5 = new JPanel();
        panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        tabbedPane.addTab("检测信息", null, panel_5, null);
        panel_5.setLayout(new CardLayout(0, 0));


        // DefaultTableModel defaultTableModel = new
        // DefaultTableModel(rowData,columnNames);
        // table = new JTable(defaultTableModel);
        // panel_2.add(table, "name_22223951814108");
        //
        // java.util.List<Member> members = null;
        // String[] arr=new String[5];
        // arr[0]="123";
        // arr[1]="123";
        // arr[2]="123";
        // arr[3]="123";
        // arr[4]="123";

        // 添加数据到表格
        // defaultTableModel.addRow(arr);

        //

        //
        // DefaultListModel<String> listModel = new DefaultListModel<String>();
        // listModel.addElement("Debbie Scott");
        // listModel.addElement("Scott Hommel");
        // listModel.addElement("Scatt Hommel");
        // People_list people_list ;

        // JList list = new JList(people_list);
        //
        // list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        // list.setBounds(10, 10, 150, 150);
        // panel.add(list);

        //
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBackground(Color.GRAY);
        tabbedPane_1.setBorder(new LineBorder(Color.GRAY, 1, true));
        tabbedPane_1.setBounds(0, 16, 105, 300);
        frame.getContentPane().add(tabbedPane_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.LIGHT_GRAY);
        panel_1.setBorder(null);
        tabbedPane_1.addTab("拍照识别", null, panel_1, null);
        //
        JButton btnNewButton_1 = new JButton("打开摄像头");
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    camera_picture.open_camera();
                    // JOptionPane.showMessageDialog(frame, "已经打开摄像头");
                    // camera_picture.grabPicture();
                    // frame.setVisible(true);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        panel_1.add(btnNewButton_1);

        JButton btnNewButton = new JButton("  拍取照片  ");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                camera_picture.grab_picture();
            }
        });

        panel_1.add(btnNewButton);
        JButton button = new JButton("照片人脸识别");
        button.setBackground(Color.LIGHT_GRAY);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonresult = fInfo.the_whole(save_pic_path);
                    System.err.println(jsonresult);
                    // peopleList.addElement("abc");
                    //
                    // DefaultListModel<String> listModel = new
                    // DefaultListModel<String>();
                    // listModel.addElement("Debbie Scott");
                    // listModel.addElement("Scott Hommel");
                    // listModel.addElement("Scatt Hommel");
                    People_list_model defaultTableModel = new People_list_model(jsonresult, 0);
                    // String[] arr=new String[5];
                    // arr[0]="123";
                    // arr[1]="123";
                    // arr[2]="123";
                    // arr[3]="123";
                    // arr[4]="123";
                    // defaultTableModel.addRow(arr);
                    table = new JTable(defaultTableModel);
                    table.setGridColor(Color.GRAY);
                    table.setSelectionBackground(Color.gray);
                    table.setSelectionForeground(Color.white);

                    panel_2.add(table, "name_22223951814108");
                    //
//                    JList list = new JList(new People_list(jsonresult));
//                    list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//                    list.setBounds(10, 10, 150, 150);
//                    panel.add(list);
                    //
                    drawRec.setJsonString(jsonresult);
                    textArea_1.setText(jsonresult);
                    drawRec.setPicPath(save_pic_path);
                    DrawRec.Face_jason face_jason = drawRec.new Face_jason();
                    // drawRec.draw(save_pic_path,50,50,150,150);
                    // picView.setIcon(drawRec.draw(save_pic_path));
                    picView.setIcon(face_jason.detectFace());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                }
            }
        });
        panel_1.add(button);

//		panel_1.add(button);

        /**
         * 拍照并添加face到相应person里面,使用前先识别
         */
        JButton btnNewButton_12 = new JButton("添加人脸到人物中");
        btnNewButton_12.setBackground(Color.LIGHT_GRAY);
        btnNewButton_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "输入人脸添加进哪个人物（person）");
                fInfo.people_add_face_useFaceID(jsonresult, input);
                JOptionPane.showMessageDialog(null, "add face to person succ");
            }
        });
        panel_1.add(btnNewButton_12);

        /**
         * 更换相片的路径  this.save_pic_path = "H:\\xiang1.jpg";  line78
         */
        JButton btnNewButton_11 = new JButton("Next Picture");
        btnNewButton_11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                i++;
                save_pic_path = "H:\\xiang"+i+".jpg";

                try {
                    jsonresult = fInfo.the_whole(save_pic_path);
                    System.err.println(jsonresult);
                    // peopleList.addElement("abc");
                    //
                    // DefaultListModel<String> listModel = new
                    // DefaultListModel<String>();
                    // listModel.addElement("Debbie Scott");
                    // listModel.addElement("Scott Hommel");
                    // listModel.addElement("Scatt Hommel");
                    People_list_model defaultTableModel = new People_list_model(jsonresult, 0);
                    // String[] arr=new String[5];
                    // arr[0]="123";
                    // arr[1]="123";
                    // arr[2]="123";
                    // arr[3]="123";
                    // arr[4]="123";
                    // defaultTableModel.addRow(arr);
                    table = new JTable(defaultTableModel);
                    table.setGridColor(Color.GRAY);
                    table.setSelectionBackground(Color.gray);
                    table.setSelectionForeground(Color.white);

                    panel_2.add(table, "name_22223951814108");
                    //
//                    JList list = new JList(new People_list(jsonresult));
//                    list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//                    list.setBounds(10, 10, 150, 150);
//                    panel.add(list);
                    //
                    drawRec.setJsonString(jsonresult);
                    textArea_1.setText(jsonresult);
                    drawRec.setPicPath(save_pic_path);
                    DrawRec.Face_jason face_jason = drawRec.new Face_jason();
                    // drawRec.draw(save_pic_path,50,50,150,150);
                    // picView.setIcon(drawRec.draw(save_pic_path));
                    picView.setIcon(face_jason.detectFace());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                }
        	}
        });
        btnNewButton_11.setBackground(Color.LIGHT_GRAY);
        panel_1.add(btnNewButton_11);
        //

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        tabbedPane_1.addTab("训练人脸", null, panel_3, null);

        JButton btn_people_creaate = new JButton("人物create");

        btn_people_creaate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String input = JOptionPane.showInputDialog(frame, "输入创建的人物名字");
                fInfo.people_create(input);
                System.out.println("创建人物成功");
                JOptionPane.showMessageDialog(null, "创建人物完成");
            }
        });
        panel_3.add(btn_people_creaate);

        JButton btnNewButton_3 = new JButton("人物delete");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "输入要删除的人物名字");
                fInfo.people_delete(input);
                JOptionPane.showMessageDialog(null, "删除人物完成");
            }
        });
        panel_3.add(btnNewButton_3);
        /**
         * group创建
         */
        JButton btnNewButton_2 = new JButton(" 创建group ");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "输入创建的group名");
                fInfo.group_create(input);
//				System.out.println(input);
                JOptionPane.showMessageDialog(null, input);

            }
        });
        panel_3.add(btnNewButton_2);

        JButton btnNewButton_4 = new JButton("人物添加进group");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fInfo.people_addIn_group();
                JOptionPane.showMessageDialog(null, "人物添加进group");
            }
        });
        panel_3.add(btnNewButton_4);

        JButton btnNewButton_6 = new JButton("group训练");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fInfo.train_identify_for_group();
                JOptionPane.showMessageDialog(null, "group训练完成");
            }
        });

        JButton btnNewButton_7 = new JButton("group删除");
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "输入删除的group名");
                fInfo.group_delete(input);
                JOptionPane.showMessageDialog(null, "删除group " + input);
            }
        });
        panel_3.add(btnNewButton_7);
        //

        JButton btnNewButton_10 = new JButton("train_for_verify");
        btnNewButton_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fInfo.train_verify_for_people();
            }
        });
        panel_3.add(btnNewButton_10);
        panel_3.add(btnNewButton_6);

        // -----------------------------信息查询------------------------------------

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        tabbedPane_1.addTab("信息查询", null, panel_4, null);

        JButton btnNewButton_5 = new JButton("group_getInfo");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "要查询的group");
                fInfo.group_getinfo(input);
                JOptionPane.showMessageDialog(null, "查询完成");
            }
        });

        /**
         * 对于一个待查询的Face列表（或者对于给定的Image中所有的Face），在一个Group中查询最相似的Person。
         *
         * 注意，当Group中的信息被修改之后（增加了Person, Face等），为了保证结果与最新数据一致，Group应当被重新train.
         * 见/train/identify 。否则调用此API时将使用最后一次train时的数据。 使用前，先识别
         */
        JButton btnNewButton_8 = new JButton("检测人物是否在group");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = fInfo.recognition_identify_face_inGroup();
                System.err.println(result);
                // peopleList.addElement("abc");
                //
                // DefaultListModel<String> listModel = new
                // DefaultListModel<String>();
                // listModel.addElement("Debbie Scott");
                // listModel.addElement("Scott Hommel");
                // listModel.addElement("Scatt Hommel");
                People_list_model defaultTableModel_2 = new People_list_model(result);
                String[] arr = new String[5];
                arr[0] = "123";
                arr[1] = "123";
                arr[2] = "123";
                arr[3] = "123";
                arr[4] = "123";
                defaultTableModel_2.addRow(arr);
                table_1 = new JTable(defaultTableModel_2);
                table_1.setGridColor(Color.GRAY);
                table_1.setSelectionBackground(Color.gray);
                table_1.setSelectionForeground(Color.white);

                panel_5.add(table_1, "name_135354393933360");
                //
            }
        });
        panel_4.add(btnNewButton_8);

        JButton btnNewButton_9 = new JButton("getresult_by_session");
        btnNewButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fInfo.info_getsession();
            }
        });
        panel_4.add(btnNewButton_9);
        panel_4.add(btnNewButton_5);

        /**
         * 获取一个Person的信息, 包括name, id, tag, 相关的face, 以及groups等信息
         */
        JButton btnNewButton_13 = new JButton("person_getInfo");
        btnNewButton_13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fInfo.people_getInfo();
                JOptionPane.showMessageDialog(null, "person_getInfo");
            }
        });


        // 已经转移
//		JButton button_1 = new JButton("isSamePerson");
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				fInfo.recognition_verify_face_isSamePerson();
//			}
//		});
//		
//		panel_4.add(button_1);

        panel_4.add(btnNewButton_13);

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
    }
}
