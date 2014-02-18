package com.sky.sns.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConversion {

	static Pattern referer_pattern = Pattern
			.compile("@([a-zA-Z_0-9\u4e00-\u9fa5]+)");

	/**
	 * 处理提到某人 @xxxx
	 * 
	 * @param msg
	 *            传入的文本内容
	 * @param referers
	 *            传出被引用到的会员名单
	 * @return 返回带有链接的文本内容
	 */
	public static String generateRefererLinks(String msg, List<String> referers) {
		StringBuilder html = new StringBuilder();
		int lastIdx = 0;
		Matcher matchr = referer_pattern.matcher(msg);
		while (matchr.find()) {
			String origion_str = matchr.group();

			String str = origion_str.substring(1, origion_str.length()).trim();

			html.append(msg.substring(lastIdx, matchr.start()));

			html.append("<a href='/n/" + str.trim()
					+ "/' class='referer' target='_blank'>@");
			html.append(str.trim());
			html.append("</a> ");
			if (referers != null)
				referers.add(str);
			lastIdx = matchr.end();
		}
		html.append(msg.substring(lastIdx));
		return html.toString();
	}

	public static String removeHtmlTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String regEx_html = "<[^>]+>";

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		return htmlStr.trim();

	}

	public static String changeToHtml(String text) {
		text = text.replace("&", "&amp;");
		text = text.replace(" ", "&nbsp;");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		text = text.replace("\"", "&quot;");
		text = text.replace("	", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return text;
	}

}
