package window.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Executor;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import communication.udp.StateUpdateListener;
import data.communication.StateData;
//import main.SettingManager;
import window.logger.LogMessageAdapter;
//import window.subMain.SubMain;
//import window.subMenu.SubMenu;

public class MainWindow extends JFrame /*implements ActionListener*/  implements LogMessageAdapter,StateUpdateListener{
	private static final long serialVersionUID = 1L;
	
	// window size
	public static final int SIZE_X = 400;
	public static final int SIZE_Y = 250;

	// logo Image
	//private JLabel logo = new JLabel( new ImageIcon("./resource/logo.png"), JLabel.LEFT );

	// Background color
	//public static final Color BACK_COLOR = Color.WHITE;
	
	// main JPanel
	//private JPanel main_panel = new JPanel();
	
	// mode label
	private JLabel mode_label = new JLabel();

	// sub JPanel
	//public SubMenu sub_menu;
	//public SubMain sub_main;
	
	// OperationLock
	//public OperationLock ope_lock = new OperationLock();

	
	public MainWindow(Executor ex) {
		//sub_menu = new SubMenu();
		//sub_main = new SubMain(ex, sm, ope_lock);

		this.setSize(SIZE_X, SIZE_Y);
		this.setLocationRelativeTo(null);
		this.setTitle("ChibaRobo Sound Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label1 = new JLabel("Sound Manager");
	    label1.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
	    
//		
//		// setup left JPanel contents
//		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
//		//sub_menu.setAlignmentX(Component.CENTER_ALIGNMENT);
//		
//		// setup left JPanel
//		left_panel.setBackground(WindowMain.BACK_COLOR);
//		left_panel.setOpaque(true);
//		left_panel.setLayout( new BoxLayout(left_panel, BoxLayout.Y_AXIS) );
//		left_panel.add(logo);
//		left_panel.add(sub_menu);
//		left_panel.add(ope_lock);
//		
//		// setup main JPanel
//		main_panel.setLayout(new BorderLayout());
//		main_panel.add(left_panel, BorderLayout.WEST);
//		main_panel.add(sub_main, BorderLayout.CENTER);
		
	    JPanel p = new JPanel();
	    p.add(label1);
	    p.add(mode_label);
		
		// add to main JFrame
		//this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(p, BorderLayout.CENTER);
		this.setVisible(true);

		
//		// set listener
//		this.sub_menu.menu_list.add_MenuClickedListener(sub_main);
//		
//		this.ope_lock.add_OpeLockListener(sub_main.card_top.server_panel.connection_panel);
//		this.ope_lock.add_OpeLockListener(sub_main.card_setting.param_setting_panel);
//		this.ope_lock.add_OpeLockListener(sub_main.card_setting.param_setting_panel.setting_new_panel);
//		this.ope_lock.add_OpeLockListener(sub_main.card_setting.update_data_panel);
	}

	@Override
	public void log_println(String x){
//		sub_main.card_log.log_println(x);
		System.out.print(x + System.lineSeparator());
	}
	@Override
	public void log_print(String x){
//		sub_main.card_log.log_println(x);
		System.out.print(x);
	}

	@Override
	public void log_print(Exception e) {
		// エラーのスタックトレースを表示
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		log_println(sw.toString());
	}
	
	public LogMessageAdapter get_LogMessageAdapter(){
		return this;
	}

	@Override
	public void state_update(StateData state) {
		this.mode_label.setText("Mode: " + state.get_mode());
	}
}