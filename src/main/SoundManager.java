package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executor;

import javax.sound.sampled.*;

import window.logger.LogMessageAdapter;

class SoundManager implements Runnable {
	// audio streams for current background musics
	AudioInputStream normal_stream = null;
	AudioInputStream battle_stream = null;

	Vector<File> normal_sound_files = new Vector<File>();
	Vector<File> battle_sound_files = new Vector<File>();

	Iterator<File> normal_sound_iter = null;
	Iterator<File> battle_sound_iter = null;

	// audio streams for jingles
	AudioInputStream opening_stream = null;
	AudioInputStream preshow_stream = null;
	AudioInputStream vgoal_stream = null;
	AudioInputStream result_stream = null;
	AudioInputStream begin_countdown_stream = null;
	AudioInputStream begin_stream = null;
	AudioInputStream end_countdown_stream = null;
	AudioInputStream end_stream = null;

	Executor _ex;

	LogMessageAdapter _logger;

	public SoundManager(Executor ex, LogMessageAdapter logger) {
		_ex = ex;
		_logger = logger;
	}

	public void loadAudioFiles() throws FileNotFoundException {

		_logger.log_println("Loading normal_music...");

		for (var sound_file : new File(System.getProperty("user.dir") + "/sound_list/normal_music").listFiles()) {
			normal_sound_files.add(sound_file);
			_logger.log_print("    Loading " + sound_file.getAbsolutePath() + " ...");

			try {
				AudioInputStream stream = AudioSystem.getAudioInputStream(sound_file);
				// 単一のオーディオ形式を含む指定した情報からデータラインの情報オブジェクトを構築
				DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, stream.getFormat());
				_logger.log_println(dataLineInfo.toString());
				if (!AudioSystem.isLineSupported(dataLineInfo)) {
					throw new UnsupportedAudioFileException();
				}
				stream.close(); // don't forget!
				_logger.log_println("done.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				_logger.log_println("failed.");
				_logger.log_print(e.toString());
			}
		}

		_logger.log_println("Loading battle_music...");
		for (var sound_file : new File(System.getProperty("user.dir") + "/sound_list/battle_music").listFiles()) {
			battle_sound_files.add(sound_file);
			_logger.log_println("  " + sound_file.getName());
		}

		normal_sound_iter = normal_sound_files.iterator();
		if (!normal_sound_iter.hasNext()) {
			throw new FileNotFoundException("normal_music was not found.");
		}

		// at least one normal (audio) file exists
		try {
			normal_stream = AudioSystem.getAudioInputStream(normal_sound_iter.next());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			_logger.log_print(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			_logger.log_print(e);
			e.printStackTrace();
		}

		battle_sound_iter = battle_sound_files.iterator();
		if (!battle_sound_iter.hasNext()) {
			throw new FileNotFoundException("battle_music was not found.");
		}

		// at least one battle (audio) file exists
		try {
			battle_stream = AudioSystem.getAudioInputStream(battle_sound_iter.next());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			_logger.log_print(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			_logger.log_print(e);
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
