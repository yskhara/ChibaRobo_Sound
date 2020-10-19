package main;

import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import communication.udp.StateUpdateListener;
//import communication.console.TcpSocket;
import communication.udp.UdpSocket;
import data.communication.StateData;
//import data.communication.FileDataManager;
//import data.robot.RoboList;
//import data.team.TeamList;
//import data.tournament.Tournament;
import main.ChibaRobo_Sound;
//import main.SettingManager;
import window.main.MainWindow;

public class ChibaRobo_Sound implements StateUpdateListener {

	public ChibaRobo_Sound() {
		Executor ex = Executors.newCachedThreadPool();
		MainWindow main_window = new MainWindow(ex);
		// SettingManager sm = new SettingManager();
		// FileDataManager fdm = new
		// FileDataManager(window_main.get_LogMessageAdapter(), sm);
		UdpSocket udp = new UdpSocket(ex, main_window.get_LogMessageAdapter());
		
		var sound_manager = new SoundManager(ex, main_window.get_LogMessageAdapter());
		ex.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sound_manager.loadAudioFiles();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// ---------------------------------------
		// set instance
		// ---------------------------------------
//		mainwindow.sub_main.card_tournament.main_view.set_robo_list(robo_list);
//		mainwindow.sub_main.card_tournament.main_view.set_team_list(team_list);
//		mainwindow.sub_main.card_tournament.main_view.set_tournament(tour);
//		
		// ---------------------------------------
		// set listener
		// ---------------------------------------
		// UDP Packet Listener
		udp.add_StateUpdateListener(main_window);
//		udp.add_ServerUpdateListener(window_main.sub_main.card_top.server_panel.connection_panel);
//		udp.add_ServerUpdateListener(window_main.sub_main.card_tournament.update_button);
//		udp.add_ServerUpdateListener(robo_list);
//		udp.add_ServerUpdateListener(team_list);
//		udp.add_ServerUpdateListener(tour.get_game_as_SUL());
//		
//		udp.add_StateUpdateListener(window_main.sub_main.card_top.show_panel);
//		udp.add_StateUpdateListener(window_main.sub_main.card_operation.show_panel);
//		
//		udp.add_TournamentUpdateListener(tour);
//		
//		// TCP connection listener
//		window_main.sub_main.card_top.server_panel.connection_panel.set_TcpConnectionListener(tcp);
//		
//		// mode listener
//		window_main.sub_main.card_operation.show_ope_panel.b_home.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_newgame.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_game.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_result.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_interview.add_SetModeListener(tcp);
//		
//		window_main.sub_main.card_operation.show_ope_panel.b_left.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_right.add_SetModeListener(tcp);
//
//		window_main.sub_main.card_operation.show_ope_panel.b_preshow.add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_opening.add_SetModeListener(tcp);
//
//		window_main.sub_main.card_operation.show_ope_panel.b_show[0].add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_show[1].add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.show_ope_panel.b_show[2].add_SetModeListener(tcp);
//
//		
//		window_main.sub_main.card_operation.game_ope_panel.winner_button[0].add_SetModeListener(tcp);
//		window_main.sub_main.card_operation.game_ope_panel.winner_button[1].add_SetModeListener(tcp);
//
//		// score listener
//		window_main.sub_main.card_operation.game_ope_panel.set_SetScoreListener(tcp);
//		
//		// winner listener
//		window_main.sub_main.card_operation.game_ope_panel.winner_button[0].set_SetWinnerListener(tcp);
//		window_main.sub_main.card_operation.game_ope_panel.winner_button[1].set_SetWinnerListener(tcp);
//
//		// clear data listener
//		window_main.sub_main.card_setting.update_data_panel.set_ClearDataListener(tcp);
//		// check integrity listener
//		window_main.sub_main.card_setting.update_data_panel.set_CheckIntegrityListener(fdm);
//		// upload data listener
//		window_main.sub_main.card_setting.update_data_panel.set_UploadDataListener(tcp);
//		// Update Database Listener
//		window_main.sub_main.card_tournament.update_button.add_UpdateDatabaseListener(robo_list);
//		window_main.sub_main.card_tournament.update_button.add_UpdateDatabaseListener(team_list);
//		window_main.sub_main.card_tournament.update_button.add_UpdateDatabaseListener(tour.get_game_as_UDL());
//		
//		// update tournament view listener
//		robo_list.set_UpdateTourViewListener(window_main.sub_main.card_tournament.get_UpdateTourViewListener());
//		team_list.set_UpdateTourViewListener(window_main.sub_main.card_tournament.get_UpdateTourViewListener());
//		tour.set_UpdateTourViewListener(window_main.sub_main.card_tournament.get_UpdateTourViewListener());
	}

	@Override
	public void state_update(StateData state) {
		String ret = "show";
		ret += "," + state.get_mode();
		ret += "," + state.get_c_start_time().getTimeInMillis();
		ret += "," + state.get_score()[0] + "," + state.get_score()[1];
		System.out.println(ret);
	}

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		//new ChibaRobo_Sound();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ChibaRobo_Sound();
				//Executor ex = Executors.newCachedThreadPool();
				//new MainWindow(ex);
			}
		});
	}

}
