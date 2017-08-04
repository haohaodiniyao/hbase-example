package com.example.hbase_example.hbase_example;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
	    Configuration configuration = HBaseConfiguration.create();  
	    configuration.set("hbase.zookeeper.quorum","192.168.157.131,192.168.157.129,192.168.157.130");
	    configuration.set("hbase.zookeeper.property.clientPort","2181");
        configuration.set("hbase.master", "192.168.157.131:16000"); 
        try {  
        	Connection connection = ConnectionFactory.createConnection(configuration);
            Table table = connection.getTable(TableName.valueOf("test".getBytes()));

            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            for(Result res: scanner){
                System.out.println(Bytes.toString(res.getValue("cf".getBytes(),"name".getBytes())));
                System.out.println(Bytes.toString(res.getValue("cf".getBytes(),"age".getBytes())));
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
}
