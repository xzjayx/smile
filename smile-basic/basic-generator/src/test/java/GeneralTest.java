
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralTest {

    @Test
    public void test(){
        String s = "UserService";
        String s1 = String.valueOf(s.toCharArray()[0] += 32).concat(s.substring(1));
        System.out.println(s1);

//        // 同理
//        char[] cs=s.toCharArray();
//        cs[0]+=32;
//        String s1 = String.valueOf(cs);
//        System.out.println(s1);
    }

    @Test
    public void test1(){
        String str = "/smile-admin";
        List<String> arr1 = new ArrayList<>();
        //arr1.add("/smile-admin/actuator/**");
        arr1.add("/actuator/**");
        arr1.add("/auth/oauth/token");
        //arr1.add("/smile-admin/test/index");

        List<String> arr2 = new ArrayList<>();
        arr2.add("/actuator/**");
        arr2.add("/test/hello");

        //集合求并集
        arr1 = arr1.stream().filter(x-> x.contains(str))
                .map(v->v.substring(str.length())).collect(Collectors.toList());
        arr1.forEach(System.out::println);
        /*arr1.addAll(arr2); 并集 https://wenku.baidu.com/view/1d99a4ff7c192279168884868762caaedd33baf8.html
        List<String> result = arr1.stream().distinct().collect(Collectors.toList());
        result.forEach(System.out::println);*/

        //交集
        //arr1.stream().filter(arr2::contains).forEach(System.out::println);


    }

    @Test
    public void test2(){
        //匹配对了case 才走进去如果进去了没有break那么会一直向下允许（后面的case就不适用了，直接进入执行找到break）
      int month = 7;
        switch (month) {
        case 1:
        case 2:
        case 12:
            System.out.println("冬季");
            break;
        case 3:
        case 4:
        case 5:
            System.out.println("春季");
            break;
        case 6:
        case 7:
        case 8:
            System.out.println("夏季");
            break;
        case 9:
        case 10:
        case 11:
            System.out.println("秋季");
            break;

        default:
            System.out.println("亲，输入有误哦");

        }

    }





    @Test
    public void test3(){
        String str = "/com/smile/basic/core/qo/sys";
        System.out.println(str.substring(1).replace("/", "."));
    }
}
