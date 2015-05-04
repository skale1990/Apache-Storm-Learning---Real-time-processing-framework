package com.learningstorm.storm.example;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class LearningStormTopology {

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		// TODO Auto-generated method stub
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("My-spout", new LearningStormSpout(), 2).setNumTasks(4);
		builder.setBolt("My-Bolt", new LearningStormBolt() ,4).shuffleGrouping("My-spout");
		
		Config conf= new Config();
		conf.setNumWorkers(2);
		
		StormSubmitter.submitTopology("MyFirstTopology", conf, builder.createTopology());
	}

}
