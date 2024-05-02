import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        ads ads = new ads();
        ads.a.add("aaa");
        ads.a.add("bbb");
        ads.a.add("aabbba");
        ads.a.add("ccaaa");
        ads.a.add("aacca");
        System.out.println(ads.a);
    }
}

