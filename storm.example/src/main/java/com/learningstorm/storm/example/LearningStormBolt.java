package com.learningstorm.storm.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class LearningStormBolt extends BaseBasicBolt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger LOG=LoggerFactory.getLogger(getClass());

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		// TODO Auto-generated method stub
		LOG.info(""+input.getSourceStreamId()+"======="+input.getValueByField("sites"));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
