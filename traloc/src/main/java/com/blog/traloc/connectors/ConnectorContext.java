package com.blog.traloc.connectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class ConnectorContext {
    public static JavaSparkContext getContext() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Traloc");
        sparkConf.setMaster("local[2]");
        sparkConf.set("spark.cassandra.connection.host", "127.0.0.1");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        return javaSparkContext;
    }
}
