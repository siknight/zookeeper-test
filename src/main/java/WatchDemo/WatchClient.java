package WatchDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WatchClient {

    private static String server="hadoop102:2181";
    private static  String nodePath="/servers";
    private static  ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, InterruptedException {
        getConnection();
        System.out.println("long  hehehhehe");
        Thread.sleep(Long.MAX_VALUE);
        System.out.println("long hehehehehhe2");
    }

    private static void listChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(nodePath, true);
        System.out.println("children="+new String(String.valueOf(children)));
//        new ArrayList<String>();
//        for (String child : children){
//
//        }
    }


    private static void getConnection() throws IOException {
           zooKeeper=new ZooKeeper(server, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType()+"---"+watchedEvent.getPath());
                try {
                    listChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
