package weico.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * 向文件中写入历史记录
 *
 */
public class MessageHistoryOperation {
	// 单例模式
	private static MessageHistoryOperation writeMessageHistory = null;
	private String					inFile	= "D:/History.data";

	private MessageHistoryOperation() {
	}

	public static MessageHistoryOperation getInstance() {
		if (writeMessageHistory == null) {
			synchronized (MessageHistoryOperation.class) {
				if (writeMessageHistory == null) {
					writeMessageHistory = new MessageHistoryOperation();
				}
			}
		}
		return writeMessageHistory;
	}

	public void write(ClientJpanel clientJpanel) {
		String message = clientJpanel.getMessagePane().getText();

		FileOutputStream fon = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			// 追加
			fon = new FileOutputStream(inFile, true);
			osw = new OutputStreamWriter(fon);
			bw = new BufferedWriter(osw);

			// 写操作 写到文件中
			int len = -1 ;
//			while((len = bw.write(message, 0, len)) != -1){
//				
//			}
			bw.write(message);
			bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fon != null){
					fon.close();
				}
				if(osw != null){
					osw.close();		
				}
				if(bw != null){
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void read(MessageHistoryJFrame messageHistoryJFrame) {
		String onceMessage = null;
		String message = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(inFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			// 读操作 从文件中读取信息并写到历史消息文本域中

			while ((onceMessage = br.readLine()) != null) {
				message += onceMessage + "\n";
			}
			messageHistoryJFrame.getMessageHistoryPane().setText(message);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
