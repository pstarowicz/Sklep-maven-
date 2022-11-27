package pl.camp.it.sklep;

import org.apache.commons.codec.digest.DigestUtils;

public class test {
    public static void main(String[] args) {
        System.out.println("seed: cdscdssdv434632*(&*)");
        System.out.println("admin+seed");
        System.out.println(DigestUtils.md5Hex("admin"+"cdscdssdv434632*(&*)"));
        System.out.println("user+seed");
        System.out.println(DigestUtils.md5Hex("user"+"cdscdssdv434632*(&*)"));
    }
}
