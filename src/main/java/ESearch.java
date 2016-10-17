import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.Map;

/**
 * Created by YKDZ075 on 2016/10/17.
 */
public class ESearch {
    public static void main(String[] args)throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", "ink-es").build();
        TransportClient client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.1.5.139"), 9300));
        GetResponse response= client.prepareGet("twitter","tweet","1").get();
        Map map= response.getSource();
        System.out.println(map.toString());

    }
}
