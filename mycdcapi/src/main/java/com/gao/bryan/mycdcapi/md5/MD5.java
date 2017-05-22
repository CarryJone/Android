package com.gao.bryan.mycdcapi.md5;

import java.security.MessageDigest;

public final class MD5 {
	public static String toMD5(String source_string) {
		String md5String = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source_string.getBytes());
			byte[] digest = md.digest();
			md5String = toHex(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5String;
	}

	public static String toHex(byte[] digest) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < digest.length; ++i) {
			byte b = digest[i];
			int value = (b & 0x7F) + (b < 0 ? 128 : 0);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));
		}
		return buffer.toString();
	}
}
