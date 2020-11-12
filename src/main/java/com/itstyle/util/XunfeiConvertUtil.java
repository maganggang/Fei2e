package com.itstyle.util;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
/**
 * 璁杞崲PCM
 * 鍒涘缓鑰� 绉戝府缃� https://blog.52itstyle.com
 * 鍒涘缓鏃堕棿	2017骞�3鏈�6鏃�
 */
public class XunfeiConvertUtil {
	public static Boolean flag = true;
	//合成监听器
	SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
		//progress为合成进度0~100 
		public void onBufferProgress(int progress) {}
	    //会话合成完成回调接口
		//uri为合成保存地址，error为错误信息，为null时表示合成会话成功
		public void onSynthesizeCompleted(String uri, SpeechError error) {
			if(error==null){
				System.out.println("chuxian:"+uri);
			}else{
				System.out.println(error);
			}
			flag = true;
		}
		public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
			// TODO Auto-generated method stub
			
		}
	};
    public void convert(String message,String path){
    	try {
    		//System.out.println(message);
    		//鏇存崲涓鸿嚜宸� 鐢宠鐨凙PPID
			SpeechUtility.createUtility(SpeechConstant.APPID + "=5fab8c9f");
			//Setting.setShowLog(true);

			// 1.鍒涘缓SpeechSynthesizer瀵硅薄
			SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
			// 2.鍚堟垚鍙傛暟璁剧疆锛岃瑙併�奙SC Reference Manual銆婼peechSynthesizer 绫�
			mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 璁剧疆鍙戦煶浜�
			mTts.setParameter(SpeechConstant.SPEED, "50");// 璁剧疆璇��
			mTts.setParameter(SpeechConstant.VOLUME, "80");// 璁剧疆闊抽噺锛岃寖鍥�0~100
			// 3.寮�濮嬪悎鎴�
			mTts.setParameter("LIB_NAME_64", "lib_name_64");
			mTts.synthesizeToUri(message,path,synthesizeToUriListener);
		} catch (Exception e) {
			System.out.println("出错");
			e.printStackTrace();
		}
    }
}
