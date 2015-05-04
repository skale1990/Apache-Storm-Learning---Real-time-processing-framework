package storm.blueprint.Chapter3.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class DispatchAlert extends BaseFunction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory
			.getLogger(DispatchAlert.class);
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		String alert = (String) tuple.getValue(0);
		LOG.error("ALERT RECEIVED [" + alert + "]");
		LOG.error("Dispatch the national guard!");
		System.exit(0);
	}
}