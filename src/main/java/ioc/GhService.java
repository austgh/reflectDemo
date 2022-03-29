package ioc;

/**
 * @author think
 * @version 1.0
 * @date 2022/3/29 16:44
 */
public class GhService {
    public GhService(){
        System.out.println("初始化构造方法");
    }

    @Override
    public String toString() {
        return "GhService{}"+this.hashCode();
    }
}
