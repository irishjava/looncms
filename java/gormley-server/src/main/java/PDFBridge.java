import org.hibernate.search.bridge.StringBridge;


public class PDFBridge implements StringBridge{

	public String objectToString(Object arg0) {
		return "pdf data";
	}

}
