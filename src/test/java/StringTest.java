import org.apache.commons.lang.StringEscapeUtils;

public class StringTest {
	public static void main(String[] args) {
		String s = "<HTML>";
		String escapeHtml = StringEscapeUtils.escapeHtml(s);
		System.out.println(escapeHtml);
	}
}
