package demo01;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZKDemo {

//    private static String connectString ="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static String connectString ="hadoop102:2181";
    private static  int sessionTimeout = 2000;
    private ZooKeeper zkClient=null;

    @Before
    public void init() throws IOException {
        zkClient=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //收到事件通知后的回调函数（用户的业务逻辑）
                System.out.println(watchedEvent.getType()+"--"+watchedEvent.getPath());
//                try {
//                    List<String> children = zkClient.getChildren("/", true);
//                    for (String child :children){
//                        System.out.println("child="+child);
//                    }
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    //创建节点
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        String path = zkClient.create("/lisi5", "lisi5".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("path="+path);
    }

    //查看节点是否存在
    @Test
    public void testExists(){
        try {
            Stat exists = zkClient.exists("/lisi5", true);
            System.out.println("exists="+exists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //查看子节点
    @Test
    public void testList() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child :children){
            System.out.println("child="+child);
        }
    }

    //获取节点数据
    @Test
    public void testGet() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/lisi3", true, null);
        System.out.println(new String(data));
        System.out.println(data.toString());
    }

    //改变节点的内容
    @Test
    public void update() throws KeeperException, InterruptedException {
        zkClient.setData("/lisi3","lisi3333".getBytes(),-1);
    }



}
