package WatchDemo;

import org.apache.zookeeper.*;

import java.io.IOException;

public class WatchServer {
    private static String server="hadoop102:2181";
    private static  String nodePath="/servers";
    private static  ZooKeeper zooKeeper=null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        getconn();
        createNode();
    }

    private static  void createNode() throws KeeperException, InterruptedException {
        zooKeeper.create("/servers/server_lisi01","server_lisi01".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }
    private static void getconn() throws IOException {
        zooKeeper=new ZooKeeper(server, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("server="+watchedEvent.getType()+"--"+watchedEvent.getPath());
            }
        });
    }


}
