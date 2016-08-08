package albert.practice.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

/**
 * username = 使用者帳號。SmGateway資料庫表格SMUser中需有此使用者，且狀態為啟用。 <br>
 * password = 使用者密碼 <br>
 * dstaddr = 受訊方手機號碼 <br>
 * DestName = 收訊人名稱。若其他系統需要與簡訊資料進行系統整合，此欄位可填入來源系統所產生的Key值，以對應回來源資料庫 <br>
 * dlvtime = 簡訊預約時間。格式為YYYY-MM-DD HH:NN:SS或YYYYMMDDHHNNSS，或是整數值代表幾秒後傳送。<br>
 * vldtime = 簡訊有效期限。格式為YYYY-MM-DD HH:NN:SS或YYYYMMDDHHNNSS，或是整數值代表傳送後幾秒後內有效。 <br>
 * smbody = 簡訊內容。必須為BIG-5編碼，長度70個中文字或是160個英數字。若有換行的需求，請填入ASCII Code 6代表換行。 <br>
 */
@Slf4j
public class SmsUtils {

	private final static String USER_AGENT = "Mozilla/5.0";

	private final String SMS_URL = "http://xxx/SmSendGet.asp?";
	private final String USER_NAME = "Test001";
	private final String PASSWORD = "TestPwd";

	public static void main(String[] args) throws Exception {
		String phone = "09";
		String smBody = "您的網路申請驗證碼為○○○○○○，僅限會員申請使用，請於收到簡訊10分鐘內完成驗證。";
		new SmsUtils().sendSms(phone, smBody);
	}

	public void sendSms(String phoneNumber, String body) throws Exception {
		body = URLEncoder.encode(body, "Big5");
		sendGet(phoneNumber, body);
	}

	private void sendGet(String phoneNumber, String body) throws IOException {

		StringBuilder url = new StringBuilder();
		url.append(SMS_URL);
		url.append("username=").append(USER_NAME);
		url.append("&password=").append(PASSWORD);
		url.append("&dstaddr=").append(phoneNumber);
		url.append("&smbody=").append(body);

		URL obj = new URL(url.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();

		log.debug("\nSending 'GET' request to URL : " + url);
		log.debug("Response Code : " + responseCode);

		@Cleanup
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine = "";
		StringBuffer response = new StringBuffer();

		while ((inputLine = bufferedReader.readLine()) != null) {
			response.append(inputLine);
		}

		// print result
		String responseStr = response.toString();
		log.debug(responseStr);

		int from = responseStr.indexOf("statuscode=");
		int to = responseStr.indexOf("AccountPoint=");
		String tmpStr = StringUtils.substring(responseStr, from, to);

		// 取得status code
		String statusCode = StringUtils.substring(tmpStr, tmpStr.length() - 1,
				tmpStr.length());
		log.info("statusCode = " + statusCode);

		// 將status code 轉成 中文訊息
		String executionResult = getDescription(statusCode);
		log.info("executionResult = " + executionResult);

		List<String> successList = Lists.newArrayList("0", "1", "2", "3", "4"); // 成功的SATUSCODE
		if (!successList.contains(statusCode)) {
			throw new RuntimeException(executionResult);
		}
	}

	/**
     * 將status code 轉成 中文訊息
     * 
     * @param statusCode
     * @return 中文訊息
     */
	private String getDescription(String statusCode) {
		String description = "";
		for (SmsStatusCodeEnum smsStatusCode : SmsStatusCodeEnum.values()) {
			if (smsStatusCode.getCode().equals(statusCode)) {
				description = smsStatusCode.getDescription();
			}
		}
		return description;
	}
}
