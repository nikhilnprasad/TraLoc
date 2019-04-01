package com.blog.traloc.analysis;

import com.blog.traloc.entity.RawData;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

import java.util.*;

/**
 * Sentiment analysis process for the blog posts
 */
public class SentimentAnalyzer {
    /**
     * Calculate the sentiment of a sentence
     *
     * @param sentence sentence
     * @return sentiment
     */
    public static int getSentiment(String sentence) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,parse,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        int sentiment = 0;
        if (sentence != null && sentence.length() > 0) {
            Annotation annotation = pipeline.process(sentence);
            for (CoreMap s : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = s.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sen = RNNCoreAnnotations.getPredictedClass(tree);
                sentiment += sen;
            }
        }
        return sentiment;
    }

    /**
     * Map Reduce to compute the sentiment of a blog post
     *
     * @param rdd RDD of raw data
     * @return list of top sentences along with sentiments
     */
    public static List<String> analyze(JavaRDD<RawData> rdd) {
        JavaPairRDD<String, Integer> sentiments = rdd
                .flatMap(s -> Arrays.asList(s.getContent().split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s")).iterator())
                .mapToPair(sentence -> new Tuple2<>(sentence, getSentiment(sentence)))
                .reduceByKey((a, b) -> a + b);
        if (sentiments.collectAsMap().size() <= 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> sentMap = sortByValue(sentiments.collectAsMap());
        List<String> list = new ArrayList<>();
        if (sentMap.size() < 5) {
            list.addAll(new ArrayList<String>(sentMap.keySet()).subList(0, sentMap.size()));
            return list;
        }
        list.addAll(new ArrayList<String>(sentMap.keySet()).subList(0, 5));
        return list;
    }


    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
