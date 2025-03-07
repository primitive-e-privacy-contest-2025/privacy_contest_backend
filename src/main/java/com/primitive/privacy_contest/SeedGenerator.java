package com.primitive.privacy_contest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SeedGenerator {
    public static String generate32CharSeed(String part1, String part2) {
        try {
            // MD5 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 두 개의 문자열을 순차적으로 업데이트
            md.update(part1.getBytes(StandardCharsets.UTF_8));
            md.update(part2.getBytes(StandardCharsets.UTF_8));

            // MD5 해시 계산
            byte[] digest = md.digest();

            // 16바이트(128비트) 해시를 16진수로 변환 -> 32자리 문자열
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 바이트 배열 -> 16진수 문자열 변환 헬퍼 메서드
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));  // 소문자 hex
            // sb.append(String.format("%02X", b)); // 대문자 hex로 쓰고 싶다면 이 방식
        }
        return sb.toString();
    }
}

