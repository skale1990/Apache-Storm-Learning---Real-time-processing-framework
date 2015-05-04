package com.learningstorm.storm.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class LearningStormSpout extends BaseRichSpout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpoutOutputCollector spoutOutputCollector=null;
	
	private HashMap<Integer, String> inputMap= new HashMap<Integer, String>();
	{
		inputMap.put(0, "google");
		inputMap.put(1, "facebook");
		inputMap.put(2, "flipkart");
		inputMap.put(3, "nokia");
		inputMap.put(4, "amazon");
		inputMap.put(5, "intuit");
		
	}
	
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		
		//we must the save the instance of collector
		this.spoutOutputCollector = collector;
		LOG.info("Topology conf: "+ conf);
		LOG.info("PIDDir:   "+context.getPIDDir());
		LOG.info("component Ids:   "+context.getComponentIds());
		
		
	}

	public void nextTuple() {
		// TODO Auto-generated method stub
		Random random= new Random();
		spoutOutputCollector.emit(UUID.randomUUID()+"", new Values(inputMap.get(random.nextInt(6))));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("sites"));
		
	}

}
