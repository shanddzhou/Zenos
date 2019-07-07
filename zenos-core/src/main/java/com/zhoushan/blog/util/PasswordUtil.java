package com.zhoushan.blog.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.zhoushan.blog.business.consts.CommonConst;

/**
 * @author zhooo
 * @version V1.0
 * @Title: PasswordUtil
 * @Package com.zhoushan.blog.util
 * @Description: 密码加密解密工具类
 * @date 2019/7/7 13:08
 */
public class PasswordUtil {
    /**
     * Ase密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String password, String salt) {
//        将安全码进行MD5加密
        byte[] key = DigestUtil.md5(salt + CommonConst.SECURITY_KEY);
//        使用加密Key创建安全密钥
        byte[] secretKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),key).getEncoded();
        AES aes = SecureUtil.aes(secretKey);
        String encryptString = aes.encryptHex(password);
        return encryptString;
    }

    /**
     * Ase解密
     * @param encryptString
     * @param salt
     * @return
     */
    public static String decrypt(String encryptString,String salt) {
        byte[] md5 = DigestUtil.md5(salt + CommonConst.SECURITY_KEY);
        byte[] secretKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), md5).getEncoded();
        AES aes = SecureUtil.aes(secretKey);
        String password = aes.decryptStr(encryptString);
        return password;
    }

}
