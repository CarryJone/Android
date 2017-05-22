package com.gao.bryan.mycdcapi.md5;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Base64;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Hermes.Hsieh on 2015/8/13.
 */
public class KeyUtil
{

    /**
     * 獲取裝置的唯一ID, UUID加密處理
     */
    public static String getDeviceID(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice = String.valueOf(tm.getDeviceId());
        String tmSerial = String.valueOf(tm.getSimSerialNumber());
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    /**
     * 獲取MD5加密字串
     *
     * @return
     */
    public static String getMD5(String string)
    {
        return MD5.toMD5(string);
    }

    /**
     * DES加密
     *
     * @param encryptString 聊天訊息內容
     * @param encryptKey    加密鑰匙 = HznsDOTS
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey) throws Exception
    {
        IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        String retureValue = new String(Base64.encode(encryptedData, Base64.DEFAULT), "UTF-8");
        return retureValue.replace("\n", "");
    }

    /**
     * DES解密
     *
     * @param decryptString 聊天訊息內容
     * @param decryptKey    加密鑰匙 = HznsDOTS
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey) throws Exception
    {
        byte[] byteMi = Base64.decode(decryptString, Base64.DEFAULT);
        IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData).replace("\n", "");
    }
}
