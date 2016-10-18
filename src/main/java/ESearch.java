import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

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
       // GetResponse response= client.prepareGet("twitter","tweet","1").get();
        SearchResponse response= client.prepareSearch("twitter").setTypes("tweet")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("user", "kimchy"))                 // Query
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                //.setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        Map map= response.getHits().getAt(0).getSource();
        System.out.println(map.toString());

    }
}
