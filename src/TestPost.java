import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestPost {

	public static void main(String[] args) {
		
		new ReadByPost().start();

	}

}
class ReadByPost extends Thread{
	@Override
	public void run() {
		try {
			
			URL url = new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("encoding", "UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			
			
			
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw  = new BufferedWriter(osw);
			
			bw.write("keyfrom=jumptiger-test&key=479647858&type=data&doctype=xml&version=1.1&q=welcome");
			bw.flush();
			
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			StringBuilder builder = new StringBuilder();
			while((line = br.readLine())!= null){
				builder.append(line);
			}
			
			bw.close();
			osw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			
			System.out.println(builder.toString());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
