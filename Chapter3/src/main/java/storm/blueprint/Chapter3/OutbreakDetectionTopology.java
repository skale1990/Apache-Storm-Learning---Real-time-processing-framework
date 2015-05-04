package storm.blueprint.Chapter3;

import storm.blueprint.Chapter3.aggregators.Count;
import storm.blueprint.Chapter3.operations.CityAssignment;
import storm.blueprint.Chapter3.operations.DiseaseFilter;
import storm.blueprint.Chapter3.operations.DispatchAlert;
import storm.blueprint.Chapter3.operations.HourAssignment;
import storm.blueprint.Chapter3.operations.OutbreakDetector;
import storm.blueprint.Chapter3.trident.state.OutbreakTrendFactory;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

public class OutbreakDetectionTopology {
	public static StormTopology buildTopology() {
		TridentTopology topology = new TridentTopology();
		DiagnosisEventSpout spout = new DiagnosisEventSpout();
		Stream inputStream = topology.newStream("event", spout);
		inputStream
				// Filter for critical events.
				.each(new Fields("event"), new DiseaseFilter())
				// Locate the closest city
				.each(new Fields("event"), new CityAssignment(),
						new Fields("city"))
				// Derive the hour segment
				.each(new Fields("event", "city"), new HourAssignment(),
						new Fields("hour", "cityDiseaseHour"))
				// Group occurrences in same city and hour
				.groupBy(new Fields("cityDiseaseHour"))
				// Count occurrences and persist the results.
				.persistentAggregate(new OutbreakTrendFactory(), new Count(),
						new Fields("count"))
				.newValuesStream()
				// Detect an outbreak
				.each(new Fields("cityDiseaseHour", "count"),
						new OutbreakDetector(), new Fields("alert"))
				// Dispatch the alert
				.each(new Fields("alert"), new DispatchAlert(), new Fields());

		return topology.build();

	}

	public static void main(String[] args) throws Exception {
		Config conf = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Sensor-Data-Trident-Topology", conf, buildTopology());
		Utils.sleep(200000);
		cluster.shutdown();
	}
}